package ecommerce;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		//GraphicsDevice device = graphics.getDefaultScreenDevice();
		JFrame frame = new JFrame();
		frame.setTitle("Ecommerce App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//device.setFullScreenWindow(frame);
	}

}
