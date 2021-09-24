package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.e_library.Book_Adapter.context;

public class Feedback extends AppCompatActivity {

    EditText username,email,phone,writefeedback;
    RatingBar rate;
    Button submitdatafeedback;

    DbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        username=findViewById(R.id.feedback_Username);
        email=findViewById(R.id.feedback_email);
        phone=findViewById(R.id.feedback_PhoneNo);
        writefeedback=findViewById(R.id.feedback_write);

        rate=findViewById(R.id.feedback_star);
        submitdatafeedback=findViewById(R.id.Student_Submit_Data);

        submitdatafeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedback();
            }
        });

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
                username.setText(cursor.getString(0));
                email.setText(cursor.getString(1));
                phone.setText(cursor.getString(2));

            }
        }

    }
    public  void feedback()
    {
        String USERNAME=username.getText().toString();
        String EMAIL=email.getText().toString();
        String PHONE=phone.getText().toString();
        String FEEDBACK=writefeedback.getText().toString();
        float rating=rate.getRating();

        Boolean checkStudent = DB.feedbackCheck(EMAIL);
        if (checkStudent == false)
        {
            Boolean insert = DB.insertFeedbackData(USERNAME, EMAIL, PHONE, FEEDBACK, rating);
            if (insert == true) {
                Toast.makeText(Feedback.this, "Thank you...!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Feedback.this, HomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Feedback.this, "Feedback failed..!", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(Feedback.this, " You alredy give Feedback Thank you..!", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Feedback.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }



    }
}