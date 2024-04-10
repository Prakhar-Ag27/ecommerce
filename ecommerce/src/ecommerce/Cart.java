package ecommerce;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cart extends JFrame {
	private JTable productTable;
	static DefaultTableModel productModel;
	private JButton checkoutButton;
	private JLabel totalLabel;
	private JButton applyCouponButton; // New button for applying coupon code
	public static ArrayList<String[]> arr = new ArrayList();
	public String[] toBeInserted = new String[4];

	public Cart() {
		setTitle("Shopping Cart");
		setSize(800, 300); // Adjusted size for the new columns
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		String cartQuery = String.format(
				"SELECT item.id, item.name, item.price, cart.quantity FROM item JOIN cart ON item.id = cart.item_id WHERE customer_id = %d;",
				GlobalVariables.userID);
		ResultSet resultSet;
		// Initialize components
		String[] columnNames = { "Product Name", "Quantity", "Price", "+", "-", "Remove" };
		productModel = new DefaultTableModel(columnNames, 0);
		try {
			resultSet = GlobalVariables.statement.executeQuery(cartQuery);
			while (resultSet.next()) {
				String id = resultSet.getString("id");
				String name = resultSet.getString("name");
				String quantity = resultSet.getString("quantity");
				String price = resultSet.getString("price");

				toBeInserted[0] = id;
				toBeInserted[1] = name;
				toBeInserted[2] = quantity;
				toBeInserted[3] = price;
				productModel.addRow(new String[] { name, quantity, price });
				arr.add(toBeInserted);
			}
		} catch (SQLException e1) {
			System.err.println("Failed to connect to the database or execute stored procedure!");
			e1.printStackTrace();
		}

		productTable = new JTable(productModel) {
			@Override
			public Class getColumnClass(int column) {
				return column == 3 || column == 4 || column == 5 ? JButton.class : Object.class;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 3 || column == 4 || column == 5;
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
				new CheckoutPage();
			}
		});

		// Add action listener to apply coupon button
		applyCouponButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Create a text field dialog for entering coupon code
				JTextField couponField = new JTextField();
				Object[] message = { "Enter Coupon Code:", couponField };
				int option = JOptionPane.showConfirmDialog(null, message, "Apply Coupon Code",
						JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String couponCode = couponField.getText();
					// Implement coupon code application functionality here
					// For now, display a message with the entered coupon code
					JOptionPane.showMessageDialog(null, "Coupon code applied successfully!\nCode: " + couponCode);
				}
			}
		});

		// Add functionality to the buttons (existing code for +/-/remove buttons)
		productTable.getColumn("+").setCellRenderer(new ButtonRenderer("Add", Color.LIGHT_GRAY));
		productTable.getColumn("+").setCellEditor(new ButtonEditor(new JCheckBox(), "Add"));

		productTable.getColumn("-").setCellRenderer(new ButtonRenderer("Subtract", Color.LIGHT_GRAY));
		productTable.getColumn("-").setCellEditor(new ButtonEditor(new JCheckBox(), "Subtract"));

		productTable.getColumn("Remove").setCellRenderer(new ButtonRenderer("Remove", Color.LIGHT_GRAY));
		productTable.getColumn("Remove").setCellEditor(new ButtonEditor(new JCheckBox(), "Remove"));
		setLocationRelativeTo(null);
		setVisible(true);
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

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		return this;
	}
}

// ButtonEditor class
class ButtonEditor extends DefaultCellEditor {
	protected JButton button;
	private int clickedRow;
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

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		clickedRow = row;
		isPushed = true;
		return button;
	}

	public Object getCellEditorValue() {
		if (isPushed) {
			// Button clicked, implement your action here
			if (label.equals("Add")) {
				// System.out.println("Add button clicked in row: " + clickedRow);
				String addQuery = String.format("CALL AddToCart(%s,%s,%s, @isValid, @addedSuccessfully);",
						GlobalVariables.userID, Cart.arr.get(clickedRow)[0], "1");
				try {
					GlobalVariables.statement.executeQuery(addQuery);

					Cart.productModel.setValueAt(
							"" + (Integer.parseInt((String) Cart.productModel.getValueAt(clickedRow, 1)) + 1),
							clickedRow, 1);
					Cart.productModel.fireTableDataChanged();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (label.equals("Subtract")) {
				// System.out.println("Subtract button clicked in row: " + clickedRow);
				String subQuery = String.format("CALL AddToCart(%s,%s,%d, @isValid, @addedSuccessfully);",
						GlobalVariables.userID, Cart.arr.get(clickedRow)[0], -1);
				try {
					GlobalVariables.statement.executeQuery(subQuery);

					Cart.productModel.setValueAt(
							"" + (Integer.parseInt((String) Cart.productModel.getValueAt(clickedRow, 1)) - 1),
							clickedRow, 1);
					Cart.productModel.fireTableDataChanged();

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				String remQuery = String.format("DELETE FROM cart WHERE item_id = '%s';", Cart.arr.get(clickedRow)[0]);
				try {
					GlobalVariables.statement.executeUpdate(remQuery);

					Cart.productModel.removeRow(clickedRow);

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
