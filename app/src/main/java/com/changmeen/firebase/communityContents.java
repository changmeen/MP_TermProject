package com.changmeen.firebase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.lang.annotation.AnnotationTypeMismatchException;
import java.util.ArrayList;
import java.util.List;


public class communityContents extends AppCompatActivity {
    private static final String TAG = "CommunityDetailActivity";
    private Context mContext = communityContents.this;
    private ImageView ivBack, ivSendReply, ivDelete_question;
    private TextView tvToolbarTitle;
    private RecyclerView recyclerview;
    private EditText etReply;
    private RelativeLayout replyBar;
    private EditText et_reply;
    private communityContentAdapter adapter;
    private ArrayList<contents> items;
    private ArrayList<Reply> replies;
    private String token;
    private DatabaseReference mDatabase;
    private LinearLayoutManager manager;
    private Community_Data community;
    private DatabaseReference mReply;
    private DatabaseReference getmReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_content);
        Intent intent = getIntent();
        community = (Community_Data) intent.getSerializableExtra("community");

        token = getSharedPreferences("pref", MODE_PRIVATE).getString("token", "");

        replyBar = (RelativeLayout) findViewById(R.id.reply_bar);
        replyBar.setVisibility(View.INVISIBLE);
        ivSendReply = findViewById(R.id.iv_reply_send);
        et_reply = findViewById(R.id.et_reply);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            finish();
        });

        delete_question();

        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Community");

        recyclerview = findViewById(R.id.rv_community_detail);
        manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerview.setLayoutManager(manager);

        mReply = FirebaseDatabase.getInstance().getReference();

        items = new ArrayList<>();
        replies = new ArrayList<>();

        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);

        ivSendReply.setOnClickListener(v -> {
            if(token.equals(""))
                Toast.makeText(mContext,"Login needs",Toast.LENGTH_SHORT).show();
            else {
                Reply reply = new Reply();
                reply.setContent(et_reply.getText().toString());
                reply.setUsername(pref.getString("UserName",""));
                reply.setToken(token);
                reply.setReply_key(mReply.child("Reply").child(community.getDBKEy()).push().getKey());
                items.add(new contents(1, reply));
                mReply.child("Reply").child(community.getDBKEy()).child(reply.getReply_key()).setValue(reply);
                shutdownReplyInput();
            }
        });
        addCommunityItem();
    }

    public void showReplyInput() {
        RelativeLayout replyBar = (RelativeLayout) findViewById(R.id.reply_bar);
        replyBar.setVisibility(View.VISIBLE);

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        etReply = (EditText) findViewById(R.id.et_reply);
        etReply.requestFocus();
        imm.showSoftInput(etReply, InputMethodManager.SHOW_IMPLICIT);
    }

    public void shutdownReplyInput() {
        RelativeLayout replyBar = (RelativeLayout) findViewById(R.id.reply_bar);
        replyBar.setVisibility(View.INVISIBLE);

        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        etReply = (EditText) findViewById(R.id.et_reply);
        etReply.setText("");
        etReply.requestFocus();
        View view = this.getCurrentFocus();
        imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    private void addCommunityItem() {
        getmReply = FirebaseDatabase.getInstance().getReference();
        getmReply.child("Reply").child(community.getDBKEy()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                items.clear();
                replies.clear();
                items.add(new contents(0, community));
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Reply mReply = dataSnapshot.getValue(Reply.class);
                    replies.add(mReply);
                    items.add(new contents(1, mReply));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.d(TAG, "onFailure: 실패");
            }
        });
        adapter = new communityContentAdapter(items, (communityContents)mContext, token);
        recyclerview.setAdapter(adapter);
    }

    private void delete_question() {
        ivDelete_question = findViewById(R.id.iv_delete_question);
        if (token.equals(community.getToken())) {
            ivDelete_question.setVisibility(View.VISIBLE);
        } else {
            ivDelete_question.setVisibility(View.INVISIBLE);
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ivDelete_question.setOnClickListener(v ->
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("게시글을 지우시겠습니까?");
            alert.setPositiveButton("accept", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mDatabase.child("Post").child(community.getDBKEy()).removeValue();
                    mDatabase.child("Reply").child(community.getDBKEy()).removeValue();
                    mDatabase.child("communitySearch").child(community.getDBKEy()).removeValue();
                    finish();
                }
            });
            alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            alert.show();
        });
    }
}