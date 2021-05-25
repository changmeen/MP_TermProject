package com.changmeen.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class Ingredient_add extends AppCompatActivity {

    public ArrayList<Ingredients_list> searchList;
    private static ArrayList<Ingredients_list> itemArrayList;


    private Ingredient_adapter ingAdapter;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private GridLayoutManager layoutManager;
    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredient_add);

        searchList = new ArrayList<>();
        itemArrayList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView = findViewById(R.id.ingredient_search_list);
        searchView= (SearchView) findViewById(R.id.ingredient_search);
        layoutManager = new GridLayoutManager(this, 5);

        recyclerView.setLayoutManager(layoutManager);

        viewIngredient();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SearchContents(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                viewIngredient();
                return false;
            }
        });

    }

    public void SearchContents(final String text) {
        mDatabase.child("Ingredient").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                searchList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Ingredients_list IngList = dataSnapshot.getValue(Ingredients_list.class);
                    if (IngList.getname().contains(text)) {
                        searchList.add(IngList);
                    }
                }
                ingAdapter.notifyDataSetChanged();

                Toast.makeText(Ingredient_add.this, "[검색버튼클릭] 검색어 = " + text, Toast.LENGTH_LONG).show();

            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });

        ingAdapter = new Ingredient_adapter(searchList, getApplicationContext());
        recyclerView.setAdapter(ingAdapter);
    }

    public void viewIngredient(){
        mDatabase.child("Ingredient").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                itemArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Ingredients_list rec = snapshot.getValue(Ingredients_list.class);
                    itemArrayList.add(rec);
                }
                ingAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Log.e("fragment1", String.valueOf(databaseError.toException()));
            }
        });
        ingAdapter = new Ingredient_adapter(itemArrayList, getApplicationContext());
        recyclerView.setAdapter(ingAdapter);
    }
}
