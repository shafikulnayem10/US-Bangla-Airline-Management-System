package JavaPackages.Admin;

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
        
       this.setTitle("BD");
       this.setSize(900, 500); 
       
       this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
           this.setLocationRelativeTo(null);
        
         ImageIcon bdFlag = new ImageIcon("src/JavaPackages/Images/bdflag.png");
        this.setIconImage(bdFlag.getImage());
      this.setLayout(new BorderLayout());
        
     titleLabel = new JLabel("Booking Flights Details", JLabel.CENTER);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
      titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLUE); 
        titleLabel.setForeground(Color.WHITE);
         this.add(titleLabel, BorderLayout.NORTH);

         String[] columnNames = {"Flight Code", "Name", "Address", "From", "To", "Trip Type"};
           tableModel = new DefaultTableModel(columnNames, 0);
        bookingTable = new JTable(tableModel);

       
   JTableHeader header = bookingTable.getTableHeader();
      header.setFont(new Font("Arial", Font.BOLD, 16));
     header.setBackground(Color.GREEN); 
     header.setForeground(Color.WHITE);
    header.setReorderingAllowed(false); 

        
      bookingTable.setFont(new Font("Arial", Font.PLAIN, 14));
       bookingTable.setRowHeight(25);
        bookingTable.setGridColor(Color.ORANGE);
        bookingTable.setBackground(Color.BLUE); 
        bookingTable.setForeground(Color.WHITE);
        bookingTable.setSelectionBackground(Color.BLUE); // Light blue when selected
        bookingTable.setSelectionForeground(Color.BLACK); // Black text when selected

        
        scrollPane = new JScrollPane(bookingTable);
       this.add(scrollPane, BorderLayout.CENTER);

       
     
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

   
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.ORANGE); 
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.addActionListener(this); 

        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
        buttonPanel.add(backButton);

       
        this.add(buttonPanel, BorderLayout.SOUTH);
         this.setVisible(true);
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == backButton){
                dispose();
            }
        }

   
}
    

