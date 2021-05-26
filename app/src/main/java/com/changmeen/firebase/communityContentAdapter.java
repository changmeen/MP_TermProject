package com.changmeen.firebase;

import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;


public class communityContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<contents> items;
    private communityContents mContext;
    private String token;
    private String Q_DBkey;
    private static final String TAG = "CommunityDetailAdapter:";

    public communityContentAdapter(ArrayList<contents> items, communityContents communityContents, String token) {
        this.items = items;
        this.mContext = communityContents;
        this.token = token;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new DetailContentViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.community_content_item,
                            parent,
                            false
                    )
            );
        } else {
            return new ReplyViewHolder(
                    LayoutInflater.from(parent.getContext()).inflate(
                            R.layout.community_reply_item,
                            parent,
                            false
                    )
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: " + position);
        if (getItemViewType(position) == 0) {
            Community_Data community = (Community_Data) items.get(position).getObject();
            Q_DBkey = community.getDBKEy();
            ((DetailContentViewHolder) holder).setItem(community);
        }
        else if (getItemViewType(position) == 1) {
            Reply reply = (Reply) items.get(position).getObject();
            ((ReplyViewHolder) holder).setItem(reply);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    public class DetailContentViewHolder extends RecyclerView.ViewHolder {

        private AppCompatButton btnReply;
        private TextView tvDetailTitle, tvDetailUsername, tvDetailContents;

        public DetailContentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDetailTitle = itemView.findViewById(R.id.tv_detail_title);
            tvDetailUsername = itemView.findViewById(R.id.tv_detail_username);
            tvDetailContents = itemView.findViewById(R.id.tv_detail_content);
            btnReply = itemView.findViewById(R.id.btn_reply);
            btnReply.setOnClickListener(v -> {
                mContext.showReplyInput();
            });
        }

        public void setItem(Community_Data community) {
            tvDetailTitle.setText(community.getTitle());
            tvDetailContents.setText(community.getContent());
            tvDetailUsername.setText(community.getUsername());
        }
    }

    public class ReplyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvReplyContent, tvReplyUsername;
        private ImageView ivDeleteReply;

        public ReplyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvReplyUsername = itemView.findViewById(R.id.tv_reply_username);
            tvReplyContent = itemView.findViewById(R.id.tv_reply_content);
            ivDeleteReply = itemView.findViewById(R.id.iv_delete_reply);
        }
        public void setItem(Reply reply) {
            tvReplyUsername.setText(reply.getUsername());
            tvReplyContent.setText(reply.getContent());
            if(reply.getToken().equals(token))
            {
                ivDeleteReply.setVisibility(View.VISIBLE);
                ivDeleteReply.setOnClickListener(v->{
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                    AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                    alert.setTitle("댓글을 지우시겠습니까?");
                    alert.setPositiveButton("accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.child("Reply").child(Q_DBkey).child(reply.getReply_key()).removeValue();
                        }
                    });
                    alert.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                        }
                    });
                    alert.show();
                });
            }
            else
            { ivDeleteReply.setVisibility(View.INVISIBLE);}
        }
    }
}