package JavaPackages.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CancelFlights extends JFrame implements ActionListener {
    private JTextField flightCodeField;
    private JComboBox fromComboBox, toComboBox;
    private JButton removeButton, backButton;
    private JLabel titleLabel, fromLabel, toLabel, flightCodeLabel;
    private JPanel formPanel, buttonPanel;

    public CancelFlights() {
        // Frame setup
        setTitle("Cancel Flights");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

       // Set the application icon
        ImageIcon bdFlag = new ImageIcon("C:/Users/USER/OneDrive - American International University-Bangladesh/Desktop/dev-1/test project/US-Bangla-Airline-Management-System/src/JavaPackages/Images/bdflag.png");
        this.setIconImage(bdFlag.getImage());

        // Title label
        titleLabel = new JLabel("Cancel Flights", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(255, 99, 71)); // Tomato color
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Form panel with GridLayout
        formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(fromLabel);

        String[] fromLocations = {"Dhaka", "Chittagong", "Sylhet"};
        fromComboBox = new JComboBox (fromLocations);
        formPanel.add(fromComboBox);

        toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(toLabel);

        String[] toLocations = {"Chittagong", "Sylhet", "Dhaka"};
        toComboBox = new JComboBox (toLocations);
        formPanel.add(toComboBox);

        flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(flightCodeLabel);

        flightCodeField = new JTextField();
        formPanel.add(flightCodeField);

        add(formPanel, BorderLayout.CENTER);

        // Button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        removeButton = new JButton("Remove Flight");
        removeButton.setFont(new Font("Arial", Font.BOLD, 14));
        removeButton.setBackground(new Color(220, 20, 60)); // Crimson
        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);
        removeButton.addActionListener(this);
        buttonPanel.add(removeButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(105, 105, 105)); // Dim gray
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            removeFlight();
        } else if (e.getSource() == backButton) {
            dispose(); // Close the window when Back button is clicked
        }
    }

    private void removeFlight() {
        String from = (String) fromComboBox.getSelectedItem();
        String to = (String) toComboBox.getSelectedItem();
        String flightCode = flightCodeField.getText().trim();
        boolean flightFound = false;

        if (from.equals(to)) {
            JOptionPane.showMessageDialog(this, "From and To locations cannot be the same!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (flightCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Flight Code cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File inputFile = new File("addandcancelflight.txt");
        File tempFile = new File("tempFlights.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(",");
                if (flightData.length > 2) {
                    String fileFlightCode = flightData[0].trim();
                    String fileFrom = flightData[1].trim();
                    String fileTo = flightData[2].trim();

                    // Compare the flight code, from, and to locations
                    if (fileFlightCode.equals(flightCode) && fileFrom.equals(from) && fileTo.equals(to)) {
                        flightFound = true; // Mark flight as found
                        continue; // Skip writing this line to remove it
                    }
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error processing file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update or handle errors
        if (flightFound) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(this, "Flight removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            tempFile.delete();
            JOptionPane.showMessageDialog(this, "No matching flight found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CancelFlights();
    }
}
