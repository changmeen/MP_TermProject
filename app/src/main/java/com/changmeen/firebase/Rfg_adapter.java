package com.changmeen.firebase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Rfg_adapter extends RecyclerView.Adapter<Rfg_adapter.ViewHolder> {

    private ArrayList<Ingredients_list> arrayList;
    private Context context;
    private Intent intent;
    private SharedPreferences prefer;


    public Rfg_adapter(ArrayList<Ingredients_list> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // 사용될 항목들 선언
        TextView rName;
        ImageView rProfile;

        public ViewHolder(View v) {
            super(v);

            this.rName = v.findViewById(R.id.Ing_RecyclerView_TextView);
            this.rProfile = v.findViewById(R.id.Ing_RecyclerView_ImageView);
        }
    }

    // 생성자 - 넘어 오는 데이터파입에 유의해야 한다.
    public Rfg_adapter(ArrayList<Ingredients_list> myDataset) {
        arrayList = myDataset;
    }

    @Override
    public Rfg_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        Rfg_adapter.ViewHolder holder = new Rfg_adapter.ViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(Rfg_adapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getimage())
                .into(holder.rProfile);

        holder.rName.setText(arrayList.get(position).getname());
        prefer = context.getSharedPreferences("pref", MODE_PRIVATE);
        holder.rProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userToken = prefer.getString("token", "");

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(arrayList.get(position).getname() + "을 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference databaseReference =  FirebaseDatabase.getInstance().getReference().
                       child("userIngredient").child(userToken).child(arrayList.get(position).getname());
                        databaseReference.removeValue();
                    }
                });
                builder.setNegativeButton("아니오",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //냅두면
                            }
                        });
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {

        return (arrayList != null ? arrayList.size() : 0);
    }

}
