package com.changmeen.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class communityActivity extends AppCompatActivity {

    ImageView ivBack, ivWrite, ivSearch;
    RecyclerView community_list;
    String token;
    SharedPreferences pref;
    private RecyclerView.LayoutManager layoutManager;
    private communityAdapter communityAdapter;
    private ArrayList<Community_Data> communities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activity);

        ivBack = findViewById(R.id.iv_back);

        pref = getSharedPreferences("pref", MODE_PRIVATE);
        token = pref.getString("token", "");

        ivBack.setOnClickListener(v -> {
            finish();
        });
        ivWrite = findViewById(R.id.iv_write);

        ivWrite.setOnClickListener(v -> {
//            if(token.equals(""))
//            {
//                intent = new Intent(this, LoginActivity.class);
//            }
            Intent intent = new Intent(this, communityWrite.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        ivSearch = findViewById(R.id.iv_search);

        ivSearch.setOnClickListener(v -> {
            Intent intent = new Intent(this, communitySearch.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        community_list = findViewById(R.id.Community_list);
        layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false);
        community_list.setLayoutManager(layoutManager);

        communities = new ArrayList<>();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Post").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                communities.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    Community_Data data = dataSnapshot.getValue(Community_Data.class);
                    communities.add(data);
                }
                communityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Log.d("실패", "onFailure: 실패");
            }
        });
        communityAdapter = new communityAdapter(communities,getApplicationContext());
        community_list.setAdapter(communityAdapter);
    }
}