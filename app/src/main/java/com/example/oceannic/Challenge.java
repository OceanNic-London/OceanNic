package com.example.oceannic;

import java.util.ArrayList;

public class Challenge {
    private String topic;
    private ArrayList<String> challenge;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<String> getChallenge(int position) {
        return challenge;
    }

    public void setChallenge(ArrayList<String> challenge) {
        this.challenge = challenge;
    }

    public Challenge(String topic, ArrayList<String> challenge) {
        this.topic = topic;
        this.challenge = challenge;
    }
}
