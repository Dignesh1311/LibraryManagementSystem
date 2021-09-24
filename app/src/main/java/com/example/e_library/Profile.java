
package com.example.e_library;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {
   ImageView Student_Photo;
   TextView Username,Email,Phone;
   DbHelper DB;
   Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Username=findViewById(R.id.get_StudentName);
        Email=findViewById(R.id.get_Email);
        Phone=findViewById(R.id.get_Phone);

        DB=new DbHelper(this);
        Cursor cursor=DB.getData();
        if(cursor.getCount()==0)
        {
            Toast.makeText(context,"NO DATA FOUND!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext()) {

                    Username.setText(cursor.getString(0));
                    Email.setText(cursor.getString(1));
                    Phone.setText(cursor.getString(2));
                }
            }
        }
    }
