/*
 *      This class handles the ability to add files to the index.  I'm still
*       working on the details. -Kerb
 */
package searchui;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Jason Kerby
 */
class AddFile {
    static void addFile(String filePath, String indexPath, Map mIndex){
        try{
            File f = new File(filePath);
            File g = new File(indexPath);
            File fIndex = new File("fIndex.txt");
            //checking to see if file is already in Map
            if (!mIndex.containsKey(filePath)) {
                mIndex.put(filePath, f.getName());
            }
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

/**
 * 
 * @author Jair Garcia-Varela
 */
class RemoveFile {
    /**
     * 
     * @param mIndex Map with the file names and paths
     * @param aList List that helps with the manipulation of the map
     */
    static void removeFile(Map mIndex, ArrayList<String> aList) {
    //creating a new window for a file chooser
    //the file chooser enables the user to select the files wanted to be removed
        java.awt.List removeList = new java.awt.List();
        //adding entries to the new window for each item in the index
        for(int i = 0; i < aList.size(); i++) removeList.add(aList.get(i));
        
        Frame removeWindow = new Frame("Remove Files"); //new window
        Panel removeCancel = new Panel(new GridLayout(5,3)); //button panel
        JButton btnRemove = new JButton("Remove");
        JButton btnCancel = new JButton("Cancel");
        
        //placement ofthe buttons
        for(int i=0; i<4; i++) removeCancel.add(new Label());
        removeCancel.add(btnRemove);
        for(int i=0; i<5; i++) removeCancel.add(new Label());
        removeCancel.add(btnCancel);
        for(int i=0; i<4; i++) removeCancel.add(new Label());
        
        removeWindow.setSize(300,600);
        removeWindow.add(new JScrollPane(removeList));
        removeWindow.add(removeCancel, BorderLayout.SOUTH);
        removeWindow.setLocationRelativeTo(null);
        removeWindow.setVisible(true);
        btnRemove.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //won't remove an item unless something is selected
                if(removeList.getSelectedItem() == null){
                    
                } else {
                    removeFile(mIndex, aList, removeList.getSelectedItem());
                }
            }
        });
        btnCancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                removeWindow.setVisible(false);
            }
        });
        removeWindow.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
		removeWindow.setVisible(false);
            }
        });
    }
    /**
     * 
     * @param mIndex map with the file names and path
     * @param aList List that helps with the manipulation of the map
     * @param pathName the full path name of the files
     */
    //removes the entry from the Collection
    static void removeFile (Map mIndex, ArrayList<String> aList, String pathName){
        if (mIndex.containsKey(pathName)) {
            mIndex.remove(pathName, mIndex.get(pathName));
            aList.remove(pathName);
        } else {
        }
        
    }
}
