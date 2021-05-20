package com.changmeen.firebase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class rec_adapter extends RecyclerView.Adapter<rec_adapter.ViewHolder>{
    private ArrayList<rec_list> arrayList;
    private Context context;
    private Intent intent;

    public rec_adapter(ArrayList<rec_list> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // 사용될 항목들 선언
        TextView rName;
        ImageView rProfile;

        public ViewHolder(View v) {
            super(v);

            this.rName = v.findViewById(R.id.rcp_RecyclerView_TextView);
            this.rProfile = v.findViewById(R.id.rcp_RecyclerView_ImageView);
        }
    }

    // 생성자 - 넘어 오는 데이터파입에 유의해야 한다.
    public rec_adapter(ArrayList<rec_list> myDataset) {
        arrayList = myDataset;
    }

    @Override
    public rec_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcp_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getImage())
                .into(holder.rProfile);

        holder.rName.setText(arrayList.get(position).getName());
        holder.rProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(v.getContext(), receipeActivity.class);
                Recipe recipe = new Recipe();

                recipe.setImage(arrayList.get(position).getImage());
                recipe.setName(arrayList.get(position).getName());
                recipe.setIngredient(arrayList.get(position).getIngredient());
                recipe.setRecipe(arrayList.get(position).getRecipe());
                recipe.setRecUrl(arrayList.get(position).getRecurl());

                intent.putExtra("list", recipe);

                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {

        return (arrayList !=null ? arrayList.size():0);
    }
}
