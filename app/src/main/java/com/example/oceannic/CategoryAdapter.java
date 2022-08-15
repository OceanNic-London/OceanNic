package com.example.oceannic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends BaseAdapter {

    ArrayList<OceanDebris> debrisArrayList = new ArrayList<>();

    TextView txt_category;

    public CategoryAdapter(ArrayList<OceanDebris> respone) {
        debrisArrayList = respone;

    }

    @Override
    public int getCount() {
        return debrisArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return debrisArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Context context = parent.getContext();

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.adapter_posts, parent, false);
        }

        txt_category = convertView.findViewById(R.id.txt_wasteName);
        txt_category.setText(debrisArrayList.get(position).getName());

        return convertView;
    }
}
