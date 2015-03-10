/*
 *      This class handles the ability to add files to the index.  I'm still
*       working on the details. -Kerb
 */
package searchui;
import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 *
 * @author Jason Kerby
 */
class AddFile {
    AddFile(String filePath, String indexPath){
        try{
            File f = new File(filePath);
            File g = new File(indexPath);
            long lastModified = f.lastModified();
            String lm = String.valueOf(lastModified);
            System.out.println(lm);
            BufferedWriter bw = new BufferedWriter(new FileWriter(indexPath, true));
            bw.write(filePath);
            bw.newLine();
            bw.write(lm);
            bw.newLine();
            bw.flush();
            bw.close();
            }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"No such file!");
        }
    }

}
