package com.example.oceannic;

import static android.graphics.ImageDecoder.*;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentMypage extends Fragment {

    ViewGroup viewGroup;
    TextView txt_name, txt_email, txt_logout;
    CircleImageView img_profile;

    String name, email;
    Uri photoUri;

    private FirebaseAuth firebaseAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_mypage, container, false);

        txt_name = viewGroup.findViewById(R.id.txt_username);
        txt_email = viewGroup.findViewById(R.id.txt_useremail);
        img_profile = viewGroup.findViewById(R.id.txt_userphoto);
        txt_logout = viewGroup.findViewById(R.id.txt_logout);

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            name = user.getDisplayName();
            email = user.getEmail();
            photoUri = user.getPhotoUrl();

            txt_name.setText(name);
            txt_email.setText(email);
            img_profile.setImageURI(photoUri);
        }

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