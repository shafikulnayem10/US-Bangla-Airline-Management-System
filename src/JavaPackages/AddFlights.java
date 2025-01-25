package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddFlights extends JFrame {
    private JTextField flightCodeField;

    public AddFlights() {
        // Frame setup
        setTitle("Add Flights");
        setSize(500, 500); // Adjusted for better view
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Manual positioning
        // Set the application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Title label
        JLabel titleLabel = new JLabel("Add Flights", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(135, 206, 250)); // Light blue
        titleLabel.setForeground(Color.WHITE); // White text
        titleLabel.setBounds(0, 10, 500, 40); // Set bounds manually
        add(titleLabel);

        // Labels and inputs
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        fromLabel.setBounds(50, 80, 150, 30);
        add(fromLabel);

        JComboBox<String> fromComboBox = new JComboBox<>(new String[]{"Dhaka", "Chittagong", "Sylhet"});
        fromComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        fromComboBox.setBounds(200, 80, 200, 30);
        add(fromComboBox);

        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        toLabel.setBounds(50, 140, 150, 30);
        add(toLabel);

        JComboBox<String> toComboBox = new JComboBox<>(new String[]{"Chittagong", "Sylhet", "Dhaka"});
        toComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        toComboBox.setBounds(200, 140, 200, 30);
        add(toComboBox);

        JLabel flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        flightCodeLabel.setBounds(50, 200, 150, 30);
        add(flightCodeLabel);

        flightCodeField = new JTextField();
        flightCodeField.setFont(new Font("Arial", Font.PLAIN, 16));
        flightCodeField.setBounds(200, 200, 200, 30);
        add(flightCodeField);

        // Add Flight button
        JButton addButton = new JButton("Add Flight");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setBackground(new Color(60, 179, 113)); // Medium sea green
        addButton.setForeground(Color.WHITE); // White text
        addButton.setFocusPainted(false);
        addButton.setBounds(150, 300, 200, 40);
        addButton.addActionListener(e -> {
            String from = (String) fromComboBox.getSelectedItem();
            String to = (String) toComboBox.getSelectedItem();
            String flightCode = flightCodeField.getText().trim();

            if (from.equals(to)) {
                JOptionPane.showMessageDialog(this, "From and To locations cannot be the same!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (flightCode.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Flight Code cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File file = new File("addandcancelflight.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(flightCode + "," + from + "," + to);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Flight added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error adding flight!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(addButton);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(255, 69, 0)); // Red-orange
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFocusPainted(false);
        backButton.setBounds(150, 360, 200, 40);
        backButton.addActionListener(e -> dispose());
        add(backButton);

        // Display the frame
        setVisible(true);
    }
}

