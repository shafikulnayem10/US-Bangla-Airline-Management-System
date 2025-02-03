package JavaPackages.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class AddFlights extends JFrame implements ActionListener {
    private JTextField flightCodeField;
    private JComboBox   fromComboBox, toComboBox;
    private JButton addButton, backButton;
    private JLabel titleLabel, fromLabel, toLabel, flightCodeLabel;
    private JPanel formPanel, buttonPanel;

    public AddFlights() {
        
        this.setTitle("Add Flights");
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));
        
        ImageIcon bdFlag = new ImageIcon("src/JavaPackages/Images/bdflag.png");
          this.setIconImage(bdFlag.getImage());

      
          titleLabel = new JLabel("Add Flights", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
         titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLUE); 
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel, BorderLayout.NORTH);
    
        
     formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
      

       fromLabel = new JLabel("From:");
         fromLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(fromLabel);

         String[] fromLocations = {"Dhaka", "Chittagong", "Sylhet"};
           fromComboBox = new JComboBox (fromLocations);
        formPanel.add(fromComboBox);

        toLabel = new JLabel("To:");
          toLabel.setFont(new Font("Arial", Font.BOLD, 14));
       formPanel.add(toLabel);

         String[] toLocations = {"Chittagong", "Sylhet", "Dhaka"};
       toComboBox = new JComboBox (toLocations);
        formPanel.add(toComboBox);

        flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(flightCodeLabel);

        flightCodeField = new JTextField();
        formPanel.add(flightCodeField);

        this.add(formPanel, BorderLayout.CENTER);


        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

       addButton = new JButton("Add Flight");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(Color.GREEN); 
       addButton.setForeground(Color.WHITE);
          addButton.addActionListener(this);
         buttonPanel.add(addButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
          backButton.setBackground(Color.GREEN); 
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
         buttonPanel.add(backButton);

       this.add(buttonPanel, BorderLayout.SOUTH);

       
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            handleaddFlight();
        } else if (e.getSource() == backButton) {
            dispose(); 
        }
    }

    private void handleaddFlight() {
        String from = (String) fromComboBox.getSelectedItem();
        String to = (String) toComboBox.getSelectedItem();
        String flightCode = flightCodeField.getText().trim();

        if (from.equals(to)) {
            JOptionPane.showMessageDialog(this, "From and To locations cannot be the same!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (flightCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Flight Code cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File file = new File("addandcancelflight.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(flightCode + "," + from + "," + to);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Flight added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding flight!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


