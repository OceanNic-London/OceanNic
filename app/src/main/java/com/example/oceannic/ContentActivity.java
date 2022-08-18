package com.example.oceannic;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ContentActivity extends AppCompatActivity {

    ImageButton btn_back;
    TextView txt_name, txt_description, txt_years, txt_synthetic_resin;
    ImageView img_trash;

    String category, name;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        category = getIntent().getStringExtra("category");
        name = getIntent().getStringExtra("name");

        btn_back = findViewById(R.id.btn_back);
        txt_name = findViewById(R.id.txt_name);
        txt_description = findViewById(R.id.txt_description);
        txt_years = findViewById(R.id.txt_years);
        txt_synthetic_resin = findViewById(R.id.txt_synthetic_resin);
        img_trash = findViewById(R.id.img_trash);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txt_name.setText(name);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("debris").child(category).child("category").child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot + "   " + snapshot.getKey());
                if(snapshot.getValue(OceanDebris.class) != null){
                    OceanDebris oceanDebris = snapshot.getValue(OceanDebris.class);

                    String description = oceanDebris.getDescription();
                    String years = oceanDebris.getYears();
                    String synthetic_resin = oceanDebris.getSynthetic_resin();

                    System.out.println("s : " + synthetic_resin);

                    txt_description.setText(description);
                    txt_years.setText(years + " YEARS");
                    txt_synthetic_resin.setText(synthetic_resin);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        String file_name = category + "_" + String.valueOf(txt_name.getText()).replaceAll("\\s+","");
        file_name = file_name.replaceAll(",", "");
        int id = getApplicationContext().getResources().getIdentifier("ic_" + file_name, "drawable", getApplicationContext().getPackageName());
        img_trash.setImageResource(id);

    }
}
