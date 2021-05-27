package com.changmeen.firebase;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class communityAdapter extends RecyclerView.Adapter<communityAdapter.CommunityViewHolder> {
    private List<Community_Data> communities;
    private Context mContext;

    public communityAdapter(List<Community_Data> communities, Context mContext) {
        this.communities = communities;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CommunityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.community_item, parent, false);
        return new CommunityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityViewHolder holder, int position) {
        holder.setCommunityItem(communities.get(position));
    }

    @Override
    public int getItemCount() {
        return communities.size();
    }

    public class CommunityViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCommunityTitle, tvUsername;

        public CommunityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCommunityTitle = itemView.findViewById(R.id.tv_community_title);
            tvUsername = itemView.findViewById(R.id.tv_username);

            itemView.setOnClickListener(v->{
                int pos = getAdapterPosition();
                Intent intent = new Intent(mContext, communityContents.class);
                intent.putExtra("community",communities.get(pos));
                intent.putExtra("pos",pos);
                itemView.getContext().startActivity(intent);
            });
        }

        public void setCommunityItem(Community_Data community) {
            tvCommunityTitle.setText(community.getTitle());
            tvUsername.setText(community.getUsername());
        }
    }
}
