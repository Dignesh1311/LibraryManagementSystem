package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Book_essus_form extends AppCompatActivity implements View.OnClickListener
 {
    Button btnDatePicker, book_essu_request, cancel_request;
    EditText txtDate,bookName, Authername,name,email,phone,enrollment;
    private int mYear, mMonth, mDay;
    CheckBox AgreeSign;
    DbHelper DB;
     Context context;

     String EnrollPattern="[0-9]{15}";
     String BookPattern="[A-Za-z]{1,15}$";
     String AuthorPattern="[A-Za-z]{2,15}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_essus_form);

      txtDate = findViewById(R.id.in_date);
        bookName = findViewById(R.id.B_name);
        Authername=findViewById(R.id.Book_essus_oth);
        name=findViewById(R.id.getNameforBook);
        email=findViewById(R.id.getEmailforBook);
        phone=findViewById(R.id.getPhoneforBook);
        enrollment=findViewById(R.id.enterEnrollment);

        AgreeSign = findViewById(R.id.Agree);
        book_essu_request = findViewById(R.id.Request_essu_book);
        cancel_request = findViewById(R.id.Cancel_Request);

        DB=new DbHelper(this);
        Cursor cursor=DB.getData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                name.setText(cursor.getString(0));
                email.setText(cursor.getString(1));
                phone.setText(cursor.getString(2));
            }
        }

        cancel_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Book_essus_form.this,Book.class);
                startActivity(intent);
            }
        });
        btnDatePicker = findViewById(R.id.btn_date);


            btnDatePicker.setOnClickListener(this);


        book_essu_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book_request();
            }
        });
    }

@Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {


                    txtDate.setText(dayOfMonth + "-" + (month + 1) + "-" + year);

                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();

        }
    }


    public void Book_request()
    {
       String DATE_REQUEST=txtDate.getText().toString();
        String BOOKNAME_REQUEST=bookName.getText().toString();
        String AUTHERNAME_REQUEST=Authername.getText().toString();
        String NAME=name.getText().toString();
        String EMAIL=email.getText().toString();
        String PHONE=phone.getText().toString();
        String ENROLL=enrollment.getText().toString();



        if(DATE_REQUEST.equals("")||BOOKNAME_REQUEST.equals("")|| NAME.equals("") || EMAIL.equals("")|| PHONE.equals("")|| ENROLL.equals(""))
        {
            Toast.makeText(Book_essus_form.this,"Pless Enter the all Field",Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (ENROLL.matches(EnrollPattern))
            {
                if (BOOKNAME_REQUEST.matches(BookPattern))
                {
                    if (AgreeSign.isChecked()) {
                        if (AUTHERNAME_REQUEST.matches(AuthorPattern))
                        {
                            Boolean BookCheck = DB.BookCheck(EMAIL);
                            if (BookCheck == false) {
                                Boolean insert = DB.insertBookrquest(DATE_REQUEST, BOOKNAME_REQUEST, AUTHERNAME_REQUEST, NAME, EMAIL, PHONE, ENROLL, "Pending");
                                if (insert == true) {
                                    Toast.makeText(Book_essus_form.this, "Sucessfully book essue request", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Book_essus_form.this, Book.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(Book_essus_form.this, "Rquest failed..!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            {
                                Toast.makeText(Book_essus_form.this, "You alredy requesting for book..!", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Book_essus_form.this,Book_status.class);
                                startActivity(intent);
                                finish();
                            }


                        } else {
                            Toast.makeText(Book_essus_form.this, "Pless Enter the AuthorName, Minimum 3 And Maximum 15 Character Only", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Book_essus_form.this,"Pless first You select Agree Box",Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(Book_essus_form.this,"Pless Enter the BoOk NAme, Minimum 1 And Maximum 15 Character Only",Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(Book_essus_form.this,"Pless Enter only 15 Digit's Number",Toast.LENGTH_SHORT).show();
            }
        }

    }
}