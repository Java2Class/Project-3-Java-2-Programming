/*
 *      This class handles the file maintenance part of the program.  Functions
 *      include Adding, Removing, and Updating.
 */
package searchui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Scanner;

/**
 *
 * @author Jason Kerby
 */
class AddFile {

    /**
     *
     * @param indexPath Provides location of the index on the user's hdd
     * @param mIndex A map with the file names and paths
     * @param aList List which helps with the manipulation of the map
     */
    static void addFile(String indexPath, Map mIndex, ArrayList<String> aList) {
        //creates a window for the user to add selected files to the index
        Frame addWindow = new Frame("Add Files");
        FileDialog fd = new FileDialog(addWindow, "Add File to Index");
        fd.setVisible(true);
        String fileName = fd.getFile();
        String file = fd.getDirectory();
        //filePath combines file name and directory into a full file path
        String filePath = file + fileName;

        //If the list contains the file, let the user know and do nothing,
        //else add the file to the list
        if (aList.contains(filePath)) {
            JOptionPane.showMessageDialog(null, "File already in index!");
        } else {
            aList.add(filePath);
            JOptionPane.showMessageDialog(addWindow, fileName + " was added to the index.");
        }
        //If filepath is null it wont add "nullnull" to mIndex (JS)
        if (filePath.toString().equals("nullnull")) {
            //filePath is null do nothing
        } else {
            //file is selected and filePath can be added to mIndex
            try {
            //This whole block of code is used to write the full file path and
                //last modified timestamp to the index
                File f = new File(filePath);
                File g = new File(indexPath);
//            File fIndex = new File("fIndex.txt");
                //checking to see if file is already in Map
                if (!mIndex.containsKey(filePath)) {
                    mIndex.put(filePath, f.lastModified()); //changed getname to last modified JS
                }
                //removed BufferedWriter and replaced with method to update textfile
                FileHandling.updateIndexTextFile(mIndex);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No such file!");
            }
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
        for (int i = 0; i < aList.size(); i++) {
            //checking to make sure no null items are printed
            //it's "nullnull" because the filepath param from addFiles
            //can have two null concatenated strings
            if (aList.get(i).toString().equals("nullnull")) {
            } else {
                removeList.add(aList.get(i));
            }
        }
        Frame removeWindow = new Frame("Remove Files"); //new window
        Panel removeCancel = new Panel(new GridLayout(5, 3)); //button panel
        JButton btnRemove = new JButton("Remove");
        JButton btnCancel = new JButton("Cancel");

        //placement ofthe buttons
        for (int i = 0; i < 4; i++) {
            removeCancel.add(new Label());
        }
        removeCancel.add(btnRemove);
        for (int i = 0; i < 5; i++) {
            removeCancel.add(new Label());
        }
        removeCancel.add(btnCancel);
        for (int i = 0; i < 4; i++) {
            removeCancel.add(new Label());
        }

        removeWindow.setSize(300, 600);
        removeWindow.add(new JScrollPane(removeList));
        removeWindow.add(removeCancel, BorderLayout.SOUTH);
        removeWindow.setLocationRelativeTo(null);
        removeWindow.setVisible(true);
        btnRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //won't remove an item unless something is selected
                if (removeList.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(null, "No item was selected.");
                } else {
                    JOptionPane.showMessageDialog(null, removeList.getSelectedItem() + " was removed from the index.");
                    removeFile(mIndex, aList, removeList.getSelectedItem());
                    removeWindow.setVisible(false);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
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
    static void removeFile(Map mIndex, ArrayList<String> aList, String pathName) {
        if (mIndex.containsKey(pathName)) {
            mIndex.remove(pathName, mIndex.get(pathName));
            aList.remove(pathName);
            //This updates the text file by deleting all contents,
            //and rewriting the file with the remaining items in the mIndex Map
            FileHandling.removeFileFromIndex(mIndex);
        } else {
        }

    }
}

/**
 *
 * @author John Silvey This class will handle reading/writing/updating the
 * index.txt file
 */
class FileHandling {

    /**
     *
     * @param mIndex map of indexed file pathnames and modified dates
     */
    public static void updateIndexTextFile(Map mIndex) {
        //This block of code will write the contents of the index map to the text file
        try {
            FileWriter fw = new FileWriter("index.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            Set<Map.Entry<String, Long>> entrySet = mIndex.entrySet();
            for (Map.Entry<String, Long> entry : entrySet) {
                if (entry.getKey().toString().equals("nullnull")) {
                    //Do nothing
                } else {//writing keys and values to separate lines
                    bw.write(entry.getKey());
                    bw.newLine();
                    bw.write(entry.getValue().toString());
                    bw.newLine();
                }
            }
            bw.flush();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("IO Exception encountered!");
            System.err.println(ex.getMessage());
        }
    }

    /**
     *
     * @param mIndex map of indexed file pathnames and modified dates
     * @param index File that stores file names and modified date
     * @return map populated from index.txt file
     */
    public static Map populateIndexMap(Map mIndex, File index) {
        //This code reads the index.txt file and populates the map
        //when the program is started
        try {
            Scanner input = new Scanner(index);
            while (input.hasNext()) {
                String pathName = input.nextLine();
                String lastModified = input.nextLine();//inputting as String to prevent mismatch types
                mIndex.put(pathName, Long.valueOf(lastModified));//writing value as long
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("File not found!");
            System.err.println(ex.getMessage());
        }
        return mIndex;
    }

    /**
     *
     * @param aList array list of files indexed
     * @param index File that stores file names and modified date
     * @return the populated array list from index.txt file
     */
    public static ArrayList populateIndexList(ArrayList<String> aList, File index) {
        //This code reads in the index.txt file and populates the 
        //aList so the files show up in the "remove files" list
        try {
            Scanner input = new Scanner(index);
            while (input.hasNext()) {
                String pathName = input.nextLine();
                String lastModified = input.nextLine();//inputting as String to prevent mismatch types
                aList.add(pathName);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("File not found!");
            System.err.println(ex.getMessage());
        }
        return aList;
    }

    /**
     *
     * @param mIndex map of indexed file pathnames and modified dates
     */
    public static void removeFileFromIndex(Map mIndex) {
        //This block of code will delete all contents of the index text file 
        //and then regenerate a new list of current items in the mIndex map
        try {
            FileOutputStream os = new FileOutputStream("index.txt");
            os.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("File not found");
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("IO Exception");
            System.err.println(ex.getMessage());
        }
        //This will repopulate the list with the contents of the mIndex Map
        FileHandling.updateIndexTextFile(mIndex);

    }
}

/**
 * This class handles updating the index when the update index button is
 * pressed.
 *
 * @author John Silvey
 */
class UpdateIndex {

    /**
     *
     * @param mIndex
     * @param aList
     */
    public static void updateIndex(Map mIndex, ArrayList<String> aList) {

        Set<Map.Entry<String, Long>> entrySet = mIndex.entrySet();
        for (Map.Entry<String, Long> entry : entrySet) {
                //variables with m prefix are for the map entries
            //variables with test prefix are for testing against the map's 
            //entries.
            String mFileName;
            long mLastModified;
            String testFileName;
            long testLastModified;

            //set variables to map record
            mFileName = entry.getKey();
            mLastModified = entry.getValue();

            //test to see if the file still exists on the computer
            File f = new File(mFileName);
            if (f.exists()) {
                    //System.out.println(f + " already exists!");
                //If file exists test lastmodified to make sure it is 
                //up to date
                testLastModified = f.lastModified();
                if (mLastModified == testLastModified) {
                        //If file is up to date do nothing
                    //System.out.println(f + " is up to date");
                } else {
                        //file not up to date remove file from map and index
                    //add the up to date file to map and index
                    //System.out.println(f + " is not up to date!");
                    RemoveFile.removeFile(mIndex, aList, mFileName);
                    //System.out.println(f + " has been removed from index.");
                    aList.add(mFileName);
                    mIndex.put(mFileName, f.lastModified());
                    FileHandling.updateIndexTextFile(mIndex);
                    JOptionPane.showMessageDialog(null, mFileName
                            + " has been updated");

                    //System.out.println(f + " has been updated!");
                }
            } else {
                    //The file no longer exists so it is removed from 
                //the map and index
                RemoveFile.removeFile(mIndex, aList, mFileName);
                JOptionPane.showMessageDialog(null, mFileName + " no longer"
                        + " exists and has been deleted from the index.");
            }

            //System.err.println(mFileName + "" + mLastModified);
        }
        JOptionPane.showMessageDialog(null, "All files up to date.");

    }

}
