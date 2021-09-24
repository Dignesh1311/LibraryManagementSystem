package com.example.e_library;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class Book_pdf_view extends AppCompatActivity {
    PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_pdf_view);

        pdfView=findViewById(R.id.pdfView);
        int book_position=getIntent().getIntExtra("key_position",0);
        if (book_position==0) {
            pdfView.fromAsset("Python.pdf").load();
        }
        else if(book_position==1)
        {
            pdfView.fromAsset("Android.pdf").load();
        }
        else if(book_position==2)
        {
            pdfView.fromAsset("BlockchainCh1.pdf").load();
        }
        else if(book_position==3)
        {
            pdfView.fromAsset("Computer_Network.pdf").load();
        }
        else if(book_position==4)
        {
            pdfView.fromAsset("Ethicle_Hacking.pdf").load();
        }
        else if(book_position==5)
        {
            pdfView.fromAsset("SE_UML.pdf").load();
        }
    }
}