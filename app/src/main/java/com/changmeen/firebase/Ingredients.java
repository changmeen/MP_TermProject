package com.changmeen.firebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Ingredients extends Fragment {

    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        viewGroup = (ViewGroup) inflater.inflate(R.layout.food_ingredients,container,false);
        return viewGroup;
    }
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.whole_menu);
//    }
//
}
