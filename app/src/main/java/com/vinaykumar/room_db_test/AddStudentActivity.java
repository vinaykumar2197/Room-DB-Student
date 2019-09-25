package com.vinaykumar.room_db_test;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddStudentActivity extends AppCompatActivity {


    private EditText etName , etEmail , etCountry;
    private Button btAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        initCreate();


    }

    public void initCreate(){

        etName = (EditText) findViewById(R.id.et_name);
        etEmail = (EditText) findViewById(R.id.et_email);
        etCountry = (EditText) findViewById(R.id.et_country);
        btAddStudent = (Button) findViewById(R.id.bt_add_student);

        btAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateFileds();
            }
        });


    }


    public void validateFileds(){
        if(TextUtils.isEmpty(etName.getText().toString())){
            Snackbar.make(findViewById(android.R.id.content),"enter student name",Snackbar.LENGTH_LONG).show();
            return;
        }
        else if(TextUtils.isEmpty(etEmail.getText().toString())){
            Snackbar.make(findViewById(android.R.id.content),"enter email field",Snackbar.LENGTH_LONG).show();
            return;
        }
        else if(TextUtils.isEmpty(etCountry.getText().toString())) {
            Snackbar.make(findViewById(android.R.id.content),"enter country name",Snackbar.LENGTH_LONG).show();
            return;
        }
        else{
            // invoking next activity
            Intent intent = new Intent();
            intent.putExtra("name",etName.getText().toString());
            intent.putExtra("email",etEmail.getText().toString());
            intent.putExtra("country",etCountry.getText().toString());
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
