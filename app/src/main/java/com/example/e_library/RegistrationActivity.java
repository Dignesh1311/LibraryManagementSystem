package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class
RegistrationActivity extends AppCompatActivity {

    TextInputLayout Username,Email,Phone,Password,CnfPassword;
    Button  Submit_data,Already_create_account;
    DbHelper DB;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[com]+";
    String phonePattern="[0-9]{10}";
    String passwordPattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!#%*?&]{8,20}$";
    String UserPattern="[A-Za-z]{3,15}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Username=findViewById(R.id.Student_Username);
        Email=findViewById(R.id.Student_email);
        Phone=findViewById(R.id.Student_PhoneNo);
        Password=findViewById(R.id.Student_Password);
        CnfPassword=findViewById(R.id.Student_Confirm_password);

        Submit_data=findViewById(R.id.Student_Submit_Data);
        Already_create_account=findViewById(R.id.Student_alredy_account);
        DB=new DbHelper(this);

        Already_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Submit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentRegistration();
            }
        });


    }
    public void StudentRegistration()
    {
        String USERNAME=Username.getEditText().getText().toString();
        String EMAIL=Email.getEditText().getText().toString();
        String PHONE=Phone.getEditText().getText().toString();
        String PASSWORD=Password.getEditText().getText().toString();
        String CNFPASSWORD=CnfPassword.getEditText().getText().toString();

        if (USERNAME.equals("")||EMAIL.equals("")||Phone.equals("")||PASSWORD.equals(""))
        {
            Toast.makeText(RegistrationActivity.this,"Pless Enter the all Field",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (PASSWORD.equals(CNFPASSWORD))
            {
                if (USERNAME.matches(UserPattern))
                {
                    if (EMAIL.matches(emailPattern)) {
                        if (PHONE.matches(phonePattern)) {
                            if ((PASSWORD.length() >= 8) && (PASSWORD.length() <= 16)) {
                                if (PASSWORD.matches(passwordPattern)) {
                                    Boolean checkStudent = DB.chekStudentEmail(EMAIL);
                                    if (checkStudent == false) {
                                        Boolean insert = DB.insertStudentData(USERNAME, EMAIL, PHONE, PASSWORD);
                                        if (insert == true) {
                                            Toast.makeText(RegistrationActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                            startActivity(intent);

                                        } else {
                                            Toast.makeText(RegistrationActivity.this, "Registered failed", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(RegistrationActivity.this, "User Already exists ! Pless SignUP", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(RegistrationActivity.this, "Enter the MinMum 1 Uppercase,1 Lowercase,1 Number,1 special Character @$!%*#?& ", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Password length should be between 8 to 16 characters", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Enter the 10 Digits Number", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Invalid email Address! ", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this, "Enter Minimum 3 and Maximum 15 Chracter Allow", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                    Toast.makeText(RegistrationActivity.this, "Password not matching ", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
