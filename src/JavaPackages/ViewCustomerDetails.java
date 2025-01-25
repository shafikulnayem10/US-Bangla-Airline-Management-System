
package JavaPackages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewCustomerDetails extends JFrame {
    public ViewCustomerDetails() {
        // Frame setup
        setTitle("BD");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Set layout to BorderLayout
        // Set the application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Title label
        JLabel titleLabel = new JLabel("Customer Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(135, 206, 250)); // Light blue
        titleLabel.setForeground(Color.WHITE); // White text
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Username", "Password", "Status", "Name"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable customerTable = new JTable(tableModel);

        // Style table header
        JTableHeader header = customerTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(60, 179, 113)); // Medium sea green
        header.setForeground(Color.WHITE); // White text
        header.setReorderingAllowed(false); // Prevent column reordering

        // Style table cells
        customerTable.setFont(new Font("Arial", Font.PLAIN, 14));
        customerTable.setRowHeight(25);
        customerTable.setGridColor(Color.LIGHT_GRAY);
        customerTable.setBackground(new Color(245, 245, 245)); // Light gray
        customerTable.setSelectionBackground(new Color(135, 206, 250)); // Light blue when selected
        customerTable.setSelectionForeground(Color.BLACK); // Black text when selected

     

        JScrollPane scrollPane = new JScrollPane(customerTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load data from users.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 4 && !"Admin".equalsIgnoreCase(userData[2])) {
                    // Only add Normal Customer and Premium Customer details to the table
                    tableModel.addRow(userData);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading customer details!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Back button setup
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(255, 69, 0)); // Red-orange
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> dispose()); // Close the frame when clicked

        // Add back button to a panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center the button
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        setVisible(true);
    }
}

