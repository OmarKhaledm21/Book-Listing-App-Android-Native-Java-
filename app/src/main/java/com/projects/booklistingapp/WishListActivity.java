package com.projects.booklistingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class WishListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);


        ListView listView = (ListView) findViewById(R.id.list);


        ArrayList<Book> books = null;

        if(books!= null && books.size()>0) {
            BooksAdapter booksAdapter = new BooksAdapter(WishListActivity.this, 0, books);
            listView.setAdapter(booksAdapter);
        }else{
            TextView textView = (TextView) findViewById(R.id.empty_view);
            textView.setText("Empty Wish List!");
        }
    }


}