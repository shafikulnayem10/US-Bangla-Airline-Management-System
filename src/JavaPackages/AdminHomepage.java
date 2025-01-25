package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminHomepage extends Homepage implements ActionListener {
    private JButton bookingFlightsButton;
    private JButton customerDetailsButton;
    private JButton addFlightsButton;
    private JButton cancelFlightsButton;
    private JButton viewProfileButton;
    private JButton backButton;

    public AdminHomepage(String currentUsername) {
        super(currentUsername);
        setVisible(true); // Ensure the frame is visible
        // Set the application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());
    }

    @Override
    protected void initializeButtons() {
        // Manually style each button

        // Booking Flights button
        bookingFlightsButton = new JButton("Booking Flights");
        bookingFlightsButton.setBounds(50, 300, 200, 50);
        bookingFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookingFlightsButton.setBackground(new Color(135, 206, 250)); // Light blue
        bookingFlightsButton.setFocusPainted(false);
        bookingFlightsButton.setActionCommand("Booking Flights");
        bookingFlightsButton.addActionListener(this);

        // Customer Details button
        customerDetailsButton = new JButton("Customer Details");
        customerDetailsButton.setBounds(300, 300, 200, 50);
        customerDetailsButton.setFont(new Font("Arial", Font.BOLD, 16));
        customerDetailsButton.setBackground(new Color(144, 238, 144)); // Light green
        customerDetailsButton.setFocusPainted(false);
        customerDetailsButton.setActionCommand("Customer Details");
        customerDetailsButton.addActionListener(this);

        // Add Flights button
        addFlightsButton = new JButton("Add Flights");
        addFlightsButton.setBounds(50, 400, 200, 50);
        addFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        addFlightsButton.setBackground(new Color(255, 182, 193)); // Light pink
        addFlightsButton.setFocusPainted(false);
        addFlightsButton.setActionCommand("Add Flights");
        addFlightsButton.addActionListener(this);

        // Cancel Flights button
        cancelFlightsButton = new JButton("Cancel Flights");
        cancelFlightsButton.setBounds(300, 400, 200, 50);
        cancelFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelFlightsButton.setBackground(new Color(255, 160, 122)); // Salmon
        cancelFlightsButton.setFocusPainted(false);
        cancelFlightsButton.setActionCommand("Cancel Flights");
        cancelFlightsButton.addActionListener(this);

        // View Profile button
        viewProfileButton = new JButton("View Profile");
        viewProfileButton.setBounds(550, 400, 200, 50);
        viewProfileButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewProfileButton.setBackground(new Color(240, 230, 140)); // Light yellow
        viewProfileButton.setFocusPainted(false);
        viewProfileButton.setActionCommand("View Profile");
        viewProfileButton.addActionListener(this);

        // Back button
        backButton = new JButton("Back");
        backButton.setBounds(300, 500, 200, 50);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(255, 69, 0)); // Red-orange
        backButton.setForeground(Color.WHITE); // White text
        backButton.setFocusPainted(false);
        backButton.setActionCommand("Back");
        backButton.addActionListener(this);

        // Add buttons to the panel
        buttonPanel.add(bookingFlightsButton);
        buttonPanel.add(customerDetailsButton);
        buttonPanel.add(addFlightsButton);
        buttonPanel.add(cancelFlightsButton);
        buttonPanel.add(viewProfileButton);
        buttonPanel.add(backButton);

        // Refresh panel
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the action command from the button
        switch (command) {
            case "Booking Flights" -> new BookingFlights(); // Open Booking Flights page
            case "Customer Details" -> new ViewCustomerDetails(); // Open Customer Details page
            case "Add Flights" -> new AddFlights(); // Open Add Flights page
            case "Cancel Flights" -> new CancelFlights(); // Open Cancel Flights page
            case "View Profile" -> new ProfileView(currentUsername); // Open Profile View page
            case "Back" -> {
                new Login(); // Return to login page
                dispose(); // Close the admin homepage
            }
            default -> JOptionPane.showMessageDialog(this, "Unknown action: " + command);
        }
    }
}
