package com.vinaykumar.room_db_test.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "student_table")
public class Student {

    @PrimaryKey(autoGenerate = true)
    private int id ;

    @ColumnInfo(name = "student_name")
    private String name ;

    @ColumnInfo(name = "student_country")
    private String country;

    @ColumnInfo(name = "student_email")
    private String email ;

    @ColumnInfo(name = "created_date")
    private String createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Ignore
    public Student() {

    }

    public Student(int id, String name, String country, String email,String createdDate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.email = email;
        this.createdDate = createdDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }


}
