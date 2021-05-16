package com.changmeen.firebase;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

public class NavigationView {
    private static final String TAG = "NavigationView";

    public static void enable(Context context, NavigationView view) {
        view.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            DrawerLayout drawer = (DrawerLayout)((Activity) context).findViewById(R.id.drawer);

            if (id == R.id.today) {
                Log.d(TAG, "enable: 오늘 뭐먹지 클릭");
                Intent intent = new Intent(context, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
            else if(id == R.id.menu){
                Log.d(TAG, "enable: 메뉴 클릭");
                Intent intent = new Intent(context, Whole_menu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
            else if (id == R.id.ref) {
                Log.d(TAG, "enable: 냉장고 클릭");
                Intent intent = new Intent(context, Ingredients.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                context.startActivity(intent);
            }
            drawer.closeDrawer(Gravity.LEFT);
            return false;
        });
    }
}
