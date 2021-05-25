package com.changmeen.community;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.changmeen.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class communityWrite extends AppCompatActivity {
    private ImageView ivBack;
    TextView tvToolbarTitle;
    private AutoCompleteTextView autoCompleteTextView;
    private AppCompatButton btnSaveCommunity;
    private TextInputEditText etTitle;
    private TextInputEditText etContents;
    private DatabaseReference mDatabase;
    public List<String> category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.community_write);
        ivBack = findViewById(R.id.iv_back);
        tvToolbarTitle = findViewById(R.id.tv_toolbar_title);
        tvToolbarTitle.setText("Write");
        ivBack.setOnClickListener(v -> {
            finish();
        });

//        autoCompleteTextView = findViewById(R.id.autoCompleteText);
//        btnSaveCommunity = findViewById(R.id.btn_save_community);
//        etTitle = findViewById(R.id.et_title);
//        etContents = findViewById(R.id.et_content);
//        category = communityActivity.category;
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.community_category_item, category);
//
//        autoCompleteTextView.setText("카테고리 선택", true);
//        autoCompleteTextView.setAdapter(arrayAdapter);
//
//        btnSaveCommunity.setOnClickListener(v -> {
//            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
//            String token = pref.getString("token", "");
//            String flag = autoCompleteTextView.getText().toString();
//            if(etTitle.getText().toString().equals("")|etContents.getText().toString().equals(""))
//                Toast.makeText(this,"Check your Input",Toast.LENGTH_SHORT).show();
//            else {
//                if (category.indexOf(flag) != -1) {
//                    Question question = new Question();
//                    question.setTitle(etTitle.getText().toString());
//                    question.setContent(etContents.getText().toString());
//                    question.setToken(token);
//                    question.setCategoryName(autoCompleteTextView.getText().toString());
//                    question.setCategoryId(category.indexOf(question.getCategoryName()));
//                    question.setUsername(pref.getString("UserName",""));
//                    question.setDBKey(mDatabase.child(autoCompleteTextView.getText().toString()).push().getKey());
//                    mDatabase.child("Post").
//                            child(autoCompleteTextView.getText().toString()).
//                            child(question.getDBKey()).setValue(question);
//                    mDatabase.child("communitySearch").child(question.getDBKey()).setValue(question);
//                    finish();
//
//                } else {
//                    Toast toast = Toast.makeText(this, "Select Category", Toast.LENGTH_SHORT);
//                    toast.show();
//                }
//            }
//        });
    }
}