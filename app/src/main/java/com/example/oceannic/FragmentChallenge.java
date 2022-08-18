package com.example.oceannic;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FragmentChallenge extends Fragment {

    ViewGroup viewGroup;

    RecyclerView recyclerView;
    TextView txt_topic_1, txt_topic_2, txt_topic_3, txt_des_1, txt_des_2, txt_des_3;
    ImageView img_1, img_2, img_3;

    ArrayList<String> respone = new ArrayList<>();
    ChallengeAdapter adapter;

    Drawable zerowaste, savewater, recycle;

    long[] children_cnt = new long[3];
    String[] arr_topic = {"Zero Waste", "Save Water", "Recycle"};
    String[] challenge = new String[3];
    int[] random_topic = new int[3];
    int[] random_challenge = new int[3];
    int topic_index = 0, chk_index = 0;

    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_challenge, container, false);

        recyclerView = viewGroup.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

        txt_topic_1 = viewGroup.findViewById(R.id.txt_topic_1);
        txt_topic_2 = viewGroup.findViewById(R.id.txt_topic_2);
        txt_topic_3 = viewGroup.findViewById(R.id.txt_topic_3);
        txt_des_1 = viewGroup.findViewById(R.id.txt_des_1);
        txt_des_2 = viewGroup.findViewById(R.id.txt_des_2);
        txt_des_3 = viewGroup.findViewById(R.id.txt_des_3);
        img_1 = viewGroup.findViewById(R.id.img_1);
        img_2 = viewGroup.findViewById(R.id.img_2);
        img_3 = viewGroup.findViewById(R.id.img_3);

        random_topic[0] = (int) (Math.random() * 3);
        random_topic[1] = (int) (Math.random() * 3);
        random_topic[2] = (int) (Math.random() * 3);

        txt_topic_1.setText(arr_topic[random_topic[0]]);
        txt_topic_2.setText(arr_topic[random_topic[1]]);
        txt_topic_3.setText(arr_topic[random_topic[2]]);

        zerowaste = getResources().getDrawable(R.drawable.ic_challenge_zerowaste);
        savewater = getResources().getDrawable(R.drawable.ic_challenge_savewater);
        recycle = getResources().getDrawable(R.drawable.ic_challenge_recycle);

        switch (random_topic[0]){
            case 0: img_1.setImageDrawable(zerowaste); break;
            case 1: img_1.setImageDrawable(savewater); break;
            case 2: img_1.setImageDrawable(recycle); break;
        }

        switch (random_topic[1]){
            case 0: img_2.setImageDrawable(zerowaste); break;
            case 1: img_2.setImageDrawable(savewater); break;
            case 2: img_2.setImageDrawable(recycle); break;
        }

        switch (random_topic[2]){
            case 0: img_3.setImageDrawable(zerowaste); break;
            case 1: img_3.setImageDrawable(savewater); break;
            case 2: img_3.setImageDrawable(recycle); break;
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("challenge").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getKey() != null){
                    String topic = snapshot.getKey();
                    respone.add(0, topic);

                    children_cnt[topic_index++] = snapshot.getChildrenCount();

                    adapter = new ChallengeAdapter(respone, getContext());
                    recyclerView.setAdapter(adapter);
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

        databaseReference.child("challenge").child((String) txt_topic_1.getText()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                chk_index = 0;
                random_challenge[0] = (int) (Math.random() * children_cnt[0]);

                if (chk_index == random_challenge[0]) {
                    challenge[0] = snapshot.getKey();
                    System.out.println(challenge[0]);

                    txt_des_1.setText(challenge[0]);
                }

                chk_index++;
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

        databaseReference.child("challenge").child((String) txt_topic_2.getText()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                chk_index = 0;
                random_challenge[1] = (int) (Math.random() * children_cnt[1]);

                while(arr_topic[0] == arr_topic[1] && random_challenge[0] != random_challenge[1]){
                    random_challenge[1] = (int) (Math.random() * children_cnt[1]);
                }

                if (chk_index == random_challenge[1]) {
                    challenge[1] = snapshot.getKey();
                    System.out.println(challenge[1]);

                    txt_des_2.setText(challenge[1]);
                }

                chk_index++;
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

        databaseReference.child("challenge").child((String) txt_topic_3.getText()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                chk_index = 0;
                random_challenge[2] = (int) (Math.random() * children_cnt[1]);

                if(arr_topic[0] == arr_topic[2]){
                    while (random_challenge[0] != random_challenge[2]){
                        random_challenge[2] = (int) (Math.random() * children_cnt[1]);
                    }
                }else if(arr_topic[1] == arr_topic[2]){
                    while (random_challenge[1] != random_challenge[2]){
                        random_challenge[2] = (int) (Math.random() * children_cnt[1]);
                    }
                }

                if (chk_index == random_challenge[2]) {
                    challenge[2] = snapshot.getKey();
                    System.out.println(challenge[2]);

                    txt_des_3.setText(challenge[2]);
                }

                chk_index++;
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