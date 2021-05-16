package com.changmeen.firebase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class TodayMenu extends Fragment {
    AutoScrollViewPager autoScrollViewPager;
    ArrayList<String> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.today_menu, container, false);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.todaymenu);

        ArrayList<Object> data = new ArrayList<>(); //이미지 url를 저장하는 arraylist
        data.add(R.drawable.kimchibap);
        data.add(R.drawable.bibimbap);
        data.add(R.drawable.tteokbok2);
        data.add(R.drawable.soysauceeggbap);


        autoScrollViewPager = (AutoScrollViewPager) v.findViewById(R.id.autoViewPager);

        AutoScrollAdapter scrollAdapter = new AutoScrollAdapter(getActivity(), data);
        autoScrollViewPager.setAdapter(scrollAdapter); //Auto Viewpager에 Adapter 장착
        autoScrollViewPager.setInterval(3000); // 페이지 넘어갈 시간 간격 설정
        autoScrollViewPager.startAutoScroll(); //Auto Scroll 시작

        return v;

    }
}