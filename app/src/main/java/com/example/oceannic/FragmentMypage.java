package com.example.oceannic;

import static android.graphics.ImageDecoder.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMypage extends Fragment {

    ViewGroup viewGroup;
    TextView txt_name, txt_email, txt_logout, txt_success_challenge, txt_start_date, txt_week;
    CircleImageView img_profile;

    String name, email, replace_email;
    Uri photoUri;

    private FirebaseAuth firebaseAuth;
    ;
    String[] topic = {"Zero Waste", "Save Water", "Recycle"};
    long count = 0;

    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_mypage, container, false);

        txt_name = viewGroup.findViewById(R.id.txt_username);
        txt_email = viewGroup.findViewById(R.id.txt_useremail);
        img_profile = viewGroup.findViewById(R.id.txt_userphoto);
        txt_logout = viewGroup.findViewById(R.id.txt_logout);
        txt_success_challenge = viewGroup.findViewById(R.id.txt_success_challenge);
        txt_start_date = viewGroup.findViewById(R.id.txt_start_Date);
        txt_week = viewGroup.findViewById(R.id.txt_week);

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            name = user.getDisplayName();
            email = user.getEmail();
            photoUri = user.getPhotoUrl();

            replace_email = email.replace(".", "");

            txt_name.setText(name);
            txt_email.setText(email);

            String uri = String.valueOf(photoUri);
            Glide.with(this).load(uri).into(img_profile);
        }

        for(int i = 0; i < 3; i++){
            int finalI = i;
            databaseReference.child("users").child(replace_email).child("topic").child(topic[i]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    count += snapshot.getChildrenCount();

                    System.out.println(count);

                    txt_success_challenge.setText(String.valueOf(count));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        databaseReference.child("users").child(replace_email).child("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str_date = (String) snapshot.getValue();
                try {
                    Date start_date = new SimpleDateFormat("yyyy-MM-dd").parse(str_date);
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(start_date);

                    long diffSec = (calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / 1000;
                    long diffDays = diffSec / (24*60*60);

                    txt_start_date.setText(String.valueOf(diffDays + 1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                Toast.makeText(getContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        return viewGroup;
    }
}