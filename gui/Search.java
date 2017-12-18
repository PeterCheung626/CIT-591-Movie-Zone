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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import user.*;
import movie.*;

/**
 *
 * @author yufancheng
 */
public class Search extends JPanel {


    private static final JPanel cards = new JPanel(new CardLayout());
    private static JLabel pageTitle;
    private static JTextField text;
    private static int choice; //choose which item in searchlist
    private static JPanel searchPanel;
    private static JLabel pageNum;
    private static User user;
  

    public Search(User u) {
        
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1000, 800));
        
        //this.setIcon(new ImageIcon("/Users/shenghu/Desktop/CIT 591/591 project/background.png"));
        user = u;
        create();
        
    }

    /* @Override
     public String toString() {
     return name;
     }*/
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

            }
        });
    }

    /**
     * show poster for a single movie
     * @param mv
     * @return 
     */
    public static JPanel addPoster(Movie mv) {
        System.out.println(mv.getTitle());
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
    public static ArrayList<Movie> createList(int count, ArrayList<Movie> mv) {
        ArrayList<Movie> movie = new ArrayList<>();
        for (int i = count; i < mv.size() && i < count + 4; i++) {
            
            movie.add(mv.get(i));
        }

        return movie;
    }
    
    /*
     This method serves to create a panel that contians four posters
     */

    public static JPanel addPages(ArrayList<Movie> mv) {
       
        JPanel page = new JPanel();
        page.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 40));
        for (int i = 0; i < mv.size(); i++) {
            page.add(addPoster(mv.get(i)));
        }

        return page;
    }

    public static void create() {
        JFrame f = new JFrame();
        f.setPreferredSize(new Dimension(1000, 800));
//        JLabel contentPane = new JLabel();
//        contentPane.setIcon(new ImageIcon("/Users/shenghu/Desktop/CIT 591/591 project/loupe.png"));
//        contentPane.setLayout( new BorderLayout() );
//        f.setContentPane( contentPane );

        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                cards.removeAll();
                f.setVisible(false);
                f.dispose();
            }
       });
        
        JPanel head = new JPanel();
        JPanel headline = new JPanel();
        JComboBox dropdown = new JComboBox();
        dropdown.setEditable(true);
        dropdown.addItem("Up-coming");
        dropdown.addItem("Top-rated");
        dropdown.addItem("Popular");
        
        headline.setPreferredSize(new Dimension(1000, 30));
        head.setLayout(new BoxLayout(head, BoxLayout.Y_AXIS));
        headline.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel searchPrompt = new JLabel("Enter the movie Id, name or keyword:");
        
        String[] searchFilter = { "ID", "title", "keyword"};
        JComboBox searchList = new JComboBox(searchFilter);
        
        pageTitle = new JLabel("<html><strong>Search Movies</strong>");

        headline.setBackground(new java.awt.Color(97, 212, 195));
        pageTitle.setBackground(new java.awt.Color(97, 212, 195));
        pageTitle.setOpaque(true);

        searchPanel.add(dropdown);
        searchPanel.add(searchPrompt);
        searchPanel.add(searchList);
        headline.add(pageTitle);
        head.add(headline);
        text = new JTextField(20);
       
        searchPanel.add(text);
        head.add(searchPanel);
        

        ArrayList<Movie> def_origin = new ArrayList<>();
        //default to display up-coming movies
        BlurSearchEngine bse_d = new BlurSearchEngine("upcoming", 1);
        def_origin.addAll(bse_d.getMovies());
        for (int i = 0; i < def_origin.size(); i = i + 4) {
            JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            pageNum = new JLabel(String.valueOf(i / 4 + 1) + " of " + String.valueOf((def_origin.size() + 3) / 4));
            pageNum.setAlignmentX(Component.CENTER_ALIGNMENT);
            //pageNumber.add(page)
            ArrayList<Movie> newMovie = createList(i, def_origin);
            searchPanel.add(pageNum);
            p.add(addPages(newMovie));
            p.add(pageNum);
            cards.add(p);
        }   
                
        //add filters for choosing default page before searching             
        dropdown.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e) {
                cards.removeAll();
                ArrayList<Movie> def_m = new ArrayList<>();
                JComboBox combo = (JComboBox)e.getSource();
                Object selected = combo.getSelectedItem();
                if(selected.toString().equals("Up-coming")) {
                    BlurSearchEngine bse1 = new BlurSearchEngine("upcoming", 1);
                    System.out.println(bse1.getMovies().size());
                    for (Movie b : bse1.getMovies()) {
                        System.out.println(b.getTitle());

                    }  
                    def_m.addAll(bse1.getMovies());
                } else if(selected.toString().equals("Top-rated")) {
                    BlurSearchEngine bse2 = new BlurSearchEngine("top_rated", 1);
                    System.out.println(bse2.getMovies().size());
                    for (Movie b : bse2.getMovies()) {
                        System.out.println(b.getTitle());
                    }
                    def_m.addAll(bse2.getMovies());   
                } else {
                    BlurSearchEngine bse3 = new BlurSearchEngine("popular", 1);
                    System.out.println(bse3.getMovies().size());
                    for (Movie b : bse3.getMovies()) {
                        System.out.println(b.getTitle());

                    }
                    def_m.addAll(bse3.getMovies());
                }
                for (int i = 0; i < def_m.size(); i = i + 4) {
                    JPanel p = new JPanel();
                    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
                    pageNum = new JLabel(String.valueOf(i/4 + 1)+" of "+String.valueOf((def_m.size() + 3) / 4));
                    pageNum.setAlignmentX(Component.CENTER_ALIGNMENT);
                    //pageNumber.add(page)
                    ArrayList<Movie> newMovie = createList(i, def_m);
                    searchPanel.add(pageNum);
                    p.add(addPages(newMovie));
                    p.add(pageNum);
                    cards.add(p);
                }                                                 
            }
        });

 

        searchList.setEditable(true);
        searchList.addActionListener(new ActionListener() {   
            public void actionPerformed(ActionEvent e) {
                JComboBox combo = (JComboBox)e.getSource();
                choice = combo.getSelectedIndex();  
            }   
        });
        searchPanel.add(new JButton(new AbstractAction("search") {
            ArrayList<Movie> m;

            @Override
            public void actionPerformed(ActionEvent e) {
                cards.removeAll();
                String searchTerm = text.getText();
                System.out.println(searchTerm);
                
                ArrayList<Movie> m = new ArrayList<>();
                System.out.println(choice);
                if (choice == 0) {
                    //search by id
                    try {
                        ExactSearchEngine ese = new ExactSearchEngine(Integer.parseInt(searchTerm));
                        
                        Movie target = ese.getMovie();
                        if (target == null) {
                            new PageNotFound().setVisible(true);
                        } else {
                            new MoviePage(target, user).setVisible(true);
                        }
                   
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid movie ID");
                    }
                    // switch to another window 
                    
                } else if (choice == 1) {
                    //search by title
                    BlurSearchEngine ese = new BlurSearchEngine(text.getText());
                    ArrayList<Movie> movies = ese.getMovies();
                    if(movies.size() == 0) {
                        new PageNotFound().setVisible(true);
                    } else {
                        m.addAll(movies);
                    }                                        
                } else {
                    //search by keywords
                    BlurSearchEngine ese = new BlurSearchEngine(text.getText(), 2);
                    ArrayList<Movie> movies = ese.getMovies();
                    if(movies.size() == 0) {
                        new PageNotFound().setVisible(true);
                    } else {
                        m.addAll(movies);
                    }    
                }
                
                int movieNum = m.size();
                
                for (int i = 0; i < movieNum; i = i + 4) {
                    JPanel p = new JPanel();
                    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
                    pageNum = new JLabel(String.valueOf(i / 4 + 1) + " of " + String.valueOf((movieNum + 3) / 4));
                    pageNum.setAlignmentX(Component.CENTER_ALIGNMENT);
                    ArrayList<Movie> newMovie = createList(i, m);
                    searchPanel.add(pageNum);
                    p.add(addPages(newMovie));
                    p.add(pageNum);
                    cards.add(p);
                }
        } 
           
        }));
        
         
        //add prev and next button to control panel
        JPanel control = new JPanel();
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
        
        
        
        f.add(head, BorderLayout.NORTH);
        f.add(cards, BorderLayout.CENTER);
        f.add(control, BorderLayout.SOUTH);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
    }
        

}
