package com.changmeen.firebase;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.changmeen.firebase.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.changmeen.firebase.R;
import com.google.firebase.database.FirebaseDatabase;


import java.util.List;

public class communityWrite extends AppCompatActivity {
    private ImageView ivBack;
    TextView tvToolbarTitle;
    private AppCompatButton btnSaveCommunity;
    private TextInputEditText etTitle;
    private TextInputEditText etContents;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.community_write);
        ivBack = findViewById(R.id.iv_back);
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Write");
        ivBack.setOnClickListener(v -> {
            finish();
        });

        btnSaveCommunity = findViewById(R.id.btn_save_community);
        etTitle = findViewById(R.id.et_title);
        etContents = findViewById(R.id.et_content);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        btnSaveCommunity.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            String token = pref.getString("token", "");
            if(etTitle.getText().toString().equals("")|etContents.getText().toString().equals(""))
                Toast.makeText(this,"Check your Input",Toast.LENGTH_SHORT).show();
            else {
                    Question question = new Question();
                    question.setTitle(etTitle.getText().toString());
                    question.setContent(etContents.getText().toString());
                    question.setToken(token);
                    question.setUsername(pref.getString("UserName",""));
                    question.setDBKey(mDatabase.child("Post").push().getKey());
                    mDatabase.child("Post").child(question.getDBKey()).setValue(question);
                    mDatabase.child("communitySearch").child(question.getDBKey()).setValue(question);
                    finish();
                }
            });
        }
    }