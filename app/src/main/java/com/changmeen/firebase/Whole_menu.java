package com.changmeen.firebase;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Whole_menu extends Fragment {

    private ViewPager vpContainer;
    private TabLayout tab_rec;
    private rec_pagerAdapter adapter;

    public Whole_menu() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.whole_menu, container, false);

        vpContainer = view.findViewById(R.id.vp_container);
        tab_rec = view.findViewById(R.id.tabs_rec);

        tab_rec.addTab(tab_rec.newTab().setText("#밥류"));
        tab_rec.addTab(tab_rec.newTab().setText("#면류"));


        adapter = new rec_pagerAdapter(getChildFragmentManager());
        vpContainer.setAdapter(adapter);
        tab_rec.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        vpContainer.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_rec));
        return view;
    }

    public class rec_pagerAdapter extends FragmentPagerAdapter {

        public rec_pagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return new rec_rice();
                }
                case 1: {
                    return new rec_noodle();
                }
                default: {
                    return null;
                }
            }
        }

        @Override
        public int getCount() {
            return 2;
        }


    }
}
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.whole_menu);
//    }
//

