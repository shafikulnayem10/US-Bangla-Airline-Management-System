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
        
        this.setTitle("BD");
       this.setSize(900, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());

      
        ImageIcon bdFlag = new ImageIcon("src/JavaPackages/Images/bdflag.png");
        this.setIconImage(bdFlag.getImage());

       
        titleLabel = new JLabel("Customer Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.BLUE); 
        titleLabel.setForeground(Color.WHITE);
        this.add(titleLabel, BorderLayout.NORTH);

      
     String[] columnNames = {"Username", "Password", "Status", "Name"};
       tableModel = new DefaultTableModel(columnNames, 0);
      customerTable = new JTable(tableModel);

       
        JTableHeader header = customerTable.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.GREEN);
         header.setForeground(Color.WHITE);
        header.setReorderingAllowed(false); 

     
       customerTable.setFont(new Font("Arial", Font.PLAIN, 14));
        customerTable.setRowHeight(25);
          customerTable.setGridColor(Color.LIGHT_GRAY);
          customerTable.setBackground(Color.PINK); 
        customerTable.setSelectionBackground(Color.BLUE); 
        customerTable.setSelectionForeground(Color.BLACK); 

      scrollPane = new JScrollPane(customerTable);
        this.add(scrollPane, BorderLayout.CENTER);

       
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 4 && !"Admin".equalsIgnoreCase(userData[2])) {
                    
                    tableModel.addRow(userData);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading customer details!", "Error", JOptionPane.ERROR_MESSAGE);
        }

       
          backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.GREEN); 
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this); 

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

       
        this.setVisible(true);
    }

   
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            dispose(); 
        }
    }
}


