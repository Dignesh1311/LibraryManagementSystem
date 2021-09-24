package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Book_status extends AppCompatActivity {
    TextView date,b_name,b_author,b_stataus,enrollmentNo;
    DbHelper db;
    Context context;
    BookStatusDisplay[] display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_status);


        date=findViewById(R.id.Book_Date_Status);
        b_name=findViewById(R.id.Bookname_Status);
        b_author=findViewById(R.id.BookAuthor_Status);
        b_stataus=findViewById(R.id.Book_Status);
        enrollmentNo=findViewById(R.id.enrollmentNo_Status);
        db=new DbHelper(this);

            display=db.displayBookStatus();
            if (display==null)
            {
                Toast.makeText(context,"No record Found",Toast.LENGTH_LONG).show();
            }
            else
            {
                for (BookStatusDisplay dis:display)
                {
                    date.setText(dis.getDate());
                    b_name.setText(dis.getBook());
                    b_author.setText(dis.getAuthor());
                    enrollmentNo.setText(dis.getEnroll());
                    b_stataus.setText(dis.getStatus());

                }
            }

    }
}