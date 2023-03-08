package com.example.myapplication.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeResponse {

    @SerializedName("response")
    @Expose
    private List<Employee> response;

    public List<Employee> getResponse() {
        return response;
    }

    public void setResponse(List<Employee> response) {
        this.response = response;
    }

    public EmployeeResponse withResponse(List<Employee> response) {
        this.response = response;
        return this;
    }

}
