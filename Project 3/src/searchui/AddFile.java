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
    AddFile(String filePath){
        try{
           File f = new File(filePath);
           BufferedReader in = new BufferedReader(new FileReader(filePath));
           String line = in.readLine();
            while(line != null){
//Testing                System.out.println(line);
//Testing                System.out.println(f);
//Testing                System.out.println(f.lastModified());
                line=in.readLine();
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"No such file!");
        }
    }
}
