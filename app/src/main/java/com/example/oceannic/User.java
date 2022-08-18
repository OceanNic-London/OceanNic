package com.example.oceannic;

public class User {
    private String email;
    private String date;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User(String email, String date) {
        this.email = email;
        this.date = date;
    }

    @Override
    public String toString() {
        return "users{" +
                "email='" + email + '\'' +
                "start_date='" + date + '\'' +
                ", topic" +
                "{" +
                    "Zero Waste{}," +
                    "Save Water{}," +
                    "Recycle{}" +
                "}" +
                '}';
    }
}
