package com.projects.booklistingapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class BookListingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private ListView listView;
    private ProgressBar progressBar;
    private TextView textView;

    private String query_book;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_listing_activity);

        String bookName = getIntent().getExtras().getString("QUERY_BOOK_NAME");
        this.setQuery_book(bookName);

        listView = (ListView) findViewById(R.id.list);
        progressBar = (ProgressBar) findViewById(R.id.pb);
        textView = (TextView) findViewById(R.id.empty_view);

        startLoadingBooks();
    }

    public void startLoadingBooks() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            getSupportLoaderManager().initLoader(0, null, this).forceLoad();
        }
    }

    public void updateUI(List<Book> bookList) {
        progressBar.setVisibility(View.GONE);
        BooksAdapter booksAdapter = new BooksAdapter(BookListingActivity.this, 0, bookList);
        listView.setAdapter(booksAdapter);
    }

    @NonNull
    @Override
    public Loader<List<Book>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.v("ERRERR","INVOKED LOADER");
        return new BooksAsyncTask(this, getQuery_book());
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Book>> loader, List<Book> data) {
        if (data != null && data.size() >= 1) {
            updateUI(data);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Book>> loader) { }

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

    public String getQuery_book() {
        return query_book;
    }

    public void setQuery_book(String query_book) {
        this.query_book = query_book;
    }
}
