import java.awt.*;
import java.awt.event.*;
import javax.swing.*;





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
}
	
	
	public void createHexagon(Graphics g, double x_start, double y_start) {
		int x = (int) x_start;
		int y = (int) y_start;
		
		ImageIcon h = new ImageIcon("C:\\Users\\Joeri Bes\\Desktop\\Datastructuren\\Legends Of Arborea\\tile.png");
		h.paintIcon(this, g, x, y);
	}
	
	
	
	
	
	
   public static void main(String[] args) {
	JFrame frame = new JFrame();
    frame.setTitle("Hexagon Tiles");
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