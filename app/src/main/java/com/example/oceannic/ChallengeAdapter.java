package com.example.oceannic;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChallengeAdapter extends RecyclerView.Adapter<ChallengeAdapter.ViewHolder> {

    ArrayList<String> topicArrayList = new ArrayList<>();
    Context context;

    public ChallengeAdapter(ArrayList<String> respone, Context context) {
        topicArrayList = respone;
        this.context = context;
    }


    @NonNull
    @Override
    public ChallengeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.adapter_challenge_btn, viewGroup, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeAdapter.ViewHolder holder, int position) {
        String topic = topicArrayList.get(position);

        holder.setItem(topic);
    }

    @Override
    public int getItemCount() {
        return topicArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txt_topic;
        CardView cardView;
        Context context;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            txt_topic = itemView.findViewById(R.id.txt_topic);
            cardView = itemView.findViewById(R.id.cardview);
            this.context = context;
        }

        public void setItem(String topic){
            txt_topic.setText(topic);

            cardView.setCardBackgroundColor(Color.TRANSPARENT);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, ChallengDetailActivity.class);
                intent.putExtra("topic", topic);
                context.startActivity(intent);
            });

        }
    }
}
