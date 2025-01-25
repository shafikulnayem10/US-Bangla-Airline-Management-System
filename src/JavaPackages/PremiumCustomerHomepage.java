package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PremiumCustomerHomepage extends Homepage implements ActionListener {
    public PremiumCustomerHomepage(String currentUsername) {
        super(currentUsername);
        setVisible(true); // Ensure the frame is visible
    }

    @Override
    protected void initializeButtons() {
        JButton bookFlightButton = new JButton("Book a Flight");
        JButton viewWeatherButton = new JButton("Weather Details");
        JButton cancelBookingButton = new JButton("Cancel Booking");
        JButton viewProfileButton = new JButton("View Profile");
        JButton viewFlightsButton = new JButton("View Flights"); // New button

        configureButton(bookFlightButton, 50, 400);
        configureButton(viewWeatherButton, 250, 400);
        configureButton(cancelBookingButton, 450, 400);
        configureButton(viewProfileButton, 650, 400);
        configureButton(viewFlightsButton, 850, 400); // Configure position of the new button

        bookFlightButton.addActionListener(e -> new BookTicket());
        viewWeatherButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Displaying weather details!"));
        cancelBookingButton.addActionListener(e -> new CancelTicket());
        viewProfileButton.addActionListener(e -> new ProfileView(currentUsername));
        viewFlightsButton.addActionListener(e -> new ViewFlights()); // Add action for "View Flights"

        buttonPanel.add(bookFlightButton);
        buttonPanel.add(viewWeatherButton);
        buttonPanel.add(cancelBookingButton);
        buttonPanel.add(viewProfileButton);
        buttonPanel.add(viewFlightsButton); // Add the new button to the panel

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Additional button actions (if needed)
    }

    private void configureButton(JButton button, int x, int y) {
        button.setBounds(x, y, 200, 50);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.LIGHT_GRAY);
        button.setFocusPainted(false);
    }
}

