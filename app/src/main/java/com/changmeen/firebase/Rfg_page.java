package com.changmeen.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Rfg_page extends Fragment {
    //왜 안들어가짐

    private RecyclerView rfg_RecyclerView;
    private RecyclerView rfg_RecyclerView_rcp_list;

    private LinearLayoutManager LinearLayoutManager;
    private GridLayoutManager GridlayoutManager;
    private Rfg_adapter adapter1;
    private rec_adapter adapter2;
    private Context mContext;

    private static ArrayList<Ingredients_list> ingredients_itemArrayList;
    private static ArrayList<Recipe> recipe_itemArrayList;
    private SharedPreferences prefer;
    String Ingredient_name="";

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rfg_page, container, false);

        rfg_RecyclerView = view.findViewById(R.id.rfg_RecyclerView);
        rfg_RecyclerView_rcp_list = view.findViewById(R.id.rfg_RecyclerView_rcp_list);
        LinearLayoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        GridlayoutManager = new GridLayoutManager(view.getContext(), 5);
        rfg_RecyclerView.setLayoutManager(LinearLayoutManager);
        rfg_RecyclerView_rcp_list.setLayoutManager(GridlayoutManager);

        ingredients_itemArrayList = new ArrayList<>();
        recipe_itemArrayList = new ArrayList<>();

        prefer = view.getContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String userToken = prefer.getString("token", "");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("userIngredient").child(userToken).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ingredients_itemArrayList.clear();
                Ingredient_name = "";
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Ingredients_list rec = snapshot.getValue(Ingredients_list.class);
                    ingredients_itemArrayList.add(rec);
                    Ingredient_name = Ingredient_name.concat(rec.getname() + " ");;
                }
                adapter1.notifyDataSetChanged();
                String[] a = Ingredient_name.split(" ");
                System.out.println("@@@@@@@@@@@@@@@@@" + a[0]);
                System.out.println("@@@@@@@@@@@@@@@@@" + a[1]);
                System.out.println("@@@@@@@@@@@@@@@@@" + a[2]);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("fragment3", String.valueOf(databaseError.toException()));
            }
        });
        adapter1 = new Rfg_adapter(ingredients_itemArrayList, view.getContext());
        rfg_RecyclerView.setAdapter(adapter1);



        ImageButton next = view.findViewById(R.id.rfg_Button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Ingredient_page.class);
                startActivityForResult(intent, 0);
            }
        });

        return view;
    }
}
