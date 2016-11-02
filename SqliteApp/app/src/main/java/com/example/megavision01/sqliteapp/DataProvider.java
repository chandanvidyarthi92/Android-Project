package com.example.megavision01.sqliteapp;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by MegaVision01 on 10/6/2016.
 */
public class DataProvider {
    private String id;
    private String name;
    private String username;
    private String password;
    private String contact;
    private String dob;
    private String qualification;
    private String gender;
    private String hobby;


    public DataProvider(String id,String name,String username,String password,String contact,String dob,String qualification,String gender,String hobby)
    {
        this.id=id;

        this.name=name;
        this.username=username;
        this.password=password;
        this.contact=contact;
        this.dob=dob;
        this.qualification=qualification;
        this.gender=gender;
        this.hobby=hobby;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
