package com.changmeen.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile_activity extends AppCompatActivity {
    private ImageView ivBack,ivSetting;
    private TextView nicknameText;
    private RecyclerView recyclerview;
    private LinearLayoutManager manager;
    private rec_adapter adapter;
    private SharedPreferences pref;
    private static ArrayList<Recipe> itemArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_information2);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            finish();
        });

        // toolbar 관련 이벤트
        ivSetting = findViewById(R.id.iv_setting);

        ivSetting.setOnClickListener(v -> {
            Intent intent = new Intent(this, Profile_edit.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        recyclerview = findViewById(R.id.recyclerView);
        manager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerview.setLayoutManager(manager);

        itemArrayList = new ArrayList<>();
        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String userToken = pref.getString("token", "");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("찜").child(userToken).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Recipe rec = snapshot.getValue(Recipe.class);
                    itemArrayList.add(rec);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("fragment1", String.valueOf(databaseError.toException()));
            }
        });
        adapter = new rec_adapter(itemArrayList, getApplicationContext());
        recyclerview.setAdapter(adapter);

        // 이름적는 부분
        nicknameText = (TextView) findViewById(R.id.textView3);
        String name = pref.getString("UserName", "");
        nicknameText.setText(name);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // 이름적는 부분
        nicknameText = (TextView) findViewById(R.id.textView3);
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        String name = pref.getString("UserName", "");

        nicknameText.setText(name);
    }

}
