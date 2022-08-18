package com.example.oceannic;

public class OceanDebris {
    private String name;
    private String description;
    private String years;
    private String synthetic_resin;

    public OceanDebris() {}

    public OceanDebris(String name, String description, String years, String synthetic_resin) {
        this.name = name;
        this.description = description;
        this.years = years;
        this.synthetic_resin = synthetic_resin;
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

    public String getSynthetic_resin() {
        return synthetic_resin;
    }

    public void setSynthetic_resin(String synthetic_resin) {
        this.synthetic_resin = synthetic_resin;
    }
}
