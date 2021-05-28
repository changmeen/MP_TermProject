package com.changmeen.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String token;
    private EditText ID, PW;

    SharedPreferences sh_Pref;
    SharedPreferences.Editor toEdit;
    String email, pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//        startActivity(intent);

        Button loginButton = (Button) findViewById(R.id.LoginButton);
        Button signUpButton = (Button) findViewById(R.id.SignUpButton);
        ID = (EditText) findViewById(R.id.editTextTextPersonName);
        PW = (EditText) findViewById(R.id.editTextTextPassword);
        mAuth = mAuth.getInstance();
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        token = pref.getString("token", "");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = ID.getText().toString();
                pw = PW.getText().toString();

                SharedPreference();

                mAuth.signInWithEmailAndPassword(email, pw)
                        .addOnCompleteListener(com.changmeen.firebase.LoginActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String id = user.getUid();
                                    String username = email;
                                    pref = getSharedPreferences("pref", MODE_PRIVATE); //이렇게 호출해야됨
                                    editor = pref.edit();
                                    editor.putString("token", id);
                                    editor.commit();
                                    Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "로그인 오류", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        applySharedPreference();
    }

    public void SharedPreference() {
        sh_Pref = getSharedPreferences("Login Credentials", MODE_PRIVATE);
        toEdit = sh_Pref.edit();
        toEdit.putString("ID", email);
        toEdit.putString("PW", pw);
        toEdit.commit();
    }

    public void applySharedPreference() {
        sh_Pref = getSharedPreferences("Login Credentials", MODE_PRIVATE);
        if(sh_Pref != null && sh_Pref.contains("ID")) {
            String id = sh_Pref.getString("ID", "no_id");
            String pw = sh_Pref.getString("PW", "no_pw");
            ID.setText(id);
            PW.setText(pw);
        }
    }
}