package JavaPackages.Share;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class ViewFlights extends JFrame {
    public ViewFlights() {
        
        this.setTitle("Available Flights");
        this.setSize(800, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10)); 

       
        JLabel headerLabel = new JLabel("Available Flights", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setOpaque(true);
        headerLabel.setBackground(Color.BLUE); 
        headerLabel.setForeground(Color.WHITE);
        add(headerLabel, BorderLayout.NORTH);

       
        String[] columnNames = {"Flight Code", "From", "To"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable flightsTable = new JTable(tableModel);
        flightsTable.setFont(new Font("Arial", Font.PLAIN, 16));
        flightsTable.setRowHeight(30);

       
        
        JScrollPane scrollPane = new JScrollPane(flightsTable);
        this.add(scrollPane, BorderLayout.CENTER);

        
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

       
      JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(Color.GREEN); 
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setPreferredSize(new Dimension(100, 40));
        closeButton.addActionListener(e -> dispose());

       
      JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE); 
         buttonPanel.add(closeButton);
        this.add(buttonPanel, BorderLayout.SOUTH);

       
        setVisible(true);
    }
}

