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
    private JComboBox  statusComboBox;
    private JButton loginButton, signupButton, changePasswordButton, closeButton;
    private JLabel titleLabel, usernameLabel, passwordLabel, statusLabel;
    private JPanel formPanel, buttonPanel;

    public Login() {
        // Set frame properties
        setTitle("Login Page");
        setSize(600, 500);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
       
        // Set the application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Load the background image
        ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("log in page bg.jpg"));
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setLayout(new BorderLayout());
        // Add backgroundLabel as content pane
        setContentPane(backgroundLabel);
        // Title Label
        titleLabel = new JLabel("Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(72, 61, 139)); // Dark Slate Blue
        titleLabel.setForeground(Color.WHITE);
        backgroundLabel.add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        formPanel.setOpaque(false);

        // Labels (Now all white)
        statusLabel = new JLabel("Log in as:");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 20));
        statusLabel.setForeground(Color.WHITE);
        formPanel.add(statusLabel);

        String[] statuses = {"Admin", "Normal Customer", "Premium Customer"};
        statusComboBox = new JComboBox (statuses);
        statusComboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        formPanel.add(statusComboBox);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setForeground(Color.WHITE);
        formPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        formPanel.add(usernameField);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 19));
        passwordLabel.setForeground(Color.WHITE);
        formPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 10));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        formPanel.add(passwordField);

        backgroundLabel.add(formPanel, BorderLayout.CENTER);

        // Button Panel
        buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        buttonPanel.setOpaque(false);

        loginButton = new JButton("Log In");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setBackground(new Color(72, 209, 204)); // Light turquoise
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.addActionListener(this);
        buttonPanel.add(loginButton);

        signupButton = new JButton("Sign Up");
        signupButton.setFont(new Font("Arial", Font.BOLD, 20));
        signupButton.setBackground(new Color(72, 209, 204)); // Light turquoise
        signupButton.setForeground(Color.WHITE);
        signupButton.setFocusPainted(false);
        signupButton.addActionListener(this);
        buttonPanel.add(signupButton);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setFont(new Font("Arial", Font.BOLD, 20));
        changePasswordButton.setBackground(new Color(72, 209, 204));
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFocusPainted(false);
        changePasswordButton.addActionListener(this);
        buttonPanel.add(changePasswordButton);

        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 20));
        closeButton.setBackground(new Color(255, 69, 58)); // Light red for close
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0)); // Exit program
        buttonPanel.add(closeButton);

        backgroundLabel.add(buttonPanel, BorderLayout.SOUTH);
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
