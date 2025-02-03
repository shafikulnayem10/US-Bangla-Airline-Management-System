package JavaPackages.Login;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



import JavaPackages.UserManagement.User;
import JavaPackages.UserManagement.UserRepository;
import java.awt.event.ActionListener;

public class Registration extends JFrame implements ActionListener {
    protected JTextField nameField, usernameField;
  protected JPasswordField passwordField;
   protected JComboBox statusComboBox;
    protected JButton signupButton;
    protected  JLabel nameLabel;
    protected JLabel usernameLabel;
    protected JLabel passwordLabel;
    protected   JLabel statusLabel;
    protected  User user;

    public Registration() {
        
       this.setTitle("User Registration");
      this.setSize(500, 400);
     this.setLocationRelativeTo(null);
     this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      this.setLayout(new GridLayout(5, 2, 10, 10));
      this.getContentPane().setBackground(Color.cyan); 

       
      nameLabel = new JLabel("Name:");
    nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
    nameLabel.setForeground(Color.WHITE); 
    this.add(nameLabel);

     nameField = new JTextField();
     nameField.setFont(new Font("Arial", Font.PLAIN, 14));
   nameField.setBackground(Color.LIGHT_GRAY); 
   this.add(nameField);
        

      usernameLabel = new JLabel("Username:");
    usernameLabel.setFont(new Font("Arial", Font.BOLD, 14));
     usernameLabel.setForeground(Color.WHITE);
     this.add(usernameLabel);

     usernameField = new JTextField();
     usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
    usernameField.setBackground(Color.LIGHT_GRAY);
    this.add(usernameField);
      

       passwordLabel = new JLabel("Password:");
      passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
      passwordLabel.setForeground(Color.WHITE);
      this.add(passwordLabel);

    passwordField = new JPasswordField();
     passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
    passwordField.setBackground(Color.LIGHT_GRAY);
   
    this.add(passwordField);
        
   statusLabel = new JLabel("Status:");
   statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
    statusLabel.setForeground(Color.WHITE);
    this.add(statusLabel);
    
  String [] statuses={"Admin", "Normal Customer", "Premium Customer"};
    statusComboBox = new JComboBox (statuses);
     statusComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
      statusComboBox.setBackground(Color.LIGHT_GRAY);
      this.add(statusComboBox);

        
     signupButton = new JButton("Sign Up");
        signupButton.setBackground(Color.GREEN); 
     signupButton.setForeground(Color.WHITE); 
     signupButton.setFont(new Font("Arial", Font.BOLD, 16)); 
        signupButton.addActionListener(this);
       this.add(new JLabel());
     this.add(signupButton);
        
        
         setVisible(true);
    }

    
    
     @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource() == signupButton){
                handleSignup();
            }
    }
    private void handleSignup() {
        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String status = (String) statusComboBox.getSelectedItem();

        if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        user = new User(name, username, password, status);

       UserRepository.saveUser(user);

        dispose(); 
        new Login(); 
    }

    public static void main(String[] args) {
        new Registration();
    }

   
}