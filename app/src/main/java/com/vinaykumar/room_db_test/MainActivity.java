package com.vinaykumar.room_db_test;

import android.app.Activity;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.vinaykumar.room_db_test.db.Student;
import com.vinaykumar.room_db_test.db.StudentAppDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    private static int REQUEST_CODE = 2001;

    StudentAppDatabase studentAppDatabase;

    ArrayList<Student> studentArrayList;
    StudentAdapter studentAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_student);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fb_add_student);

        studentAppDatabase = Room.databaseBuilder(getApplicationContext(),StudentAppDatabase.class,"StudentDB")
                .build();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddStudentActivity.class);
                startActivityForResult(intent,REQUEST_CODE);

            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        studentAdapter = new StudentAdapter();
        recyclerView.setAdapter(studentAdapter);

        loadData();



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                Student student = studentArrayList.get(viewHolder.getAdapterPosition());
                new DeleteStudent().execute(student);
            }
        }).attachToRecyclerView(recyclerView);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
                String currentDate = simpleDateFormat.format(new Date());

                String name = data.getStringExtra("name");
                String email = data.getStringExtra("email");
                String country = data.getStringExtra("country");

                Student student = new Student();
                student.setName(name);
                student.setEmail(email);
                student.setCountry(country);
                student.setCreatedDate(currentDate);

                addNewStudent(student);

        }

    }


    public void addNewStudent(Student student){
        new AddNewStudentAsyncTask().execute(student);
    }


    private class AddNewStudentAsyncTask extends AsyncTask<Student,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentAppDatabase.studentDAO().addStudent(students[0]);
            return null;
           // return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }
    }

    public void loadData(){
        new LoadStudentAsyncTask().execute();
    }


    private class LoadStudentAsyncTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            studentArrayList = (ArrayList<Student>)studentAppDatabase.studentDAO().getAllStudents();
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            studentAdapter.setStudents(studentArrayList);

        }


    }


    private class DeleteStudent extends AsyncTask<Student,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentAppDatabase.studentDAO().removeStudent(students[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            loadData();
        }


    }


//    RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, d MMM yyyy");
//            String currentDate = simpleDateFormat.format(new Date());
//
//            String name1 = "name1";
//            String email1 = "email1";
//            String country1 = "country1";
//
//            Student student = new Student();
//            student.setName(name1);
//            student.setEmail(email1);
//            student.setCountry(country1);
//            student.setCreatedDate(currentDate);
//
//            addNewStudent(student);
//
//
//
//
//            String name2 = "name1";
//            String email2 = "email1";
//            String country2 = "country1";
//
//            Student student2 = new Student();
//            student2.setName(name2);
//            student2.setEmail(email2);
//            student2.setCountry(country2);
//            student2.setCreatedDate(currentDate);
//
//            addNewStudent(student2);
//
//        }
//
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//        }
//    };

}
