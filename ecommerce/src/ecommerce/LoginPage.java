package ecommerce;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage implements ActionListener {

	maxFrame m = new maxFrame("Login - E-Commerce App");
	
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("User ID: ");
	JLabel userPasswordLabel = new JLabel("Password: ");
	JLabel messageLabel = new JLabel();
	
	LoginPage(){
		m.setLayout(null);
		
		userIDLabel.setBounds(50,100,75, 25);
		userPasswordLabel.setBounds(50,150,75, 25);
		messageLabel.setBounds(125, 250, 250, 35);
		messageLabel.setFont(new Font(null, Font.BOLD, 25));
		userIDField.setBounds(125, 100, 200, 25);
		userPasswordField.setBounds(125, 150, 200, 25);
		loginButton.setBounds(125, 200, 95, 25);
		loginButton.addActionListener(this);
		resetButton.setBounds(225, 200, 95, 25);
		resetButton.addActionListener(this);
		resetButton.setFocusable(false);
		loginButton.setFocusable(false);
		
		m.add(userIDLabel);
		m.add(userPasswordLabel);
		m.add(messageLabel);
		m.add(userIDField);
		m.add(userPasswordField);
		m.add(loginButton);
		m.add(resetButton);
		m.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
		}
		
		if(e.getSource()==loginButton) {
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			//Add JDBC here to check user and navigate if exists with given credentials
			messageLabel.setForeground(Color.green);
			messageLabel.setText("Login Successful!");
			new UserPage();
			m.dispose();
			
		}
	}

}
