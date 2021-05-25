package com.changmeen.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class receipeActivity extends YouTubeBaseActivity {

    private TextView rName;
    private ImageView rProfile;
    private TextView rIngredient;
    private TextView rRecipe;

    YouTubePlayerView playerView;
    YouTubePlayer.OnInitializedListener listener;

    private static String API_KEY = "AIzaSyBRYfG12NILfVtCzhTMCumjaQDRkxPtQ5k";  // 여기 받은 콭솔키 입력좀
    private static String videoId;

    Intent intent;
    private Recipe recipe;

    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.rcp_view);

        rName = (TextView) findViewById(R.id.rcp_name);
        rIngredient = (TextView) findViewById(R.id.rcp_ingredient);
        rRecipe = (TextView) findViewById(R.id.rcp_recipe);
        rProfile = (ImageView) findViewById(R.id.rcp_ImageView);

        intent = getIntent();
        recipe = (Recipe) intent.getSerializableExtra("list");

        rName.setText(recipe.getName());
        rIngredient.setText(recipe.getIngredient());
        rRecipe.setText(recipe.getRecipe());
        Glide.with(this)
                .load(recipe.getImage())
                .into(rProfile);

        videoId = recipe.getRecUrl();
        playerView = findViewById(R.id.rcp_video);
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
