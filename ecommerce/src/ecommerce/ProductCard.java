package ecommerce;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProductCard extends JPanel {
    private JLabel nameLabel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;
    private JButton addToCartButton;

    public ProductCard(String name, String description, double price) {
    	
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder()); // Add border for visual separation
        setPreferredSize(new Dimension(250, 150));

        nameLabel = new JLabel(name);
        descriptionLabel = new JLabel(description);
        priceLabel = new JLabel(String.format("$%.2f", price));

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(nameLabel);
        infoPanel.add(descriptionLabel);
        infoPanel.add(priceLabel);

        addToCartButton = new JButton("Add to Cart");
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add logic to add product to cart
                JOptionPane.showMessageDialog(null, "Product added to cart: " + name);
            }
        });

        add(infoPanel, BorderLayout.CENTER);
        add(addToCartButton, BorderLayout.SOUTH);  
    }
}
