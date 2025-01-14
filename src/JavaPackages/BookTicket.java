
package JavaPackages;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookTicket extends JFrame implements ActionListener {
    protected JTextField nameField, addressField;
    protected JComboBox<String> fromComboBox, toComboBox, tripTypeComboBox;
    protected JButton bookButton;

    public BookTicket() {
        // Frame setup
        setTitle("Book Ticket");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Icon Panel
        JPanel iconPanel = new JPanel(new BorderLayout());
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("bookflighticon.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel iconLabel = new JLabel(resizedIcon, JLabel.CENTER);
        iconPanel.add(iconLabel, BorderLayout.CENTER);
        add(iconPanel, BorderLayout.WEST);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Color labelColor = Color.GREEN;

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(labelFont);
        fromLabel.setForeground(labelColor);
        formPanel.add(fromLabel);
        fromComboBox = new JComboBox<>(new String[]{"Dhaka", "Chittagong", "Sylhet"});
        fromComboBox.setBackground(Color.cyan);
        formPanel.add(fromComboBox);

        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(labelFont);
        toLabel.setForeground(labelColor);
        formPanel.add(toLabel);
        toComboBox = new JComboBox<>(new String[]{"Chittagong", "Sylhet", "Dhaka"});
        toComboBox.setBackground(Color.ORANGE);
        formPanel.add(toComboBox);

        JLabel tripTypeLabel = new JLabel("Trip Type:");
        tripTypeLabel.setFont(labelFont);
        tripTypeLabel.setForeground(labelColor);
        formPanel.add(tripTypeLabel);
        tripTypeComboBox = new JComboBox<>(new String[]{"One Way", "Round Trip"});
        tripTypeComboBox.setBackground(Color.cyan);
        formPanel.add(tripTypeComboBox);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(labelColor);
        formPanel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBackground(Color.MAGENTA);
        formPanel.add(nameField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(labelFont);
        addressLabel.setForeground(labelColor);
        formPanel.add(addressLabel);
        addressField = new JTextField();
        addressField.setBackground(Color.orange);
        formPanel.add(addressField);

        bookButton = new JButton("Book Flight");
        bookButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookButton.setBackground(Color.BLUE);
        bookButton.setForeground(Color.WHITE);
        bookButton.addActionListener(this);
        formPanel.add(new JLabel()); 
        formPanel.add(bookButton);

        add(formPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookButton) {
            JOptionPane.showMessageDialog(this, "Ticket Booked Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
