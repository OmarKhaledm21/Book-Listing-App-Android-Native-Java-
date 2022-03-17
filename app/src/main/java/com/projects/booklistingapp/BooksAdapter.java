package com.projects.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BooksAdapter extends ArrayAdapter<Book> {

    public BooksAdapter(@NonNull Context context, int resource, @NonNull List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        Book book = getItem(position);

        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        titleView.setText(book.getTitle());

        TextView authorView = (TextView) listItemView.findViewById(R.id.authors);
        StringBuilder authors = new StringBuilder();
        for(int i=0; i<book.getAuthors().length; i++){
            authors.append( book.getAuthors()[i]);
            authors.append(", ");
        }
        String au = authors.toString();
        authorView.setText(au);

        TextView publishDateView = (TextView) listItemView.findViewById(R.id.publish_date);
        publishDateView.setText(book.getPublishDate());

        return listItemView;
    }
}
