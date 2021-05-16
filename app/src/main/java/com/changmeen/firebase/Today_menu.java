package com.changmeen.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class Today_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.today_menu);

        Button subButton1 = (Button) findViewById(R.id.button1);
        Button subButton2 = (Button) findViewById(R.id.button2);
        Button subButton3 = (Button) findViewById(R.id.button3);

        subButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), youtube.class);
                startActivity(intent);
            }
        });
        subButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), youtube.class);
                startActivity(intent);
            }
        });
        subButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), youtube.class);
                startActivity(intent);
            }
        });
    }
}