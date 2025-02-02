package JavaPackages.Share;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CancelTicket extends JFrame implements ActionListener {
    private JTextField flightCodeField, addressField, nameField;
    private JButton cancelButton, backButton;
    private JLabel titleLabel, flightCodeLabel, nameLabel, addressLabel;
    private JPanel formPanel, buttonPanel;

    public CancelTicket() {
        // **Frame setup**
        setTitle("Cancel Ticket");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // **Title Label**
        titleLabel = new JLabel("Cancel Your Ticket", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(0, 102, 204)); // Blue
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // **Form Panel (GridLayout)**
        formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setBackground(new Color(240, 248, 255)); // Light blue

        // **Labels & Input Fields**
        flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        flightCodeLabel.setForeground(Color.BLACK);
        formPanel.add(flightCodeLabel);

        flightCodeField = new JTextField();
        flightCodeField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(flightCodeField);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.BLACK);
        formPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(nameField);

        addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 14));
        addressLabel.setForeground(Color.BLACK);
        formPanel.add(addressLabel);

        addressField = new JTextField();
        addressField.setFont(new Font("Arial", Font.PLAIN, 14));
        formPanel.add(addressField);

        add(formPanel, BorderLayout.CENTER);

        // **Button Panel**
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 248, 255));

        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(255, 69, 0)); // Red-orange
        backButton.setForeground(Color.WHITE);
        backButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            handlecancelTicket();
        } else if (e.getSource() == backButton) {
            dispose();
            //new Login(); // Return to Login Page
        }
    }

    private void handlecancelTicket() {
        String flightCodeToCancel = flightCodeField.getText().trim();
        String nameToMatch = nameField.getText().trim();
        String addressToMatch = addressField.getText().trim();

        if (flightCodeToCancel.isEmpty() || nameToMatch.isEmpty() || addressToMatch.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // **Step 1: Verify if the flight code exists in addandcancelflight.txt**
        boolean isFlightValid = false;
        try (BufferedReader flightReader = new BufferedReader(new FileReader("addandcancelflight.txt"))) {
            String line;
            while ((line = flightReader.readLine()) != null) {
                String[] flightData = line.split(",");
                if (flightData[0].equals(flightCodeToCancel)) {
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

        // **Step 2: Cancel the ticket from bookflightList.txt**
        File inputFile = new File("bookflightList.txt");
        File tempFile = new File("tempFile.txt");
        boolean ticketFound = false;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                String[] ticketData = currentLine.split(",");
                if (ticketData.length > 1 && ticketData[0].equals(flightCodeToCancel)
                        && ticketData[1].equals(nameToMatch) && ticketData[2].equals(addressToMatch)) {
                    ticketFound = true; // Found the ticket to cancel
                    continue; // Skip writing this line
                }
                writer.write(currentLine);
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error processing ticket cancellation!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (ticketFound) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(this, "Ticket Cancelled Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            tempFile.delete(); // Clean up temporary file if no match found
            JOptionPane.showMessageDialog(this, "No matching ticket found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CancelTicket();
    }
}
