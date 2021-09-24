package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class ContactUs extends AppCompatActivity {
   // GridView gridView;
    int image[]={R.drawable.call, R.drawable.facebook, R.drawable.instagram, R.drawable.emailll};
  //  public static String image[] = {"PROFILE", "BOOK ", "CONTACT US", "FEEDBACK"};
    GridView gridView;
    String [] name={"Call","Facebook","Instagram","Email"};
    //int [] image={R.drawable.call,R.drawable.facebook,R.drawable.instagram,R.drawable.gmail};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        gridView = findViewById(R.id.contact_grid);
        custome_contact contactUsAdapter = new custome_contact(ContactUs.this,image,name);
        gridView.setAdapter(contactUsAdapter);
    }
}
