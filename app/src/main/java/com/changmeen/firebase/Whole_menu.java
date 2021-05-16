package com.changmeen.firebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Whole_menu extends Fragment {

    ViewGroup viewGroup;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        viewGroup = (ViewGroup) inflater.inflate(R.layout.whole_menu,container,false);

        Button rice1 = (Button) viewGroup.findViewById(R.id.rice1);
        Button rice2 = (Button) viewGroup.findViewById(R.id.rice2);
        Button rice3 = (Button) viewGroup.findViewById(R.id.rice3);
        Button rice4 = (Button) viewGroup.findViewById(R.id.rice4);
        Button rice5 = (Button) viewGroup.findViewById(R.id.rice5);

        Button noodle1 = (Button) viewGroup.findViewById(R.id.noodle1);
        Button noodle2 = (Button) viewGroup.findViewById(R.id.noodle2);
        Button noodle3 = (Button) viewGroup.findViewById(R.id.noodle3);
        Button noodle4 = (Button) viewGroup.findViewById(R.id.noodle4);
        Button noodle5 = (Button) viewGroup.findViewById(R.id.noodle5);

        Button soup1 = (Button) viewGroup.findViewById(R.id.soup1);
        Button soup2 = (Button) viewGroup.findViewById(R.id.soup2);
        Button soup3 = (Button) viewGroup.findViewById(R.id.soup3);
        Button soup4 = (Button) viewGroup.findViewById(R.id.soup4);
        Button soup5 = (Button) viewGroup.findViewById(R.id.soup5);

        Button side1 = (Button) viewGroup.findViewById(R.id.side1);
        Button side2 = (Button) viewGroup.findViewById(R.id.side2);
        Button side3 = (Button) viewGroup.findViewById(R.id.side3);
        Button side4 = (Button) viewGroup.findViewById(R.id.side4);
        Button side5 = (Button) viewGroup.findViewById(R.id.side5);

        Button dessert1 = (Button) viewGroup.findViewById(R.id.dessert1);
        Button dessert2 = (Button) viewGroup.findViewById(R.id.dessert2);
        Button dessert3 = (Button) viewGroup.findViewById(R.id.dessert3);
        Button dessert4 = (Button) viewGroup.findViewById(R.id.dessert4);
        Button dessert5 = (Button) viewGroup.findViewById(R.id.dessert5);

        rice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", rice1.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        rice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", rice2.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        rice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", rice3.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        rice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", rice4.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        rice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", rice5.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        noodle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", noodle1.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        noodle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", noodle2.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        noodle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", noodle3.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        noodle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", noodle4.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        noodle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", noodle5.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        soup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", soup1.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        soup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", soup2.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        soup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", soup3.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        soup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", soup4.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        soup5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", soup5.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        side1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", side1.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        side2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", side2.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        side3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", side3.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        side4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", side4.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        side5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", side5.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        dessert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", dessert1.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        dessert2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", dessert2.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        dessert3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", dessert3.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        dessert4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", dessert4.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        dessert5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewRecipe viewrecipe = new viewRecipe();

                Bundle bundle = new Bundle();
                bundle.putString("recipeName", dessert5.getText().toString());
                viewrecipe.setArguments(bundle);

                getFragmentManager().beginTransaction().replace(R.id.Layout1, viewrecipe).addToBackStack(null).commit();

            }
        });

        return viewGroup;
    }


//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.whole_menu);
//    }
//

}
