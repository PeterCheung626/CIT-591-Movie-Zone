/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviezone;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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

	/**
	 * constructor that takes the movie id.
	 * 
	 * @param id
	 */
	public ExactSearchEngine(int id) {
		url = head + "/movie/" + id + keyString;
		keyUrl = head + "/movie/" + id + "/keywords" + keyString;
	}

	/**
	 * this method get json object from url
	 * 
	 * @return success/ error message
	 */
	public String connect() {
		ArrayList<Movie> movies = new ArrayList<>();
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
				// moviesJSON.add(jsonObject);
				movie = new Movie(jsonObject);
				// movies.add(m);
				System.out.println(movie.getOverView());
				System.out.println(movie.getTitle());
				findKeywords(movie);

			} catch (JSONException e) {
				e.printStackTrace();
				System.out.println("results not found");
			}
			return result;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			// Toast.makeText(getApplicationContext(), "Cound not find movie",
			// Toast.LENGTH_LONG);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * reset url by id for next use
	 * 
	 * @param id
	 */
	public void reset(int id) {
		url = head + "/movie/" + id + keyString;
		movie = null;
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
				// System.out.println(result);
				JSONArray jsonKeywords = jsonObject.getJSONArray("keywords");
				for (int i = 0; i < jsonKeywords.length(); i++) {
					JSONObject k = jsonKeywords.getJSONObject(i);
					String word = k.getString("name");
					// System.out.println(word);
					m.addKeywords(word);
				}

			} catch (JSONException e) {
				e.printStackTrace();
				System.out.println("results not found");
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
			// Toast.makeText(getApplicationContext(), "Cound not find movie",
			// Toast.LENGTH_LONG);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

