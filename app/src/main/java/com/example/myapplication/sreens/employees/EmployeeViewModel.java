package com.example.myapplication.sreens.employees;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.POJO.Employee;
import com.example.myapplication.POJO.EmployeeResponse;
import com.example.myapplication.api.ApiFactory;
import com.example.myapplication.api.ApiService;
import com.example.myapplication.data.AppDatabase;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeViewModel extends AndroidViewModel {

    private static AppDatabase database;
    private LiveData<List<Employee>> employees;
    private CompositeDisposable compositeDisposable;
    private MutableLiveData<Throwable> errors;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        database = AppDatabase.getInstance(application);
        employees = database.employeeDao().getAllEmployees();
        compositeDisposable = new CompositeDisposable();
        errors = new MutableLiveData<>();
    }

    public void clearError(){
        errors.setValue(null);
    }

    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    public void insertEmployee(List<Employee> employees){
        new InsertEmployeeTask().execute(employees);
    }

    private static class InsertEmployeeTask extends AsyncTask<List<Employee>, Void, Void>{

        @Override
        protected Void doInBackground(List<Employee>... lists) {
            if (lists != null && lists.length > 0){
                database.employeeDao().insertEmployee(lists[0]);
            }
            return null;
        }
    }

    public void deleteAllEmployees(){
        new DeleteAllEmployeeTask().execute();
    }

    private static class DeleteAllEmployeeTask extends AsyncTask<Void, Void, Void>{


        @Override
        protected Void doInBackground(Void... voids) {
            database.employeeDao().deleteAllEmployees();
            return null;
        }
    }

    public void loadData(){
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        Disposable disposable = apiService.getEmployees()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EmployeeResponse>() {
                    @Override
                    public void accept(EmployeeResponse employeeResponse) throws Exception {
                        deleteAllEmployees();
                        insertEmployee(employeeResponse.getResponse());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        errors.setValue(throwable);
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
