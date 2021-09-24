package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout Email,password;
    Button forget_password,submit,user_new_accout;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email=findViewById(R.id.Email_Student);
        password=findViewById(R.id.Password_Student_login);
        //forget_password=findViewById(R.id.Student_forgot_password);
        submit=findViewById(R.id.Submit_Student_Data);
        user_new_accout=findViewById(R.id.creat_new_Accout);
        DB=new DbHelper(this);

        user_new_accout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentLogin();
            }
        });
    }
    public void StudentLogin()
    {
        String EMAIL=Email.getEditText().getText().toString();
        String PASSWORD=password.getEditText().getText().toString();
        if (EMAIL.equals("")||PASSWORD.equals(""))
        {
            Toast.makeText(LoginActivity.this,"Pless Enter the all field",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Boolean checkStudentPassword=DB.checkStudentEmailPassword(EMAIL,PASSWORD);
            if (checkStudentPassword==true)
            {
                Toast.makeText(LoginActivity.this,"Signin Sucsessfully ",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);

            }
            else
            {
                Toast.makeText(LoginActivity.this,"Invalid Cradential",Toast.LENGTH_SHORT).show();
            }

        }
    }
}