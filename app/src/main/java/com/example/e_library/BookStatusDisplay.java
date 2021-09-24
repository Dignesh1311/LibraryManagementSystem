package com.example.e_library;

import android.widget.TextView;

public class BookStatusDisplay {
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBook() {
        return Book;
    }

    public void setBook(String book) {
        Book = book;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEnroll() {
        return enroll;
    }

    public void setEnroll(String enroll) {
        this.enroll = enroll;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String date;
    String Book;
    String author;
    String enroll;
    String status;

    public BookStatusDisplay() {
        this.date = date;
        this.Book = Book;
        this.author = author;
        this.enroll = enroll;
        this.status = status;
    }
}