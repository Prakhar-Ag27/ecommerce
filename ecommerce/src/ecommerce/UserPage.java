package ecommerce;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class UserPage implements ActionListener {
	maxFrame m = new maxFrame("User HomePage - ECommerce App");
	JLabel l = new JLabel("Welcome to User Page!");
	JButton searchButton;
	JButton userAvatarButton;

	UserPage() {
		JPanel nav = new JPanel(); // Panel for nav
		nav.setBackground(Color.DARK_GRAY);
		nav.setPreferredSize(new Dimension(100, 100));

		JPanel search = new JPanel();
		search.setBackground(Color.DARK_GRAY);
		searchButton = new JButton("Search");
		JTextField searchBox = new JTextField();
		searchButton.setFocusable(false);
		searchBox.setPreferredSize(new Dimension(420, 50));
		searchButton.setPreferredSize(new Dimension(150, 50));
		searchButton.addActionListener(this);
		search.add(searchBox);
		search.add(searchButton);
		
		ImageIcon walletIcon = new ImageIcon("src/img/wallet.png");
		JButton walletButton = new JButton();
		walletButton.setText("Wallet");
		Image scaledImage = walletIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
		walletButton.setIcon(scaledIcon);
		walletButton.setHorizontalTextPosition(JButton.RIGHT);
		walletButton.setVerticalTextPosition(JButton.CENTER);
		walletButton.setFocusable(false);

		userAvatarButton = new JButton("Username");
		userAvatarButton.setPreferredSize(new Dimension(250, 50));
		userAvatarButton.addActionListener(this);
		userAvatarButton.setFocusable(false);

		JPanel screen = new JPanel(); // Panel for main screen
		// screen.setBackground(Color.blue);

		nav.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		nav.add(search);
		nav.add(walletButton);
		nav.add(userAvatarButton);
		m.add(nav, BorderLayout.NORTH);
		m.add(screen, BorderLayout.CENTER);
		m.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			System.out.println("Search Query inititated.");
		}
		if (e.getSource() == userAvatarButton) {
			JPopupMenu popupMenu = new JPopupMenu();
			popupMenu.setPreferredSize(new Dimension(250, 100));
			JMenuItem profileItem = new JMenuItem("View Profile");
			JMenuItem shoppingCart = new JMenuItem("Shopping Cart");
			JMenuItem logoutItem = new JMenuItem("Logout");
            logoutItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Perform logout action here
                	m.dispose();
                	new LoginPage();
                    JOptionPane.showMessageDialog(null, "Logged out!");
                    // Close the dropdown menu
                    popupMenu.setVisible(false);
                    
                    
                }
            });
            
            popupMenu.add(profileItem);
            popupMenu.add(shoppingCart);
            popupMenu.add(logoutItem);
            popupMenu.show(userAvatarButton, 0, userAvatarButton.getHeight());
		}
	}
}
