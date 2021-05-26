package com.changmeen.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class Profile_edit extends AppCompatActivity {
    private androidx.appcompat.widget.AppCompatButton confirm_button;
    private EditText nickname_edit;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_edit);

        nickname_edit = findViewById(R.id.editNickname);
        confirm_button = findViewById(R.id.btn_edit_profile);
        confirm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nickname = nickname_edit.getText().toString();
                pref = getSharedPreferences("pref", MODE_PRIVATE); //이렇게 호출해야됨
                editor = pref.edit();
                editor.putString("UserName", nickname);
                editor.commit();
                Toast.makeText(getApplicationContext(), "변경 성공", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
