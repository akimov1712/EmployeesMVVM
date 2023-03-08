package com.example.myapplication.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myapplication.POJO.Employee;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("SELECT * FROM employee")
    LiveData<List<Employee>> getAllEmployees();

    @Insert
    void insertEmployee(List<Employee> employees);

    @Query("DELETE FROM employee")
    void deleteAllEmployees();
}
