package ecommerce;

import java.awt.Font;

import javax.swing.JLabel;

public class UserPage {
	maxFrame m = new maxFrame("User HomePage - ECommerce App");
	JLabel l = new JLabel("Welcome to User Page!");
	UserPage(){
		l.setFont(new Font(null, Font.BOLD, 25));
		m.add(l);
		m.setVisible(true);
	}
}
