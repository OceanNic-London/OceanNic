package com.example.oceannic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FragmentInfo extends Fragment {

    ViewGroup viewGroup;
    String category = "";

    ImageButton btn_back;
    TextView txt_category;
    RecyclerView list_category;
    HorizontalScrollView scrollView;
    FragmentTransaction transaction;

    ArrayList<OceanDebris> respone = new ArrayList<>();
    CategoryAdapter adapter;

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
        scrollView= viewGroup.findViewById(R.id.scrollView);
        list_category = viewGroup.findViewById(R.id.recyclerView);

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

        databaseReference.child("debris").child(category).child("category").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                System.out.println(snapshot.getValue().getClass());

                if(snapshot.getValue(OceanDebris.class) != null){
                    OceanDebris oceanDebris = snapshot.getValue(OceanDebris.class);

                    String name = oceanDebris.getName();

                    respone.add(0, new OceanDebris(name));

                    adapter = new CategoryAdapter(respone, getContext());
                    list_category.setAdapter(adapter);
                    list_category.setLayoutManager(new LinearLayoutManager(getContext()));
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
