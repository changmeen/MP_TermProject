package com.changmeen.firebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class TodayMenu extends Fragment {

    private DatabaseReference mDatabase;
    ArrayList<String> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.today_menu, container, false);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.todaymenu);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("Food").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    String readstr;
                    readstr = String.valueOf(task.getResult().getValue());

                    while (readstr.indexOf("image") != -1) {
                        int sidx = readstr.indexOf("image") + 6;
                        int eidx = readstr.indexOf(",");

                        data.add(readstr.substring(sidx, eidx));

                        readstr = readstr.substring(eidx);

                        if (readstr.indexOf("image") != -1)
                            readstr = readstr.substring(readstr.indexOf("image"));
                    }


                }
            }
        });


        return v;

    }
}