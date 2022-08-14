package com.example.oceannic;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    FragmentCategory categoryFragment;

    BottomNavigationView bottomNavigationView;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryFragment = new FragmentCategory();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,categoryFragment).commitAllowingStateLoss();

        bottomNavigationView=findViewById(R.id.navigation);
        menu=bottomNavigationView.getMenu();

        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        bottomNavigationView.setSelectedItemId(R.id.homeicon);  //선택된 아이템 지정
    }// onCreate()..

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
                    break;

                case R.id.challengeicon:
                    menuItem.setIcon(R.drawable.ic_icon_challenge_svg);    // 선택한 이미지 변경
                    menu.findItem(R.id.homeicon).setIcon(R.drawable.ic_icon_home_color);
                    menu.findItem(R.id.infoicon).setIcon(R.drawable.ic_icon_info_color);
                    menu.findItem(R.id.myicon).setIcon(R.drawable.ic_icon_mypage_color);
                    break;

                case R.id.infoicon:
                    menuItem.setIcon(R.drawable.ic_icon_info_svg);    // 선택한 이미지 변경
                    menu.findItem(R.id.homeicon).setIcon(R.drawable.ic_icon_home_color);
                    menu.findItem(R.id.challengeicon).setIcon(R.drawable.ic_icon_challenge_color);
                    menu.findItem(R.id.myicon).setIcon(R.drawable.ic_icon_mypage_color);

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, categoryFragment).commitAllowingStateLoss();
                    break;

                case R.id.myicon:
                    menuItem.setIcon(R.drawable.ic_icon_mypage_svg);    // 선택한 이미지 변경
                    menu.findItem(R.id.homeicon).setIcon(R.drawable.ic_icon_home_color);
                    menu.findItem(R.id.challengeicon).setIcon(R.drawable.ic_icon_challenge_color);
                    menu.findItem(R.id.infoicon).setIcon(R.drawable.ic_icon_info_color);
                    break;
            }// switch()..
            return true;
        }
    }// ItemSelectedListener class..
}// MainActivity class..