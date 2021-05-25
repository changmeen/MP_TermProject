package com.changmeen.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class communityActivity extends AppCompatActivity {

    public static List<String> category;
    ImageView ivBack, ivWrite, ivSearch;
    TextView tvToolbarTitle;
    ViewPager vpContainer;
    TabLayout tab_community;
    String token;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.community_activity);

        ivBack = findViewById(R.id.iv_back);

        category = new ArrayList<>(Arrays.asList(new String[]{"# 밥류", "# 면류"}));
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

        vpContainer = findViewById(R.id.vp_container);
        tab_community = findViewById(R.id.tabs_community);

        tab_community.addTab(tab_community.newTab().setText("# 밥류"));
        tab_community.addTab(tab_community.newTab().setText("# 면류"));

        communityPager pager = new communityPager(getSupportFragmentManager(), 2);
        vpContainer.setAdapter(pager);

        tab_community.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vpContainer.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vpContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_community));
    }

}