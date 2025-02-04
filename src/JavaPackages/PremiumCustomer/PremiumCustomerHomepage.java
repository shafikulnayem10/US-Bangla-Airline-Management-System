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
    private JButton bookFlightButton, cancelFlightButton, profileButton, viewFlightsButton, customerSupportButton, weatherInfoButton,loungeAccessButton,flightUpgradeButton, logoutButton;
    
    public PremiumCustomerHomepage(String currentUsername) {
        super(currentUsername);
        initializeButtons();
        setVisible(true); 
    }
 
    @Override
    protected void initializeButtons() {
    
        buttonPanel.setLayout(null);

     
        bookFlightButton = new JButton("Book Flight");
        bookFlightButton.setBounds(300, 100, 200, 50);
        bookFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookFlightButton.setBackground(Color.GREEN);
        bookFlightButton.setForeground(Color.BLACK);
        bookFlightButton.addActionListener(e -> new BookTicket());
        buttonPanel.add(bookFlightButton);
 
        
        cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.setBounds(550, 100, 200, 50);
        cancelFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelFlightButton.setBackground(Color.GREEN); 
        cancelFlightButton.setForeground(Color.BLACK);
        cancelFlightButton.addActionListener(e -> new CancelTicket());
        buttonPanel.add(cancelFlightButton);
 
        viewFlightsButton = new JButton("View Flight Details");
        viewFlightsButton.setBounds(300, 180, 200, 50);
        viewFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewFlightsButton.setBackground(Color.GREEN); 
        viewFlightsButton.setForeground(Color.BLACK);
        viewFlightsButton.addActionListener(e -> new ViewFlights());
        buttonPanel.add(viewFlightsButton);
 
       
        customerSupportButton = new JButton("Customer Support");
        customerSupportButton.setBounds(550, 180, 200, 50);
        customerSupportButton.setFont(new Font("Arial", Font.BOLD, 16));
        customerSupportButton.setBackground(Color.GREEN); 
        customerSupportButton.setForeground(Color.BLACK);
        customerSupportButton.addActionListener(e -> new CustomerSupport());
        buttonPanel.add(customerSupportButton);
 
        
        weatherInfoButton = new JButton("Weather Information");
        weatherInfoButton.setBounds(300, 260, 200, 50);
        weatherInfoButton.setFont(new Font("Arial", Font.BOLD, 16));
        weatherInfoButton.setBackground(Color.ORANGE); 
        weatherInfoButton.setForeground(Color.BLACK);
        weatherInfoButton.addActionListener(e -> new WeatherInformation());
        buttonPanel.add(weatherInfoButton);
     

JButton loungeAccessButton = new JButton("Lounge Access");
loungeAccessButton.setBounds(550, 260, 200, 50);
loungeAccessButton.setFont(new Font("Arial", Font.BOLD, 16));
loungeAccessButton.setBackground(Color.PINK);
loungeAccessButton.setForeground(Color.BLACK);
loungeAccessButton.addActionListener(e -> new LoungeAccess());
buttonPanel.add(loungeAccessButton);


JButton flightUpgradeButton = new JButton("Request Flight Upgrade");
flightUpgradeButton.setBounds(300, 360, 250, 50);
flightUpgradeButton.setFont(new Font("Arial", Font.BOLD, 16));
flightUpgradeButton.setBackground(Color.YELLOW);
flightUpgradeButton.setForeground(Color.BLACK);
flightUpgradeButton.addActionListener(e -> new FlightUpgrade());
buttonPanel.add(flightUpgradeButton);

        
        ImageIcon viewprofileicon = new ImageIcon("src/JavaPackages/Images/viewprofileicon.jpg");
        profileButton = new JButton(viewprofileicon);
        profileButton.setBounds(650, 330, 80, 80);
        profileButton.setFont(new Font("Arial", Font.BOLD, 16));
        profileButton.setBackground(Color.ORANGE); 
        profileButton.setForeground(Color.BLACK);
        profileButton.addActionListener(e -> {
            new ProfileView(currentUsername, this);
        });
        buttonPanel.add(profileButton);

        
        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(375, 450, 200, 50);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(Color.RED); 
        logoutButton.setForeground(Color.BLACK);
        logoutButton.addActionListener(e -> {
            dispose(); 
            new Login();
        });
        buttonPanel.add(logoutButton);
    }
public static void main(String[] args) {
    new PremiumCustomerHomepage("TestUser"); 
}

}
