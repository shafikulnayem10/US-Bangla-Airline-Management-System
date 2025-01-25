package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Registration extends JFrame {
    protected JTextField nameField, usernameField;
    protected JPasswordField passwordField;
    protected JComboBox<String> statusComboBox;
    protected JButton signupButton;

    public Registration() {
        // Frame setup
        setTitle("User Registration");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Customize background color of the main frame
        getContentPane().setBackground(Color.cyan); // Light blue background

        // Components
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(new Color(25, 25, 112)); // Navy color

        nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBackground(new Color(245, 245, 245)); // Light gray background
        nameField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169))); // Gray border

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        usernameLabel.setForeground(new Color(25, 25, 112));

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBackground(new Color(245, 245, 245));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passwordLabel.setForeground(new Color(25, 25, 112));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(new Color(245, 245, 245));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setForeground(new Color(25, 25, 112));

        statusComboBox = new JComboBox<>(new String[]{"Admin", "Normal Customer", "Premium Customer"});
        statusComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        statusComboBox.setBackground(new Color(245, 245, 245));
        statusComboBox.setBorder(BorderFactory.createLineBorder(new Color(169, 169, 169)));

        // Sign Up Button with Manual Styling
        signupButton = new JButton("Sign Up");
        signupButton.setBackground(new Color(34, 139, 34)); // Green background
        signupButton.setForeground(Color.WHITE); // White text
        signupButton.setFont(new Font("Arial", Font.BOLD, 16)); // Bold font, size 16
        signupButton.setFocusPainted(false); // Remove focus border
        signupButton.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 0), 2)); // Dark green border
        signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor to hand on hover
        signupButton.addActionListener(this::handleSignup);

        // Adding components to the frame
        add(nameLabel);
        add(nameField);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(statusLabel);
        add(statusComboBox);
        add(new JLabel()); // Empty space
        add(signupButton);

        setVisible(true);
    }

    private void handleSignup(ActionEvent e) {
        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String status = (String) statusComboBox.getSelectedItem();

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File file = new File("users.txt");
        try {
            if (!file.exists() && !file.createNewFile()) {
                JOptionPane.showMessageDialog(this, "Failed to create the file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error creating file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "," + password + "," + status + "," + name);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Signup Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close registration window
            new Login(); // Open login page
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving user details!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Registration();
    }
}
