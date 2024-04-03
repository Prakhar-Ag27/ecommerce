package ecommerce;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class wallet extends JFrame {
    private double balance = 0.0; // Initial balance

    private JLabel balanceLabel;

    public wallet() {
        setTitle("Wallet Balance");
        setSize(500, 500); // Reduced frame size for demonstration
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        balanceLabel = new JLabel("Balance: $" + balance);
        JButton addButton = new JButton("ADD");
        addButton.setFocusable(false);
        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFocusable(false);
        // Set preferred size for buttons
        addButton.setPreferredSize(new Dimension(100, 40));
        withdrawButton.setPreferredSize(new Dimension(100, 40));

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddMenu();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showWithdrawMenu();
            }
        });

        // Create layout and add components
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 500,20));
        panel.setPreferredSize(new Dimension(100,40));
        panel.add(balanceLabel);
        panel.add(addButton);
        panel.add(withdrawButton);

        add(panel);
        setVisible(true);
    }

    private void showAddMenu() {
        JPopupMenu menu = new JPopupMenu();
        JTextField amountField = new JTextField();
        menu.setPreferredSize(new Dimension(250, 75));
        JButton addAmountButton = new JButton("Add");
        addAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    balance += amount;
                    updateBalanceLabel();
                    menu.setVisible(false);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        });
        menu.add(new JLabel("Enter amount to add:"));
        menu.add(amountField);
        menu.add(addAmountButton);
        menu.show(this, ((getWidth() / 2)-125), ((getHeight() / 2)-37));
    }

    private void showWithdrawMenu() {
        JPopupMenu menu = new JPopupMenu();
        menu.setPreferredSize(new Dimension(250, 75));
        JTextField amountField = new JTextField();
        JButton withdrawAmountButton = new JButton("Withdraw");
        withdrawAmountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    if (amount <= balance) {
                        balance -= amount;
                        updateBalanceLabel();
                        menu.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        });
        menu.add(new JLabel("Enter amount to withdraw:"));
        menu.add(amountField);
        menu.add(withdrawAmountButton);
        menu.show(this, ((getWidth() / 2)-125), ((getHeight() / 2)-37));
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: $" + balance);
    }

    
}
