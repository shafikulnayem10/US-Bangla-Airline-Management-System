package JavaPackages.Admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CancelFlights extends JFrame implements ActionListener {
     private JTextField flightCodeField;
     private JComboBox fromComboBox, toComboBox;
    private JButton removeButton, backButton;
    private JLabel titleLabel, fromLabel, toLabel, flightCodeLabel;
    private JPanel formPanel, buttonPanel;

    public CancelFlights() {
       
        this.setTitle("Cancel Flights");
        this.setSize(500, 300);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

      
        ImageIcon bdFlag = new ImageIcon("src/JavaPackages/Images/bdflag.png");
        this.setIconImage(bdFlag.getImage());

       
        titleLabel = new JLabel("Cancel Flights", JLabel.CENTER);
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

    
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        

        removeButton = new JButton("Remove Flight");
        removeButton.setFont(new Font("Arial", Font.BOLD, 14));
        removeButton.setBackground(Color.GREEN); 
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(this);
        buttonPanel.add(removeButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.RED); 
        backButton.setForeground(Color.WHITE);
        
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == removeButton) {
            handleremoveFlight();
        } else if (e.getSource() == backButton) {
            dispose(); // Close the window when Back button is clicked
        }
    }

    private void handleremoveFlight() {
        String from = (String) fromComboBox.getSelectedItem();
        String to = (String) toComboBox.getSelectedItem();
        String flightCode = flightCodeField.getText().trim();
        boolean flightFound = false;

        if (from.equals(to)) {
            JOptionPane.showMessageDialog(this, "From and To locations cannot be the same!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (flightCode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Flight Code cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File inputFile = new File("addandcancelflight.txt");
        File tempFile = new File("tempFlights.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] flightData = line.split(",");
      
               if (flightData.length > 2&& flightData[0].equals(flightCode) &&  flightData[1].equals(from) && flightData[2].equals(to)) {
                        flightFound = true; 
                        continue;
                    }
                
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error processing file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        
        if (flightFound) {
            if (inputFile.delete() && tempFile.renameTo(inputFile)) {
                JOptionPane.showMessageDialog(this, "Flight removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error updating file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            tempFile.delete();
            JOptionPane.showMessageDialog(this, "No matching flight found!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new CancelFlights();
    }
}
