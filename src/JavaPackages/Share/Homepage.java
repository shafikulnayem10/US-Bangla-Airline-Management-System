package JavaPackages.Share;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public abstract class Homepage extends JFrame {
   protected  JPanel headerPanel;
    protected  JLayeredPane layeredPane; 
    protected  JPanel buttonPanel,messagePanel ; 
    protected String currentUsername;
    protected JLabel logoLabel,airPlaneIconLabel,importantTextLabel,messageLabel, footerLabel   ;
    public Homepage(String currentUsername) {
        this.currentUsername = currentUsername; 

       
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("BD");
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setLayout(new BorderLayout(20, 15));

        ImageIcon bdFlag = new ImageIcon("src/JavaPackages/Images/bdflag.png");
        setIconImage(bdFlag.getImage());
      
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
    

       
      ImageIcon logoIcon = new ImageIcon("src/JavaPackages/Images/USBANGLAICON.png");
     logoLabel = new JLabel(logoIcon);
      headerPanel.add(logoLabel, BorderLayout.WEST);

         messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        messagePanel.setBackground(Color.WHITE);

        
        ImageIcon airplaneIcon = new ImageIcon("src/JavaPackages/Images/AirPlaneIcon.png");
       airPlaneIconLabel = new JLabel( airplaneIcon);
        messagePanel.add(airPlaneIconLabel);
        
        importantTextLabel = new JLabel("Important");
        importantTextLabel.setFont(new Font("Arial", Font.BOLD, 24));
        importantTextLabel.setForeground(Color.red); 
      messagePanel.add(importantTextLabel);
       
        messageLabel = new JLabel("Enjoy SELF CHECK-IN KIOSK in domestic terminal");
      messageLabel.setFont(new Font("Arial", Font.PLAIN, 24));
       messageLabel.setForeground(Color.BLACK);
        messagePanel.add(messageLabel);
    
 
        headerPanel.add(messagePanel, BorderLayout.CENTER);
        
        this.add(headerPanel, BorderLayout.NORTH);

  
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null); 
        this.add(layeredPane, BorderLayout.CENTER);

        
        JPanel backgroundPanel = new JPanel() {
            public final Image backgroundImage = new ImageIcon("src/JavaPackages/Images/AirPlanePic.jpg").getImage();

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setBounds(0, 0, 1920, 1080);
        layeredPane.add(backgroundPanel, Integer.valueOf(0)); 

        
      buttonPanel = new JPanel();
     buttonPanel.setOpaque(false); 
        buttonPanel.setLayout(null); 
        buttonPanel.setBounds(0, 0, 1920, 1080);
        layeredPane.add(buttonPanel, Integer.valueOf(1)); 

        
        footerLabel = new JLabel("© 2025 US-Bangla Airlines", JLabel.CENTER);
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
         this.add(footerLabel, BorderLayout.SOUTH);

        initializeButtons();
    }

    
    protected abstract void initializeButtons();

  

}




