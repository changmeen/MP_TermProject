package com.changmeen.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Profile_Adapter extends RecyclerView.Adapter<Profile_Adapter.ViewHolder> {
    private ArrayList<Recipe> arrayList;
    private Context context;
    private Intent intent;
    private SharedPreferences prefer;
    public Profile_Adapter(ArrayList<Recipe> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // 사용될 항목들 선언
        TextView rName;
        ImageView rProfile;

        public ViewHolder(View v) {
            super(v);

            // account_item XML
            this.rName = v.findViewById(R.id.itemName);
            this.rProfile = v.findViewById(R.id.itemImage);
        }
    }

    // 생성자 - 넘어 오는 데이터파입에 유의해야 한다.
    public Profile_Adapter(ArrayList<Recipe> myDataset) {
        arrayList = myDataset;
    }

    @Override
    public Profile_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Profile_Adapter.ViewHolder holder = new Profile_Adapter.ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Profile_Adapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage())
                .into(holder.rProfile);

        holder.rName.setText(arrayList.get(position).getName());
        prefer = context.getSharedPreferences("pref" ,MODE_PRIVATE);
        holder.rProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), receipeActivity.class);
                Recipe recipe = new Recipe();

                recipe.setImage(arrayList.get(position).getImage());
                recipe.setName(arrayList.get(position).getName());
                recipe.setIngredient(arrayList.get(position).getIngredient());
                recipe.setRecipe(arrayList.get(position).getRecipe());
                recipe.setRecUrl(arrayList.get(position).getRecUrl());

                intent.putExtra("list", recipe);

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return (arrayList != null ? arrayList.size() : 0);
    }
}
