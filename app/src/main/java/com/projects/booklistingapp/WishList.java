package com.projects.booklistingapp;

import android.util.Log;

import java.util.ArrayList;

public class WishList {
    private final ArrayList<Book> books_list;

    public WishList() {
        books_list  = new ArrayList<>();
    }

    public WishList(ArrayList<Book> books_list) {
        this.books_list = books_list;
    }

    public ArrayList<Book> getBooks_list() {
        return books_list;
    }

    public void addBook(Book book){
        books_list.add(book);
    }

    public void removeBook(Book book){
        books_list.remove(book);
    }
}
