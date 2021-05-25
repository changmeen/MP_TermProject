package com.changmeen.community;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class communitySearch extends AppCompatActivity {
    private ImageView ivBack;
    private final Context mContext = communitySearch.this;
    private TextView tvToolbarTitle, etSearch;
    private Button bt_search;
    private LinearLayoutManager manager;
    private SharedPreferences pref;
    private String token;
    private RecyclerView rvSearchList;
    // private CommunityAdapter communityAdapter;
    //  private List<Community_Data> community;
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
    }
}