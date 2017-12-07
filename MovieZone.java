/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviezone;
import java.awt.*;   
import javax.swing.*; 
/**
 *
 * @author mayitian
 */
public class MovieZone {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f = new JFrame("Movie Zone"); // Make new JFrame object  
        f.setSize(400, 300);                   // Set size of window
        f.setVisible(true);                    // Show window
        /*
        JLabel L = new JLabel("Search Page");
	f.getContentPane().add(L);     // Stick label L on contentPane   
        f.setVisible(true);
        
        JPanel P = new JPanel();                  // Make a JPanel;       
        P.add(L);                   // Add lable L to JPanel P
        f.getContentPane().add(P);  // Add panel P to JFrame f  
        f.setSize(400, 300);
        f.setVisible(true);
        */
        JTextField search = new JTextField();
        f.getContentPane().add(search);
        search.setEditable(true);
        search.setSize(200, 20);
        search.setVisible(true);
        search.setText("search for movies");
        String searchKey = search.getText();
        System.out.println(searchKey);
        
        JButton submit = new JButton("search! ");
        f.getContentPane().add(submit);
        f.setSize(50, 60);
        f.setVisible(true);
        
    }
    
}
