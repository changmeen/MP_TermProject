package com.changmeen.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Ingredient_page extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private Ingredient_adapter adapter;
    private static ArrayList<Ingredients_list> itemArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredient_page, container, false);

        recyclerView = view.findViewById(R.id.ingredient_RecyclerView);
        layoutManager = new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(layoutManager);

        itemArrayList = new ArrayList<>();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Ingredient").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Ingredients_list rec = snapshot.getValue(Ingredients_list.class);
                    itemArrayList.add(rec);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("fragment1", String.valueOf(databaseError.toException()));
            }
        });
        adapter = new Ingredient_adapter(itemArrayList, getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }
}
