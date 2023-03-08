package com.example.myapplication.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specialty {

    @SerializedName("specialty_id")
    @Expose
    private Integer specialtyId;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
    }

    public Specialty withSpecialtyId(Integer specialtyId) {
        this.specialtyId = specialtyId;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specialty withName(String name) {
        this.name = name;
        return this;
    }
}
