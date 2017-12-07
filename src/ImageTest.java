
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yufancheng
 */

    public class ImageTest extends JApplet{
     @Override
    public void init(){
    this.getContentPane().setLayout(new FlowLayout());
    ImageIcon icon = new ImageIcon("https://images-na.ssl-images-amazon.com/images/M/MV5BNzdmZTk4MTktZmExNi00OWEwLTgxZDctNTE4NWMwNjc1Nzg2XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_UX182_CR0,0,182,268_AL_.jpg");
    JLabel lb1 =  new JLabel("JLabel2");
    
    lb1.setIcon(new ImageIcon(getClass().getResource("https://images-na.ssl-images-amazon.com/images/M/MV5BNzdmZTk4MTktZmExNi00OWEwLTgxZDctNTE4NWMwNjc1Nzg2XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_UX182_CR0,0,182,268_AL_.jpg")));
    
    add(lb1);
    super.init();
    
    }
}


