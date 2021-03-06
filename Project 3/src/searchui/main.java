/*
 * Search Engine UI main class for COP-2805
 * February 16, 2015
 */
package searchui;

import java.io.*;
import javax.swing.*;
import java.util.*;

/**
 * @author Chris Howard
 * @author Jair Garcia-Varela
 * @author Jason Kerby
 * @author John Silvey
 * @author Haven Brewer
 */
class main { //this class just has the main method

    public static void main(String[] args) throws Exception {
        //Creates the index needed for the program in the user's default folder.
        //Needs to be in a try/catch, so that's why it was included.  Also, if
        //the index isn't found, it creates one and generates a dialog box.
        File index = new File("index.txt");
        try {
            if (index.createNewFile()) {
                JOptionPane.showMessageDialog(null, "Index file wasn't found, so it was created.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Index not created.");
        }
        String s = index.getAbsolutePath();
        Map<String, Long> mIndex = new HashMap();
        IndexTools.populateIndexMap(mIndex, index);
        ArrayList<String> aList = new ArrayList<>();
        IndexTools.populateIndexList(aList, index);

        UI search = new UI(s, mIndex, aList); //creates a new UI object.
    }
}
