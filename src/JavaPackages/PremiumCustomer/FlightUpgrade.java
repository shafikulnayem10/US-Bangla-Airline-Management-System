package JavaPackages.PremiumCustomer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FlightUpgrade extends JFrame implements ActionListener {
    private JTextField usernameField, flightCodeField;
    private JComboBox upgradeClassBox;
    private JButton submitButton, backButton;

    public FlightUpgrade() {
        setTitle("Request Flight Upgrade");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Flight Upgrade Request", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLUE);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

      
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);

        formPanel.add(new JLabel("Flight Code:"));
        flightCodeField = new JTextField();
        formPanel.add(flightCodeField);

        formPanel.add(new JLabel("Upgrade To:"));
        String[] classOptions = {"Business Class", "First Class"};
        upgradeClassBox = new JComboBox<>(classOptions);
        formPanel.add(upgradeClassBox);

        add(formPanel, BorderLayout.CENTER);

       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        submitButton = new JButton("Submit Request");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(Color.GREEN);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        buttonPanel.add(submitButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            handleUpgradeRequest();
        } else if (e.getSource() == backButton) {
            dispose();
        }
    }

    private void handleUpgradeRequest() {
        String username = usernameField.getText().trim();
        String flightCode = flightCodeField.getText().trim();
        String upgradeClass = (String) upgradeClassBox.getSelectedItem();

        if (username.isEmpty() || flightCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File file = new File("upgradeRequests.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(username + "," + flightCode + "," + upgradeClass);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Upgrade Request Submitted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error saving request!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FlightUpgrade();
    }
}
