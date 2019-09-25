package com.vinaykumar.room_db_test.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    void addStudent(Student student);

    @Query("SELECT * FROM student_table")
    List<Student> getAllStudents();

    @Delete
    void  removeStudent(Student student);


}
