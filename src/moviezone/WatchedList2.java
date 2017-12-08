/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviezone;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author yufancheng
 */
public class WatchedList2 extends JPanel{
     JPanel myPanel;

    public WatchedList2 (JPanel thisPanel) {
        myPanel = thisPanel;
        //this.setLocationRelativeTo(null);
    }
    
     public void addLabel(JPanel myResults) {
       
     }
     
     
     public static void main(String[] args){
     JFrame frame = new JFrame("Doctor Zhivago");//Make a frame
     GridLayout layout =  new GridLayout(1,4);
     /*frame.setLayout(new java.awt.GridLayout());
     layout.setHgap(5);
     layout.setVgap(5);*/
     frame.setLayout(new GridLayout(1,4));
     frame.setLocationRelativeTo(null);
    frame.setSize(600, 600);//Give it a size
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//Make it go away on close
    JPanel panel = new JPanel();//Make a panel
     GridBagLayout panelLayout = new GridBagLayout();
     /*layout.setHgap(10);
     layout.setVgap(10);*/
    panel.setLayout(panelLayout);
    GridBagConstraints gbc = new GridBagConstraints();
   // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
         // EmptyBorder panelBorder = new EmptyBorder(5, 0,0, 0);
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    frame.add(panel);//Add it to your frame
    frame.add(panel1);//Add it to your frame
      JButton bt1 = new JButton();
      //bt1.setPreferredSize(new Dimension(400, 400));
      JLabel lb1 = new JLabel("Doctor Zhivago");
      //lb1.setLocationRelativeTo(bt1);
      JButton bt2 = new JButton();
      JLabel lb2 = new JLabel("Doctor Zhivago2");
      JButton bt3 = new JButton();
      JLabel lb3 = new JLabel("Doctor Zhivago");
      Image image = null;
      
      Image image2 = null;
      Image image3 = null;
    try {
            //JLabel lb1 = new JLabel("jLabel2");
           String posterUrl = "https://images-na.ssl-images-amazon.com/images/M/MV5BNzdmZTk4MTktZmExNi00OWEwLTgxZDctNTE4NWMwNjc1Nzg2XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_UX182_CR0,0,182,268_AL_.jpg";
            URL url = new URL(posterUrl);
            image = ImageIO.read(url);
            image2 = ImageIO.read(url);
            //Image newimg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
            
            bt1.setIcon(new ImageIcon(image)); 
             bt2.setIcon(new ImageIcon(image2));
            // bt3.setIcon(new ImageIcon(image2));
    
              panel.add(bt1,gbc);
              //panel.addGap(0,0,0);
              panel.add(lb1,gbc);
             
              panel1.add(bt2);
              panel1.add(lb2);
              //panel2.add(bt3);
              //panel2.add(lb3);
              
              //movePoster(lb1);
        } catch (IOException e) {
            e.printStackTrace();

                 ImageIcon I22 = new ImageIcon();
                        bt1.setIcon(I22);
        }

    frame.setVisible(true);//Show the frame
     
     }
}
