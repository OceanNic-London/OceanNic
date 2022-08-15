package com.example.oceannic;

public class OceanDebris {
    private String name;
    private String description;
    private String years;

    public OceanDebris() {}

    public OceanDebris(String name, String description, String years) {
        this.name = name;
        this.description = description;
        this.years = years;
    }

    public OceanDebris(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }
}
