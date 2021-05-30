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


public class noFavorite_recipe_Activity extends YouTubeBaseActivity {

    private TextView rName;
    private ImageView rProfile;
    private TextView rIngredient;
    private TextView rRecipe;
    YouTubePlayerView playerView;
    YouTubePlayer.OnInitializedListener listener;
    private SharedPreferences prefer;
    private Button btn1;
    private static String API_KEY = "AIzaSyBRYfG12NILfVtCzhTMCumjaQDRkxPtQ5k";  // 여기 받은 콭솔키 입력좀
    private static String videoId;
    Intent intent;
    private Recipe recipe;
    private String userToken;


    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.rcp_view2);

        rName = (TextView) findViewById(R.id.rcp_name1);//음식 이름
        rIngredient = (TextView) findViewById(R.id.rcp_ingredient1);//음식 재료
        rRecipe = (TextView) findViewById(R.id.rcp_recipe1);// 만드는 법
        rProfile = (ImageView) findViewById(R.id.rcp_ImageView1); //맨 위사진
        prefer = getSharedPreferences("pref", MODE_PRIVATE);
        btn1 = findViewById(R.id.Wla_delete);
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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("찜").child(userToken).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Recipe rec = snapshot.getValue(Recipe.class);
                            if (recipe.getName().equals(rec.getName())) {
                                show1();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("fragment1", String.valueOf(databaseError.toException()));
                    }
                });
            }
        });


        playerView = findViewById(R.id.rcp_video1);//유튜브 영상
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

    public void show1() {
        if(! noFavorite_recipe_Activity.this.isFinishing()) {
            AlertDialog.Builder obj = new AlertDialog.Builder(this);
            obj.setTitle("찜을 취소하시겠습니까?");
            obj.setIcon(R.mipmap.ic_launcher);
            MyListener m = new MyListener();
            obj.setPositiveButton("네", m);
            obj.setNegativeButton("아니요", m);
            obj.setMessage("Are you sure you want to cancel the favorite?");
            obj.show();
        }
    }

    public class MyListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == -1) {
                DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference()
                        .child("찜").child(userToken).child(recipe.getName());
                mPostReference.removeValue();
                finish();
            }
        }
    }
}
