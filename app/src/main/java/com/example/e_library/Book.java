package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Book extends AppCompatActivity {
    RecyclerView Book_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
         Book_detail=findViewById(R.id.BookRCv);
         Book_book[] books=new Book_book[5];

        books[0]=new Book_book(R.drawable.python,"Python","Saikat Dutt");
        books[1]=new Book_book(R.drawable.blockchain,"Blockchain","Bikramaditya");
        books[2]=new Book_book(R.drawable.fon,"FON","Bhushan Trivedi");
        books[3]=new Book_book(R.drawable.uml,"SE_UML","Rajib Mall");
        books[4]=new Book_book(R.drawable.android,"Android","Michael Burton");

        Book_Adapter book_adapter=new Book_Adapter();
        book_adapter.setItems(books);
        Book_detail.setAdapter(book_adapter);


    }
}