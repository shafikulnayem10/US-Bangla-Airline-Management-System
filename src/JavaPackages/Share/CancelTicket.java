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
        
        setTitle("Cancel Ticket");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

       
          titleLabel = new JLabel("Cancel Your Ticket", JLabel.CENTER);
       titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
       titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLUE);
       titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

       
          formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(Color.cyan);

        
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

      
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.GREEN); 
        backButton.setForeground(Color.WHITE);
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

        File inputFile = new File("bookflightList.txt");
        File tempFile = new File("tempFile.txt");
        boolean ticketFound = false;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String Line;

            while ((Line = reader.readLine()) != null) {
                String[] ticketData = Line.split(",");
                if (ticketData.length > 1 && ticketData[0].equals(flightCodeToCancel)
                        && ticketData[1].equals(nameToMatch) && ticketData[2].equals(addressToMatch)) {
                    ticketFound = true; 
                    continue; 
                }
                writer.write(Line);
                writer.newLine();
            }
        } catch (Exception ex) {
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
            tempFile.delete(); 
            JOptionPane.showMessageDialog(this, "No matching ticket found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CancelTicket();
    }
}
