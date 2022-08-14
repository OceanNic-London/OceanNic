package com.example.oceannic;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class FragmentCategory extends Fragment {

    ViewGroup viewGroup;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_category,container,false);

        Intent intent = new Intent(getContext(), ActivityInfo.class);

        switch (viewGroup.getId()){
            case R.id.layout_1:
                intent.putExtra("category", "plastic");
                startActivity(intent);
                break;
            case R.id.layout_2:
                intent.putExtra("category", "paper");
                startActivity(intent);
                break;
            case R.id.layout_3:
                intent.putExtra("category", "clothes");
                startActivity(intent);
                break;
            case R.id.layout_4:
                intent.putExtra("category", "etc");
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + viewGroup.getId());
        }

        return viewGroup;
    }
}