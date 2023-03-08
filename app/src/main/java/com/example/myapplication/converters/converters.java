package com.example.myapplication.converters;

import androidx.room.TypeConverter;

import com.example.myapplication.POJO.Specialty;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class converters {

    @TypeConverter
    public String listSpecialtyToString(List<Specialty> specialties){
        return new Gson().toJson(specialties);
    }

    @TypeConverter
    public List<Specialty> StringToListSpecialty(String specialtiesAsString){
        Gson gson = new Gson();
        ArrayList objects = gson.fromJson(specialtiesAsString, ArrayList.class);
        ArrayList<Specialty> specialties = new ArrayList<>();
        for (Object o: objects){
            specialties.add(gson.fromJson(o.toString(), Specialty.class));
        }
        return specialties;
    }

}
