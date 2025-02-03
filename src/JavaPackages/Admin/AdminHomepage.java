package JavaPackages.Admin;

import JavaPackages.Share.ViewFlights;
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
        this.setVisible(true); 
        
        
        ImageIcon bdFlag = new ImageIcon("src/JavaPackages/Images/bdflag.png");
        this.setIconImage(bdFlag.getImage());
    }

    @Override
    protected void initializeButtons() {
        

        
       bookingFlightsButton = new JButton("Booking Flights");
       bookingFlightsButton.setBounds(50, 300, 200, 50);
     bookingFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
       bookingFlightsButton.setBackground(Color.BLUE);
       bookingFlightsButton.setForeground(Color.WHITE);
       bookingFlightsButton.setFocusPainted(false);
     bookingFlightsButton.setActionCommand("Booking Flights");
       bookingFlightsButton.addActionListener(this);
        buttonPanel.add(bookingFlightsButton);

       
      customerDetailsButton = new JButton("Customer Details");
      customerDetailsButton.setBounds(300, 300, 200, 50);
     customerDetailsButton.setFont(new Font("Arial", Font.BOLD, 16));
      customerDetailsButton.setBackground(Color.GREEN);
      customerDetailsButton.setActionCommand("Customer Details");
    customerDetailsButton.addActionListener(this);
        buttonPanel.add(customerDetailsButton);

     
      addFlightsButton = new JButton("Add Flights");
     addFlightsButton.setBounds(50, 400, 200, 50);
       addFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
       addFlightsButton.setBackground(Color.pink); 
       addFlightsButton.setActionCommand("Add Flights");
        addFlightsButton.addActionListener(this);
        buttonPanel.add(addFlightsButton);

      cancelFlightsButton = new JButton("Cancel Flights");
        cancelFlightsButton.setBounds(300, 400, 200, 50);
        cancelFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelFlightsButton.setBackground(Color.GREEN); 
      cancelFlightsButton.setActionCommand("Cancel Flights");
         cancelFlightsButton.addActionListener(this);
            buttonPanel.add(cancelFlightsButton);

        
        viewFlightsButton = new JButton("View Flights");
        viewFlightsButton.setBounds(50, 500, 200, 50);
        viewFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewFlightsButton.setBackground(Color.BLUE); 
       viewFlightsButton.setActionCommand("View Flights");
     viewFlightsButton.addActionListener(this);
       buttonPanel.add(viewFlightsButton);

      
        ImageIcon viewprofileicon = new ImageIcon("src/JavaPackages/Images/viewprofileicon.jpg");
        viewProfileButton = new JButton(viewprofileicon);
        viewProfileButton.setBounds(550, 300, 80, 80);
        viewProfileButton.setFont(new Font("Arial", Font.BOLD, 16));
          viewProfileButton.setBackground(Color.WHITE);
          viewProfileButton.setActionCommand("View Profile");
        viewProfileButton.addActionListener(this);
        buttonPanel.add(viewProfileButton);

       
        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(300, 500, 200, 50);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(Color.GREEN); 
        logoutButton.setForeground(Color.WHITE);
     logoutButton.setActionCommand("Log Out");
        logoutButton.addActionListener(this);
        buttonPanel.add(logoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); 

        if (command.equals("Booking Flights")) {
            new BookingFlights();
        } else if (command.equals("Customer Details")) {
            new ViewCustomerDetails(); 
        } else if (command.equals("Add Flights")) {
            new AddFlights(); 
        } else if (command.equals("Cancel Flights")) {
            new CancelFlights(); 
        } else if (command.equals("View Flights")) {
            new ViewFlights(); 
        } else if (command.equals("View Profile")) {
            new ProfileView(currentUsername, this); 
        } else if (command.equals("Log Out")) {
            new Login(); 
            dispose(); 
        } else {
            JOptionPane.showMessageDialog(this, "Unknown action: " + command);
        }
    }
}
