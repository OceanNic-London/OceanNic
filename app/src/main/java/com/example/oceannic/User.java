package com.example.oceannic;

public class User {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "users{" +
                "email='" + email + '\'' +
                ", topic" +
                "{" +
                    "Zero Waste{}," +
                    "Save Water{}," +
                    "Recycle{}" +
                "}" +
                '}';
    }
}
