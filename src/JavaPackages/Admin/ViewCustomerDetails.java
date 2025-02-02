package JavaPackages.Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ViewCustomerDetails extends JFrame implements ActionListener {
    private JTable customerTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton backButton;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private ImageIcon bdFlag;

    public ViewCustomerDetails() {
        // Frame setup
        setTitle("BD");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

      // Set the application icon
        ImageIcon bdFlag = new ImageIcon("C:/Users/USER/OneDrive - American International University-Bangladesh/Desktop/dev-1/test project/US-Bangla-Airline-Management-System/src/JavaPackages/Images/bdflag.png");
        this.setIconImage(bdFlag.getImage());

        // Title label setup
        titleLabel = new JLabel("Customer Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(135, 206, 250)); // Light blue
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Username", "Password", "Status", "Name"};
        tableModel = new DefaultTableModel(columnNames, 0);
        customerTable = new JTable(tableModel);

        // Style table header
        JTableHeader header = customerTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(60, 179, 113)); // Medium sea green
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false); // Prevent column reordering

        // Style table cells
        customerTable.setFont(new Font("Arial", Font.PLAIN, 14));
        customerTable.setRowHeight(25);
        customerTable.setGridColor(Color.LIGHT_GRAY);
        customerTable.setBackground(new Color(245, 245, 245)); // Light gray
        customerTable.setSelectionBackground(new Color(135, 206, 250)); // Light blue when selected
        customerTable.setSelectionForeground(Color.BLACK); // Black text when selected

        // Scroll pane
        scrollPane = new JScrollPane(customerTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load customer data
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 4 && !"Admin".equalsIgnoreCase(userData[2])) {
                    // Only add Normal Customer and Premium Customer details to the table
                    tableModel.addRow(userData);
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error loading customer details!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Back button setup
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(255, 69, 0)); // Red-orange
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this); // Add ActionListener

        // Add back button to a panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center the button
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        setVisible(true);
    }

   
    // ActionListener implementation for Back button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose(); // Close the frame
        }
    }
}


