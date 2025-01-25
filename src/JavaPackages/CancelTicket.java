package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CancelTicket extends JFrame implements ActionListener {
    private JTextField flightCodeField, addressField;
    private JButton cancelButton;

    public CancelTicket() {
        // Frame setup
        setTitle("Cancel Ticket");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Header
        JLabel headerLabel = new JLabel("Cancel Your Ticket", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.RED);
        add(headerLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        Font labelFont = new Font("Arial", Font.BOLD, 14);

        JLabel flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(labelFont);
        flightCodeLabel.setForeground(Color.GREEN);
        formPanel.add(flightCodeLabel);

        flightCodeField = new JTextField();
        flightCodeField.setBackground(Color.LIGHT_GRAY);
        formPanel.add(flightCodeField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(Color.GREEN);
        formPanel.add(addressLabel);

        addressField = new JTextField();
        addressField.setBackground(Color.ORANGE);
        formPanel.add(addressField);

        add(formPanel, BorderLayout.CENTER);

        // Cancel Button
        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        add(cancelButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            String flightCodeToCancel = flightCodeField.getText().trim();
            String addressToMatch = addressField.getText().trim();
            boolean ticketFound = false;

            if (flightCodeToCancel.isEmpty() || addressToMatch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Both fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File inputFile = new File("bookflightList.txt");
            File tempFile = new File("tempFile.txt");

            try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
            ) {
                String currentLine;

                while ((currentLine = reader.readLine()) != null) {
                    String[] ticketData = currentLine.split(",");
                    if (ticketData.length > 1 && ticketData[0].equals(flightCodeToCancel) && ticketData[2].equals(addressToMatch)) {
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
                } else {
                    JOptionPane.showMessageDialog(this, "Error updating file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                tempFile.delete(); // Clean up temporary file if no match found
                JOptionPane.showMessageDialog(this, "No matching ticket found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new CancelTicket();
    }
}
