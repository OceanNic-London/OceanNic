package com.example.oceannic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FragmentHome extends Fragment {

    ViewGroup viewGroup;

    TextView txt_successful, txt_startDate;

    String email;
    String start_date;
    String[] topic = {"Zero Waste", "Save Water", "Recycle"};
    long count = 0;

    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        txt_successful = viewGroup.findViewById(R.id.txt_successful);
        txt_startDate = viewGroup.findViewById(R.id.txt_startDate);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            email = user.getEmail();
            email = email.replace(".", "");

        }

        for(int i = 0; i < 3; i++){
            databaseReference.child("users").child(email).child("topic").child(topic[i]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    count += snapshot.getChildrenCount();

                    txt_successful.setText(String.valueOf(count));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        databaseReference.child("users").child(email).child("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return viewGroup;
    }
}