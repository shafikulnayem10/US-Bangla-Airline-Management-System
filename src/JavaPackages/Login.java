package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox statusComboBox;
    private JButton loginButton, signupButton, changePasswordButton, closeButton;

    public Login() {
        // Set frame properties
        setTitle("Login Page");
        setSize(600, 500);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
          // Set the application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Load the background image
      ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("log in page bg.jpg"));




        // Create a custom panel for the background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null); // Use absolute layout for components
        setContentPane(backgroundPanel);

        // Create and style components manually
        JLabel statusLabel = new JLabel("Log in as:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBounds(50, 50, 150, 30);
        backgroundPanel.add(statusLabel);

        String statuses[] = {"Admin", "Normal Customer", "Premium Customer"};
        statusComboBox = new JComboBox(statuses);
        statusComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        statusComboBox.setBackground(Color.WHITE);
        statusComboBox.setForeground(Color.DARK_GRAY);
        statusComboBox.setBounds(200, 50, 300, 30);
        backgroundPanel.add(statusComboBox);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setBounds(50, 100, 150, 30);
        backgroundPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBackground(Color.WHITE);
        usernameField.setForeground(Color.DARK_GRAY);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        usernameField.setBounds(200, 100, 300, 30);
        backgroundPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(50, 150, 150, 30);
        backgroundPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.DARK_GRAY);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        passwordField.setBounds(200, 150, 300, 30);
        backgroundPanel.add(passwordField);

        loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(72, 209, 204)); // Light turquoise
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        loginButton.setBounds(50, 220, 200, 40);
        loginButton.addActionListener(this);
        backgroundPanel.add(loginButton);

        signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        signupButton.setBackground(new Color(72, 209, 204)); // Light turquoise
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        signupButton.setBounds(300, 220, 200, 40);
        signupButton.addActionListener(this);
        backgroundPanel.add(signupButton);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 16));
        changePasswordButton.setBackground(new Color(72, 209, 204)); // Light turquoise
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        changePasswordButton.setBounds(50, 290, 200, 40);
        changePasswordButton.addActionListener(this);
        backgroundPanel.add(changePasswordButton);

        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(new Color(255, 69, 58)); // Light red for close
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        closeButton.setBounds(300, 290, 200, 40);
        closeButton.addActionListener(e -> System.exit(0)); // Exit program
        backgroundPanel.add(closeButton);

        // Make the frame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == signupButton) {
            new Registration(); // Open registration window
        } else if (e.getSource() == changePasswordButton) {
            new ChangePassword(); // Open change password window
        }
    }

    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String status = (String) statusComboBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData[0].equals(username) && userData[1].equals(password) && userData[2].equals(status)) {
                    JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Close login window
                    // Navigate to the appropriate homepage
                    if ("Admin".equals(status)) {
                        new AdminHomepage(username);
                    } else if ("Normal Customer".equals(status)) {
                        new NormalCustomerHomepage(username);
                    } else if ("Premium Customer".equals(status)) {
                        new PremiumCustomerHomepage(username);
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading user data!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
