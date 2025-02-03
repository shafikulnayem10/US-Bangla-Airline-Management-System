package JavaPackages.UserManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.JOptionPane;


public class UserRepository {

    public static void saveUser(User user) {
        File file = new File("users.txt");
     
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(user.toString());
           writer.newLine();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error saving user details!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

  
}