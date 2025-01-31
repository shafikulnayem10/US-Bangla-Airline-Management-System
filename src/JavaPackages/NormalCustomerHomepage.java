package JavaPackages;

import javax.swing.*;
import java.awt.*;

public class NormalCustomerHomepage extends Homepage  {
    public NormalCustomerHomepage(String currentUsername) {
        super(currentUsername);
        initializeButtons();
        setVisible(true); // Ensure the frame is visible
    }

    @Override
    protected void initializeButtons() {
        // Set layout to null for manual styling
        buttonPanel.setLayout(null);

        // Book Flight Button
        JButton bookFlightButton = new JButton("Book a Flight");
        bookFlightButton.setBounds(100, 100, 250, 50); // Position and size
        bookFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookFlightButton.setBackground(new Color(135, 206, 250)); // Light blue
        bookFlightButton.setForeground(Color.BLACK);
        bookFlightButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        bookFlightButton.addActionListener(e -> new BookTicket());
        buttonPanel.add(bookFlightButton);

        // Cancel Booking Button
        JButton cancelBookingButton = new JButton("Cancel Booking");
        cancelBookingButton.setBounds(400, 100, 250, 50); // Position and size
        cancelBookingButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelBookingButton.setBackground(new Color(240, 128, 128)); // Light coral
        cancelBookingButton.setForeground(Color.BLACK);
        cancelBookingButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        cancelBookingButton.addActionListener(e -> new CancelTicket());
        buttonPanel.add(cancelBookingButton);

        // View Profile Button
        ImageIcon viewprofileicon=new ImageIcon(getClass().getResource("viewprofileimage.png"));
      
        JButton viewProfileButton = new JButton(viewprofileicon);
        viewProfileButton.setBounds(160, 180, 100, 100); // Position and size
        viewProfileButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewProfileButton.setBackground(Color.WHITE); // Pale green
        viewProfileButton.setForeground(Color.BLACK);
        viewProfileButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        viewProfileButton.addActionListener(e -> new ProfileView(currentUsername));
        buttonPanel.add(viewProfileButton);

        // View Flights Button
        JButton viewFlightsButton = new JButton("View Flights");
        viewFlightsButton.setBounds(400, 200, 250, 50); // Position and size
        viewFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewFlightsButton.setBackground(new Color(255, 228, 181)); // Moccasin
        viewFlightsButton.setForeground(Color.BLACK);
        viewFlightsButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        viewFlightsButton.addActionListener(e -> new ViewFlights());
        buttonPanel.add(viewFlightsButton);

        // Back Button
        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBounds(250, 300, 250, 50); // Position and size at the end
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(new Color(255, 69, 0)); // Red-orange
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        logoutButton.addActionListener(e -> {
            // Navigate back to login page
            dispose(); // Close the current frame
            new Login(); // Assuming LoginPage is the login class
        });
        buttonPanel.add(logoutButton);

       
    }

    

   
}