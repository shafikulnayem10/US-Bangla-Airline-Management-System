
package JavaPackages;

import javax.swing.*;
//import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BookingFlights extends JFrame {
    public BookingFlights() {
        // Frame setup
        setTitle("BD");
        setSize(900, 500); // Updated size for better view
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // Set layout to BorderLayout
        // Set the application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Title label
        JLabel titleLabel = new JLabel("Booking Flights Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(135, 206, 250)); // Light blue
        titleLabel.setForeground(Color.WHITE); // White text
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Flight Code", "Name", "Address", "From", "To", "Trip Type"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable bookingTable = new JTable(tableModel);

        // Style table header
        JTableHeader header = bookingTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(60, 179, 113)); // Medium sea green
        header.setForeground(Color.WHITE); // White text
        header.setReorderingAllowed(false); // Prevent column reordering

        // Style table cells
        bookingTable.setFont(new Font("Arial", Font.PLAIN, 14));
        bookingTable.setRowHeight(25);
        bookingTable.setGridColor(Color.LIGHT_GRAY);
        bookingTable.setBackground(new Color(245, 245, 245)); // Light gray
        bookingTable.setSelectionBackground(new Color(135, 206, 250)); // Light blue when selected
        bookingTable.setSelectionForeground(Color.BLACK); // Black text when selected

       

        JScrollPane scrollPane = new JScrollPane(bookingTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load data from bookflightList.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("bookflightList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookingData = line.split(",");
                if (bookingData.length == 6) {
                    tableModel.addRow(bookingData);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading booking details!", "Error", JOptionPane.ERROR_MESSAGE);
        }

// Back button setup
JButton backButton = new JButton("Back");
backButton.setFont(new Font("Arial", Font.BOLD, 16));
backButton.setBackground(new Color(255, 69, 0)); // Red-orange
backButton.setForeground(Color.WHITE); // White text
backButton.setFocusPainted(false);
backButton.addActionListener(e -> dispose()); // Close the frame when clicked

// Add button to a panel with layout manager
JPanel buttonPanel = new JPanel();
buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center the button
buttonPanel.add(backButton);

// Add button panel to the frame
add(buttonPanel, BorderLayout.SOUTH);


 // Display the frame
 setVisible(true);
    }
}
