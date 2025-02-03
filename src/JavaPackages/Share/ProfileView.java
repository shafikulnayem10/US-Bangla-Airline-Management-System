package JavaPackages.Share;

import JavaPackages.Login.Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ProfileView extends JFrame implements ActionListener {
    private JTextField nameField, usernameField;
    private JPasswordField passwordField;
    private JComboBox  statusComboBox;
    private JButton updateButton, backButton;
    private JLabel titleLabel, nameLabel, usernameLabel, passwordLabel, statusLabel;
    private String currentUsername;
    private JPanel formPanel, buttonPanel;
    private JFrame parentHomepage;


    public ProfileView(String currentUsername, JFrame parentHomepage) {
        this.currentUsername = currentUsername;
        this.parentHomepage = parentHomepage;

      
        setTitle("Profile View");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

       
        ImageIcon bdFlag = new ImageIcon("src/JavaPackages/Images/bdflag.png");
        this.setIconImage(bdFlag.getImage());

        titleLabel = new JLabel("Profile Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.YELLOW); 
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        
        formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
      

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(nameLabel);

        nameField = new JTextField();
        formPanel.add(nameField);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setEditable(false); 
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
        statusComboBox = new JComboBox (statuses);
        formPanel.add(statusComboBox);

        this.add(formPanel, BorderLayout.CENTER);

        
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
       
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        updateButton = new JButton("Update");
        updateButton.setFont(new Font("Arial", Font.BOLD, 14));
        updateButton.setBackground(Color.BLUE); 
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(this);
        buttonPanel.add(updateButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.GREEN);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);
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
        JOptionPane.showMessageDialog(this, "Profile updated successfully! Please log in again.", "Success", JOptionPane.INFORMATION_MESSAGE);
        
        if (parentHomepage != null) {
            parentHomepage.dispose(); 
        }

        new Login(); 
        dispose(); 
    } else {
        JOptionPane.showMessageDialog(this, "Error saving updated profile!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
 else {
            tempFile.delete();
            JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

