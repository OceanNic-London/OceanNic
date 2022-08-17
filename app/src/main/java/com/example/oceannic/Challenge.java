package com.example.oceannic;

public class Challenge {
    private String topic;
    private String challenge_name;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getChallenge_name() {
        return challenge_name;
    }

    public void setChallenge_name(String challenge_name) {
        this.challenge_name = challenge_name;
    }

    public Challenge() {
    }

    public Challenge(String challenge_name) {
        this.challenge_name = challenge_name;
    }

    public Challenge(String topic, String challenge_name) {
        this.topic = topic;
        this.challenge_name = challenge_name;
    }
}
