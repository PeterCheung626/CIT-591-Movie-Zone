/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package movie;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author mayitian
 */
public class BlurSearchEngine {
    private static final String keyString = "?api_key=edf1b9d248b7dee1398bb1159e9f19cc";
    private static final String poster = "https://image.tmdb.org/t/p/w1280/";
    private static final String head = "https://api.themoviedb.org/3";
    private String url;
    ArrayList<Movie> movies;
    ArrayList<String> cast;
    int count;
    
    /**
     * constructor for similar/recommend movie search
     * @param id target movie id
     * @param select 1 for similar search, 2 for recommendation
     */
    public BlurSearchEngine (int id, int select) {
        count = 5;
        if (select == 1) url = head + "/movie/" + id + "/similar" + keyString + "&language=en-US";//&page=1";   
        else url = head + "/movie/" + id + "/recommendations" + keyString + "&language=en-US";//&page=1";
        connect();
    }
    
    /**
     * search movies base on title
     * @param title
     * @param getMovie, false for search 
     */
    public BlurSearchEngine (String title) {
        count = 20;
        String tmp = title.replace(" ", "%20");
        url = head + "/search/movie" + keyString + "&query=" + tmp + "&include_adult=false&page=1";//&page=1
        connect();
        
    }
    
    /**
     * SEARCH for English movies(now playing/ top rated/ upcoming/ keywords )
     * @param condition
     * @param select 1 for general search(now playing, top rated/upcoming. 2 for keyword search
     */
    public BlurSearchEngine (String condition, int select) {
        count = 20;
        if (select == 1) {
            url = head + "/movie/" + condition.replace(" ", "_") + keyString + "&language=en-US&page=1";
        } else {
            url = head + "/search/keyword" + keyString + "&query=" + condition.replace(" ", "%20") + "&language=en-US&page=1";
        }
        //System.out.println(url);
        connect();
    }
    
    /**
     * this method get json object from url
     * @return success/ error message
     */
	private void connect() {
            movies = new ArrayList<>();
            String result = "";
            URL searchUrl ;
            HttpURLConnection urlConnection = null;
            try {
                searchUrl = new URL(url);
                urlConnection = (HttpURLConnection) searchUrl.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonMovies = jsonObject.getJSONArray("results");
                    ExactSearchEngine s = new ExactSearchEngine(0);
                    for (int i = 0; i < jsonMovies.length() && i < count; i++) {
                        int id = jsonMovies.getJSONObject(i).getInt("id");
                        s.reset(id);
                        if (s.getMovie() != null) {
                            movies.add(s.getMovie());
                        }
                    }

                } catch (JSONException e) {
                }
            } catch (MalformedURLException e) {
            } catch (IOException e) {
            }
	}
        
        public ArrayList<Movie> getMovies() {
            return movies;
        }
}
