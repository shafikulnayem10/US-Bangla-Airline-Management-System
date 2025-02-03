
package JavaPackages.PremiumCustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CustomerSupport extends JFrame implements ActionListener {
    private JTextField usernameField, emailField;
    private JTextArea problemArea;
    private JButton submitButton, backButton;

    public CustomerSupport() {
        
        setTitle("Customer Support");
        setSize(500, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       
        JLabel titleLabel = new JLabel("Customer Support", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLUE); 
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

       
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

       
        formPanel.add(new JLabel("E-mail:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        JLabel problemLabel = new JLabel("Problem:");
        formPanel.add(problemLabel);
        
        problemArea = new JTextArea(3, 20);
        problemArea.setLineWrap(true);
        problemArea.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(problemArea);
        formPanel.add(scrollPane);

        add(formPanel, BorderLayout.CENTER);

       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(Color.GREEN); 
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.ORANGE); 
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            handleSupportRequest();
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }

    private void handleSupportRequest() {
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String problem = problemArea.getText().trim();

        if (username.isEmpty() || email.isEmpty() || problem.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File file = new File("supportRequests.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "," + email + "," + problem);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Support request submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving request!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CustomerSupport();
    }
}
