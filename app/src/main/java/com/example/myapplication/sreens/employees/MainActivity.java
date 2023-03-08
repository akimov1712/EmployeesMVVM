package com.example.myapplication.sreens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.POJO.Employee;
import com.example.myapplication.POJO.EmployeeResponse;
import com.example.myapplication.R;
import com.example.myapplication.adapters.EmployeesAdapter;
import com.example.myapplication.api.ApiFactory;
import com.example.myapplication.api.ApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public static EmployeesAdapter adapter;
    private EmployeeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new EmployeesAdapter();
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
            }
        });
        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null){
                    Toast.makeText(MainActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    viewModel.clearError();
                }
            }
        });
        viewModel.loadData();

    }

}