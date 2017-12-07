/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviezone;

import java.util.ArrayList;

/**
 *
 * @author yufancheng
 */
public class ZoneUser {
    private ArrayList<MovieReserve> movieWatched;
    private MovieReserve movie;
    
    public ZoneUser(){
     movieWatched = new ArrayList<>();
     
    }
    
    
    public void addMovie(MovieReserve m){
      movieWatched.add(m);
        
    
    }
    
    public ArrayList<MovieReserve> getUserMovie(){
     return movieWatched;
    }

}
