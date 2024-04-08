package ecommerce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SellerPage implements ActionListener {
	maxFrame m = new maxFrame("User HomePage - ECommerce App");
	JLabel l = new JLabel("Welcome to User Page!");
	JButton searchButton;
	JButton userAvatarButton;
	JPanel screen;

	SellerPage() {
		JPanel nav = new JPanel(); // Panel for nav
		nav.setBackground(Color.WHITE);
		nav.setPreferredSize(new Dimension(100, 100));
		nav.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Add spacing between components

		ImageIcon couponImg = new ImageIcon("src/img/coupon.png");
		JButton couponButton = new JButton();
		couponButton.setText("Manage Coupons");
		Image scaledCouponImg = couponImg.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon scaledCartIcon = new ImageIcon(scaledCouponImg);
		couponButton.setIcon(scaledCartIcon);
		couponButton.setHorizontalTextPosition(JButton.RIGHT);
		couponButton.setVerticalTextPosition(JButton.CENTER);
		couponButton.setFocusable(false);
		couponButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Cart();
			}
		});
		
		ImageIcon manageInventoryImgIcon = new ImageIcon("src/img/manage.png");
		JButton manageInventoryButton = new JButton();
		manageInventoryButton.setText("Manage Inventory");
		Image manageInventoryImg = manageInventoryImgIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		manageInventoryImgIcon = new ImageIcon(manageInventoryImg);
		manageInventoryButton.setIcon(manageInventoryImgIcon);
		manageInventoryButton.setHorizontalTextPosition(JButton.RIGHT);
		manageInventoryButton.setVerticalTextPosition(JButton.CENTER);
		manageInventoryButton.setFocusable(false);
		manageInventoryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdvancedSearch();
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

		screen = new JPanel(); // Panel for main screen
		screen.setBackground(Color.LIGHT_GRAY); // Set screen background color

		// Change layout of screen panel to BoxLayout with Y_AXIS alignment
		screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));

		// Add screen panel to JScrollPane
		JScrollPane scrollPane = new JScrollPane(screen, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// Set preferred size of JScrollPane

		nav.add(app);
		//nav.add(search);
		//nav.add(manageInventoryButton);
		nav.add(couponButton);
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
					rowPanel.add(new ProductCardSeller("name", "description", 23.99));
				}
			}

			// Add row panel to the screen panel
			screen.add(rowPanel);
		}

		m.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchButton) {
			screen.removeAll();

			// JDBC here for adding content according to search query;

			screen.revalidate();
			screen.repaint();
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
