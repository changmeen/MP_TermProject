package com.changmeen.firebase;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.changmeen.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class rec_noodle extends Fragment {

    private RecyclerView recyclerView;
    private rec_adapter adapter;
    private GridLayoutManager layoutManager;
    private static ArrayList<Recipe> itemArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rec_frag, container, false);

        recyclerView = view.findViewById(R.id.my_recycler_view);
        layoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        itemArrayList = new ArrayList<>(); //레시피 객체를 담을 어레이리스트 (어댑터쪽으로)

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("Food").child("Noodle").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //파이어베이스 데이터베이스의 데이터를 받아오는 곳
                itemArrayList.clear(); // 기존 배열리스트가 존재하지 않게 초기화
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) { // 반복문으로  데이터 List를 추출해냄
                    Recipe rec = snapshot.getValue(Recipe.class);//만들어뒀던 레시피 리스트  객체에 데이터를 담는다
                    itemArrayList.add(rec);//담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged();//리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //디비를 가져오던 중 에러 발생 시
                Log.e("fragment1", String.valueOf(databaseError.toException()));
            }
        });
        adapter = new rec_adapter(itemArrayList, getContext());
        recyclerView.setAdapter(adapter);//리사이클러뷰에 어댑터 연결
        return view;
    }
}
