package com.projects.booklistingapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="MyWishlist.db";
    private static final String TABLE_NAME = "BooksWishList";
    private static final String KEY_ID = "id";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE "+TABLE_NAME+"(" +
                KEY_ID +" VARCHAR(40) PRIMARY KEY);";
        sqLiteDatabase.execSQL(CREATE_ITEMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BooksWishList");
        onCreate(sqLiteDatabase);
    }

    public boolean addItem(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.v("BOOK_ID",book.getId()+"JJ");
        contentValues.put(KEY_ID,book.getId());


        db.insert(TABLE_NAME,null,contentValues);
        db.close();
        return true;
    }

    public boolean removeItem(Book book) {
        return false;
    }

    public boolean editItem(Book oldBook, Book newBook) {
        return false;
    }

    public ArrayList<String> getBooks() {
        ArrayList<String> booksList = new ArrayList<>();
        String query = "SELECT id FROM BooksWishList;";
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()){
                do{
                    String bookId = cursor.getString(0).trim();
                    Log.v("SQQ","HI "+bookId);
                    booksList.add(bookId);
                }while (cursor.moveToNext());
            }
            return booksList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
