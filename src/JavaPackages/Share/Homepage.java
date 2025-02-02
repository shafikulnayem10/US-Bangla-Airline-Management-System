package JavaPackages.Share;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class Homepage extends JFrame {
    protected  JLayeredPane layeredPane; // For layering components
    protected  JPanel buttonPanel; // Transparent panel for buttons
    protected String currentUsername; // Stores the username of the logged-in user
   protected  JPanel headerPanel;
    public Homepage(String currentUsername) {
        this.currentUsername = currentUsername; // Pass the username of the logged-in user

        // Frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("BD");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout(20, 15));

      // Set the application icon
        ImageIcon bdFlag = new ImageIcon("C:/Users/USER/OneDrive - American International University-Bangladesh/Desktop/dev-1/test project/US-Bangla-Airline-Management-System/src/JavaPackages/Images/bdflag.png");
        this.setIconImage(bdFlag.getImage());
      //frame section ends
        // Add header section
        addHeader();

        // Layered pane setup
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null); // Absolute layout for custom placement
        this.add(layeredPane, BorderLayout.CENTER);

        // Background panel
        JPanel backgroundPanel = new JPanel() {
            public final Image backgroundImage = new ImageIcon("C:/Users/USER/OneDrive - American International University-Bangladesh/Desktop/dev-1/test project/US-Bangla-Airline-Management-System/src/JavaPackages/Images/AirPlanePic.jpg").getImage();

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setBounds(0, 0, 1920, 1080);
        layeredPane.add(backgroundPanel, Integer.valueOf(0)); // Add background at the lowest layer

        // Button panel setup
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Transparent panel
        buttonPanel.setLayout(null); // Absolute layout for precise positioning
        buttonPanel.setBounds(0, 0, 1920, 1080);
        layeredPane.add(buttonPanel, Integer.valueOf(1)); // Add buttons above the background

        // Add footer section
        JLabel footerLabel = new JLabel("© 2025 US-Bangla Airlines", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        this.add(footerLabel, BorderLayout.SOUTH);

        initializeButtons();
    }

    // Abstract method to initialize buttons
    protected abstract void initializeButtons();

    // Method to add the header
    private void addHeader() {
        // Header panel setup
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 3);

        // US-Bangla Logo on the left
        ImageIcon logoIcon = new ImageIcon("C:/Users/USER/OneDrive - American International University-Bangladesh/Desktop/dev-1/test project/US-Bangla-Airline-Management-System/src/JavaPackages/Images/USBANGLAICON.png");
        JLabel logoLabel = new JLabel(logoIcon);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Center Panel for "Important Text" section
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        messagePanel.setBackground(Color.WHITE);

        // Airplane Icon
        ImageIcon airplaneIcon = new ImageIcon("C:/Users/USER/OneDrive - American International University-Bangladesh/Desktop/dev-1/test project/US-Bangla-Airline-Management-System/src/JavaPackages/Images/AirPlaneIcon.png");
        JLabel airPlaneIconLabel = new JLabel( airplaneIcon);
        airPlaneIconLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        messagePanel.add(airPlaneIconLabel);
        // "Important" Text section
        JLabel importantTextLabel = new JLabel("Important");
        importantTextLabel.setFont(new Font("Arial", Font.BOLD, 24));
        importantTextLabel.setForeground(new Color(139, 0, 0)); // Dark red color
        messagePanel.add(importantTextLabel);
        // Message Text section
        JLabel messageLabel = new JLabel("Enjoy SELF CHECK-IN KIOSK in domestic terminal");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        messageLabel.setForeground(Color.BLACK);
        messagePanel.add(messageLabel);
    
  
        // Add message panel to the center of the header
        headerPanel.add(messagePanel, BorderLayout.CENTER);
        headerPanel.setBorder(border);

        // Add header panel to the frame
        this.add(headerPanel, BorderLayout.NORTH);
    }

    //Abstract method for button actions
   // public abstract void actionPerformed(ActionEvent e);
}




