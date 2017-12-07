/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MovieSource;
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
    
    /**
     * constructor for similar movie search
     * @param id
     */
    public BlurSearchEngine (int id) {
        url = head + "/movie/" + id + "/similar" + keyString;   
    }
    
    /**
     * search movies base on title
     * @param title
     */
    public BlurSearchEngine (String title) {
        String tmp = title.replace(" ", "%20");
        url = head + "/search/movie" + keyString + "&query=" + tmp + "&include_adult=false";//&page=1
    }
    
  //SEARCH for English movies(now playing/ top rated/ keywords )
    public BlurSearchEngine (String condition, int select) {
    		if (select < 0) {
    			new BlurSearchEngine(condition);			
    		}
    		else {
	    		
			if (select == 1) {
				url = head + "/movie/" + condition.replace(" ", "_") + keyString + "&language=en-US";
			} else if (select == 2) {
				url = head + "/search/keyword" + keyString + "&query=" + condition.replace(" ", "%20") + "&page=1";
			}
    		}
    }
    
    /**
     * this method get json object from url
     * @return success/ error message
     */
	public String connect() {
		ArrayList<Movie> movies = new ArrayList<>();
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
                //moviesJSON.add(jsonObject);
                /*
                Movie movie = new Movie(jsonObject);
                movies.add(m);
                System.out.println(movie.getOverView());
                System.out.println(movie.getTitle());
                */
                for (int i = 0; i < jsonMovies.length(); i++) {
                		int id = jsonMovies.getJSONObject(i).getInt("id");
                		System.out.println("id : " + id);
                		ExactSearchEngine s = new ExactSearchEngine(id);
                		s.connect();
                		//System.out.println(s.getMovie().getTitle());
                }

            } catch (JSONException e) {
                e.printStackTrace();
                System.out.println("results not found");
            }
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            //Toast.makeText(getApplicationContext(), "Cound not find movie", Toast.LENGTH_LONG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
}
