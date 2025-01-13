package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PremiumCustomerHomepage extends Homepage implements ActionListener {
    @Override
    protected void initializeButtons() {
        // Create buttons specific to premium customers
        JButton bookFlightButton = new JButton("Book a Flight");
        JButton viewWeatherButton = new JButton("Weather Details");
        JButton cancelBookingButton = new JButton("Cancel Ticket");
        JButton premiumSupportButton = new JButton("Contact Premium Support");

        // Style the buttons manually
        bookFlightButton.setBackground(Color.DARK_GRAY);
        bookFlightButton.setForeground(Color.WHITE);
        bookFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookFlightButton.setBorder(BorderFactory.createEtchedBorder());
        bookFlightButton.setFocusPainted(false);

        viewWeatherButton.setBackground(Color.BLUE);
        viewWeatherButton.setForeground(Color.WHITE);
        viewWeatherButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewWeatherButton.setBorder(BorderFactory.createEtchedBorder());
        viewWeatherButton.setFocusPainted(false);

        cancelBookingButton.setBackground(Color.ORANGE);
        cancelBookingButton.setForeground(Color.BLACK);
        cancelBookingButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelBookingButton.setBorder(BorderFactory.createEtchedBorder());
        cancelBookingButton.setFocusPainted(false);

        premiumSupportButton.setBackground(Color.GREEN);
        premiumSupportButton.setForeground(Color.WHITE);
        premiumSupportButton.setFont(new Font("Arial", Font.BOLD, 16));
        premiumSupportButton.setBorder(BorderFactory.createEtchedBorder());
        premiumSupportButton.setFocusPainted(false);

        // Add hover effects directly within the method
        bookFlightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bookFlightButton.setBackground(Color.LIGHT_GRAY); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bookFlightButton.setBackground(Color.DARK_GRAY); // Revert to original color
            }
        });

        viewWeatherButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewWeatherButton.setBackground(Color.CYAN); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                viewWeatherButton.setBackground(Color.BLUE); // Revert to original color
            }
        });

        cancelBookingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelBookingButton.setBackground(Color.YELLOW); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelBookingButton.setBackground(Color.ORANGE); // Revert to original color
            }
        });

        premiumSupportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                premiumSupportButton.setBackground(Color.GREEN); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                premiumSupportButton.setBackground(Color.GREEN); // Revert to original color
            }
        });

        // Set button positions to place them in the middle of the available space
        int yPosition = 400; // Adjust as needed for vertical alignment
        int buttonWidth = 250;
        int buttonHeight = 50;

        bookFlightButton.setBounds(50, yPosition, buttonWidth, buttonHeight);
        viewWeatherButton.setBounds(350, yPosition, buttonWidth, buttonHeight);
        cancelBookingButton.setBounds(650, yPosition, buttonWidth, buttonHeight);
        premiumSupportButton.setBounds(950, yPosition, buttonWidth, buttonHeight);

        // Add action listeners to the buttons
        bookFlightButton.addActionListener(this);
        viewWeatherButton.addActionListener(this);
        cancelBookingButton.addActionListener(this);
        premiumSupportButton.addActionListener(this);

        // Add buttons to the button panel
        buttonPanel.add(bookFlightButton);
        buttonPanel.add(viewWeatherButton);
        buttonPanel.add(cancelBookingButton);
        buttonPanel.add(premiumSupportButton);

        // Revalidate and repaint to ensure proper rendering
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click events
        String command = e.getActionCommand();
        switch (command) {
            case "Book a Flight" -> JOptionPane.showMessageDialog(this, "Navigating to flight booking!");
            case "Weather Details" -> JOptionPane.showMessageDialog(this, "Displaying weather details for your location!");
            case "Cancel Ticket" -> JOptionPane.showMessageDialog(this, "Cancel ticket functionality initiated!");
            case "Contact Premium Support" -> JOptionPane.showMessageDialog(this, "Connecting to premium support!");
            default -> JOptionPane.showMessageDialog(this, "Unknown action: " + command);
        }
    }

    public PremiumCustomerHomepage() {
        super(); // Call the abstract class constructor
        setVisible(true); // Ensure the frame is visible
    }
}
