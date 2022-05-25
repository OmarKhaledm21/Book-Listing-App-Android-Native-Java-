# Book Listing App.
#### This application takes some input from user which represents a topic of interest where the application give user some book suggests retrived from Google Books API.
#### 1. This project is implemented in Java.
#### 2. I used Loader Manager to do async tasks in a background thread (Tasks like downloading books images and getting books info json response).
#### 3. I used Connectivity Manager to check for internet connections and ListViews to show results also implemented an array adapter for using custom list views.
#### 4. Also Http connections were openned to send requests and recieve requests/responses containning Json book data.
#### 5. Sqlite DB was added for storing books wish list.

##### - Main screen for taking user input (Interest topic for book suggestions)
<img src="https://github.com/OmarKhaledm21/Book-Listing-App-Android-Native-Java-/blob/master/SS/main_screen_input.png" alt="drawing" width="270"/>


##### - Some result from google books api
<img src="https://github.com/OmarKhaledm21/Book-Listing-App-Android-Native-Java-/blob/master/SS/Dogs_suggest.png" alt="drawing" width="270"/>


##### - Checking description by clicking on book item
<img src="https://github.com/OmarKhaledm21/Book-Listing-App-Android-Native-Java-/blob/master/SS/description_on_item_click.png" alt="drawing" width="270"/>


##### - Checkbox clicked to add books to wishlist stored in SQLite database
<img src="https://github.com/OmarKhaledm21/Book-Listing-App-Android-Native-Java-/blob/master/SS/wishlist_checkbox.png" alt="drawing" width="270"/>

##### - Wishlist activity which retrived all user saved books from previous searchs
<img src="https://github.com/OmarKhaledm21/Book-Listing-App-Android-Native-Java-/blob/master/SS/wishlist_sqlite_retrival.png" alt="drawing" width="270"/>


