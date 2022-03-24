package com.projects.booklistingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity{
    private String book_name_input;
    private EditText input;
    private Button confirm;
    private ListView listView;

    private View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setBook_name_input(input.getText().toString().trim());
            Intent intent = new Intent(MainActivity.this,BookListingActivity.class);
            intent.putExtra("QUERY_BOOK_NAME",getBook_name_input());
            startActivity(intent);
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
        confirm.setOnClickListener(btnListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu1:
                Intent intent = new Intent(MainActivity.this,WishListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}