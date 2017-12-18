/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package movie;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;
import org.json.*;

public class Movie {
    
    private int id;
    private int runtime;
    private double popularity;
    private double ratings;
    private String title;
    private String poster;
    private String overview;
    private String director;
    private Date releaseDate;
    private ArrayList<String> genres;
    private ArrayList<String> keywords;
    private ArrayList<String> productCountry;
    private ArrayList<String> cast;
    private ArrayList<String> languages;
    private static final String postHead = "https://image.tmdb.org/t/p/w1280/";

    
    public Movie(JSONObject m) {
        keywords = new ArrayList<>();
        cast = new ArrayList<>();
        genres = new ArrayList<>();
        productCountry = new ArrayList<>();
        languages = new ArrayList<>();
        try {
            id = m.getInt("id");
            popularity = m.getDouble("popularity");
            title = m.getString("title");
            try {
                poster = postHead + m.getString("poster_path");
            } catch (JSONException e) {
                poster = "http://gogmat.com/images/cancelling%20gmat%20score.jpg";
                //System.out.println("poster not found: " + id);
            }
            try {
            overview = m.getString("overview");
            } catch (JSONException e) {}
            try {
                releaseDate = new Date(m.getString("release_date"));
            } catch (JSONException e) {e.printStackTrace();
            } catch (NumberFormatException e) {releaseDate = null;};
            try {
                runtime = m.getInt("runtime");
            } catch (JSONException e) {runtime = 0;}
            try {
            ratings = m.getDouble("vote_average");
            } catch (JSONException e) {ratings = 0;}
            // get all genres
            try {
                JSONArray g = m.getJSONArray("genres");
                for (int i = 0; i < g.length(); i++) {
                    JSONObject genre = g.getJSONObject(i);
                    genres.add(genre.getString("name"));
                }
            } catch (JSONException e) {e.printStackTrace();};
     
            //get product countries
            try {
                JSONArray pc = m.getJSONArray("production_countries");
                for (int i = 0; i < pc.length(); i++) {
                    JSONObject country = pc.getJSONObject(i);
                    String c = country.getString("name");
                    if (c.equalsIgnoreCase("United States of America")) c = "USA";
                    productCountry.add(c);
                }
            } catch (JSONException e) { e.printStackTrace();}

            try {
                JSONArray lang = m.getJSONArray("spoken_languages");
                for (int i = 0; i < lang.length(); i++) {
                    JSONObject language = lang.getJSONObject(i);
                    languages.add(language.getString("name"));
                }
            } catch (JSONException e) { e.printStackTrace();}

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
        
        public ArrayList<Movie> getSimilarMovies () {
            BlurSearchEngine bse = new BlurSearchEngine(this.id, 1);
            return bse.getMovies();
        }
        
        public ArrayList<Movie> getRecommendations() {
        BlurSearchEngine bse = new BlurSearchEngine(this.id, 2);

        return bse.getMovies();
    }

    

    /**
     * determine whether
     *
     * @param g
     * @return
     */
    public boolean isGenre(String g) {
        for (String s : genres) {
            if (s.equalsIgnoreCase(g)) {
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList<Movie> recommend(ArrayList<Movie> like, ArrayList<Movie> watched) {
        ArrayList<Movie> rec = new ArrayList<>();
        HashMap<Integer, Movie> history = new HashMap<>();
        for (Movie m: watched) {
            history.put(m.getId(), m);
        }
        Random random = new Random();
        int recTime = 0;
        while (recTime < like.size()) {
            int r = random.nextInt(like.size());
            ArrayList<Movie> singleList = like.get(r).getRecommendations();
            for (Movie tmp : singleList) {
                if (!history.keySet().contains(tmp.getId())) {
                    rec.add(tmp);
                }
                if (rec.size() >= 4) return rec;
            }
            recTime++;
        }
        return rec;
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

    public String getOverview() {
        if (overview.length() > 350) return overview.substring(0, 350) + "...";
        return overview;
    }

    public String getPoster() {
        return poster;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public ArrayList<String> getGenres() {
        if (genres.isEmpty()) return null;
        return genres;
    }


    public ArrayList<String> getProductCountry() {
        if (productCountry.isEmpty()) return null; 
        return productCountry;
    }

    public int getRunTime() {
        return runtime;
    }

    public String getDirector() {
        return director;
    }

    public double getRatings() {
        return ratings;
    }

    public ArrayList<String> getKeywords() {
        if (keywords.isEmpty()) return null;
        return keywords;
    }

    public ArrayList<String> getCast() {
        if (cast.isEmpty()) return null;
        return cast;
    }

    public ArrayList<String> getLanguages() {
        if (languages.isEmpty()) return null;
        return languages;
    }
}
