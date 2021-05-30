package com.changmeen.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Random;


public class TodayMenu extends Fragment {

    private DatabaseReference mDatabase;

    ArrayList<String> image= new ArrayList<>();
    ArrayList<String> name = new ArrayList<>();
    ArrayList<String> ingredient = new ArrayList<>();
    ArrayList<String> rec = new ArrayList<>();
    ArrayList<String> url = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.today_menu, container, false);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.todaymenu);

        ImageButton recommend_btn = (ImageButton) v.findViewById(R.id.recommendBtn);
        ImageView recommend_rec = (ImageView) v.findViewById(R.id.recommendRec);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Food").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    String readstr;
                    readstr = String.valueOf(task.getResult().getValue());

                    String noodle = readstr.substring(readstr.indexOf("Noodle") + 8, readstr.indexOf("Rice"));
                    String rice = readstr.substring(readstr.indexOf("Rice") + 6, readstr.length());

                    while (noodle.indexOf("{") != -1) {

                        image.add(noodle.substring(noodle.indexOf("image") + 6, noodle.indexOf("ingredient") - 2));
                        ingredient.add(noodle.substring(noodle.indexOf("ingredient") + 11, noodle.indexOf("name") - 2));
                        name.add(noodle.substring(noodle.indexOf("name") + 5, noodle.indexOf("recipe") - 2));
                        rec.add(noodle.substring(noodle.indexOf("recipe") + 7, noodle.indexOf("recUrl") - 2));
                        url.add(noodle.substring(noodle.indexOf("recUrl") + 7, noodle.indexOf("}")));

                        noodle = noodle.substring(noodle.indexOf("}")+1);

                    }

                    while (rice.indexOf("{") != -1) {

                        image.add(rice.substring(rice.indexOf("image") + 6, rice.indexOf("ingredient") - 2));
                        ingredient.add(rice.substring(rice.indexOf("ingredient") + 11, rice.indexOf("name") - 2));
                        name.add(rice.substring(rice.indexOf("name") + 5, rice.indexOf("recipe") - 2));
                        rec.add(rice.substring(rice.indexOf("recipe") + 7, rice.indexOf("recUrl") - 2));
                        url.add(rice.substring(rice.indexOf("recUrl") + 7, rice.indexOf("}")));

                        rice = rice.substring(rice.indexOf("}")+1);

                    }

                    recommend_btn.setVisibility(View.VISIBLE);
                }
            }
        });

        recommend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                int rand = random.nextInt(image.size());

                String temp = image.get(rand);
                Glide.with(view.getContext()).load(temp).into(recommend_rec);

                recommend_rec.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view2) {
                        Intent intent = new Intent(view2.getContext(), Favorite_recipe_Activity.class);
                        Recipe recipe = new Recipe();

                        recipe.setImage(image.get(rand));
                        recipe.setName(name.get(rand));
                        recipe.setIngredient(ingredient.get(rand));
                        recipe.setRecipe(rec.get(rand));
                        recipe.setRecUrl(url.get(rand));

                        intent.putExtra("list", recipe);

                        view2.getContext().startActivity(intent);
                    }
                });

            }
        });


        return v;

    }
}