package JavaPackages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NormalCustomerHomepage extends Homepage implements ActionListener {
    @Override
    protected void initializeButtons() {
        // Create buttons specific to normal customers
        JButton bookFlightButton = new JButton("Book a Flight");
        JButton viewBookingDetailsButton = new JButton("View Flight Details");
        JButton cancelBookingButton = new JButton("Cancel Booking");

        // Style the buttons manually
        bookFlightButton.setBackground(Color.ORANGE);
        bookFlightButton.setForeground(Color.WHITE);
        bookFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookFlightButton.setBorder(BorderFactory.createEtchedBorder());
        bookFlightButton.setFocusPainted(false);

        viewBookingDetailsButton.setBackground(Color.MAGENTA);
        viewBookingDetailsButton.setForeground(Color.WHITE);
        viewBookingDetailsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewBookingDetailsButton.setBorder(BorderFactory.createEtchedBorder());
        viewBookingDetailsButton.setFocusPainted(false);

        cancelBookingButton.setBackground(Color.YELLOW);
        cancelBookingButton.setForeground(Color.BLACK);
        cancelBookingButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelBookingButton.setBorder(BorderFactory.createEtchedBorder());
        cancelBookingButton.setFocusPainted(false);

        // Add hover effects directly within the method
        bookFlightButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                bookFlightButton.setBackground(Color.RED); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bookFlightButton.setBackground(Color.ORANGE); // Revert to original color
            }
        });

        viewBookingDetailsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                viewBookingDetailsButton.setBackground(Color.BLUE); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                viewBookingDetailsButton.setBackground(Color.MAGENTA); // Revert to original color
            }
        });

        cancelBookingButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelBookingButton.setBackground(Color.GRAY); // Change to hover color
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelBookingButton.setBackground(Color.YELLOW); // Revert to original color
            }
        });

        // Set button positions to place them in the middle of the available space
        int yPosition = 400; // Adjust as needed for vertical alignment
        int buttonWidth = 200;
        int buttonHeight = 50;

        bookFlightButton.setBounds(100, yPosition, buttonWidth, buttonHeight);
        viewBookingDetailsButton.setBounds(400, yPosition, buttonWidth, buttonHeight);
        cancelBookingButton.setBounds(700, yPosition, buttonWidth, buttonHeight);

        // Add action listeners to the buttons
        bookFlightButton.addActionListener(this);
        viewBookingDetailsButton.addActionListener(this);
        cancelBookingButton.addActionListener(this);

        // Add buttons to the button panel
        buttonPanel.add(bookFlightButton);
        buttonPanel.add(viewBookingDetailsButton);
        buttonPanel.add(cancelBookingButton);

        // Revalidate and repaint to ensure proper rendering
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click events
        String command = e.getActionCommand();
       /* switch (command) {
            case "Book a Flight" -> JOptionPane.showMessageDialog(this, "Navigating to flight booking!");
            case "View Flight Details" -> JOptionPane.showMessageDialog(this, "Displaying booking details!");
            case "Cancel Booking" -> JOptionPane.showMessageDialog(this, "Cancel booking functionality!");
            default -> JOptionPane.showMessageDialog(this, "Unknown action: " + command);
        }*/
        if(command.equals("Book a Flight")){
           new BookTicket();
           //cancelBookingButton.setVisible(true); // Show the toggle button after booking
          }
        else if(command.equals("Cancel Booking")){
           new CancelTicket();
           //cancelTicketButton.setVisible(true); // Show the toggle button after booking
          }
    }

    public NormalCustomerHomepage() {
        super(); // Call the abstract class constructor
        setVisible(true); // Ensure the frame is visible
    }
}

