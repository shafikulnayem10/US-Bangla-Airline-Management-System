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
     
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Additional button actions (if needed)
    }

  
}

