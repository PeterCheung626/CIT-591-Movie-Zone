/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import static java.awt.PageAttributes.ColorType.COLOR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JToggleButton;
import user.*;
import movie.*;


/**
 *
 * @author yufancheng
 */
public class HomePage extends JPanel {

    private static final JPanel cards = new JPanel(new CardLayout());
    private static final JPanel biggerCards = new JPanel(new CardLayout());
    private static JFrame f;
    private static JLabel pageTitle;
    private static JPanel control;
    private static JButton watchedList;
    private static JButton likeList;
    private static JPanel main;
    private static JButton search;
    private static JLabel pageNum;
    private static Search p;
    private static User user;
    private static ArrayList<String> watchedMovieID;
    private static ArrayList<String> likeMovieID;
    private static ArrayList<Movie> watchedMovieList;
    private static ArrayList<Movie> likeMovieList;
    private static JButton recommendationButton;
    private static JLabel welcome;
    private static JPanel defaultDisplay ;
    private static JToggleButton toggleBtn;
    private static GridBagConstraints gbc;
    private static JPanel left;
    private static JPanel dummyControl;

    private static void defaultDisplay() {
        defaultDisplay.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel greeting = new JLabel("Hi", SwingConstants.CENTER);
        greeting.setFont(new Font("Tahoma", Font.BOLD, 110));
        greeting.setForeground(new java.awt.Color(97, 212, 195));
        welcome = new JLabel("Welcome To");
        welcome.setFont(new Font("Tahoma", Font.BOLD, 110));
        welcome.setForeground(new java.awt.Color(97, 212, 195));
        
        JLabel moviezone = new JLabel("MovieZone");
        moviezone.setFont(new Font("Tahoma", Font.BOLD, 110));
        moviezone.setForeground(new java.awt.Color(97, 212, 195));
        defaultDisplay.add(greeting, c);
        defaultDisplay.add(welcome, c);
        defaultDisplay.add(moviezone, c);
        cards.add(defaultDisplay);
        main.add(cards);
        
    }

    public HomePage(User u) {
        this.setPreferredSize(new Dimension(1000, 800));
        user = u;
        watchedMovieList = new ArrayList<Movie>();
        likeMovieList = new ArrayList<Movie>();
        cards.setBorder(new EmptyBorder(40, 10, 10, 10));
        create();
    }

    //main method
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {               
               //new HomePage();
            }
        });
    }
    
    
    
    
     /**
     * show poster for a single movie
     * @param mv
     * @return 
     */
    public static JPanel addMoviePoster(Movie mv) {
        //System.out.println(mv.getTitle());
        JPanel panel = new JPanel();
        BoxLayout l = new BoxLayout(panel, BoxLayout.PAGE_AXIS);
        panel.setLayout(l);
        JButton de = new JButton("details");
        de.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("movie switched to : " + m.getTitle());
                new MoviePage(mv, user).setVisible(true);
            }
        });
        JLabel lb = new JLabel();
        JLabel title = new JLabel(mv.getTitle());
        title.setPreferredSize(new Dimension(200, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        de.setAlignmentX(Component.CENTER_ALIGNMENT);
        lb.setAlignmentX(Component.CENTER_ALIGNMENT);

        Image image = null;

        try {
            String posterUrl = mv.getPoster();
            URL url = new URL(posterUrl);
            image = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(image);
            ImageIcon newIcon = new ImageIcon(icon.getImage().getScaledInstance(160, 220, Image.SCALE_DEFAULT));
            lb.setIcon(newIcon);
            panel.add(lb);
            panel.add(title);
            panel.add(de);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return panel;
    }
    
    
 
    /**
     * split movies into group of four
     * @param count
     * @param mv
     * @return 
     */
    public static ArrayList<Movie> createMovieList(int count, ArrayList<Movie> mv) {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = count; i < mv.size() && i < count + 4; i++) {
            
            movies.add(mv.get(i));
        }

        return movies;
    }
    

    
    
    public static JPanel addMoviePages(ArrayList<Movie> mv) {
       
        JPanel page = new JPanel();
        page.setLayout(new GridLayout(2,2));
        for (int i = 0; i < mv.size(); i++) {
            page.add(addMoviePoster(mv.get(i)));
        }

        return page;
    }
    
    
    
    public static void create() {
        f = new JFrame();       
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                cards.removeAll();
                f.setVisible(false);
                f.dispose();
            }
        });
        f.setPreferredSize(new Dimension(1000, 800));
  
        JPanel headline = new JPanel();
        headline.setBackground(new java.awt.Color(97, 212, 195)); 
        headline.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        pageTitle = new JLabel("<html><strong>Home Page</strong></html>");
        pageTitle.setForeground(Color.WHITE);
 
        JButton signOut = new JButton("Sign Out");

        headline.add(pageTitle);
        headline.add(Box.createHorizontalStrut(800));
        headline.add(signOut);
        
        main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
        main.add(Box.createVerticalStrut(20));
        
        //sidebar of user
        left = new JPanel();
        left.setLayout(new GridBagLayout());
        left.setMaximumSize(new Dimension(40, 500));
        left.setBackground(new java.awt.Color(97, 212, 195));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,0,20,0);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        search = new JButton("<html><strong>Search Movies</strong></html>");
        watchedList = new JButton("<html><strong>WatchedList</strong></html>");
        likeList = new JButton("<html><strong>LikeList</strong></html>");
        recommendationButton = new JButton("<html><strong>Recommendations</strong></html>");
        
        //set icon to each button
        search.setIcon(new ImageIcon("/Users/shenghu/Desktop/CIT 591/591 project/loupe.png"));
        watchedList.setIcon(new ImageIcon("/Users/shenghu/Desktop/CIT 591/591 project/video-play.png"));
        likeList.setIcon(new ImageIcon("/Users/shenghu/Desktop/CIT 591/591 project/heart.png"));
        recommendationButton.setIcon(new ImageIcon("/Users/shenghu/Desktop/CIT 591/591 project/quality.png"));
        
        //add button to sidebar      
        left.add(search, gbc);
        left.add(watchedList, gbc);
        left.add(likeList, gbc);
        left.add(recommendationButton, gbc);
        left.setVisible(false);
        main.add(left);
        
        //toogle button
        toggleBtn = new JToggleButton();
        toggleBtn.setIcon(new ImageIcon("/Users/shenghu/Desktop/CIT 591/591 project/dragr.png"));
        toggleBtn.setHorizontalAlignment(SwingConstants.LEFT);
        main.add(toggleBtn);
        toggleBtn.setOpaque(false);
        toggleBtn.setContentAreaFilled(false);
        toggleBtn.setBorderPainted(false);
        toggleBtn.setMaximumSize(new Dimension(5, 80));
        
        toggleBtn.addActionListener(new ActionListener() {

         public void actionPerformed(ActionEvent e) {
            JToggleButton tBtn = (JToggleButton)e.getSource();
            //toggleBtn = (JToggleButton)e.getSource();
            if (tBtn.isSelected()) {
                System.out.println("button selected");                
                left.setVisible(true);
                toggleBtn.setIcon(new ImageIcon("/Users/shenghu/Desktop/CIT 591/591 project/dragl.png"));
            } else {
                left.setVisible(false);
                System.out.println("button not selected");
                toggleBtn.setIcon(new ImageIcon("/Users/shenghu/Desktop/CIT 591/591 project/dragr.png"));
            }
         }
      });
        
        
        
        
        //default page of user
        defaultDisplay = new JPanel();
        defaultDisplay();
        
        
        //add prev and next button to control panel
        control = new JPanel();
        dummyControl = new JPanel();
        dummyControl.setPreferredSize(new Dimension(220, 20));
        control.add(dummyControl);

        control.add(new JButton(new AbstractAction("<-Prev") {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.previous(cards);
            }
        }));
        
        control.add(new JButton(new AbstractAction("Next->") {
            
            @Override
            
            public void actionPerformed(ActionEvent e) {               
                CardLayout cl = (CardLayout) cards.getLayout();            
                cl.next(cards);  
            }            
        }));
        

                 
        search.addActionListener(new java.awt.event.ActionListener() {
            //@Override           
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPerformed(evt);
            }           
        });
        
            
        watchedList.addActionListener(new java.awt.event.ActionListener() {
            //@Override            
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                watchedListactionPerformed(evt);
            }           
        });
        
            
        likeList.addActionListener(new java.awt.event.ActionListener() {
            //@Override           
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                likeListactionPerformed(evt);
            }            
        });
        
        
        recommendationButton.addActionListener(new java.awt.event.ActionListener() {
            // @Override      
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recommendationPerformed(evt);
            }           
        });
        
            
        signOut.addActionListener(new java.awt.event.ActionListener() {
            //@Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signOutPerformed(evt);
            }
        });
     
        f.add(headline, BorderLayout.NORTH);
        f.add(main, BorderLayout.CENTER);
        f.add(control, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
         
    }
    

    private static void watchedListactionPerformed(java.awt.event.ActionEvent evt){
        cards.removeAll();
        cards.revalidate();
        cards.repaint();
        watchedMovieList = new ArrayList<Movie>();

        watchedMovieID = user.getWatchedList();
        for (int i = 0; i < watchedMovieID.size(); i++) {
            String singleWatchedMovie = watchedMovieID.get(i).replace("\"", "");
            ExactSearchEngine ese = new ExactSearchEngine(Integer.parseInt(singleWatchedMovie));
            Movie m = ese.getMovie();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            System.out.println(m.getId());
            watchedMovieList.add(m);
        }
        
        int movieNum = watchedMovieList.size();
                                
        for (int i = 0; i < movieNum; i = i + 4) {
            JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));          
            pageNum = new JLabel(String.valueOf(i/4 + 1)+" of "+String.valueOf((movieNum + 3) / 4));
            ArrayList<Movie> newMovie = createMovieList(i, watchedMovieList);     
            p.add(addMoviePages(newMovie));
            p.add(pageNum);
            cards.add(p);           
            main.add(cards);
        }
    }
    
    private static void likeListactionPerformed(java.awt.event.ActionEvent evt){
       
        cards.removeAll();
        cards.revalidate();
        cards.repaint();
        likeMovieList = new ArrayList<Movie>();
        likeMovieID= user.getLikeList();
        
        for (int i = 0; i < likeMovieID.size(); i++) {
            String singleLikedMovie = likeMovieID.get(i).replace("\"", "");
            ExactSearchEngine ese = new ExactSearchEngine(Integer.parseInt(singleLikedMovie));
            Movie m = ese.getMovie();
            //System.out.println(m.getId());
            likeMovieList.add(m);
        }
        int movieNum = likeMovieList.size();
               
        for (int i = 0; i < movieNum; i = i + 4) {     
            JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            pageNum = new JLabel(String.valueOf(i / 4 + 1) + " of " + String.valueOf((movieNum + 3) / 4));
            ArrayList<Movie> newMovie = createMovieList(i, likeMovieList);
            p.add(addMoviePages(newMovie));
            p.add(pageNum);
            cards.add(p);
            main.add(cards);
        }
    }
        
    private static void searchPerformed(java.awt.event.ActionEvent evt) {
        cards.removeAll();
        cards.revalidate();
        cards.repaint();

        defaultDisplay = new JPanel();
        defaultDisplay();
        
        p = new Search(user);          
    }
       
    private static void recommendationPerformed(java.awt.event.ActionEvent evt){
        cards.removeAll();
        cards.revalidate();
        cards.repaint();
        System.out.println(likeMovieList.size());
        System.out.println(watchedMovieList.size());
        ArrayList<Movie> rec = Movie.recommend(likeMovieList, watchedMovieList);
        
        int movieNum = rec.size();
        System.out.println("recommend list:");
        for (Movie m : rec) {
            System.out.println(m.getTitle());
        }                       
        for (int i = 0; i < movieNum; i = i + 4) {
            JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));          
            ArrayList<Movie> newMovie = createMovieList(i, rec);     
            p.add(addMoviePages(newMovie));
            cards.add(p);           
            main.add(cards);
        }
        
    }
    
    private static void signOutPerformed(java.awt.event.ActionEvent evt){
       cards.removeAll();
       SignIn sign_in = new SignIn();
       sign_in.setVisible(true);
       f.dispose();
    }
}

