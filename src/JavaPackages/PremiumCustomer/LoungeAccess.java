package JavaPackages.PremiumCustomer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoungeAccess extends JFrame {
    String[] columnNames = {"Airport", "Lounge Name", "Services"};
        String[][] rows = {
        {"Dhaka", "SkyLounge", "Free Wi-Fi, Refreshments"},
         {"Chittagong", "VIP Lounge", "Comfortable Seating, Snacks"},
          {"Sylhet", "Premium Lounge", "Private Rooms, Free Drinks"}
        };
        
        
    public LoungeAccess() {
        setTitle("Lounge Access");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JLabel titleLabel = new JLabel("Exclusive Lounges for Premium Customers", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.PINK);
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

       
        DefaultTableModel model = new DefaultTableModel(rows, columnNames);
        JTable loungeTable = new JTable(model);
        loungeTable.setFont(new Font("Arial", Font.PLAIN, 14));
        loungeTable.setRowHeight(25);
        loungeTable.setDefaultEditor(Object.class, null); 

       
        JScrollPane scrollPane = new JScrollPane(loungeTable);
        add(scrollPane, BorderLayout.CENTER);

       
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(Color.ORANGE);
        closeButton.setForeground(Color.BLACK);
        closeButton.addActionListener(e -> dispose());

       
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoungeAccess();
    }
}
