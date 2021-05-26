package com.changmeen.firebase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class Rfg_page extends Fragment {
    //왜 안들어가짐 ㅡㅡ

    private RecyclerView listview1;
    private RecyclerView listview2;
    Ingredient_adapter adapter1;
    private rec_adapter adapter2;
    private Context mContext;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.rfg_page, container, false);

        ImageButton next = view.findViewById(R.id.rfg_Button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Ingredient_page.class);
                startActivityForResult(intent, 0);
            }
        });

        return view;
    }
}
