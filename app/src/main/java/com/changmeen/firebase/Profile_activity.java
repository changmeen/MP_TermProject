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

public class Profile_activity extends AppCompatActivity {
    private ImageView ivBack,ivSetting;
    private TextView nicknameText;
    Profile_Adapter adapter;
    private SharedPreferences prefer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_information2);

        ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> {
            finish();
        });

        ivSetting = findViewById(R.id.iv_setting);

        ivSetting.setOnClickListener(v -> {
            Intent intent = new Intent(this, Profile_edit.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        nicknameText = findViewById(R.id.textView3);

        prefer = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String nickname = prefer.getString("Username", "");
        System.out.println("###############################"+nickname);

        nicknameText.setText(nickname);


        init();
        getData();

    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new Profile_Adapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        Profile_item data = new Profile_item(R.drawable.baek1, "짬뽕");
        adapter.addItem(data);
    }
}
