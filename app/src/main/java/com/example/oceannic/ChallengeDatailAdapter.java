package com.example.oceannic;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        TextView txt_challenge1;
        CheckBox chk_1;

        private DatabaseReference databaseReference;

        String user_email;

        public ViewHolder(@NonNull View itemView ) {
            super(itemView);

            txt_challenge1 = itemView.findViewById(R.id.txt_challenge1);
            chk_1 = itemView.findViewById(R.id.chk_1);
//            chk_1.setButtonDrawable(R.drawable.round_challenge_checkbox_blue);
            cardview = itemView.findViewById(R.id.cardview);

            databaseReference = FirebaseDatabase.getInstance().getReference();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            user_email = user.getEmail().replace(".", "");
        }

        public void setItem(Challenge challenge){
            txt_challenge1.setText(challenge.getChallenge_name());

            databaseReference.child("users").child(user_email).child("topic").child(challenge.getTopic()).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    System.out.println(snapshot.getKey());
                    if(challenge.getChallenge_name().equals(snapshot.getKey())){
                        System.out.println("chk " );
                        chk_1.setChecked(true);
                    }
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            chk_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if(chk_1.isChecked()){
                        chk_1.setChecked(true);
                        databaseReference.child("users").child(user_email).child("topic").child(challenge.getTopic()).child((String) txt_challenge1.getText()).setValue(txt_challenge1.getText());
                    }else{
                        chk_1.setChecked(false);
                        databaseReference.child("users").child(user_email).child("topic").child(challenge.getTopic()).child((String) txt_challenge1.getText()).removeValue();
                    }
                }
            });
        }
    }
}
