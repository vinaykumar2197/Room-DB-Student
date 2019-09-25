package com.vinaykumar.room_db_test.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Student.class},version = 1)
public abstract class StudentAppDatabase extends RoomDatabase {

    public abstract StudentDAO studentDAO();

}
