package com.example.e_library;

public class Book_book {
        int logo;
        String book_name;
        String book_Author;

    public Book_book(int logo, String book_name, String book_Author) {
        this.logo = logo;
        this.book_name = book_name;
        this.book_Author = book_Author;
    }


    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_Author() {
        return book_Author;
    }

    public void setBook_Author(String book_Author) {
        this.book_Author = book_Author;
    }




}
