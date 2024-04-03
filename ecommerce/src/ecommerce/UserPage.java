package ecommerce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserPage implements ActionListener {
	maxFrame m = new maxFrame("User HomePage - ECommerce App");
	JLabel l = new JLabel("Welcome to User Page!");
	JButton searchButton;
	JButton userAvatarButton;

	UserPage() {
		JPanel nav = new JPanel(); // Panel for nav
		nav.setBackground(Color.WHITE);
		nav.setPreferredSize(new Dimension(100, 100));
		nav.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Add spacing between components

		JPanel search = new JPanel();
		search.setBackground(Color.WHITE);
		searchButton = new JButton("Search");
		JTextField searchBox = new JTextField();
		searchBox.setPreferredSize(new Dimension(300, 30)); // Adjust search box size
		searchButton.setPreferredSize(new Dimension(100, 30)); // Adjust search button size
		searchButton.addActionListener(this);
		search.add(searchBox);
		search.add(searchButton);

		ImageIcon walletIcon = new ImageIcon("src/img/wallet.png");
		JButton walletButton = new JButton();
		walletButton.setText("Wallet");
		Image scaledImage = walletIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(scaledImage);
		walletButton.setIcon(scaledIcon);
		walletButton.setHorizontalTextPosition(JButton.RIGHT);
		walletButton.setVerticalTextPosition(JButton.CENTER);
		walletButton.setFocusable(false);
		walletButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked Wallet");
			}
		});

		ImageIcon originalIcon = new ImageIcon("src/img/logo.jpg");
		Image scaledAppImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon scaledAppIcon = new ImageIcon(scaledAppImage);
		JLabel app = new JLabel();
		app.setIcon(scaledAppIcon);

		userAvatarButton = new JButton("Username");
		userAvatarButton.setPreferredSize(new Dimension(150, 30)); // Adjust userAvatarButton size
		userAvatarButton.addActionListener(this);
		userAvatarButton.setFocusable(false);

		JPanel screen = new JPanel(); // Panel for main screen
		screen.setBackground(Color.LIGHT_GRAY); // Set screen background color

		nav.add(app);
		nav.add(search);
		nav.add(walletButton);
		nav.add(userAvatarButton);
		m.add(nav, BorderLayout.NORTH);
		m.add(screen, BorderLayout.CENTER);
		m.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			System.out.println("Search Query initiated.");
		}
		if (e.getSource() == userAvatarButton) {
			JPopupMenu popupMenu = new JPopupMenu();
			popupMenu.setPreferredSize(new Dimension(150, 100));
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
