import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shenghu
 */



public class SimilarMoviesPanel extends JPanel{
	private static final JPanel cards = new JPanel(new CardLayout());
	/*
	public SimilarMoviesPanel() {
		this.setLayout(new GridLayout(1, 4));
		String t1 = "Jack Reacher: Never Go Back";
		String p1 = "https://image.tmdb.org/t/p/w1280//IfB9hy4JH1eH6HEfIgIGORXi5h.jpg";
		MoviePanel m1 = new MoviePanel(t1, p1);
		
		String t2 = "Avatar";
		String p2 = "https://image.tmdb.org/t/p/w1280//kmcqlZGaSh20zpTbuoF0Cdn07dT.jpg";
		MoviePanel m2 = new MoviePanel(t2, p2);
		
		String t3 = "Spider-Man 3";
		String p3 = "https://image.tmdb.org/t/p/w1280//rLwhewWypBL88VFGf6haSdnNj2U.jpg";
		MoviePanel m3 = new MoviePanel(t3, p3);
		
		String t4 = "The Avengers";
		String p4 = "https://image.tmdb.org/t/p/w1280//cezWGskPY5x7GaglTTRN4Fugfb8.jpg";
		MoviePanel m4 = new MoviePanel(t4, p4);

		this.add(m1);
		this.add(m2);
		this.add(m3);
		this.add(m4);
		//this.setSize(new Dimension(1000, 800));

	}
*/
	
	public static void main(String[] args) throws Exception {
		//SwingUtilities.invokeAndWait(new Runnable() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
                             create();
			}
		});
	}
	
	private static void create() {
            JFrame f = new JFrame();
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            for (int i = 0; i < 9; i++) {
                    SimilarMoviesPanel p = new SimilarMoviesPanel();
                            JButton btn = new JButton(String.valueOf(i));
                            p.add(btn);
                cards.add(p);
            }
            JPanel control = new JPanel();
            control.add(new JButton(new AbstractAction("\u22b2Prev") {

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
            f.add(cards, BorderLayout.CENTER);
            f.add(control, BorderLayout.SOUTH);
            f.pack();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
    }
}

