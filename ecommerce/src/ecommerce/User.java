package ecommerce;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class User extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public User() {
        setTitle("User Information");
        setSize(800, 400);
        setLocationRelativeTo(null); // Center the frame on the screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a table model with columns
        String[] columns = {"Field Name", "Data"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Disable editing by default
            }
        };
        table = new JTable(model);

        // Add user data to the table
        Map<String, String> userData = getUserData(); // Example data, replace this with actual user data
        for (Map.Entry<String, String> entry : userData.entrySet()) {
            model.addRow(new Object[]{entry.getKey(), entry.getValue()});
        }

        // Create edit button
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == 8) {
                	new wallet();
                }
                else if (selectedRow != -1) {
                	System.out.print(selectedRow);
                    // Open a menu or dialog to edit the selected user's information
                    String fieldName = (String) model.getValueAt(selectedRow, 0);
                    String currentData = (String) model.getValueAt(selectedRow, 1);
                    String newData = JOptionPane.showInputDialog(null, "Enter new data for " + fieldName + ":", currentData);
                    if (newData != null) {
                        model.setValueAt(newData, selectedRow, 1);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a field to edit.");
                }
            }
        });

        // Add components to a panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(editButton, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(panel);

        setVisible(true);
    }

    private Map<String, String> getUserData() {
        // Example user data, replace this with your actual user data
        Map<String, String> userData = new LinkedHashMap<>();
        userData.put("Address", "123c , Cement Mafia Colony , Dhamnod");
        userData.put("First Name", "Praxxx");
        userData.put("Last Name", "Agrawal");
        userData.put("ID", "JD123");
        userData.put("Age", "30");
        userData.put("Sex", "Male");
        userData.put("Mobile Number", "123-456-7890");
        userData.put("Email", "john.doe@example.com");
        userData.put("Wallet", "$1000");
        userData.put("Password", "password123");
        return userData;
    }

    
}
