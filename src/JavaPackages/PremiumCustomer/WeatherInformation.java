package JavaPackages.PremiumCustomer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class WeatherInformation extends JFrame {
    public WeatherInformation() {
        // **Frame Setup**
        setTitle("Weather Information");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // **Title Label**
        JLabel titleLabel = new JLabel("Weather Report", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(70, 130, 180)); // Steel blue
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // **Table Setup**
        String[] columnNames = {"Date", "Sylhet (°C)", "Chittagong (°C)", "Dhaka (°C)"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable weatherTable = new JTable(tableModel);
        weatherTable.setFont(new Font("Arial", Font.PLAIN, 14));
        weatherTable.setRowHeight(25);

        // **Disable Editing (Easiest Way)**
        weatherTable.setDefaultEditor(Object.class, null);

        // **Directly Add Rows to TableModel**
        tableModel.addRow(new Object[]{"2025-01-25", "25°C", "27°C", "30°C"});
        tableModel.addRow(new Object[]{"2025-01-26", "26°C", "28°C", "31°C"});
        tableModel.addRow(new Object[]{"2025-01-27", "27°C", "29°C", "32°C"});
        tableModel.addRow(new Object[]{"2025-01-28", "24°C", "26°C", "29°C"});
        tableModel.addRow(new Object[]{"2025-01-29", "23°C", "25°C", "28°C"});
        tableModel.addRow(new Object[]{"2025-01-30", "26°C", "27°C", "30°C"});

        JScrollPane scrollPane = new JScrollPane(weatherTable);
        add(scrollPane, BorderLayout.CENTER);

        // **Close Button**
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setBackground(new Color(255, 69, 0)); // Red-orange
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dispose());

        // **Button Panel**
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new WeatherInformation();
    }
}
