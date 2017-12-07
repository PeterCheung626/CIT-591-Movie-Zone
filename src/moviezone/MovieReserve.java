/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MoievSource;

import java.net.URL;

/**
 *
 * @author yufancheng
 */
public class MovieReserve {
    
    private String MovieName;
    private URL MoviePoster;
    public MovieReserve(String name, URL url){
       name = MovieName;
       url = MoviePoster;
    }
    
    
    public String getMovieName(){
    
        return MovieName;
    }
    
    public URL getMovieUrl(){
     
        return MoviePoster;
    }
            
}
