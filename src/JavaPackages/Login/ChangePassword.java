package JavaPackages.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ChangePassword extends JFrame implements ActionListener {
 private JTextField usernameField;
  private JPasswordField passwordField;
  private JComboBox  statusComboBox;
  private JButton updateButton, backButton;
  private JLabel titleLabel, usernameLabel, passwordLabel, statusLabel;
 private JPanel formPanel, buttonPanel;

    public ChangePassword() {
       
      this.setTitle("Change Password");
      this. setSize(500, 400);
      this. setLocationRelativeTo(null);
       this. setDefaultCloseOperation(DISPOSE_ON_CLOSE);
     this. setLayout(new BorderLayout());

        
      titleLabel = new JLabel("Change Password", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
      titleLabel.setOpaque(true);
      titleLabel.setBackground(Color.BLUE);
       titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
       this. add(titleLabel, BorderLayout.NORTH);

      formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
      formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
     formPanel.setBackground(Color.MAGENTA); 

      usernameLabel = new JLabel("Username:");
       usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
      usernameLabel.setForeground(Color.BLACK);
      formPanel.add(usernameLabel);

      usernameField = new JTextField();
       usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
      formPanel.add(usernameField);

         passwordLabel = new JLabel("New Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
      passwordLabel.setForeground(Color.BLACK);
       formPanel.add(passwordLabel);

        passwordField = new JPasswordField();
      passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
      formPanel.add(passwordField);

      statusLabel = new JLabel("Status:");
      statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        statusLabel.setForeground(Color.BLACK);
      formPanel.add(statusLabel);

      String[] statuses = {"Admin", "Normal Customer", "Premium Customer"};
      statusComboBox = new JComboBox (statuses);
      statusComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
     formPanel.add(statusComboBox);

      this.add(formPanel, BorderLayout.CENTER);

      
      buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
     buttonPanel.setBackground(Color.BLUE);

      updateButton = new JButton("Update");
     updateButton.setFont(new Font("Arial", Font.BOLD, 16));
     updateButton.setBackground(Color.GREEN);
      updateButton.setForeground(Color.WHITE);
     updateButton.addActionListener(this);
      buttonPanel.add(updateButton);

     backButton = new JButton("Back");
    backButton.setFont(new Font("Arial", Font.BOLD, 16));
     backButton.setBackground(Color.GREEN); 
    backButton.setForeground(Color.WHITE);
     backButton.addActionListener(this);
    buttonPanel.add(backButton);

       this.add(buttonPanel, BorderLayout.SOUTH);

    setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            handleUpdate();
        } else if (e.getSource() == backButton) {
            dispose();
         
        }
    }

    private void handleUpdate() {
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
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error processing file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userFound) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
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
