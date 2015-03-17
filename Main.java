import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import java.util.*;
import java.io.*;




public class Main extends JPanel {
	
	public void paintComponent(Graphics g) {
	
	super.paintComponent(g);
	
	double x_start = 268;
	double y_start = 0;
	
	int tile_height = 46;
	int tile_width = 68;
	
	int count = 5;
	
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < count; j++) {
			double x =  ((i*tile_width) + x_start - j*tile_width) ;
			double y =  ((i*tile_height/2) + y_start + j*tile_height/2);
				
			createHexagon(g, x, y);
		}
		count++;
	}	
	
	count--;
	
	double x_start2 = 540;
	double y_start2 = 92;
	
	for (int i = 5; i < 9; i++) {
		count--;
		for (int j = 0; j < count; j++) {
			double x = (x_start2 - j*tile_width);
			double y = ((i*tile_height) - y_start2 + j*tile_height/2);
			
			createHexagon(g, x, y);
		}
	}
	
	int x_pos = 6;
	int y_pos = 6;
	
	createUnit(g, x_pos, y_pos);
	
	

	
	Scanner in = new Scanner(System.in);
	int test = Integer.parseInt(in.next());
	
	if( test == 1) {
		x_pos = 7;
		y_pos = 7;
		createUnit(g, x_pos, y_pos);
	
	}
	
	
	
	
	
	
	
	
	
	
	
}
	
	public void createUnit(Graphics g, int x_pos, int y_pos) {
		int tile_height = 46;
		int tile_width = 68;
		
		int x = 0;
		int y = 0;
		
		
		if (x_pos < 5) {
			x = 12 + x_pos*tile_width ;
			y = 78 - (x_pos*tile_height/2) + (y_pos*tile_height);
		} else {
			x = 12 + x_pos*tile_width ;
			y = 78 - (69) + ((x_pos-5)*tile_height/2) + (y_pos*tile_height);
		}
		
		Graphics2D g2 = (Graphics2D) g;

		Image img1 = Toolkit.getDefaultToolkit().getImage("goblin.png");
		g2.drawImage(img1, x, y, this);
		
		
		
		
		
		
		
		
		//ImageIcon goblin = new ImageIcon("goblin.png");
		//goblin.paintIcon(this, g, x, y);
		
		
		
		//goblin.paintIcon(null, g, x, y);
		
		//goblin.paintIcon(this, g, 100, 100);
		
		
		
		
		
	}
	
	
	
	public void createHexagon(Graphics g, double x_start, double y_start) {
		int x = (int) x_start;
		int y = (int) y_start;
		
		ImageIcon h = new ImageIcon("tile.png");
		h.paintIcon(this, g, x, y);
	}
	
	
	
	
	
	
   public static void main(String[] args) {
	JFrame frame = new JFrame();
    frame.setTitle("Legends Of Arborea");
    frame.setSize(649, 477);
    frame.addWindowListener(new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			System.exit(0);
        }
	});
	Container contentPane = frame.getContentPane();
	contentPane.add(new Main());
	frame.setVisible(true);
	}
}