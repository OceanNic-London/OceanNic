package com.example.oceannic;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    String email;
    boolean chk_user = true;

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, new FragmentHome()).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

        bottomNavigationView.setItemIconTintList(null);
        menu=bottomNavigationView.getMenu();

        // 로그인 유저 저장
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            email = user.getEmail();
            email = email.replace(".", "");
            System.out.println(email);
            addUser(email);
        }

    }

    public void addUser(String email){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String getTime = sdf.format(date);

        User user = new User(email, getTime);

        mDatabase.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
                String key = snapshot.child(email).getKey();
                System.out.println(key);

                if(email.equals(key)) chk_user = false;

                if(snapshot.getKey() != null && chk_user){
                    mDatabase.child("users").child(email).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "유저 저장", Toast.LENGTH_SHORT).show();
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(), "유저 저장 실패", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId())
            {
                case R.id.homeicon:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commitAllowingStateLoss();
                    break;

                case R.id.challengeicon:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentChallenge()).commitAllowingStateLoss();
                    break;

                case R.id.infoicon:

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCategory()).commitAllowingStateLoss();
                    break;

                case R.id.myicon:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMypage()).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}