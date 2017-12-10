
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import static javafx.scene.paint.Color.rgb;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shenghu
 */
public class MoviePanel extends JPanel{
    //private static final JPanel cards = new JPanel(new CardLayout());
    
    public MoviePanel(Movie m) {
        BoxLayout l = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(l);
        JLabel title = new JLabel(m.getTitle());
        JLabel poster = new JLabel();
        JButton details = new JButton("details");
        title.setPreferredSize(new Dimension(200, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);       
        poster.setAlignmentX(Component.CENTER_ALIGNMENT);
        details.setAlignmentX(Component.CENTER_ALIGNMENT);
        try {
            URL url = new URL(m.getPoster());
            ImageIcon icon = new ImageIcon(ImageIO.read(url));
            ImageIcon newIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 150, Image.SCALE_DEFAULT));
            poster.setIcon(newIcon);
        } catch(IOException ex) {

        }   
        this.add(poster);      
        this.add(title);
        this.add(details);
        this.setBackground(new Color(204,204,204));
        
    }
    
    
    /*
    public MoviePanel(String t, String p) {
    		BoxLayout l = new BoxLayout(this, BoxLayout.PAGE_AXIS);
    		
    		this.setLayout(l);
        //JLabel title = new JLabel("<html>"+ t + "</html>");
    		JLabel title = new JLabel(t);
        JLabel poster = new JLabel();
        JButton details = new JButton("details");
        title.setPreferredSize(new Dimension(200, 50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);       
        poster.setAlignmentX(Component.CENTER_ALIGNMENT);
        details.setAlignmentX(Component.CENTER_ALIGNMENT);
        	try {
        		URL url = new URL(p);
        		ImageIcon icon = new ImageIcon(ImageIO.read(url));
        		ImageIcon newIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 150, Image.SCALE_DEFAULT));
            poster.setIcon(newIcon);
        	} catch(IOException ex) {

        }   
        	this.add(poster);      
        this.add(title);
        this.add(details);
        
    }
    */



    
}
