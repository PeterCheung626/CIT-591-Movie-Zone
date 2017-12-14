/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviezone;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author mayitian
 */
public class SearchTester {
    
    public static void main(String[] args) {
    
    //search a movie by id and print out its attributes
    
    //dont't run all queries together!!!!(over request limits)
    
//    ExactSearchEngine ese = new ExactSearchEngine(36670);
//    ese.connect();
//    Movie m = ese.getMovie();
//    System.out.println(m == null);
//    System.out.println("title: " + m.getTitle());
//    System.out.println("id : " + m.getId());
//    System.out.println("poster : " + m.getPoster());
//    System.out.println("popularity : " + m.getPopularity());
//    //System.out.println("vote average : " + m.getVote());
//    System.out.println("overview : " + m.getOverview());
//    System.out.println("director : " + m.getDirector());
//    System.out.println("date : " + m.getReleaseDate().toString());
//    System.out.println(m.getDirector() == null);
//    System.out.println("\n");
//    System.out.println(m.getReleaseDate()==null);
//    System.out.println("gernres: " + m.getGenres()==null);
//    System.out.println(m.getLanguages()==null);
//    System.out.println(m.getKeywords()==null);
//    System.out.println(m.getProductCountry()==null);
//     System.out.println(m.getCast()==null);
//    
//    //System.out.println(m.getDate()==null);
//    
//        try {
//            System.out.println(m.getReleaseDate());
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        System.out.println("========");
//        try {
//            for (String s : m.getLanguages()) {
//                System.out.println("language : " + s);
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println("genres: ");
//            for (String s : m.getGenres()) {
//                System.out.print(" " + s + " ");
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println("keywords: ");
//            for (String s : m.getKeywords()) {
//                System.out.println(s + " ");
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            System.out.println("countries: ");
//            for (String s : m.getProductCountry()) {
//                System.out.print(s + " ");
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        try {
//            System.out.println("cast: ");
//            for (String s : m.getCast()) {
//                System.out.print(s + " ");
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//    
    
    
//    System.out.println();
//    System.out.println("cast: ");
//    for (String s : m.getCast()) {
//        System.out.println(s + " ");
//    }
//    System.out.println("\n");
//    System.out.println("runtime : " + m.getRunTime());
//    //System.out.println("home page: " + m.getHomePage());
//    for (String s: m.getProductCountry()) {
//    System.out.println("country: " + s);
//    }
    
//    System.out.println("similiar movies:  ");
//    for (Movie s : m.getSimilarMovies()) {
//        //System.out.println(s.getId());
//        System.out.println(s.getTitle());
//    }
    /*
    System.out.println();
    System.out.println("recommend movies:  ");
    for (Movie s : m.getRecommendations()) {
        //System.out.println(s.getId());
        System.out.println(s.getTitle());
    }
      /* 
    // to search movies by title, call blurSearchEngine constructor with a string representing the title
    BlurSearchEngine bse = new BlurSearchEngine("superman");
    //connect() must be called to get data from api
    bse.connect();
    //use getMovies() to get search results (arrayList of movies)
    for (Movie b : bse.getMovies()) {
        System.out.println(b.getTitle());
    
    }
    */
    //search for now playing movies
//    BlurSearchEngine bse1 = new BlurSearchEngine("popular", 1);
//    bse1.connect();
//    ArrayList<Movie> like = bse1.getMovies();
//    System.out.println(like.size());
//    System.out.println("like list:");
//    for (Movie b : like) {
//        System.out.println(b.getTitle());
//    
//    }
//    
//    //search for top rated movies
//    BlurSearchEngine bse2 = new BlurSearchEngine("top_rated", 1);
//    bse2.connect();
//    ArrayList<Movie> watch = bse2.getMovies();
//    System.out.println(watch.size());
//    System.out.println("watch list:");
//    for (Movie b : watch) {
//        System.out.println(b.getTitle());
//    
//    }
    
    ExactSearchEngine ese1 = new ExactSearchEngine(36670);
    ese1.connect();
    Movie m1 = ese1.getMovie();
    System.out.println(m1.getTitle());
        
    ExactSearchEngine ese2 = new ExactSearchEngine(954);
    ese2.connect();
    Movie m2 = ese2.getMovie();
    System.out.println(m1.getTitle());
    
    ArrayList<Movie> like = m1.getSimilarMovies();
    try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    ArrayList<Movie> watch = m2.getSimilarMovies();
    try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    
        System.out.println("like list:");
          System.out.println(like.size());
    for (Movie b : like) {
        System.out.println(b.getTitle());
    
    }

        System.out.println("watch list:");
          System.out.println(watch.size());
    for (Movie b : watch) {
        System.out.println(b.getTitle());
    
    }

    
    

    ArrayList<Movie> rec = Movie.recommend(like, watch);
    System.out.println("reccomend list:");
    for (Movie m : rec) {
        System.out.println(m.getTitle());
    }
    
//    
//    //search for upcoming movies
//    BlurSearchEngine bse3 = new BlurSearchEngine("upcoming", 1);
//    bse3.connect();
//    System.out.println(bse3.getMovies().size());
//    for (Movie b : bse3.getMovies()) {
//        System.out.println(b.getTitle());
//    
//    }
    
    
    
    //search movies by keywords
//    BlurSearchEngine bse4 = new BlurSearchEngine("classic", 2);
//    bse4.connect();
//    System.out.println(bse4.getMovies().size());
//    System.out.println(bse4.getMovies().size());
//    for (Movie b : bse4.getMovies()) {
//        System.out.println(b.getTitle());
//        System.out.println(b.getPoster());
//    
//    }
//    
//    
    
    
    }
}
