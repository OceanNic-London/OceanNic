package com.example.oceannic;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;
    String email;

    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, new FragmentCategory()).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

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
        User user = new User(email);

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
    }

    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId())
            {
                case R.id.homeicon:
                    menuItem.setIcon(R.drawable.ic_icon_home_svg);    // 선택한 이미지 변경
                    menu.findItem(R.id.challengeicon).setIcon(R.drawable.ic_icon_challenge_svg);
                    menu.findItem(R.id.infoicon).setIcon(R.drawable.ic_icon_info_svg);
                    menu.findItem(R.id.myicon).setIcon(R.drawable.ic_icon_mypage_svg);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentHome()).commitAllowingStateLoss();
                    break;

                case R.id.challengeicon:
                    menuItem.setIcon(R.drawable.ic_icon_challenge_svg);    // 선택한 이미지 변경
                    menu.findItem(R.id.homeicon).setIcon(R.drawable.ic_icon_home_color);
                    menu.findItem(R.id.infoicon).setIcon(R.drawable.ic_icon_info_color);
                    menu.findItem(R.id.myicon).setIcon(R.drawable.ic_icon_mypage_color);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentChallenge()).commitAllowingStateLoss();
                    break;

                case R.id.infoicon:
                    menuItem.setIcon(R.drawable.ic_icon_info_svg);    // 선택한 이미지 변경
                    menu.findItem(R.id.homeicon).setIcon(R.drawable.ic_icon_home_color);
                    menu.findItem(R.id.challengeicon).setIcon(R.drawable.ic_icon_challenge_color);
                    menu.findItem(R.id.myicon).setIcon(R.drawable.ic_icon_mypage_color);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentCategory()).commitAllowingStateLoss();
                    break;

                case R.id.myicon:
                    menuItem.setIcon(R.drawable.ic_icon_mypage_svg);    // 선택한 이미지 변경
                    menu.findItem(R.id.homeicon).setIcon(R.drawable.ic_icon_home_color);
                    menu.findItem(R.id.challengeicon).setIcon(R.drawable.ic_icon_challenge_color);
                    menu.findItem(R.id.infoicon).setIcon(R.drawable.ic_icon_info_color);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentMypage()).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }
}