package JavaPackages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewFlights extends JFrame {
    public ViewFlights() {
        // Frame setup
        setTitle("Available Flights");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); // Add spacing between components

        // Header label
        JLabel headerLabel = new JLabel("Available Flights", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(new Color(70, 130, 180)); // Steel blue
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(headerLabel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Flight Code", "From", "To"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable flightsTable = new JTable(tableModel);
        flightsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        flightsTable.setRowHeight(30);

       
        // Scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(flightsTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));
        add(scrollPane, BorderLayout.CENTER);

        // Load data from addandcancelflight.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("addandcancelflight.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(",");
                if (flightData.length == 3) {
                    tableModel.addRow(flightData);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading flight details!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Close button setup
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(new Color(255, 69, 0)); // Red-orange
        closeButton.setForeground(Color.WHITE);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        closeButton.setFocusPainted(false);
        closeButton.setPreferredSize(new Dimension(100, 40));
        closeButton.addActionListener(e -> dispose()); // Close the frame

        // Button panel for the close button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE); // Match the background
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Frame visibility
        setVisible(true);
    }
}

