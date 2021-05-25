package com.changmeen.firebase;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Profile_activity extends AppCompatActivity {
    Profile_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_information2);
        init();
        getData();

    }

    private void init(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new Profile_Adapter();
        recyclerView.setAdapter(adapter);
    }

    private void getData(){
        DataBookmark data = new DataBookmark(R.drawable.baek1, "짬뽕");
        adapter.addItem(data);
        data = new DataBookmark(R.drawable.baek1, "콜라");
        adapter.addItem(data);
        data = new DataBookmark(R.drawable.baek1, "제로콜라");
        adapter.addItem(data);
        data = new DataBookmark(R.drawable.baek1, "앙기모");
        adapter.addItem(data);
        data = new DataBookmark(R.drawable.baek1, "정글은");
        adapter.addItem(data);
        data = new DataBookmark(R.drawable.baek1, "팤선생");
        adapter.addItem(data);
    }
}
