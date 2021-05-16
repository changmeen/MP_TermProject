package com.changmeen.firebase;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavigationView nv;
    ActionBarDrawerToggle barDrawerToggle;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main2);
        setContentView(R.layout.drawbar);
        drawer = findViewById(R.id.drawer);
        //준석
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);//기본 제목을 없애줍니다.
        actionBar.setDisplayHomeAsUpEnabled(true);



        //조절용 토글 버튼 객체 생성
        barDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        //삼선아이콘 모양
        barDrawerToggle.syncState();


        LayoutInflater mInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        mInflater.inflate(R.layout.drawbar, drawer, true);
        nv = findViewById(R.id.nv);
        NavigationView.enable(MainActivity.this, nv);
    }

    //뒤로가기 눌렀을 때
    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()==0) {
            // AlertDialog 빌더를 이용해 종료시 발생시킬 창을 띄운다
            AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
            alBuilder.setMessage("종료하시겠습니까?");

            // "예" 버튼을 누르면 실행되는 리스너
            alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish(); // 현재 액티비티를 종료한다. (MainActivity에서 작동하기 때문에 애플리케이션을 종료한다.)
                }
            });
            // "아니오" 버튼을 누르면 실행되는 리스너
            alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    return; // 아무런 작업도 하지 않고 돌아간다
                }
            });
            alBuilder.setTitle("프로그램 종료");
            alBuilder.show(); // AlertDialog.Bulider로 만든 AlertDialog를 보여준다.
        }
        else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        barDrawerToggle.onOptionsItemSelected(item);
        switch(item.getItemId()){
            case R.id.logout:
                show();
                break;
            case R.id.account:
                break;
//            case android.R.id.home:
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
    void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog Title");
        builder.setMessage("AlertDialog Content");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"예를 선택했습니다.",Toast.LENGTH_LONG).show();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"아니오를 선택했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tool_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}



