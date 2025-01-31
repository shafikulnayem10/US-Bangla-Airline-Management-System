package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddFlights extends JFrame implements ActionListener {
    private JTextField flightCodeField;
    private JComboBox   fromComboBox, toComboBox;
    private JButton addButton, backButton;
    private JLabel titleLabel, fromLabel, toLabel, flightCodeLabel;
    private JPanel formPanel, buttonPanel;

    public AddFlights() {
        // Frame setup
        setTitle("Add Flights");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Set the application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Title label
        titleLabel = new JLabel("Add Flights", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(135, 206, 250)); // Light blue
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

        addButton = new JButton("Add Flight");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(60, 179, 113)); // Medium sea green
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 69, 0)); // Red-orange
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
        if (e.getSource() == addButton) {
            addFlight();
        } else if (e.getSource() == backButton) {
            dispose(); // Close the window when Back button is clicked
        }
    }

    private void addFlight() {
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
    }
}


