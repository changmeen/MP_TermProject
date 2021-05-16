package com.changmeen.firebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

    public class viewRecipe extends Fragment {
        ViewGroup viewGroup;
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            viewGroup = (ViewGroup) inflater.inflate(R.layout.view_recipe,container,false);

            EditText recipename = (EditText) viewGroup.findViewById(R.id.recipename);
            String parameter = "";

            if(getArguments() != null) {
                parameter = getArguments().getString("recipeName");
                recipename.setText(parameter + "의 레시피");
            }

            if(!parameter.equals("")){
            }

            return viewGroup;
        }

}
