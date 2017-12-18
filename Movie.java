/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviezone;

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
    private ArrayList<String> productComp;
    private ArrayList<String> productCountry;
    private ArrayList<String> cast;
    private ArrayList<String> languages;
    private static final String postHead = "https://image.tmdb.org/t/p/w1280/";

    // comparators for sorting
    private static final Comparator<Movie> BY_ID = new ById();
    private static final Comparator<Movie> BY_POPULARITY = new ByPopularity();
    private static final Comparator<Movie> BY_DATE = new ByDate();

    public Movie(JSONObject m) {
        parseJson(m);
    }
	
    public void parseJson(JSONObject m) {
        keywords = new ArrayList<>();
        cast = new ArrayList<>();
        try {
            id = m.getInt("id");
            popularity = m.getDouble("popularity");
            title = m.getString("title");
            try {
                poster = postHead + m.getString("poster_path");
            } catch (JSONException e) {
                poster = "https://static.tumblr.com/opuuuju/lWjn7izq1/coming-soon.png";
                System.out.println("poster not found: " + id);
            }
            try {
                overview = m.getString("overview");
            } catch (JSONException e) {
                System.out.println("overview not found");
                overview = "not found";
            }
            
            try {
                releaseDate = new Date(m.getString("release_date"));
            } catch (JSONException e) {e.printStackTrace();};
            runtime = m.getInt("runtime");
            ratings = m.getDouble("vote_average");

            // get all genres
            try {
                genres = new ArrayList<>();
                JSONArray g = m.getJSONArray("genres");
                for (int i = 0; i < g.length(); i++) {
                    JSONObject genre = g.getJSONObject(i);
                    genres.add(genre.getString("name"));
                }
            } catch (Exception e) {e.printStackTrace();};
     
            //get product countries
            try {
                productCountry = new ArrayList<>();
                JSONArray pc = m.getJSONArray("production_countries");
                for (int i = 0; i < pc.length(); i++) {
                    JSONObject country = pc.getJSONObject(i);
                    productCountry.add(country.getString("name"));
                }
            } catch (Exception e) { e.printStackTrace();}

            try {
                languages = new ArrayList<>();
                JSONArray lang = m.getJSONArray("spoken_languages");
                for (int i = 0; i < lang.length(); i++) {
                    JSONObject language = lang.getJSONObject(i);
                    languages.add(language.getString("name"));
                }
            } catch (Exception e) { e.printStackTrace();}

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
            bse.connect();
            return bse.getMovies();
        }
        
        public ArrayList<Movie> getRecommendations() {
        BlurSearchEngine bse = new BlurSearchEngine(this.id, 2);
        bse.connect();

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
            if (dif > 0) {
                return 1;
            }
            if (dif < 0) {
                return -1;
            } else {
                return 0;
            }
        }

    }

    private static class ByDate implements Comparator<Movie> {

        public int compare(Movie f, Movie s) {
            return f.getReleaseDate().compareTo(s.getReleaseDate());
        }
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
                if (rec.size() >= 5) return rec;
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
        return overview;
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
        return languages;
    }
}
