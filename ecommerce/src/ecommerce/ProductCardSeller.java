package ecommerce;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductCardSeller extends JPanel {
	private JLabel nameLabel;
	private JLabel descriptionLabel;
	private JLabel priceLabel;
	private JLabel categoryLabel;
	private JLabel discountLabel;
	private JButton addToCartButton;

    public ProductCardSeller(String name, String description, double price, int discount, String category, int id) {
    	
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder()); // Add border for visual separation
        setPreferredSize(new Dimension(250, 150));

        nameLabel = new JLabel(name);
		descriptionLabel = new JLabel(description);
		categoryLabel = new JLabel(String.format("%s", category));
		priceLabel = new JLabel(String.format("$%.2f", price));
		discountLabel = new JLabel(String.format("%d", discount));
		
		JPanel infoPanel = new JPanel(new GridLayout(3, 1));
		infoPanel.add(nameLabel);
		infoPanel.add(descriptionLabel);
		infoPanel.add(categoryLabel);
		infoPanel.add(priceLabel);
		infoPanel.add(discountLabel);

        addToCartButton = new JButton("Edit details");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic to add product to cart
                new Product();
            }
        });

        add(infoPanel, BorderLayout.CENTER);
        add(addToCartButton, BorderLayout.SOUTH);  
    }
}
