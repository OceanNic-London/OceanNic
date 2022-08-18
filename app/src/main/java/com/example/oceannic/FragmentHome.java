package com.example.oceannic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    LinearLayout background;

    String email;
    String[] topic = {"Zero Waste", "Save Water", "Recycle"};
    long count = 0, total_cnt;
    int id_background;

    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);

        txt_successful = viewGroup.findViewById(R.id.txt_successful);
        txt_startDate = viewGroup.findViewById(R.id.txt_startDate);
        background = viewGroup.findViewById(R.id.layout_background);

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            email = user.getEmail();
            email = email.replace(".", "");
        }

        for(int i = 0; i < 3; i++){
            int finalI = i;
            databaseReference.child("users").child(email).child("topic").child(topic[i]).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    count += snapshot.getChildrenCount();

                    System.out.println(count);

                    txt_successful.setText(String.valueOf(count));

                    databaseReference.child("challenge").child(topic[finalI]).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            total_cnt += snapshot.getChildrenCount();

                            if(finalI == 2){
                                switch ((int) (count / 5)) {
                                    case 4:
                                        id_background = getResources().getIdentifier("home_100", "drawable", getContext().getPackageName());
                                        background.setBackgroundResource(id_background);
                                        break;
                                    case 3:
                                        id_background = getResources().getIdentifier("home_75", "drawable", getContext().getPackageName());
                                        background.setBackgroundResource(id_background);
                                        break;
                                    case 2:
                                        id_background = getResources().getIdentifier("home_50", "drawable", getContext().getPackageName());
                                        background.setBackgroundResource(id_background);
                                        break;
                                    case 1:
                                        id_background = getResources().getIdentifier("home_25", "drawable", getContext().getPackageName());
                                        background.setBackgroundResource(id_background);
                                        break;
                                    case 0:
                                        id_background = getResources().getIdentifier("home_0", "drawable", getContext().getPackageName());
                                        background.setBackgroundResource(id_background);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        databaseReference.child("users").child(email).child("date").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str_date = (String) snapshot.getValue();
                try {
                    Date start_date = new SimpleDateFormat("yyyy-MM-dd").parse(str_date);
                    Calendar calendar2 = Calendar.getInstance();
                    calendar2.setTime(start_date);

                    long diffSec = (calendar.getTimeInMillis() - calendar2.getTimeInMillis()) / 1000;
                    long diffDays = diffSec / (24*60*60);

                    txt_startDate.setText(String.valueOf(diffDays + 1));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return viewGroup;
    }
}