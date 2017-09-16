package com.example.android.books;


import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    public NetworkUtils() {
    }

    private static final String BASE_URL = "https://www.googleanpis.com/books/v1/volumes";

    public static URL parseUrl(String keyword){
        String buildUrl = BASE_URL + "?q=" + keyword;
        URL url = null;
        try {
            url = new URL(buildUrl);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getJSON(URL url) throws IOException{
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                InputStream stream = connection.getInputStream();
                Scanner scanner = new Scanner(stream);
                scanner.useDelimiter("\\A");
                if (scanner.hasNext()) {
                    return scanner.next();
                } else {
                    return null;
                }
            }
            catch (Exception e){
                Log.e("Error", e.toString() );
                return null;
            }
            finally {
                connection.disconnect();
            }
    }
}
