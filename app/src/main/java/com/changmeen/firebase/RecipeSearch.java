package com.changmeen.firebase;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;

public class RecipeSearch extends AppCompatActivity {

    private ArrayList<Recipe> arrayList;
    private rec_adapter recAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private EditText searchET;
    private Button recipeBtn;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_search);
        //hojin

        arrayList = new ArrayList<>();
        recyclerView = findViewById(R.id.recipe_search_list);
        searchET = findViewById(R.id.search_text);
        recipeBtn = findViewById(R.id.btn_search);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        recipeBtn.setOnClickListener(v -> {
            SearchContents(searchET.getText().toString());
            Toast.makeText(this, searchET.getText().toString(), Toast.LENGTH_SHORT).show();
        });
    }

    public void SearchContents(final String text) {
        mDatabase.child("Search").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                arrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Recipe RecList = dataSnapshot.getValue(Recipe.class);
                    if (RecList.getName().contains(text)) {
                        arrayList.add(RecList);
                        recAdapter = new rec_adapter(arrayList, getApplicationContext());
                    }
                }
                recyclerView.setAdapter(recAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }


}
