package com.example.oceannic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class FragmentCategory extends Fragment {

    ViewGroup viewGroup;
    FrameLayout layout_1, layout_2, layout_3, layout_4;
    FragmentTransaction transaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_category,container,false);

        layout_1 = viewGroup.findViewById(R.id.layout_1);
        layout_2 = viewGroup.findViewById(R.id.layout_2);
        layout_3 = viewGroup.findViewById(R.id.layout_3);
        layout_4 = viewGroup.findViewById(R.id.layout_4);

        transaction = getActivity().getSupportFragmentManager().beginTransaction();

        layout_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.fragment_container, new FragmentInfo("plastic")).commit();
            }
        });

        layout_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.fragment_container, new FragmentInfo("paper")).commit();
            }
        });

        layout_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.fragment_container, new FragmentInfo("clothes")).commit();
            }
        });

        layout_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaction.replace(R.id.fragment_container, new FragmentInfo("etc")).commit();
            }
        });

        return viewGroup;
    }
}