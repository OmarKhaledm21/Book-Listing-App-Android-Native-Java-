package com.projects.booklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class QueryUtils {
    private static final String LOG_TAG = "UTILS";

    //TODO USE OPT JSON
    public static ArrayList<Book> parseJsonResponse(String jsonResponse){
        ArrayList<Book> books = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(jsonResponse);
            JSONArray itemsArray = root.getJSONArray("items");
            for(int i=0; i<itemsArray.length(); i++){
                final JSONObject item = itemsArray.getJSONObject(i);
                final JSONObject volumeInfo = item.getJSONObject("volumeInfo");

                String title = volumeInfo.getString("title");


                final JSONArray authorsArray = volumeInfo.optJSONArray("authors");
                String[] authors;
                if(authorsArray!=null) {
                    authors = new String[authorsArray.length()];
                    for (int j = 0; j < authorsArray.length(); j++) {
                        authors[j] = authorsArray.getString(j);
                    }
                }else{
                    authors = new String[1];
                    authors[0]="";
                }
                String publishDate = volumeInfo.getString("publishedDate");
                String description = volumeInfo.getString("description");
                String previewLink = volumeInfo.getString("infoLink");

               // final JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String thumbnail_link = "";
//                if(imageLinks!=null) {
//                    thumbnail_link = imageLinks.getString("smallThumbnail");
//                }
                books.add(new Book(title,authors,publishDate,description,previewLink,thumbnail_link));

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return books;
    }

    public static URL getURL(String book_name){
        StringBuilder base = new StringBuilder();
        base.append("https://www.googleapis.com/books/v1/volumes?q=");
        base.append(book_name);
        base.append("&maxResults=10");
        String fullUrl = base.toString();

        URL url = null;
        try {
            url = new URL(fullUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String readFromStream(InputStream inputStream){
        StringBuilder result = new StringBuilder();
        try {
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String response = "";
        if(url==null){
            return response;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                response = readFromStream(inputStream);
            } else {
                Log.v(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.v(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // function must handle java.io.IOException here
                inputStream.close();
            }
        }
        return response;
    }


}
