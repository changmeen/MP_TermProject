package com.changmeen.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile_activity extends AppCompatActivity {
    private ImageView ivBack,ivSetting;
    private TextView nicknameText;
    private Profile_Adapter adapter;
    private SharedPreferences pref;
    private DatabaseReference mDatabase;
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
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        // 이름적는 부분
        nicknameText = (TextView) findViewById(R.id.textView3);
        pref = getSharedPreferences("pref", MODE_PRIVATE);
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
