package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminHomepage extends Homepage implements ActionListener {
    @Override
    protected void initializeButtons() {
        // Create buttons specific to the admin
        JButton viewFlightsButton = new JButton("View Flight Details");
        JButton cancelFlightsButton = new JButton("Cancel Flight");
        JButton viewCustomerDetailsButton = new JButton("View Customer Details");

        // Style the buttons manually
        viewFlightsButton.setBackground(Color.BLUE);
        viewFlightsButton.setForeground(Color.WHITE);
        viewFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewFlightsButton.setBorder(BorderFactory.createEtchedBorder());
        viewFlightsButton.setFocusPainted(false);

        cancelFlightsButton.setBackground(Color.RED);
        cancelFlightsButton.setForeground(Color.WHITE);
        cancelFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelFlightsButton.setBorder(BorderFactory.createEtchedBorder());
        cancelFlightsButton.setFocusPainted(false);

        viewCustomerDetailsButton.setBackground(Color.GREEN);
        viewCustomerDetailsButton.setForeground(Color.WHITE);
        viewCustomerDetailsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewCustomerDetailsButton.setBorder(BorderFactory.createEtchedBorder());
        viewCustomerDetailsButton.setFocusPainted(false);

        // Add hover effects directly within the method
        viewFlightsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewFlightsButton.setBackground(Color.CYAN); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                viewFlightsButton.setBackground(Color.BLUE); // Revert to original color
            }
        });

        cancelFlightsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelFlightsButton.setBackground(Color.PINK); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelFlightsButton.setBackground(Color.RED); // Revert to original color
            }
        });

        viewCustomerDetailsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewCustomerDetailsButton.setBackground(Color.LIGHT_GRAY); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                viewCustomerDetailsButton.setBackground(Color.GREEN); // Revert to original color
            }
        });

        // Set button positions to place them in the middle of the available space
        int yPosition = 400; // Adjust as needed for vertical alignment
        int buttonWidth = 200;
        int buttonHeight = 50;

        viewFlightsButton.setBounds(100, yPosition, buttonWidth, buttonHeight);
        cancelFlightsButton.setBounds(400, yPosition, buttonWidth, buttonHeight);
        viewCustomerDetailsButton.setBounds(700, yPosition, buttonWidth, buttonHeight);

        // Add action listeners to the buttons
        viewFlightsButton.addActionListener(this);
        cancelFlightsButton.addActionListener(this);
        viewCustomerDetailsButton.addActionListener(this);

        // Add buttons to the button panel
        buttonPanel.add(viewFlightsButton);
        buttonPanel.add(cancelFlightsButton);
        buttonPanel.add(viewCustomerDetailsButton);

        // Revalidate and repaint to ensure proper rendering
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click events
        String command = e.getActionCommand();
        switch (command) {
            case "View Flight Details" -> JOptionPane.showMessageDialog(this, "Displaying flight details!");
            case "Cancel Flight" -> JOptionPane.showMessageDialog(this, "Cancel flight functionality!");
            case "View Customer Details" -> JOptionPane.showMessageDialog(this, "Displaying customer details!");
            default -> JOptionPane.showMessageDialog(this, "Unknown action: " + command);
        }
    }

    public AdminHomepage() {
        super(); // Call the abstract class constructor
        setVisible(true); // Ensure the frame is visible
    }
}

