package ecommerce;

//import javax.swing.JComboBox;
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;

import javax.swing.*;

public class CheckoutPage extends JFrame {
    
	public CheckoutPage() {
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
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Payment Mode:"));
        panel.add(paymentDropdown);
        panel.add(new JLabel("Enter Address:"));
        panel.add(addressField);
        panel.add(checkoutButton);

        setTitle("Checkout Page");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(panel);
        setVisible(true);
    }
}
