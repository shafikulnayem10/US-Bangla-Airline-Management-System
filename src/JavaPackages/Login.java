
package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    public Login() {
        // Set frame properties
        setTitle("Login Page");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top section for branding
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.DARK_GRAY);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel logo = new JLabel("US-BANGLA AIRLINES", JLabel.CENTER);
        logo.setFont(new Font("Arial", Font.BOLD, 20));
        logo.setForeground(Color.WHITE);
        topPanel.add(Box.createVerticalStrut(10)); // Add space
        topPanel.add(logo);
        topPanel.add(Box.createVerticalStrut(10)); // Add space

        add(topPanel, BorderLayout.NORTH);

        // Center panel for login form
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.WHITE);

        JLabel loginLabel = new JLabel("Log in", JLabel.CENTER);
        loginLabel.setFont(new Font("Arial", Font.BOLD, 24));
        loginLabel.setBounds(240, 20, 120, 30);
        centerPanel.add(loginLabel);

        JLabel usernameLabel = new JLabel("Login");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(150, 80, 100, 20);
        centerPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(230, 80, 200, 25);
        centerPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(150, 130, 100, 20);
        centerPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(230, 130, 200, 25);
        centerPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(Color.RED);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(230, 210, 100, 30);
        centerPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                // Validate credentials
                if ("admin".equals(username) && "admin".equals(password)) {
                    dispose(); // Close the login window
                    new AdminHomepage(); // Open Admin Homepage
                } else if ("customer".equals(username) && "customer".equals(password)) {
                    dispose();
                    new NormalCustomerHomepage(); // Open Normal Customer Homepage
                } else if ("premium".equals(username) && "premium".equals(password)) {
                    dispose();
                    new PremiumCustomerHomepage(); // Open Premium Customer Homepage
                } else {
                    JOptionPane.showMessageDialog(
                            Login.this,
                            "Invalid username or password!",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        add(centerPanel, BorderLayout.CENTER);

        // Footer section
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(Color.DARK_GRAY);
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JLabel footerText = new JLabel("© 2025 US-Bangla Airlines");
        footerText.setFont(new Font("Arial", Font.PLAIN, 12));
        footerText.setForeground(Color.WHITE);
        footerPanel.add(footerText);

        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}


