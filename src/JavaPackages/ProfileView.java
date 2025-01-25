package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ProfileView extends JFrame implements ActionListener {
    private JTextField nameField, usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> statusComboBox;
    private JButton updateButton, backButton;
    private final String currentUsername;

    public ProfileView(String currentUsername) {
        this.currentUsername = currentUsername;

        // Frame setup
        setTitle("Profile View");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Manual positioning

        // Set application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Title Label
        JLabel titleLabel = new JLabel("Profile Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(new Color(70, 130, 180)); // Steel blue
        titleLabel.setOpaque(true);
        titleLabel.setBounds(0, 0, 600, 50);
        add(titleLabel);

        // Name Label
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setBounds(50, 80, 150, 30);
        add(nameLabel);

        // Name Field
        nameField = new JTextField();
        nameField.setBounds(200, 80, 300, 30);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(nameField);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setBounds(50, 140, 150, 30);
        add(usernameLabel);

        // Username Field
        usernameField = new JTextField();
        usernameField.setBounds(200, 140, 300, 30);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        usernameField.setEditable(false); // Username is not editable
        add(usernameField);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setBounds(50, 200, 150, 30);
        add(passwordLabel);

        // Password Field
        passwordField = new JPasswordField();
        passwordField.setBounds(200, 200, 300, 30);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(passwordField);

        // Status Label
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBounds(50, 260, 150, 30);
        add(statusLabel);

        // Status ComboBox
        statusComboBox = new JComboBox<>(new String[]{"Admin", "Normal Customer", "Premium Customer"});
        statusComboBox.setBounds(200, 260, 300, 30);
        statusComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        add(statusComboBox);

        // Update Button
        updateButton = new JButton("Update");
        updateButton.setBounds(200, 320, 120, 40);
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBackground(new Color(100, 149, 237)); // Cornflower blue
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setBorder(BorderFactory.createEmptyBorder());
        updateButton.addActionListener(this);
        add(updateButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(350, 320, 120, 40);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 69, 0)); // Red orange
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder());
        backButton.addActionListener(e -> dispose());
        add(backButton);

        // Load user data
        loadUserData();

        setVisible(true);
    }

    private void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(currentUsername)) {
                    usernameField.setText(userData[0]);
                    passwordField.setText(userData[1]);
                    statusComboBox.setSelectedItem(userData[2]);
                    nameField.setText(userData[3]);
                    break;
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading user data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String updatedName = nameField.getText().trim();
            String updatedPassword = new String(passwordField.getPassword()).trim();
            String updatedStatus = (String) statusComboBox.getSelectedItem();

            if (updatedName.isEmpty() || updatedPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File inputFile = new File("users.txt");
            File tempFile = new File("tempUsers.txt");
            boolean userUpdated = false;

            try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
            ) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] userData = line.split(",");
                    if (userData[0].equals(currentUsername)) {
                        writer.write(currentUsername + "," + updatedPassword + "," + updatedStatus + "," + updatedName);
                        writer.newLine();
                        userUpdated = true;
                    } else {
                        writer.write(line);
                        writer.newLine();
                    }
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error updating profile!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (userUpdated) {
                if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                    JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error saving updated profile!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                tempFile.delete();
                JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
