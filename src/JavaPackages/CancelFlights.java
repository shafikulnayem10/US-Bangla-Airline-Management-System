package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class CancelFlights extends JFrame {
    private JTextField flightCodeField;

    public CancelFlights() {
        setTitle("Cancel Flights");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main Content Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.BLUE);

        // Add From and To Dropdowns
        JPanel fromPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fromPanel.setBackground(Color.BLUE);
        JLabel fromLabel = new JLabel("From:");
        String[] fromString = {"Dhaka", "Chittagong", "Sylhet"};
        JComboBox<String> fromComboBox = new JComboBox<>(fromString);
        fromPanel.add(fromLabel);
        fromPanel.add(fromComboBox);

        JPanel toPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toPanel.setBackground(Color.BLUE);
        JLabel toLabel = new JLabel("To:");
        String[] toString = {"Chittagong", "Sylhet", "Dhaka"};
        JComboBox<String> toComboBox = new JComboBox<>(toString);
        toPanel.add(toLabel);
        toPanel.add(toComboBox);

        // Add Flight Code Input
        JPanel flightCodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flightCodePanel.setBackground(Color.BLUE);
        JLabel flightCodeLabel = new JLabel("Flight Code:");
        flightCodeField = new JTextField(15);
        flightCodePanel.add(flightCodeLabel);
        flightCodePanel.add(flightCodeField);

        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        JButton removeButton = new JButton("Remove Flight");
        removeButton.setBackground(Color.RED);
        removeButton.setForeground(Color.WHITE);

        JButton backButton = new JButton("Back");
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);

        // Back Button Action
        backButton.addActionListener(e -> dispose());

        // Remove Button Action
        removeButton.addActionListener(e -> {
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

                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    String[] flightData = currentLine.split(",");
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
                    writer.write(currentLine);
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
        });

        // Add buttons to the button panel
        buttonPanel.add(backButton);
        buttonPanel.add(removeButton);

        // Add all components to the main panel
        mainPanel.add(fromPanel);
        mainPanel.add(toPanel);
        mainPanel.add(flightCodePanel);

        // Add panels to the frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CancelFlights();
    }
}

