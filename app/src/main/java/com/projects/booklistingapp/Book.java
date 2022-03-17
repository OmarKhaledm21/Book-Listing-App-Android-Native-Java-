package com.projects.booklistingapp;

public class Book {
    private String title;
    private String[] authors;
    private String publishDate;
    private String description;
    private String link;
    private String imageLink;

    public Book(String title, String[] authors, String publishDate, String description, String link, String imageLink) {
        this.title = title;
        this.authors = authors;
        this.publishDate = publishDate;
        this.description = description;
        this.link = link;
        this.imageLink = imageLink;
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
}
