/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author shenghu
 */
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class User {
	private Driver driver;
	private String username;
        private String password;
	private int age;
        
	
	public User(String username, String password) {
		driver = GraphDatabase.driver("bolt://localhost");
		this.username = username;
                this.password = password;
		//this.age = age;
        }

    public User() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
	
	public ArrayList<String> getLikeList() {	
		ArrayList<String> list = new ArrayList<String>();	
		//match (u: user {name : "Tom"})
		String query = "match (u:User {name: \"" + username + "\" })-[:Likes]->(m) return m.id as id";
		try (Session session = driver.session()) {
			StatementResult result = session.run(query);
			while(result.hasNext()) {
				list.add(result.next().get("id").toString());
			}
		}
		return list;		
	}
	
	public ArrayList<String> getWatchedList() {	
		ArrayList<String> list = new ArrayList<String>();	
		//match (u: user {name : "Tom"})
		String query = "match (u:User {name: \"" + username + "\" })-[:Watched]->(m) return m.id as id";
		try (Session session = driver.session()) {
			StatementResult result = session.run(query);
			while(result.hasNext()) {
				list.add(result.next().get("id").toString());
			}
		}
		return list;		
	}
	
	
	
	//when a user click the "add" button of one movie, carry out this code
	public void addMovie(String movieId) {
		String query = "match (u:User {name: \"" + username + "\"})"
				+ " merge (m:Movie {id: \"" + movieId + "\"}) "
				+ "merge (u)-[r:Likes]->(m)";
		try (Session session = driver.session()) {
			session.run(query);
		}	
	}
        
        //when a user click the "add" button of one movie, carry out this code
	public void addHistoryMovie(String movieId) {
		String query = "match (u:User {name: \"" + username + "\"})"
				+ " merge (m:Movie {id: \"" + movieId + "\"}) "
				+ "merge (u)-[r:Watched]->(m)";
		try (Session session = driver.session()) {
			session.run(query);
		}	
	}
	
	public void hasWatched(String movieId) {	
		String query = "match (u:User {name: \"" + username + "\"})-"
				+ "[r1:Likes]->(m:Movie {id: \"" + movieId + "\"})"
				+ " delete r1 "
				+ "create (u)-[r2:Watched]->(m)";
		try (Session session = driver.session()) {			
			session.run(query);
		}		
	}
        
        public void setAge(int a) {
            age = a;
            String query = "match (u:User {name: \"" + username + "\", password: \"" + password + "\"}) set u.age = " + a;
            try (Session session = driver.session()) {
		session.run(query);
                System.out.println("set success");
            }
            
        }
       
	public static void main(String[] args) {
		User u = new User("tpp", "7777888");
                //u.setAge(20);

		u.addMovie("333");
                //u.addHistoryMovie("268");
//                u.addMovie("tt0452829");
//                u.addMovie("tt0437740");
//                u.addMovie("tt0460009");
//		u.addMovie("tt0449088");
//		u.hasWatched("tt0449088");
		System.out.println(u.getLikeList()); //username = Tom
		System.out.println(u.getWatchedList());
	}


}
