package com.projects.booklistingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BooksAdapter extends ArrayAdapter<Book> {
    private DBHelper db;
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

        TextView titleView = (TextView) listItemView.findViewById(R.id.book_title);
        titleView.setText(book.getTitle());

        TextView authorView = (TextView) listItemView.findViewById(R.id.book_authors);
        StringBuilder authors = new StringBuilder();

        TextView description = (TextView) listItemView.findViewById(R.id.description);
        if(book.getDescription()!= null && !book.getDescription().equals("")){
            description.setText(book.getDescription());
        }
        description.setVisibility(View.GONE);
        for(int i=0; i<book.getAuthors().length; i++){
            authors.append( book.getAuthors()[i]);
            authors.append(", ");
        }
        String au = authors.toString();
        authorView.setText(au);

        TextView publishDate = (TextView)listItemView.findViewById(R.id.publish_date);
        if(!book.getPublishDate().equals("") && book.getPublishDate()!= null){
            publishDate.setText(book.getPublishDate());
        }

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.img);
        if(book.getBitmap() != null){
            imageView.setImageBitmap(book.getBitmap());
        }

        ImageButton imageBtn = (ImageButton) listItemView.findViewById(R.id.arrow);

        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(book.getLink()!=null && !book.getLink().equals("")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(book.getLink()));
                    getContext().startActivity(intent);
                }else{
                    Toast.makeText(getContext(),R.string.no_link_provided,Toast.LENGTH_SHORT).show();
                }
            }
        });

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(description.getVisibility() == View.GONE){
                    description.setVisibility(View.VISIBLE);
                }else{
                    description.setVisibility(View.GONE);
                }

            }
        });

        CheckBox checkBox = (CheckBox) listItemView.findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Log.v("BOOK_DB_ID",book.getId());
                if(isChecked && book.getId()!=null){
                   db = new DBHelper(getContext());
                    Log.v("BOOK_DB_ID",book.getId());
                   db.addItem(book);
                }else{
                    //Remove book from wish list
                }
            }
        });



        return listItemView;
    }
}
