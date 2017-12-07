/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviezone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import org.json.*;

public class Movie {
	private int id;
        private int runtime;
	private double popularity;
	private String title;
	private String poster;
	private String overView;
        private String language;
        private String homepage;
        private String director;
	private Date releaseDate;
	private ArrayList<String> genres;
	private ArrayList<String> keywords;
	private ArrayList<String> productComp;
        private ArrayList<String> productCountry;
        private ArrayList<String> cast;
	private static final String postHead = "https://image.tmdb.org/t/p/w1280/";
        

	// comparators for sorting
	private static final Comparator<Movie> BY_ID = new ById();
	private static final Comparator<Movie> BY_POPULARITY = new ByPopularity();
	private static final Comparator<Movie> BY_DATE = new ByDate();

	public Movie(JSONObject m) {
		keywords = new ArrayList<>();
		try {
			id = m.getInt("id");
			popularity = m.getDouble("popularity");
			title = m.getString("title");
			try {
				poster = postHead + m.getString("poster_path");
			} catch (JSONException e) {
				System.out.println("poster not found");
			}
			overView = m.getString("overview");
			releaseDate = new Date(m.getString("release_date"));
                        language = m.getString("original_language");
                        homepage = m.getString("homepage");
                        runtime = m.getInt("runtime");
                            
			// get all genres
			genres = new ArrayList<>();
			JSONArray g = m.getJSONArray("genres");
			// JSONArray g = new JSONArray(genreInfo);
			for (int i = 0; i < g.length(); i++) {
				JSONObject genre = g.getJSONObject(i);
				genres.add(genre.getString("name"));
			}

			// get all production companies
			productComp = new ArrayList<>();
			try {
				JSONArray pc = new JSONArray(m.getString("production_companies"));
				for (int i = 0; i < pc.length(); i++) {
					JSONObject company = pc.getJSONObject(i);
					productComp.add(company.getString("name"));
				}
			} catch (JSONException e) {
				// no production company
			}
                        
                        //get product countries
                        productCountry = new ArrayList<String>();
                        try {
                            JSONArray pc = new JSONArray(m.getString("production_countries"));
                            for (int i = 0; i < pc.length(); i++) {
					JSONObject country = pc.getJSONObject(i);
					productCountry.add(country.getString("name"));
				}
                        } catch (JSONException e) {
                            
                        }

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {

		}
	}
	
	/**
	 * this method add a keywords of this movie
	 * @author mayitian
	 *
	 */
	public void addKeywords(String s) {
		keywords.add(s);
	}
        
        /**
         * this method add a cast member
         * @param n name
         * @param c character/job
         */
        public void addCast(String n) {
            cast.add(n);
        }
        
        /**
         * this method set the director name
         * @param n director name
         */
        public void setDirector(String n) {
            director = n;
        }
        public ArrayList<Movie> getSimiliarMovies () {
            BlurSearchEngine bse = new BlurSearchEngine(this.id);
            return bse.getMovies();
        }
	private static class ById implements Comparator<Movie> {
		public int compare(Movie f, Movie s) {
			return f.getId() - s.getId();
		}
	}

	private static class ByPopularity implements Comparator<Movie> {
		public int compare(Movie f, Movie s) {
			double dif = f.getPopularity() - s.getPopularity();
			if (dif > 0)
				return 1;
			if (dif < 0)
				return -1;
			else
				return 0;
		}

	}

	private static class ByDate implements Comparator<Movie> {
		public int compare(Movie f, Movie s) {
			return f.getReleaseDate().compareTo(s.getReleaseDate());
		}
	}

	/**
	 * determine whether 
	 * @param g
	 * @return
	 */
	public boolean isGenre(String g) {
		for (String s : genres) {
			if (s.equalsIgnoreCase(g)) return true;
		}
		return false;
	}
	
	
	public int getId() {
		return id;
	}

	public double getPopularity() {
		return popularity;
	}

	public String getTitle() {
		return title;
	}

	public String getOverView() {
		return overView;
	}

	public String getPoster() {
		return poster;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public ArrayList<String> getGenres() {
		return genres;
	}

	public ArrayList<String> getProduct_comp() {
		return productComp;
	}
        
        public ArrayList<String> getProductCountry() {
            return productCountry;
        }
        
        public int getRunTime() {
            return runtime;
        }
        
        public String getHomePage() {
            return homepage;
        }
        
        
}

