package com.changmeen.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LoadingDBSection extends AppCompatActivity {

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_loading_db_section);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        write();
    }

    private void write(){
        Map<String, Object> map = new HashMap<>();
        map.put("ing", "water");
        map.put("rec", "aaaa");

        mDatabase.child("asdf").setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Sucess!", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(getApplicationContext(), "Next!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
