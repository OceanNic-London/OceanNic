package com.example.oceannic;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContentActivity extends AppCompatActivity {

    ImageButton btn_back;
    TextView txt_name, txt_description, txt_years;

    String category, name;

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        category = getIntent().getStringExtra("category");
        name = getIntent().getStringExtra("name");

        btn_back = findViewById(R.id.btn_back);
        txt_name = findViewById(R.id.txt_name);
        txt_description = findViewById(R.id.txt_description);
        txt_years = findViewById(R.id.txt_years);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentInfo fragmentInfo = new FragmentInfo();
                transaction.replace(R.id.fragment_container, fragmentInfo).commitAllowingStateLoss();
            }
        });

        txt_name.setText(name);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("debris").child(category).child("category").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if(snapshot.getValue(OceanDebris.class) != null){
                    OceanDebris oceanDebris = snapshot.getValue(OceanDebris.class);

                    String description = oceanDebris.getDescription();
                    String years = oceanDebris.getYears();

                    txt_description.setText(description);
                    txt_years.setText(years + " YEARS");
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
