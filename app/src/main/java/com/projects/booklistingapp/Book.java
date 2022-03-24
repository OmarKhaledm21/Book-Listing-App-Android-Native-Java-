package com.projects.booklistingapp;

import android.graphics.Bitmap;

public class Book {
    private String Id;
    private String title;
    private String[] authors;
    private String publishDate;
    private String description;
    private String link;
    private String imageLink;
    private Bitmap bitmap;
    
    public Book(String Id, String title, String[] authors, String publishDate, String description, String link, String imageLink, Bitmap bitmap) {
        this.Id = Id;
        this.title = title;
        this.authors = authors;
        this.publishDate = publishDate;
        this.description = description;
        this.link = link;
        this.imageLink = imageLink;
        this.bitmap = bitmap;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
