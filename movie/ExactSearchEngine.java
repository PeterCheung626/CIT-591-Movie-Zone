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
import javax.swing.JOptionPane;

import org.json.*;
/**
 * this is an accurate search engine, the result contains one or no movie.
 * 
 * @author mayitian
 *
 */
public class ExactSearchEngine {
	private static final String keyString = "?api_key=edf1b9d248b7dee1398bb1159e9f19cc";

	private static final String head = "https://api.themoviedb.org/3";
	private String url;
	private Movie movie;
	private String keyUrl;
        private String castUrl;
        private int id;
        
	/**
	 * constructor that takes the movie id
	 * @param id
	 */
    public ExactSearchEngine(int id) {

        this.id = id;
        formUrl();
        connect();

    }

    private void formUrl() {
        url = head + "/movie/" + id + keyString;
        keyUrl = head + "/movie/" + id + "/keywords" + keyString;
        castUrl = head + "/movie/" + id + "/credits" + keyString;
    }



	/**
	 * this method get json object from url
	 * this method connect url for movie details
	 * @return success/ error message
	 */
	private void connect(){
		
		String result = "";
		URL searchUrl;

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
				movie = new Movie(jsonObject);
				findKeywords(movie);
                                findCast(movie);

			} catch (JSONException e) {
			}
		} catch (MalformedURLException e) {
		} catch (IOException e) {}
	}

	/**
	 * reset url by id for next use
	 * 
	 * @param id
	 */
	public void reset(int id) {
		this.id = id;
		movie = null;
                formUrl();
                connect();
	}

	/**
	 * this method return the last search result for engine
	 * 
	 * @return
	 */
	public Movie getMovie() {
            
           return movie;
	}

	/**
	 * this method find the keywords of a movie
	 */
	private void findKeywords(Movie m) {
		String result = "";
		URL keywordUrl;

		HttpURLConnection keywordUrlConnection = null;
		try {
			keywordUrl = new URL(keyUrl);
			keywordUrlConnection = (HttpURLConnection) keywordUrl.openConnection();
			InputStream in = keywordUrlConnection.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			int data = reader.read();

			while (data != -1) {
				char current = (char) data;
				result += current;
				data = reader.read();
			}
			try {
				JSONObject jsonObject = new JSONObject(result);
				JSONArray jsonKeywords = jsonObject.getJSONArray("keywords");
				for (int i = 0; i < jsonKeywords.length(); i++) {
					JSONObject k = jsonKeywords.getJSONObject(i);
					String word = k.getString("name");
					m.addKeywords(word);
				}

			} catch (JSONException e) {
			}
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}

	}
        
        private void findCast(Movie m) {
            String result = "";
		URL keywordUrl;

		HttpURLConnection keywordUrlConnection = null;
		try {
			keywordUrl = new URL(castUrl);
			keywordUrlConnection = (HttpURLConnection) keywordUrl.openConnection();
			InputStream in = keywordUrlConnection.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			int data = reader.read();

			while (data != -1) {
				char current = (char) data;
				result += current;
				data = reader.read();
			}
			try {
				JSONObject jsonObject = new JSONObject(result);
				JSONArray cast = jsonObject.getJSONArray("cast");
				for (int i = 0; i < cast.length(); i++) {
					JSONObject k = cast.getJSONObject(i);
					String name = k.getString("name");
					m.addCast(name);
				}
                                JSONArray crews = jsonObject.getJSONArray("crew");
                                String directorName = "";
                                for (int i = 0; i < crews.length(); i++) {
                                    JSONObject d = crews.getJSONObject(i);
                                    String job = d.getString("job");
                                    if (job.equalsIgnoreCase("director")) {
                                        directorName = d.getString("name");
                                        m.setDirector(directorName);
                                    }
                                }
			} catch (JSONException e) {
			}

		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}

	}
        
}


