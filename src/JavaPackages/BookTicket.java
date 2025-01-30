package JavaPackages;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class BookTicket extends JFrame implements ActionListener {
    protected JTextField nameField, addressField, flightCodeField;
    protected JComboBox fromComboBox, toComboBox, tripTypeComboBox;
    protected JButton bookButton;

    public BookTicket() {
        // Frame setup
        setTitle("Book Ticket");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Icon Panel
        JPanel iconPanel = new JPanel(new BorderLayout());
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("bookflighticon.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(resizedIcon, JLabel.CENTER);
        iconPanel.add(iconLabel, BorderLayout.CENTER);
        add(iconPanel, BorderLayout.WEST);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = Color.GREEN;

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(labelFont);
        fromLabel.setForeground(labelColor);
        formPanel.add(fromLabel);
        String fromString []={"Dhaka", "Chittagong", "Sylhet"};
        fromComboBox = new JComboBox (fromString);
        fromComboBox.setBackground(Color.CYAN);
        formPanel.add(fromComboBox);

        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(labelFont);
        toLabel.setForeground(labelColor);
        formPanel.add(toLabel);
        String toString[]={"Chittagong", "Sylhet", "Dhaka"};
        toComboBox = new JComboBox(toString);
        toComboBox.setBackground(Color.ORANGE);
        formPanel.add(toComboBox);

        JLabel tripTypeLabel = new JLabel("Trip Type:");
        tripTypeLabel.setFont(labelFont);
        tripTypeLabel.setForeground(labelColor);
        formPanel.add(tripTypeLabel);
        String tripType[]={"One Way", "Round Trip"};
        tripTypeComboBox = new JComboBox(tripType);
        tripTypeComboBox.setBackground(Color.CYAN);
        formPanel.add(tripTypeComboBox);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(labelColor);
        formPanel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBackground(Color.MAGENTA);
        formPanel.add(nameField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(labelColor);
        formPanel.add(addressLabel);
        addressField = new JTextField();
        addressField.setBackground(Color.ORANGE);
        formPanel.add(addressField);

        JLabel flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(labelFont);
        flightCodeLabel.setForeground(labelColor);
        formPanel.add(flightCodeLabel);
        flightCodeField = new JTextField();
        flightCodeField.setBackground(Color.LIGHT_GRAY); // Allow user input
        formPanel.add(flightCodeField);

        bookButton = new JButton("Book Flight");
        bookButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookButton.setBackground(Color.BLUE);
        bookButton.setForeground(Color.WHITE);
        bookButton.addActionListener(this);
        formPanel.add(new JLabel());
        formPanel.add(bookButton);

        add(formPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            String flightCode = flightCodeField.getText().trim();
            String name = nameField.getText().trim();
            String address = addressField.getText().trim();
            String from = (String) fromComboBox.getSelectedItem();
            String to = (String) toComboBox.getSelectedItem();
            String tripType = (String) tripTypeComboBox.getSelectedItem();

            // Validate input
            if (flightCode.isEmpty() || name.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verify if the flight code exists in addandcancelflight.txt
            boolean isFlightValid = false;
            try (BufferedReader flightReader = new BufferedReader(new FileReader("addandcancelflight.txt"))) {
                String line;
                while ((line = flightReader.readLine()) != null) {
                    String[] flightData = line.split(",");
                    if (flightData[0].equals(flightCode)) {
                        isFlightValid = true;
                        break;
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error verifying flight code!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isFlightValid) {
                JOptionPane.showMessageDialog(this, "Invalid flight code!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            File file = new File("bookflightList.txt");
           

            // Write booking details to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(flightCode + "," + name + "," + address + "," + from + "," + to + "," + tripType);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Ticket Booked Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving ticket details!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new BookTicket();
    }
}