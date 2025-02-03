package JavaPackages.NormalCustomer;

import JavaPackages.Share.ViewFlights;
import JavaPackages.Share.BookTicket;
import JavaPackages.Share.CancelTicket;
import JavaPackages.Share.Homepage;
import JavaPackages.Login.Login;
import JavaPackages.Share.ProfileView;
import javax.swing.*;
import java.awt.*;

public class NormalCustomerHomepage extends Homepage  {
    public NormalCustomerHomepage(String currentUsername) {
        super(currentUsername);
        initializeButtons();
        setVisible(true); 
    }

    @Override
    protected void initializeButtons() {
       
        buttonPanel.setLayout(null);

        
        JButton bookFlightButton = new JButton("Book a Flight");
        bookFlightButton.setBounds(100, 100, 250, 50); 
        bookFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookFlightButton.setBackground(Color.BLUE); 
        bookFlightButton.setForeground(Color.BLACK);
        bookFlightButton.addActionListener(e -> new BookTicket());
        buttonPanel.add(bookFlightButton);

       
      JButton cancelBookingButton = new JButton("Cancel Booking");
      cancelBookingButton.setBounds(400, 100, 250, 50); 
      cancelBookingButton.setFont(new Font("Arial", Font.BOLD, 16));
    cancelBookingButton.setBackground(Color.orange); 
        cancelBookingButton.setForeground(Color.BLACK);
      cancelBookingButton.addActionListener(e -> new CancelTicket());
        buttonPanel.add(cancelBookingButton);

      
        ImageIcon viewprofileicon = new ImageIcon("src/JavaPackages/Images/viewprofileicon.jpg");
       JButton viewProfileButton = new JButton(viewprofileicon);
      
        viewProfileButton.setBounds(160, 180, 100, 100); 
           viewProfileButton.setFont(new Font("Arial", Font.BOLD, 16));
      viewProfileButton.setBackground(Color.WHITE);
        viewProfileButton.setForeground(Color.BLACK);
        viewProfileButton.addActionListener(e -> {
            new ProfileView(currentUsername, this);
        });
        buttonPanel.add(viewProfileButton);

        
        JButton viewFlightsButton = new JButton("View Flights");
     viewFlightsButton.setBounds(400, 200, 250, 50); 
        viewFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewFlightsButton.setBackground(Color.MAGENTA);
     viewFlightsButton.setForeground(Color.BLACK);
           viewFlightsButton.addActionListener(e -> new ViewFlights());
        buttonPanel.add(viewFlightsButton);

       
          JButton logoutButton = new JButton("Log Out");
     logoutButton.setBounds(250, 300, 250, 50); 
      logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
      logoutButton.setBackground(new Color(255, 69, 0));
        logoutButton.setForeground(Color.WHITE);
          logoutButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
      logoutButton.addActionListener(e -> {
            
            dispose(); 
            new Login(); 
        });
        buttonPanel.add(logoutButton);

       
    }

    

   
}