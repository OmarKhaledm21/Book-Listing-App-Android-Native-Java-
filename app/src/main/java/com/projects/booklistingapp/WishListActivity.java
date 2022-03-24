package com.projects.booklistingapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class WishListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    ListView listView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);

        listView = (ListView) findViewById(R.id.list);
        textView = (TextView) findViewById(R.id.empty_view);
        startLoadingBooks();
    }

    public void startLoadingBooks() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.v("NOW_LOADING_WISH_LIST","NN");
            getSupportLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            textView.setText(R.string.no_internet_connection);
        }
    }

    public void updateList(ArrayList<Book> books) {
        Log.v("NOW_LOADING_WISH_LIST","Nb");
        if (books != null && books.size() > 0) {
            BooksAdapter booksAdapter = new BooksAdapter(WishListActivity.this, 0, books);
            Log.v("NOW_LOADING_WISH_LIST","Na");
            listView.setAdapter(booksAdapter);
        } else {
            TextView textView = (TextView) findViewById(R.id.empty_view);
            textView.setText("Empty Wish List!");
        }
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.v("NOW_LOADING_WISH_LIST","Nc");
        return new WishListAsyncTask(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {
        if (data != null && data.size() > 0) {
            updateList((ArrayList<Book>) data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
    }
}

class WishListAsyncTask extends AsyncTaskLoader<List<Book>> {
    public WishListAsyncTask(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Nullable
    @Override
    public List<Book> loadInBackground() {
        DBHelper db = new DBHelper(getContext());
        ArrayList<String> bookIds = db.getBooks();
        Log.v("NOW_LOADING_WISH_LIST","NNsize"+bookIds.size());
        ArrayList<String> jsonResponses = new ArrayList<>();
        for (int i = 0; i < bookIds.size(); i++) {
            URL url = QueryUtils.getBookURL(bookIds.get(i));
            try {
                jsonResponses.add(QueryUtils.makeHttpRequest(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return QueryUtils.parseJsonResponse(jsonResponses);
    }
}