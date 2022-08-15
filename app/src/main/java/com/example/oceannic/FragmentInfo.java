package com.example.oceannic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FragmentInfo extends Fragment {

    ViewGroup viewGroup;
    String category = "";

    ImageButton btn_back;
    TextView txt_category;
    LinearLayout linear_scroll;
    HorizontalScrollView scrollView;
    FragmentTransaction transaction;

    ArrayList<OceanDebris> respone = new ArrayList<>();
    Adapter adapter;

    private DatabaseReference databaseReference;

    public FragmentInfo(){}

    public FragmentInfo(String category) {
        this.category = category;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_info,container,false);

        btn_back = viewGroup.findViewById(R.id.btn_back);
        txt_category = viewGroup.findViewById(R.id.txt_category);
        linear_scroll = viewGroup.findViewById(R.id.linear_scroll);
        scrollView= viewGroup.findViewById(R.id.scrollView);

        transaction = getActivity().getSupportFragmentManager().beginTransaction();

        // 카테고리 텍스트 변경
        txt_category.setText(category);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.fragment_container, new FragmentCategory()).commit();
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("debris").orderByChild(category).orderByChild("category").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getValue(OceanDebris.class) != null){
                    OceanDebris oceanDebris = snapshot.getValue(OceanDebris.class);

                    String name = oceanDebris.getName();
                    respone.add(0, new OceanDebris(name));

                    adapter = new CategoryAdapter(respone);


                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return viewGroup;
    }
}
