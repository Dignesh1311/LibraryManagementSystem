package com.example.e_library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    ImageView profile,books,contact,feedback;
    GridView gridView;
    Context context;
    ArrayList arrayList;
    Toolbar toolbar;

    public static int[] gridViewImages = {R.drawable.profile1, R.drawable.books,R.drawable.books,R.drawable.books, R.drawable.contact, R.drawable.feedback};
    public static String[] gridViewStrings = {"PROFILE", "BOOK ","BOOK_READ","BOOK_STATUS","CONTACT US", "FEEDBACK"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gridView=findViewById(R.id.grid_view);
        gridView.setAdapter(new CustomAndroidGridViewAdapter(this,gridViewImages,gridViewStrings));

        initInstances();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
       /* if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ProfileFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_message);
        }*/

    }

    private void initInstances()
    {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
         toolbar= (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("E-Library");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_Home:
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.nav_share:
                Intent intentInvite = new Intent(Intent.ACTION_SEND);
                intentInvite.setType("text/plain");
                String body = "Link to your app";
                String subject = "Your Subject";
                intentInvite.putExtra(Intent.EXTRA_SUBJECT, subject);
                intentInvite.putExtra(Intent.EXTRA_TEXT, body);
                startActivity(Intent.createChooser(intentInvite, "Share using"));
                break;
            case R.id.nav_Logout:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);

                builder.setTitle("Library Management");
                builder.setMessage("Are You Want To Exit This App");
                builder.setIcon(R.drawable.icon);


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(HomeActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }
                });
                builder.show();
                break;
            default:
                return onOptionsItemSelected(item);


        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}