package JavaPackages.PremiumCustomer;
 
import JavaPackages.Share.ViewFlights;
import JavaPackages.Share.BookTicket;
import JavaPackages.Share.CancelTicket;
import JavaPackages.Share.Homepage;
import JavaPackages.Login.Login;
import JavaPackages.Share.ProfileView;
import javax.swing.*;
import java.awt.*;
 
public class PremiumCustomerHomepage extends Homepage  {
    private JButton bookFlightButton, cancelFlightButton, profileButton, viewFlightsButton, customerSupportButton, weatherInfoButton, logoutButton;
 
    public PremiumCustomerHomepage(String currentUsername) {
        super(currentUsername);
        initializeButtons();
        setVisible(true); 
    }
 
    @Override
    protected void initializeButtons() {
    
        buttonPanel.setLayout(null);

     
        bookFlightButton = new JButton("Book Flight");
        bookFlightButton.setBounds(250, 100, 250, 50);
        bookFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookFlightButton.setBackground(Color.GREEN);
        bookFlightButton.setForeground(Color.BLACK);
        bookFlightButton.addActionListener(e -> new BookTicket());
        buttonPanel.add(bookFlightButton);
 
        
        cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.setBounds(550, 100, 250, 50);
        cancelFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelFlightButton.setBackground(Color.GREEN); 
        cancelFlightButton.setForeground(Color.BLACK);
        cancelFlightButton.addActionListener(e -> new CancelTicket());
        buttonPanel.add(cancelFlightButton);
 
        viewFlightsButton = new JButton("View Flight Details");
        viewFlightsButton.setBounds(250, 180, 250, 50);
        viewFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewFlightsButton.setBackground(Color.GREEN); 
        viewFlightsButton.setForeground(Color.BLACK);
        viewFlightsButton.addActionListener(e -> new ViewFlights());
        buttonPanel.add(viewFlightsButton);
 
       
        customerSupportButton = new JButton("Customer Support");
        customerSupportButton.setBounds(550, 180, 250, 50);
        customerSupportButton.setFont(new Font("Arial", Font.BOLD, 16));
        customerSupportButton.setBackground(Color.GREEN); 
        customerSupportButton.setForeground(Color.BLACK);
        customerSupportButton.addActionListener(e -> new CustomerSupport());
        buttonPanel.add(customerSupportButton);
 
        
        weatherInfoButton = new JButton("Weather Information");
        weatherInfoButton.setBounds(250, 260, 250, 50);
        weatherInfoButton.setFont(new Font("Arial", Font.BOLD, 16));
        weatherInfoButton.setBackground(Color.BLUE); 
        weatherInfoButton.setForeground(Color.WHITE);
        weatherInfoButton.addActionListener(e -> new WeatherInformation());
        buttonPanel.add(weatherInfoButton);
 
       
        ImageIcon viewprofileicon = new ImageIcon("src/JavaPackages/Images/viewprofileicon.jpg");
        profileButton = new JButton(viewprofileicon);
        profileButton.setBounds(550, 260, 80, 80);
        profileButton.setFont(new Font("Arial", Font.BOLD, 16));
        profileButton.setBackground(Color.WHITE); 
        profileButton.setForeground(Color.BLACK);
        profileButton.addActionListener(e -> {
            new ProfileView(currentUsername, this);
        });
        buttonPanel.add(profileButton);

        
        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(400, 400, 250, 50);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(Color.RED); 
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> {
            dispose(); 
            new Login();
        });
        buttonPanel.add(logoutButton);
    }
 
   
}
