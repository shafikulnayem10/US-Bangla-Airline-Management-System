package JavaPackages;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class BookingFlights extends JFrame implements ActionListener {
    private JTable bookingTable;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    private JButton backButton;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private ImageIcon bdFlag;

    public BookingFlights() {
        // Frame setup
        setTitle("BD");
        setSize(900, 500); // Updated size for better view
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);

        // Set the application icon
        bdFlag = new ImageIcon(getClass().getResource("bdflag.png"));
        this.setIconImage(bdFlag.getImage());

        // Title label setup
        titleLabel = new JLabel("Booking Flights Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(135, 206, 250)); // Light blue
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Table setup
        String[] columnNames = {"Flight Code", "Name", "Address", "From", "To", "Trip Type"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookingTable = new JTable(tableModel);

        // Style table header
        JTableHeader header = bookingTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(new Color(60, 179, 113)); // Medium sea green
        header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false); // Prevent column reordering

        // Style table cells
        bookingTable.setFont(new Font("Arial", Font.PLAIN, 14));
        bookingTable.setRowHeight(25);
        bookingTable.setGridColor(Color.LIGHT_GRAY);
        bookingTable.setBackground(new Color(245, 245, 245)); // Light gray
        bookingTable.setSelectionBackground(new Color(135, 206, 250)); // Light blue when selected
        bookingTable.setSelectionForeground(Color.BLACK); // Black text when selected

        // Scroll pane
        scrollPane = new JScrollPane(bookingTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load data from bookflightList.txt
        
        try (BufferedReader reader = new BufferedReader(new FileReader("bookflightList.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookingData = line.split(",");
                if (bookingData.length == 6) {
                    tableModel.addRow(bookingData);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading booking details!", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Back button setup
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(new Color(255, 69, 0)); // Red-orange
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this); // Close the frame when clicked

        // Add button to a panel with layout manager
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Center the button
        buttonPanel.add(backButton);

        // Add button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == backButton){
                dispose();
            }
        }

   
}
    

