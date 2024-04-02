

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class maxFrame extends JFrame {
	maxFrame() {
		this.setTitle("Ecommerce App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(true);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		ImageIcon img = new ImageIcon("src/img/logo.jpg");
		this.setIconImage(img.getImage());
	}
}
