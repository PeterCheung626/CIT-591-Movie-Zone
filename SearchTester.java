/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviezone;

/**
 *
 * @author mayitian
 */
public class SearchTester {
    
    public static void main(String[] args) {
    
    //search a movie by id and print out its attributes
    
    //dont't run all queries together!!!!(over request limits)
    
    ExactSearchEngine ese = new ExactSearchEngine(9538);
    ese.connect();
    Movie m = ese.getMovie();
    System.out.println("title: " + m.getTitle());
    System.out.println("id : " + m.getId());
    System.out.println("poster : " + m.getPoster());
    System.out.println("popularity : " + m.getPopularity());
    System.out.println("vote average : " + m.getVote());
    System.out.println("overview : " + m.getOverView());
    System.out.println("director : " + m.getDirector());
    System.out.println("genres: ");
    for (String s : m.getGenres()) {
        System.out.print(" " + s + " ");
    }
    System.out.println("keywords: ");
    for (String s : m.getKeywords()) {
        System.out.print(" " + s + " ");
    }
    System.out.println("\n");
    System.out.println("runtime : " + m.getRunTime());
    System.out.println("home page: " + m.getHomePage());
    
    System.out.println("similiar movies:  ");
    for (Movie s : m.getSimiliarMovies()) {
        //System.out.println(s.getId());
        System.out.println(s.getTitle());
    }
    
    System.out.println();
    
        
    // to search movies by title, call blurSearchEngine constructor with a string representing the title
    BlurSearchEngine bse = new BlurSearchEngine("superman");
    //connect() must be called to get data from api
    bse.connect();
    //use getMovies() to get search results (arrayList of movies)
    for (Movie b : bse.getMovies()) {
        System.out.println(b.getTitle());
    
    }
    
    //search for now playing movies
    BlurSearchEngine bse1 = new BlurSearchEngine("now playing", 1);
    bse1.connect();
    System.out.println(bse1.getMovies().size());
    for (Movie b : bse1.getMovies()) {
        System.out.println(b.getTitle());
    
    }
    
    //search for top rated movies
    BlurSearchEngine bse2 = new BlurSearchEngine("top_rated", 1);
    bse2.connect();
    System.out.println(bse2.getMovies().size());
    for (Movie b : bse2.getMovies()) {
        System.out.println(b.getTitle());
    
    }
    
    //search for upcoming movies
    BlurSearchEngine bse3 = new BlurSearchEngine("upcoming", 1);
    bse3.connect();
    System.out.println(bse3.getMovies().size());
    for (Movie b : bse3.getMovies()) {
        System.out.println(b.getTitle());
    
    }
    
    
    //search movies by keywords
    BlurSearchEngine bse4 = new BlurSearchEngine("murder", 2);
    bse4.connect();
    System.out.println(bse4.getMovies().size());
    for (Movie b : bse4.getMovies()) {
        System.out.println(b.getTitle());
    
    }
    
    
    
    
    }
}
