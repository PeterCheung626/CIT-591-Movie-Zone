/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author yufancheng
 */
public class Search extends JPanel {


    private static final JPanel cards = new JPanel(new CardLayout());
   // private static final JPanel head = new JPanel(new )
    private static JButton pageTitle;
    private static JTextField text;
    private static int choice;
    private static JPanel searchPanel;
    private static JLabel pageNum;
  

    public Search() {
        
        this.setLayout(null);
        this.setPreferredSize(new Dimension(1000, 800));
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
               
                ArrayList<Movie> Movies = new ArrayList<>();
               
                Search test = new Search();
                
               // create();
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
                new MoviePage(mv).setVisible(true);
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
            //JLabel lb1 = new JLabel("jLabel2");
            String posterUrl = mv.getPoster();
            URL url = new URL(posterUrl);
            image = ImageIO.read(url);
            ImageIcon icon = new ImageIcon(image);
            ImageIcon newIcon = new ImageIcon(icon.getImage().getScaledInstance(160, 220, Image.SCALE_DEFAULT));
            lb.setIcon(newIcon);
            //lb.setIcon(new ImageIcon(image)); 

            panel.add(lb);
            panel.add(title);
            panel.add(de);
            //panel.add(title);
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
        //int count  = 0;
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
        //page.setSize(new Dimension(200, 200));
        page.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 40));
       // page.setLayout(new BorderLayout());
        for (int i = 0; i < mv.size(); i++) {
            page.add(addPoster(mv.get(i)));
        }

        return page;
    }

    public static void create() {
        JFrame f = new JFrame();
        f.setPreferredSize(new Dimension(1000, 800));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // JPanel mainPanel = new JPanel();
        JPanel head = new JPanel();
        JPanel headline = new JPanel();
        headline.setPreferredSize(new Dimension(1000, 30));
        head.setLayout(new BoxLayout(head, BoxLayout.Y_AXIS));
        //headline.setLayout(new BoxLayout(headline, BoxLayout.X_AXIS));
        headline.setLayout(new FlowLayout(FlowLayout.LEADING));
        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel searchPrompt = new JLabel("Enter the movie Id, name or keyword:");
        
        String[] searchFilter = { "ID", "title", "keyword"};
        JComboBox searchList = new JComboBox(searchFilter);
        
        pageTitle = new JButton("<html><strong>Search Movies</strong>");
        // JTextField searchTerm = new JTextField("Enter Search Term Here", 20);
        headline.setBackground(new java.awt.Color(97, 212, 195));
        pageTitle.setBackground(new java.awt.Color(97, 212, 195));
       // pageTitle.setForeground(new java.awt.Color(97, 212, 195));
        pageTitle.setOpaque(true);
        pageTitle.setBorderPainted(false);
        //pageTitle.setAlignmentX(pageTitle.LEFT_ALIGNMENT);
        //JButton logOut = new JButton("Sign Out");
        searchPanel.add(searchPrompt);
        searchPanel.add(searchList);
        headline.add(pageTitle);
        //headline.add(Box.createHorizontalStrut(600));
        //headline.add(logOut);
        head.add(headline);
        text = new JTextField(20);
       
        searchPanel.add(text);
        head.add(searchPanel);
        //String searchTerm = text.getText();
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
                
                if (choice == 0) {
                    //search by id
                    ExactSearchEngine ese = new ExactSearchEngine(Integer.parseInt(searchTerm));
                    ese.connect();
                    Movie target = ese.getMovie();
                    if(target == null) {
                        new PageNotFound().setVisible(true);
                    } else {
                        new MoviePage(target).setVisible(true);
                    }
                    
                    // switch to another window 
                    
                } else if (choice == 1) {
                    //search by title
                    BlurSearchEngine ese = new BlurSearchEngine(text.getText());
                    ese.connect();
                    m.addAll(ese.getMovies());
                    
                } else {
                    //search by keywords
                    BlurSearchEngine ese = new BlurSearchEngine(text.getText(), 2);
                    ese.connect();
                    m.addAll(ese.getMovies());
                }
                
                int movieNum = m.size();
                int count = 0;
        //static int total = imgUrls.size() ;
                
        for (int i = 0; i < movieNum; i = i + 4) {

            Movie mv = m.get(i);
           
            JPanel p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
           // p.setLayout(new GridLayout(2,2, 50, 50));
            count++;
             pageNum = new JLabel(String.valueOf(i/4 + 1)+" of "+String.valueOf(movieNum/4 + 1 ));
              JPanel pageNumber = new JPanel();
            //pageNumber.add(page)
            ArrayList<Movie> newMovie = createList(i, m);
            //searchPanel.add(pageNum);
            p.add(addPages(newMovie));
            p.add(pageNum);
            cards.add(p);
        }
        
         //searchPanel.add(pageNum);
            } 
           
        }));
       // head.add(searchPanel); //Add Search Prompt

      //  head.setAlignmentX(pageTitle.LEFT_ALIGNMENT);
        // f.add(head);
        /*
        int count = 0;
        //static int total = imgUrls.size() ;
        for (int i = 0; i < m.size(); i = i + 4) {
            Movie mv = m.get(i);
            SearchResultV3 p = new SearchResultV3();
            //if(i != imgUrls.size() - 1){
            // p.add(addPoster(imgAddress));
            //count = i;
            // for(int  j = i; j < i + 4; j++){
            ArrayList newMovie = createList(i, m);
            p.add(addPages(newMovie));
            //}
            /*ArrayList newImg = createList(count, imgUrls);
             count += 4;
             p.add(addPages(newImg));*/

            //if(i != imgUrls.size()){
            // cards.add(searchTerm);
            //cards.add(p, p.toString());
            //count++;
            // }
           // count += 4;
        //}
         
        /*for(int i = 0; i < imgUrls.size() - count; i++){
         String imgAddress = imgUrls.get(i);
         SearchResult p = new SearchResult("Panel " + String.valueOf(i));
         ArrayList newImg = createList(count + i, imgUrls);
         p.add(addPages(newImg));
         cards.add(p, p.toString());
         }*/
        //JPanel searchPanel = new JPanel();
       /*
         filter.add(new JButton(new AbstractAction("Top Rated"){
         @Override
         public void actionPerformed(ActionEvent e) {
         CardLayout cl = (CardLayout) cards.getLayout();
         cl.previous(cards);
         }
         }));
        
         filter.add(new JButton(new AbstractAction("Upcoming"){
         @Override
         public void actionPerformed(ActionEvent e) {
         CardLayout cl = (CardLayout) cards.getLayout();
         cl.previous(cards);
         }
         }));
         
         filter.add(new JButton(new AbstractAction("Most Popular"){
         @Override
         public void actionPerformed(ActionEvent e) {
         CardLayout cl = (CardLayout) cards.getLayout();
         cl.previous(cards);
         }
         }));
         */
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
             
                cl.next(cards);
               

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
        /*public void pageTitleactionPerformed(java.awt.event.ActionEvent evt){
             
        }*/

}
