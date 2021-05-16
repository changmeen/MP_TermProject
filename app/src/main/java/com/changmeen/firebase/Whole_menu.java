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
    private RecyclerView listview;
    private Context mContext;
    private static ArrayList<rec_list> itemArrayList;
    private rec_adapter adapter;
    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewGroup = (ViewGroup) inflater.inflate(R.layout.whole_menu, container, false);

        Rice(viewGroup);
        noodle(viewGroup);

        return viewGroup;
    }

    private void Rice(View view) {;
        listview = viewGroup.findViewById(R.id.rcp_RecyclerView_eating_alone);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        listview.setLayoutManager(layoutManager);

        itemArrayList = new ArrayList<>();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Food").child("Rice").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    rec_list courseList = snapshot.getValue(rec_list.class);
                    itemArrayList.add(courseList);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("fragment1", String.valueOf(databaseError.toException()));
            }
        });
        adapter = new rec_adapter(itemArrayList, getContext());
        listview.setAdapter(adapter);

    }

    private void noodle(View view){;
        listview = viewGroup.findViewById(R.id.rcp_RecyclerView_noodle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        listview.setLayoutManager(layoutManager);

        itemArrayList = new ArrayList<>();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Food").child("Noodle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    rec_list courseList = snapshot.getValue(rec_list.class);
                    itemArrayList.add(courseList);
                }
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("fragment1", String.valueOf(databaseError.toException()));
            }
        });
        adapter = new rec_adapter(itemArrayList, getContext());
        listview.setAdapter(adapter);
    }
}


//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.whole_menu);
//    }
//

