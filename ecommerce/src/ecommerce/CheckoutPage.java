package ecommerce;

import java.awt.Color;
import java.sql.SQLException;

//import javax.swing.JComboBox;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;

import javax.swing.*;

public class CheckoutPage extends JFrame {
    
	public CheckoutPage(double cartAmount) {
		
        String[] paymentOptions = {"Wallet", "Cash on Delivery"};
        JComboBox<String> paymentDropdown = new JComboBox<>(paymentOptions);

        JTextField addressField = new JTextField(20); // Address input field

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            String selectedOption = (String) paymentDropdown.getSelectedItem();
            String address = addressField.getText();

            System.out.println("Payment mode: " + selectedOption);
            System.out.println("Delivery address: " + address);

            JOptionPane.showMessageDialog(this, "Order Confirmed", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            if(selectedOption == "Wallet") {walletCheckout(cartAmount);}
            try {
    			GlobalVariables.statement.executeUpdate(
    					String.format("Delete from cart where customer_id=%d",GlobalVariables.userID)
    					);
    			
    			
    		} catch (SQLException e1) {
    			System.err.println("Failed to connect to the database or execute stored procedure!");
    			e1.printStackTrace();
    		}
            dispose();
            
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Payment Mode:"));
        panel.add(paymentDropdown);
        panel.add(new JLabel("Enter Address:"));
        panel.add(addressField);
        panel.add(checkoutButton);

        setTitle("Checkout Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        add(panel);
        setVisible(true);
    }
	
	public void walletCheckout(double cartAmount) {
		try {
			GlobalVariables.statement.executeUpdate(
					String.format("Update user set wallet_amount=wallet_amount- %s where id=%d", cartAmount,GlobalVariables.userID)
					);
			
			
		} catch (SQLException e1) {
			System.err.println("Failed to connect to the database or execute stored procedure!");
			e1.printStackTrace();
		}
	}
}
