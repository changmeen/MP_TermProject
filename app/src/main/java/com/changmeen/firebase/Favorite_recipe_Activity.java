package com.changmeen.firebase;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Favorite_recipe_Activity extends YouTubeBaseActivity {

    private TextView rName;
    private ImageView rProfile;
    private TextView rIngredient;
    private TextView rRecipe;
    YouTubePlayerView playerView;
    YouTubePlayer.OnInitializedListener listener;
    private SharedPreferences prefer;
    private Button btn;
    private static String API_KEY = "AIzaSyBRYfG12NILfVtCzhTMCumjaQDRkxPtQ5k";  // 여기 받은 콭솔키 입력좀
    private static String videoId;
    private DatabaseReference mDatabase;
    Intent intent;
    private Recipe recipe;
    private String userToken;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.rcp_view);
        // rpc_view XML
        rName = (TextView) findViewById(R.id.rcp_name);//음식 이름
        rIngredient = (TextView) findViewById(R.id.rcp_ingredient);//음식 재료
        rRecipe = (TextView) findViewById(R.id.rcp_recipe);// 만드는 법
        rProfile = (ImageView) findViewById(R.id.rcp_ImageView); //맨 위사진
        prefer = getSharedPreferences("pref", MODE_PRIVATE);
        btn = findViewById(R.id.Wla);
        prefer = this.getSharedPreferences("pref", Context.MODE_PRIVATE);
        userToken = prefer.getString("token", "");

        intent = getIntent();//rec_adapter.java
        recipe = (Recipe) intent.getSerializableExtra("list");//rec_adapter에서 넘겨받은 객체

        rName.setText(recipe.getName());
        rIngredient.setText(recipe.getIngredient());
        rRecipe.setText(recipe.getRecipe());
        Glide.with(this)
                .load(recipe.getImage())
                .into(rProfile);

        videoId = recipe.getRecUrl();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference order = mDatabase.child("찜");
                order.child(userToken).child(recipe.getName()).setValue(recipe);
                Toast.makeText(getApplicationContext(), recipe.getName() + " 을(를)찜 하셨습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        });

        playerView = findViewById(R.id.rcp_video);//유튜브 영상
        listener = new YouTubePlayer.OnInitializedListener() {

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
        playerView.initialize(API_KEY, listener);
    }

}