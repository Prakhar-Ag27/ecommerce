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
        searchBox.setPreferredSize(new Dimension(300, 40)); // Adjust search box size
        searchButton.setPreferredSize(new Dimension(100, 40)); // Adjust search button size
        searchButton.setFocusable(false);
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
                new wallet();
            }
        });

        ImageIcon shoppingCartImg = new ImageIcon("src/img/shoppingCart.png");
        JButton shoppingCart = new JButton();
        shoppingCart.setText("Shopping Cart");
        Image scaledCartImage = shoppingCartImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledCartIcon = new ImageIcon(scaledCartImage);
        shoppingCart.setIcon(scaledCartIcon);
        shoppingCart.setHorizontalTextPosition(JButton.RIGHT);
        shoppingCart.setVerticalTextPosition(JButton.CENTER);
        shoppingCart.setFocusable(false);
        shoppingCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckoutPage();
            }
        });

        ImageIcon originalIcon = new ImageIcon("src/img/logo.jpg");
        Image scaledAppImage = originalIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledAppIcon = new ImageIcon(scaledAppImage);
        JLabel app = new JLabel();
        app.setIcon(scaledAppIcon);

        userAvatarButton = new JButton("Username");
        userAvatarButton.setPreferredSize(new Dimension(150, 40)); // Adjust userAvatarButton size
        userAvatarButton.addActionListener(this);
        userAvatarButton.setFocusable(false);

        JPanel screen = new JPanel(); // Panel for main screen
        screen.setBackground(Color.LIGHT_GRAY); // Set screen background color

        // Change layout of screen panel to BoxLayout with Y_AXIS alignment
        screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));

        // Add screen panel to JScrollPane
        JScrollPane scrollPane = new JScrollPane(screen, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
         // Set preferred size of JScrollPane

        nav.add(app);
        nav.add(search);
        nav.add(walletButton);
        nav.add(shoppingCart);
        nav.add(userAvatarButton);

        m.add(nav, BorderLayout.NORTH);
        m.add(scrollPane, BorderLayout.CENTER);

        // Create a panel for each row
        for (int i = 0; i < 100; i += 6) {
            JPanel rowPanel = new JPanel(); // Panel for each row
            rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 57, 25)); // Set layout for row panel

            // Add six ProductCard components to each row
            for (int j = i; j < i + 6; j++) {
                if (j < 7) { // Ensure not to add more than 100 ProductCard components
                    rowPanel.add(new ProductCard("name", "description", 23.99));
                }
            }

            // Add row panel to the screen panel
            screen.add(rowPanel);
        }

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
			popupMenu.add(logoutItem);
			popupMenu.show(userAvatarButton, 0, userAvatarButton.getHeight());
		}
	}
}
