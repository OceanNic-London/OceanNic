package com.example.oceannic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ChallengDetailActivity extends AppCompatActivity {

    ImageButton btn_close;
    TextView txt_topic;
    RecyclerView recyclerView;

    String topic;
    ArrayList<Challenge> respone = new ArrayList<>();
    ChallengeDatailAdapter adapter;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge_detail);

        btn_close = findViewById(R.id.btn_close);
        txt_topic = findViewById(R.id.txt_topic);
        recyclerView = findViewById(R.id.recyclerView);

        topic = getIntent().getStringExtra("topic");

        databaseReference = FirebaseDatabase.getInstance().getReference();

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txt_topic.setText(topic);

        databaseReference.child("challenge").child(topic).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.getValue(Challenge.class) != null) {
                    Challenge challenge = snapshot.getValue(Challenge.class);
                    String str = challenge.getChallenge_name();

                    respone.add(0, new Challenge(str));
                    System.out.println("res : " + respone.get(0).getChallenge_name());

                    adapter = new ChallengeDatailAdapter(respone);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
    }
}
