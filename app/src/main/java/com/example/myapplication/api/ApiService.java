package com.example.myapplication.api;

import com.example.myapplication.POJO.Employee;
import com.example.myapplication.POJO.EmployeeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees();

}
