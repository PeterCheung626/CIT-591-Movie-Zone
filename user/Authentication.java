/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shenghu
 */

package user;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import User;


public class Authentication {
    private Driver driver;
    
    public Authentication() {
	driver = GraphDatabase.driver("bolt://localhost");		
    }
    
    //sign-in authentication
    public User signIn(String username, String password) {
        if(username.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Please enter a valid username.");
            return null; 
        } else if(password.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Password can not be empty.");
            return null;
        } else {
            String query1 = "match (u:User {name: \"" + username + "\" }) return u.name as name";
            //test username correct
            try (Session session = driver.session()) {
                StatementResult result = session.run(query1);
                result.next().get("name");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Username does not exist.");
                return null;           
            }

            //test password correct
            String query2 = "match (u:User {name: \"" + username + "\", password: \"" + password + "\" }) return u.name as name";
            try (Session session = driver.session()) {
                StatementResult result = session.run(query2);
                result.next().get("name");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Password is incorrect!");
                return null;
            }
        }

        return new User(username, password);       
    }
    
    //sign-up authentication
    public User signUp(String username, String password, String confirmedPassword, String age, String q1, String q2, String q3) {
        if(username.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Username can not be empty.");
            return null; 
        } else if(password.trim().length() < 8) {
            JOptionPane.showMessageDialog(null, "Password can not be less than 8 characters.");
            return null; 
        } else if(!confirmedPassword.trim().equals(password)) {
            JOptionPane.showMessageDialog(null, "The Passwords you entered do not match.");
            return null; 
        } else if(age.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Age can not be empty.");
            return null; 
        } else if(q1.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "First security question can not be empty.");
            return null; 
        } else if(q2.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Second security question can not be empty.");
            return null; 
        } else if(q3.trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Third security question can not be empty.");
            return null; 
        } else {
            String query1 = "match (u:User {name: \"" + username + "\" }) return u.name as name";
            try (Session session = driver.session()) {
                StatementResult result = session.run(query1);
                result.next().get("name");
            } catch (Exception e) {          
                String query = "create (u:User {name: \"" + username + "\", password: \"" + password + "\"})";
                try (Session session = driver.session()) {
                    session.run(query);
                }
                User u = new User(username, password);
                JOptionPane.showMessageDialog(null, "Sign up successfully. Welcome to join in Movie Zone!");
                return u;    
            }
            JOptionPane.showMessageDialog(null, "Username has already been enrolled.");
            return null; 
        }       
    }
     
}
    
    
    

