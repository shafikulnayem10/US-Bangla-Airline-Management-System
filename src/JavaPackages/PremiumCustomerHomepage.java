package JavaPackages;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class PremiumCustomerHomepage extends Homepage implements ActionListener {
    private JButton bookFlightButton, cancelFlightButton, profileButton, viewFlightsButton, customerSupportButton, weatherInfoButton, logoutButton;
 
    public PremiumCustomerHomepage(String currentUsername) {
        super(currentUsername);
        initializeButtons();
        setVisible(true); // Ensure the frame is visible
    }
 
    @Override
    protected void initializeButtons() {
        // **Set absolute layout for manual positioning**
        buttonPanel.setLayout(null);

        // **Book Flight Button**
        bookFlightButton = new JButton("Book Flight");
        bookFlightButton.setBounds(250, 100, 250, 50);
        bookFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookFlightButton.setBackground(new Color(135, 206, 250)); // Light blue
        bookFlightButton.setForeground(Color.BLACK);
        bookFlightButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        bookFlightButton.addActionListener(e -> new BookTicket());
        buttonPanel.add(bookFlightButton);
 
        // **Cancel Flight Button**
        cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.setBounds(550, 100, 250, 50);
        cancelFlightButton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelFlightButton.setBackground(new Color(240, 128, 128)); // Light coral
        cancelFlightButton.setForeground(Color.BLACK);
        cancelFlightButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        cancelFlightButton.addActionListener(e -> new CancelTicket());
        buttonPanel.add(cancelFlightButton);
 
        // **View Flights Button**
        viewFlightsButton = new JButton("View Flight Details");
        viewFlightsButton.setBounds(250, 180, 250, 50);
        viewFlightsButton.setFont(new Font("Arial", Font.BOLD, 16));
        viewFlightsButton.setBackground(new Color(255, 228, 181)); // Moccasin
        viewFlightsButton.setForeground(Color.BLACK);
        viewFlightsButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        viewFlightsButton.addActionListener(e -> new ViewFlights());
        buttonPanel.add(viewFlightsButton);
 
        // **Customer Support Button**
        customerSupportButton = new JButton("Customer Support");
        customerSupportButton.setBounds(550, 180, 250, 50);
        customerSupportButton.setFont(new Font("Arial", Font.BOLD, 16));
        customerSupportButton.setBackground(new Color(152, 251, 152)); // Pale green
        customerSupportButton.setForeground(Color.BLACK);
        customerSupportButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        customerSupportButton.addActionListener(e -> new CustomerSupport());
        buttonPanel.add(customerSupportButton);
 
        // **Weather Information Button**
        weatherInfoButton = new JButton("Weather Information");
        weatherInfoButton.setBounds(250, 260, 250, 50);
        weatherInfoButton.setFont(new Font("Arial", Font.BOLD, 16));
        weatherInfoButton.setBackground(new Color(70, 130, 180)); // Steel blue
        weatherInfoButton.setForeground(Color.WHITE);
        weatherInfoButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        weatherInfoButton.addActionListener(e -> new WeatherInformation());
        buttonPanel.add(weatherInfoButton);
 
        // **Profile Button**
        profileButton = new JButton("Profile");
        profileButton.setBounds(550, 260, 250, 50);
        profileButton.setFont(new Font("Arial", Font.BOLD, 16));
        profileButton.setBackground(new Color(255, 215, 0)); // Gold
        profileButton.setForeground(Color.BLACK);
        profileButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        profileButton.addActionListener(e -> new ProfileView(currentUsername));
        buttonPanel.add(profileButton);

        // **Logout Button**
        logoutButton = new JButton("Log Out");
        logoutButton.setBounds(400, 340, 250, 50);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBackground(new Color(255, 69, 0)); // Red-orange
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        logoutButton.addActionListener(e -> {
            dispose(); // Close the current frame
            new Login(); // Navigate back to the login page
        });
        buttonPanel.add(logoutButton);
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        // Not needed as action listeners are set directly in initializeButtons()
    }
}
