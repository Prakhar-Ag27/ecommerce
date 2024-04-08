package ecommerce;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cart extends JFrame {
    private JTable productTable;
    private DefaultTableModel productModel;
    private JButton checkoutButton;
    private JLabel totalLabel;
    private JButton applyCouponButton; // New button for applying coupon code

    public Cart() {
        setTitle("Shopping Cart");
        setSize(800, 300); // Adjusted size for the new columns
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        String[] columnNames = {"Product Name", "Quantity", "Price", "Expected Delivery Date", "+", "-", "Remove"};
        productModel = new DefaultTableModel(columnNames, 0);
        productModel.addRow(new String[]{"Product A", "2 items", "$50", "04/10/2024"});
        productModel.addRow(new String[]{"Product B", "1 item", "$30", "04/15/2024"});
        productModel.addRow(new String[]{"Product C", "3 items", "$20", "04/12/2024"});

        productTable = new JTable(productModel) {
            @Override
            public Class getColumnClass(int column) {
                return column == 4 || column == 5 || column == 6 ? JButton.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5 || column == 6;
            }
        };
        productTable.setRowHeight(30);
        JScrollPane productTableScrollPane = new JScrollPane(productTable);

        checkoutButton = new JButton("Checkout");
        totalLabel = new JLabel("Total Payable Amount: $0");

        // New button for applying coupon code
        applyCouponButton = new JButton("Apply Coupon Code");

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        add(productTableScrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(checkoutButton, BorderLayout.SOUTH);
        bottomPanel.add(totalLabel, BorderLayout.NORTH);
        bottomPanel.add(applyCouponButton, BorderLayout.CENTER); // Add the new button
        add(bottomPanel, BorderLayout.SOUTH);

        // Add action listener to checkout button
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Calculate total payable amount
                double totalAmount = calculateTotal();
                totalLabel.setText("Total Payable Amount: $" + totalAmount);

                // Display total payable amount
                JOptionPane.showMessageDialog(null, "Total Payable Amount: $" + totalAmount);
            }
        });

        // Add action listener to apply coupon button
        applyCouponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a text field dialog for entering coupon code
                JTextField couponField = new JTextField();
                Object[] message = {
                        "Enter Coupon Code:", couponField
                };
                int option = JOptionPane.showConfirmDialog(null, message, "Apply Coupon Code", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String couponCode = couponField.getText();
                    // Implement coupon code application functionality here
                    // For now, display a message with the entered coupon code
                    JOptionPane.showMessageDialog(null, "Coupon code applied successfully!\nCode: " + couponCode);
                }
            }
        });

        // Add functionality to the buttons (existing code for +/-/remove buttons)
        productTable.getColumn("+").setCellRenderer(new ButtonRenderer("Add", Color.BLUE));
        productTable.getColumn("+").setCellEditor(new ButtonEditor(new JCheckBox(), "Add"));

        productTable.getColumn("-").setCellRenderer(new ButtonRenderer("Subtract", Color.BLUE));
        productTable.getColumn("-").setCellEditor(new ButtonEditor(new JCheckBox(), "Subtract"));

        productTable.getColumn("Remove").setCellRenderer(new ButtonRenderer("Remove", Color.BLUE));
        productTable.getColumn("Remove").setCellEditor(new ButtonEditor(new JCheckBox(), "Remove"));
    }

    private double calculateTotal() {
        double total = 0.0;
        for (int row = 0; row < productModel.getRowCount(); row++) {
            String priceString = (String) productModel.getValueAt(row, 2);
            double price = Double.parseDouble(priceString.substring(1)); // Remove the '$' sign
            String quantityString = (String) productModel.getValueAt(row, 1);
            int quantity = Integer.parseInt(quantityString.split(" ")[0]);
            total += price * quantity;
        }
        return total;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Cart().setVisible(true);
            }
        });
    }

    // ButtonRenderer class
    class ButtonRenderer extends JButton implements TableCellRenderer {
        private String label;

        public ButtonRenderer(String label, Color color) {
            this.label = label;
            setText(label);
            setOpaque(true);
            setBackground(color); // Set background color to blue
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            return this;
        }
    }

    // ButtonEditor class
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, String label) {
            super(checkBox);
            this.label = label;
            button = new JButton(label);
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            isPushed = true;
            return button;
        }

        public Object getCellEditorValue() {
            if (isPushed) {
                // Button clicked, implement your action here
                System.out.println(label + " Clicked");
            }
            isPushed = false;
            return label;
        }

        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
