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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Ingredient_adapter extends RecyclerView.Adapter<Ingredient_adapter.ViewHolder> {
    private ArrayList<Ingredients_list> arrayList;
    private Context context;
    private Intent intent;
    private SharedPreferences prefer;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();

    public Ingredient_adapter(ArrayList<Ingredients_list> arrayList, Context context) {
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

    // 생성자 - 넘어 오는 데이터타입에 유의해야 한다.
    public Ingredient_adapter(ArrayList<Ingredients_list> myDataset) {
        arrayList = myDataset;
    }

    // 리스튜뷰가 어뎁터에 연결된 다음에 뷰 홀더를 최더를 최초로 만들어냄
    @Override
    public Ingredient_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    //매칭을 시켜주는 역할
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getimage())
                .into(holder.rProfile);

        holder.rName.setText(arrayList.get(position).getname());
        prefer = context.getSharedPreferences("pref" ,MODE_PRIVATE);
        holder.rProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userToken = prefer.getString("token", "");

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(arrayList.get(position).getname() + "을 추가하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child("userIngredient").child(userToken).child(arrayList.get(position).getname()).child("name").setValue(arrayList.get(position).getname());
                        databaseReference.child("userIngredient").child(userToken).child(arrayList.get(position).getname()).child("image").setValue(arrayList.get(position).getimage());
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
