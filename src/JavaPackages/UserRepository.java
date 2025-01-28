package JavaPackages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;


public class UserRepository {

    public static void saveUser(User user) {
        File file = new File("users.txt");
        try {
            if (!file.exists() && !file.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Failed to create the file!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error creating file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(user.toString());
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Signup Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving user details!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

  
}