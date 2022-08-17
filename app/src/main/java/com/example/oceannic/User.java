package com.example.oceannic;

public class User {
    private String email;
    private String topic;
    private String checked_challenge;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getChecked_challenge() {
        return checked_challenge;
    }

    public void setChecked_challenge(String checked_challenge) {
        this.checked_challenge = checked_challenge;
    }

    public User(String email, String topic, String checked_challenge) {
        this.email = email;
        this.topic = topic;
        this.checked_challenge = checked_challenge;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", topic='" + topic +
                "{" +
                    "checked_challenge='" + checked_challenge + '\'' +
                "}" +
                '}';
    }
}
