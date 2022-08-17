package com.example.oceannic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChallengeDatailAdapter extends RecyclerView.Adapter<ChallengeDatailAdapter.ViewHolder> {

    ArrayList<Challenge> arrayList = new ArrayList<>();

    public ChallengeDatailAdapter(ArrayList<Challenge> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ChallengeDatailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.adapter_challenge_checkbox, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChallengeDatailAdapter.ViewHolder holder, int position) {
        Challenge challenge = arrayList.get(position);

        holder.setItem(challenge);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardview;
        TextView txt_challenge1, txt_challenge2;
        CheckBox chk_1, chk_2;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);

            txt_challenge1 = itemView.findViewById(R.id.txt_challenge1);
            chk_1 = itemView.findViewById(R.id.chk_1);
            cardview = itemView.findViewById(R.id.cardview);
        }

        public void setItem(Challenge challenge){
            txt_challenge1.setText(challenge.getChallenge_name());

            cardview.setCardBackgroundColor(Color.TRANSPARENT);
        }
    }
}
