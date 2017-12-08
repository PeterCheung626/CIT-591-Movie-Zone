/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
	
package moviezone;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author yufancheng
 */
public class SearchResult extends JPanel {

    private static final Random random = new Random();
    private static final JPanel cards = new JPanel(new CardLayout());
    //private static final JButton posters = new JButton();
    private final String name;
    //private final URL url;

    public SearchResult(String name) {
        this.name = name;
        //this.url = url;
        this.setPreferredSize(new Dimension(604, 420));
       
       //p this.setBackground(new Color(random.nextInt()));
        //this.add(new JButton.setIcon(name));
    }

    @Override
    public String toString() {
        return name;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                ArrayList<String> imageUrls = new ArrayList<>();
                imageUrls.add("https://images-na.ssl-images-amazon.com/images/M/MV5BNzdmZTk4MTktZmExNi00OWEwLTgxZDctNTE4NWMwNjc1Nzg2XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_UX182_CR0,0,182,268_AL_.jpg");
                
                imageUrls.add("https://images-na.ssl-images-amazon.com/images/M/MV5BMjA5Njk3MjM4OV5BMl5BanBnXkFtZTcwMTc5MTE1MQ@@._V1_UY268_CR0,0,182,268_AL_.jpg");
                imageUrls.add("https://images-na.ssl-images-amazon.com/images/M/MV5BYWQwOWVkMGItMDU2Yy00YjIzLWJkMjEtNmVkZjE3MjMwYzEzXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg");
                imageUrls.add("https://images-na.ssl-images-amazon.com/images/M/MV5BNGRmOWY0MGUtNTVhMy00NzRlLTljNDAtNTBiNTRlODgxNmY2XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_UY268_CR4,0,182,268_AL_.jpg");
                imageUrls.add("https://images-na.ssl-images-amazon.com/images/M/MV5BY2FmZTY5YTktOWRlYy00NmIyLWE0ZmQtZDg2YjlmMzczZDZiXkEyXkFqcGdeQXVyNjg4NzAyOTA@._V1_UX182_CR0,0,182,268_AL_.jpg");
                imageUrls.add("https://images-na.ssl-images-amazon.com/images/M/MV5BMTU0ODQ4NDg2OF5BMl5BanBnXkFtZTgwNzczNTE4OTE@._V1_UX182_CR0,0,182,268_AL_.jpg");
                imageUrls.add("https://images-na.ssl-images-amazon.com/images/M/MV5BMTM2NjAxOTQzNl5BMl5BanBnXkFtZTcwMjk4NzU3MQ@@._V1_UY268_CR8,0,182,268_AL_.jpg");
                imageUrls.add("https://images-na.ssl-images-amazon.com/images/M/MV5BNzc2ZThkOGItZGY5YS00MDYwLTkyOTAtNDRmZWIwMGRhYTc0L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_UX182_CR0,0,182,268_AL_.jpg");
                
                
                
                create(imageUrls);
            }
        });
    }
    public static JPanel addPoster(String imageAddress){
        JPanel panel = new JPanel();
        BoxLayout l = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(l);
        JButton de = new JButton("details");
        
        JLabel lb = new JLabel();
        JLabel title = new JLabel("title");
        title.setPreferredSize(new Dimension(200, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        de.setAlignmentX(Component.CENTER_ALIGNMENT);
        lb.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        Image image = null;
        
    try {
            //JLabel lb1 = new JLabel("jLabel2");
           String posterUrl = imageAddress;
            URL url = new URL(posterUrl);
            image = ImageIO.read(url);
           ImageIcon icon = new ImageIcon(image);
          ImageIcon newIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 150, Image.SCALE_DEFAULT));
            lb.setIcon(newIcon);
            //lb.setIcon(new ImageIcon(image)); 
            
            
            panel.add(lb);
            panel.add(title);
            panel.add(de);
            //panel.add(title);
    }catch (Exception e){
     e.printStackTrace();

                
    }
       return panel;
    }
    
    
    public static ArrayList<String> createList(int count, ArrayList<String> img){
       //int count  = 0;
       ArrayList<String> newImg = new ArrayList<>();
       for(int i = count; i < count + 4; i++){
        newImg.add(img.get(i));
       }
       
       return newImg;
    }
    /*
    This method serves to create a panel that contians four posters
    */
    public static JPanel addPages(ArrayList<String> img){
         JPanel page = new JPanel();
         page.setLayout(new GridLayout(1,4));
         
         for(int i = 0; i < 4; i++){
            page.add(addPoster(img.get(i)));
         }
         
         return page;
    }

    public static void create(ArrayList<String> imgUrls) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel head = new JPanel();
        JPanel headline = new JPanel();
        head.setLayout(new BoxLayout(head, BoxLayout.Y_AXIS));
        headline.setLayout(new FlowLayout(FlowLayout.LEFT));
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel searchPrompt = new JLabel("Enter the movie Id, name or keyword:");
        JLabel pageTitle = new JLabel("Home Page");
       // JTextField searchTerm = new JTextField("Enter Search Term Here", 20);
        headline.setBackground(new java.awt.Color(97, 212, 195));
        pageTitle.setForeground(new java.awt.Color(255, 255, 255));
       pageTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
       searchPanel.add(searchPrompt);
        headline.add(pageTitle);
        head.add(headline);
      //  head.setAlignmentX(pageTitle.LEFT_ALIGNMENT);
        // f.add(head);
         
        int count = 0;
        //static int total = imgUrls.size() ;
        for (int i = 0; i < imgUrls.size(); i = i + 4) {
            
            String imgAddress = imgUrls.get(i);
            SearchResult p = new SearchResult("Panel " + String.valueOf(i));
           
            //if(i != imgUrls.size() - 1){
           // p.add(addPoster(imgAddress));
            //count = i;
          // for(int  j = i; j < i + 4; j++){
            ArrayList newImg = createList(i, imgUrls);
            p.add(addPages(newImg));
            
            //}
            /*ArrayList newImg = createList(count, imgUrls);
            count += 4;
            p.add(addPages(newImg));*/
            
            if(i != imgUrls.size()){
               // cards.add(searchTerm);
                
            cards.add(p, p.toString());
            //count++;
            }
            count += 4;
        }
        /*for(int i = 0; i < imgUrls.size() - count; i++){
            String imgAddress = imgUrls.get(i);
            SearchResult p = new SearchResult("Panel " + String.valueOf(i));
             ArrayList newImg = createList(count + i, imgUrls);
            p.add(addPages(newImg));
            cards.add(p, p.toString());
        }*/
        //JPanel searchPanel = new JPanel();
        JTextField searchTerm = new JTextField("Enter Search Term Here", 20);
        searchPanel.add(searchTerm);
        head.add(searchPanel);
        JPanel control = new JPanel();
        control.add(new JButton(new AbstractAction("<-Prev") {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.previous(cards);
            }
        }));
        control.add(new JButton(new AbstractAction("Next\u22b3") {
            
            @Override
            
            public void actionPerformed(ActionEvent e) {
                
                CardLayout cl = (CardLayout) cards.getLayout();
                //if(count != total) {
                cl.next(cards);
               // )
            //}
                
            }
        }));
        f.add(head, BorderLayout.NORTH);
       // f.add(searchPanel, BorderLayout.NORTH);
        f.add(cards, BorderLayout.CENTER);
        f.add(control, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
