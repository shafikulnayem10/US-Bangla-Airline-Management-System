package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class ChangePassword extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> statusComboBox;
    private JButton updateButton;

    public ChangePassword() {
        // Frame setup
        setTitle("Change Password");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null); // Use absolute layout for manual placement

        // Background color
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue

        // Labels
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setForeground(new Color(25, 25, 112)); // Navy color
        usernameLabel.setBounds(50, 50, 150, 30);

        JLabel passwordLabel = new JLabel("New Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(new Color(25, 25, 112));
        passwordLabel.setBounds(50, 100, 150, 30);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setForeground(new Color(25, 25, 112));
        statusLabel.setBounds(50, 150, 150, 30);

        // Text fields
        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBackground(new Color(245, 245, 245)); // Light gray
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));
        usernameField.setBounds(200, 50, 200, 30);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(new Color(245, 245, 245)); // Light gray
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));
        passwordField.setBounds(200, 100, 200, 30);

        statusComboBox = new JComboBox<>(new String[]{"Admin", "Normal Customer", "Premium Customer"});
        statusComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        statusComboBox.setBackground(new Color(245, 245, 245)); // Light gray
        statusComboBox.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));
        statusComboBox.setBounds(200, 150, 200, 30);

        // Update button
        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 16));
        updateButton.setBackground(new Color(34, 139, 34)); // Green
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 2));
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.setBounds(150, 250, 150, 40);
        updateButton.addActionListener(this::handleUpdate);

        // Adding components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(statusLabel);
        add(statusComboBox);
        add(updateButton);

        setVisible(true);
    }

    private void handleUpdate(ActionEvent e) {
        String username = usernameField.getText().trim();
        String newPassword = new String(passwordField.getPassword()).trim();
        String status = (String) statusComboBox.getSelectedItem();

        if (username.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File inputFile = new File("users.txt");
        File tempFile = new File("tempUsers.txt");
        boolean userFound = false;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(username)) {
                    writer.write(username + "," + newPassword + "," + status + "," + userData[3]);
                    writer.newLine();
                    userFound = true;
                } else {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error processing file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userFound) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(this, "Details updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close change password window
            } else {
                JOptionPane.showMessageDialog(this, "Error updating file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            tempFile.delete();
            JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ChangePassword();
    }
}
