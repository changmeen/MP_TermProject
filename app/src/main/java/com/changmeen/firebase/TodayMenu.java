package com.changmeen.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TodayMenu extends Fragment {

    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        viewGroup = (ViewGroup) inflater.inflate(R.layout.today_menu,container,false);

        Button subButton1 = (Button) viewGroup.findViewById(R.id.button1);
        Button subButton2 = (Button) viewGroup.findViewById(R.id.button2);
        Button subButton3 = (Button) viewGroup.findViewById(R.id.button3);

        subButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.Layout1, new child1()).addToBackStack(null).commit();
            }
        });
        subButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.Layout1, new child2()).addToBackStack(null).commit();
            }
        });
        subButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getActivity(), youtube.class);
               startActivity(intent);
            }
        });
        return viewGroup;
    }
}
