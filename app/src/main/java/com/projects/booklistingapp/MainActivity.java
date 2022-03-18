package com.projects.booklistingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private String book_name_input;
    private EditText input;
    private Button confirm;
    private ProgressBar progressBar;
    private ListView listView;
    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            progressBar.setVisibility(View.VISIBLE);
            setBook_name_input(input.getText().toString().trim());
            startLoadingBooks();
            input.setVisibility(View.GONE);
            confirm.setVisibility(View.GONE);
        }
    };

    public void setBook_name_input(String book_name_input) {
        this.book_name_input = book_name_input;
    }

    public String getBook_name_input() {
        return book_name_input;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.book_input);
        confirm = (Button) findViewById(R.id.confirmBtn);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        progressBar.setVisibility(View.GONE);
        confirm.setOnClickListener(btnListener);
    }

    public void startLoadingBooks() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            getSupportLoaderManager().initLoader(0, null, this).forceLoad();

        }
    }


    public void updateUI(List<Book> bookList) {
        progressBar.setVisibility(View.GONE);
        listView = (ListView) findViewById(R.id.list);
        BooksAdapter booksAdapter = new BooksAdapter(MainActivity.this, 0, bookList);
        listView.setAdapter(booksAdapter);
    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        return new BooksAsyncTask(this, getBook_name_input());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> data) {
        if (data != null && data.size() >= 1) {
            updateUI(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) {
        loader = null;
    }

    @Override
    public void onBackPressed() {
        Activity act = this;
        Intent intent = new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();
    }

    static class BooksAsyncTask extends AsyncTaskLoader<List<Book>> {
        private String book_name;

        public BooksAsyncTask(@NonNull Context context, String book_name) {
            super(context);
            this.book_name = book_name;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }

        @Nullable
        @Override
        public List<Book> loadInBackground() {
            if (book_name == null || book_name.equals("")) {
                return null;
            }
            URL url = QueryUtils.getURL(this.book_name);
            String jsonResponse = null;
            try {
                jsonResponse = QueryUtils.makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return QueryUtils.parseJsonResponse(jsonResponse);
        }
    }
}