package com.changmeen.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class communitySearch extends AppCompatActivity {
    private ImageView ivBack;
    private final Context mContext = communitySearch.this;
    private TextView tvToolbarTitle, etSearch;
    private Button bt_search;
    private LinearLayoutManager manager;
    private SharedPreferences pref;
    private String token;
    private RecyclerView recyclerView;
    private communityAdapter communityAdapter;
    private ArrayList<Community_Data> community;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_search);

        ivBack = findViewById(R.id.iv_back);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        token = pref.getString("token", "");
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Search");
        etSearch = findViewById(R.id.et_search);
        bt_search = findViewById(R.id.btn_search);
        ivBack.setOnClickListener(v -> {
            finish();
        });

        recyclerView = findViewById(R.id.rv_search_list);
        manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        community = new ArrayList<>();

        bt_search.setOnClickListener(v -> {
            SearchContents(etSearch.getText().toString());
//            Toast.makeText(this, etSearch.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    public void SearchContents(final String text) {
        mDatabase.child("communitySearch").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                community.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Community_Data data = dataSnapshot.getValue(Community_Data.class);
                    if (data.getTitle().contains(text)) {
                        community.add(0, data);
                        communityAdapter = new communityAdapter(community, mContext);
                    }
                }
                recyclerView.setAdapter(communityAdapter);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }
}