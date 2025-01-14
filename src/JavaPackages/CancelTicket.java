
package JavaPackages;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CancelTicket extends BookTicket {
    private JTextField flightCodeField, pnrField;
    private JButton cancelButton;

    public CancelTicket() {
        // Modify frame title
        setTitle("Cancel Ticket");
        setSize(400, 300);

        // Adjust layout
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Cancel Your Ticket", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.RED);
        add(headerLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.GREEN);
        centerPanel.add(nameLabel);
        centerPanel.add(nameField);

        JLabel flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setForeground(Color.GREEN);
        flightCodeField = new JTextField();
        flightCodeField.setBackground(Color.RED);
        centerPanel.add(flightCodeLabel);
        centerPanel.add(flightCodeField);

        JLabel pnrLabel = new JLabel("PNR Number:");
        pnrLabel.setForeground(Color.GREEN);
        pnrField = new JTextField();
        pnrField.setBackground(Color.CYAN);
        centerPanel.add(pnrLabel);
        centerPanel.add(pnrField);

        add(centerPanel, BorderLayout.CENTER);

        cancelButton = new JButton("Cancel Ticket");
        cancelButton.setBackground(Color.RED);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        add(cancelButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton) {
            JOptionPane.showMessageDialog(this, "Ticket Cancelled Successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            nameField.setText("");
            flightCodeField.setText("");
            pnrField.setText("");
        }
    }

    public static void main(String[] args) {
        new CancelTicket();
    }
}


