package JavaPackages;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class Homepage extends JFrame {
    protected final JLayeredPane layeredPane; // For layering components
    protected final JPanel buttonPanel; // Transparent panel for buttons

    public Homepage() {
        // Frame setup
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("BD");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout(20, 15));

        // Set the application icon
        ImageIcon bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Add header section
        addHeader();

        // Layered pane setup
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null); // Absolute layout for custom placement
        this.add(layeredPane, BorderLayout.CENTER);

        // Background panel
        JPanel backgroundPanel = new JPanel() {
            private final Image backgroundImage = new ImageIcon(getClass().getResource("AirPlanePic.jpg")).getImage();

            @Override
            protected void paintComponent(Graphics g) {
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
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Border border = BorderFactory.createLineBorder(Color.BLUE, 3);

        // US-Bangla Logo on the left
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("USBANGLAICON.png"));
        JLabel logoLabel = new JLabel(logoIcon);
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // Center Panel for "Important Text" section
        JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        messagePanel.setBackground(Color.WHITE);

        // Airplane Icon
        ImageIcon airplaneIcon = new ImageIcon(getClass().getResource("AirPlaneIcon.png"));
        JLabel airPlaneIconLabel = new JLabel(airplaneIcon);
        airPlaneIconLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        // "Important" Text section
        JLabel importantTextLabel = new JLabel("Important");
        importantTextLabel.setFont(new Font("Arial", Font.BOLD, 24));
        importantTextLabel.setForeground(new Color(139, 0, 0)); // Dark red color

        // Message Text section
        JLabel messageLabel = new JLabel("Enjoy SELF CHECK-IN KIOSK in domestic terminal");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        messageLabel.setForeground(Color.BLACK);

        // Add airplane icon and messages to the message panel
        messagePanel.add(airPlaneIconLabel);
        messagePanel.add(importantTextLabel);
        messagePanel.add(messageLabel);

        // Add message panel to the center of the header
        headerPanel.add(messagePanel, BorderLayout.CENTER);
        headerPanel.setBorder(border);

        // Add header panel to the frame
        this.add(headerPanel, BorderLayout.NORTH);
    }

    // Abstract method for button actions
    public abstract void actionPerformed(ActionEvent e);
}



