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
    private JLabel titleLabel, nameLabel, usernameLabel, passwordLabel, statusLabel;
    private String currentUsername;
    private JPanel formPanel, buttonPanel;

    public ProfileView(String currentUsername) {
        this.currentUsername = currentUsername;

        // Frame setup
        setTitle("Profile View");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Set application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Title Label
        titleLabel = new JLabel("Profile Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(70, 130, 180)); // Steel blue
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel with GridLayout
        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(nameLabel);

        nameField = new JTextField();
        formPanel.add(nameField);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setEditable(false); // Username is not editable
        formPanel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        statusLabel = new JLabel("Status:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(statusLabel);

        String[] statuses = {"Admin", "Normal Customer", "Premium Customer"};
        statusComboBox = new JComboBox<>(statuses);
        formPanel.add(statusComboBox);

        add(formPanel, BorderLayout.CENTER);

        // Load user data
        loadUserData();

        // Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBackground(new Color(100, 149, 237)); // Cornflower blue
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.addActionListener(this);
        buttonPanel.add(updateButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(255, 69, 0)); // Red-orange
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

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
            handleupdateProfile();
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }

    private void handleupdateProfile() {
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

