package JavaPackages.Admin;

import JavaPackages.Admin.ViewCustomerDetails;
import JavaPackages.Admin.ViewFlights;
import JavaPackages.Admin.CancelFlights;
import JavaPackages.Admin.AddFlights;
import JavaPackages.Share.BookingFlights;
import JavaPackages.Share.Homepage;
import JavaPackages.Login.Login;
import JavaPackages.Share.ProfileView;
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
    private JButton logoutButton;
    private JButton viewFlightsButton;

    public AdminHomepage(String currentUsername) {
        super(currentUsername);
        setVisible(true); // Ensure the frame is visible
        
        // Set the application icon
        ImageIcon bdFlag = new ImageIcon("C:/Users/USER/OneDrive - American International University-Bangladesh/Desktop/dev-1/test project/US-Bangla-Airline-Management-System/src/JavaPackages/Images/bdflag.png");
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
        buttonPanel.add(bookingFlightsButton);

        // Customer Details button
        customerDetailsButton = new JButton("Customer Details");
        customerDetailsButton.setBounds(300, 300, 200, 50);
        customerDetailsButton.setFont(new Font("Arial", Font.BOLD, 16));
        customerDetailsButton.setBackground(new Color(144, 238, 144)); // Light green
        customerDetailsButton.setFocusPainted(false);
        customerDetailsButton.setActionCommand("Customer Details");
        customerDetailsButton.addActionListener(this);
        buttonPanel.add(customerDetailsButton);

        // Add Flights button
        addFlightsButton = new JButton("Add Flights");
        addFlightsButton.setBounds(50, 400, 200, 50);
        addFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        addFlightsButton.setBackground(new Color(255, 182, 193)); // Light pink
        addFlightsButton.setFocusPainted(false);
        addFlightsButton.setActionCommand("Add Flights");
        addFlightsButton.addActionListener(this);
        buttonPanel.add(addFlightsButton);

        // Cancel Flights button
        cancelFlightsButton = new JButton("Cancel Flights");
        cancelFlightsButton.setBounds(300, 400, 200, 50);
        cancelFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelFlightsButton.setBackground(new Color(255, 160, 122)); // Salmon
        cancelFlightsButton.setFocusPainted(false);
        cancelFlightsButton.setActionCommand("Cancel Flights");
        cancelFlightsButton.addActionListener(this);
        buttonPanel.add(cancelFlightsButton);

        // View Flights button
        viewFlightsButton = new JButton("View Flights");
        viewFlightsButton.setBounds(50, 500, 200, 50);
        viewFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewFlightsButton.setBackground(new Color(173, 216, 230)); // Light blue
        viewFlightsButton.setFocusPainted(false);
        viewFlightsButton.setActionCommand("View Flights");
        viewFlightsButton.addActionListener(this);
        buttonPanel.add(viewFlightsButton);

        // View Profile button
        ImageIcon viewprofileicon = new ImageIcon("C:/Users/USER/OneDrive - American International University-Bangladesh/Desktop/dev-1/test project/US-Bangla-Airline-Management-System/src/JavaPackages/Images/viewprofileicon.jpg");
        viewProfileButton = new JButton(viewprofileicon);
        viewProfileButton.setBounds(550, 300, 80, 80);
        viewProfileButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewProfileButton.setBackground(Color.WHITE);
        viewProfileButton.setFocusPainted(false);
        viewProfileButton.setActionCommand("View Profile");
        viewProfileButton.addActionListener(this);
        buttonPanel.add(viewProfileButton);

        // Logout button
        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(300, 500, 200, 50);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(new Color(255, 69, 0)); // Red-orange
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setActionCommand("Log Out");
        logoutButton.addActionListener(this);
        buttonPanel.add(logoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // Get the action command from the button

        if (command.equals("Booking Flights")) {
            new BookingFlights(); // Open Booking Flights page
        } else if (command.equals("Customer Details")) {
            new ViewCustomerDetails(); // Open Customer Details page
        } else if (command.equals("Add Flights")) {
            new AddFlights(); // Open Add Flights page
        } else if (command.equals("Cancel Flights")) {
            new CancelFlights(); // Open Cancel Flights page
        } else if (command.equals("View Flights")) {
            new ViewFlights(); // Open View Flights page
        } else if (command.equals("View Profile")) {
            new ProfileView(currentUsername, this); // Open Profile View page
        } else if (command.equals("Log Out")) {
            new Login(); // Return to login page
            dispose(); // Close the admin homepage
        } else {
            JOptionPane.showMessageDialog(this, "Unknown action: " + command);
        }
    }
}
