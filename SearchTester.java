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
    System.out.println("\n");
    System.out.println("runtime : " + m.getRunTime());
    System.out.println("home page: " + m.getHomePage());
    System.out.println("similiar movies:  ");
    for (Movie s : m.getSimiliarMovies()) {
        //System.out.println(s.getId());
        System.out.println(s.getTitle());
    }
    System.out.println();
    
    }
}
