package com.example.oceannic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<OceanDebris> debrisArrayList = new ArrayList<>();
    Context context;
    String category;

    public CategoryAdapter(ArrayList<OceanDebris> respone, Context context, String category) {
        debrisArrayList = respone;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.adapter_posts, viewGroup, false);

        return new ViewHolder(view, context, category);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        OceanDebris oceanDebris = debrisArrayList.get(position);

        holder.setItem(oceanDebris);
    }

    @Override
    public int getItemCount() {
        return debrisArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_wasteName;
        CardView cardview;
        Context context;

        String category;

        FragmentTransaction transaction;

        public ViewHolder(@NonNull View itemView, Context context, String category) {
            super(itemView);

            txt_wasteName = itemView.findViewById(R.id.txt_wasteName);
            cardview = itemView.findViewById(R.id.cardview);
            this.context = context;
            this.category = category;
        }

        public void setItem(OceanDebris oceanDebris){
            txt_wasteName.setText(oceanDebris.getName());
            System.out.println(oceanDebris.getName() + "   " + txt_wasteName.getText());

            cardview.setCardBackgroundColor(Color.TRANSPARENT);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ContentActivity.class);
                intent.putExtra("name", oceanDebris.getName());
                intent.putExtra("category", category);
                context.startActivity(intent);
            });
        }
    }
}
