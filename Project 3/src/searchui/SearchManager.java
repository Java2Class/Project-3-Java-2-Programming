/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchui;

import java.text.*;
import java.util.*;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author Jason Kerby
 */
public class SearchManager {

    /**
     *
     * @param searchType An int which determines the type of search you want to
     * do: -1: An ALL search 0: An ANY search 1: An EXACT search
     * @param aList An array list used to identify files in the index for
     * searching
     */
    static void determineSearch(int searchType, ArrayList<String> aList) {

        try {
            if (searchType == -1) {
                allSearch(aList);
            } else if (searchType == 0) {
                anySearch(aList);
            } else {
                exactSearch(aList);
            }
        } catch (Exception e) {
            JFrame errorFrame = new JFrame();
            JOptionPane.showMessageDialog(errorFrame, "Please enter a search string!");
        }
    }

    //normalizes the string entered by the user and converts it to lower case for easier searching
    static String normalizeString() {
        String normalizedString = Normalizer.normalize(SearchString.getString().toLowerCase(), Normalizer.Form.NFKC);
        //TESTING// normalizedString = normalizedString.replaceAll("[^\\p{IsAlphabetic}\\p{Digit}]", "");
        return normalizedString;
    }

    //this is mainly a test method that reads in the contents of all files in the index
    //@author Jason Kerby
    static ArrayList<String> indexReader(int indexNumber, ArrayList<String> aList) {
        String s = new String(aList.get(indexNumber));
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(s));
            while (scanner.hasNext()) {
                list.add(scanner.next().toLowerCase());//adds lowercase text to the ArrayList
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("No such file!");
        }

        return list;
    }

    //performs an all(AND) search - John Silvey
    static void allSearch(ArrayList<String> aList) {
        //StringBuilder for a popup menu which shows at the end of the search.
        StringBuilder results = new StringBuilder();
        //ArrayList which will contain the paths of index files
        ArrayList<String> contents = new ArrayList<>();
        //Calls the normalizeString method to normalize the string.
        String inputString = normalizeString();
        //copy aList to another arrayList for testing
        ArrayList<String> aListCopy = new ArrayList<>();

        //copy aList into duplicate array so the actual index is not deleted
        for(int i =0; i< aList.size();i++){
            aListCopy.set((i), aList.get(i));
        }
                  

        //Populates an ArrayList with the input given by the user
        Scanner scanner = new Scanner(inputString);
        ArrayList<String> modifiedInput = new ArrayList<>();
        while (scanner.hasNext()) {
            modifiedInput.add(scanner.next());
        }
        
        //The functionality of the method. The file must contain ALL words in the 
        //search. If any single word is missing the file is deleted from the copied index
        
        for (int i = 0; i < aList.size(); i++) {
            //read in the contents of each file
            contents = indexReader(i, aList);
            int j = 0;
            String testString = modifiedInput.get(j);
            //Loop through the search words in the text field
            
            while (j < modifiedInput.size()){
                //if a search word is not found discard that file from the copy array
                if (!contents.contains(testString)){
                    aListCopy.remove(i); 
                        }
                //increment to go to next word in search string
                j++;               
                    }
            
                }
        //this is just debugging code to see which files are left in the copied index
        for(int i = 0; i< aListCopy.size(); i++)
        {
        JOptionPane.showMessageDialog(null,aListCopy.get(i));
            }

        //  Code below is not yet working

        //JOPtionPane which presents the user with a list of matches or a window which
        //tells them that the program couldn't find anything.
        if (results.length() < 1) {
            JFrame resultsFrame = new JFrame("Sorry!");
            JOptionPane.showMessageDialog(resultsFrame, "No applicable file found.");
        } else {
            JFrame resultsFrame = new JFrame("Awesome!");
            JOptionPane.showMessageDialog(resultsFrame, results, "Awesome!", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    //performs an any(OR) search
    //@author Jason Kerby
    static void anySearch(ArrayList<String> aList) {
        //StringBuilder for a popup menu which shows at the end of the search.
        StringBuilder results = new StringBuilder();
        //ArrayList which will contain the paths of index files
        ArrayList<String> contents = new ArrayList<>();
        //Calls the normalizeString method to normalize the string.
        String inputString = normalizeString();

        //Populates an ArrayList with the input given by the user
        Scanner scanner = new Scanner(inputString);
        ArrayList<String> modifiedInput = new ArrayList<>();
        while (scanner.hasNext()) {
            modifiedInput.add(scanner.next());
        }

        //The functionality of the method.  If anything the user types is found, it will be
        //returned
        for (int i = 0; i < aList.size(); i++) {
            contents = indexReader(i, aList);
            for (int j = 0; j < modifiedInput.size(); j++) {
                String testString = modifiedInput.get(j);
                if (contents.contains(testString)) {
                    results.append("Search term found in file: " + aList.get(i) + "!\n");
                    break;
                }
            }
        }

        //JOPtionPane which presents the user with a list of matches or a window which
        //tells them that the program couldn't find anything.
        if (results.length() < 1) {
            JFrame resultsFrame = new JFrame("Sorry!");
            JOptionPane.showMessageDialog(resultsFrame, "No applicable file found.");
        } else {
            JFrame resultsFrame = new JFrame("Awesome!");
            JOptionPane.showMessageDialog(resultsFrame, results, "Awesome!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //performs an exact(PHRASE) search
    //@author Jair Garcia-Varela
    static void exactSearch(ArrayList<String> aList) {
        //StringBuilder for a popup menu which shows at the end of the search.
        StringBuilder results = new StringBuilder();
        //ArrayList which will contain the paths of index files
        ArrayList<String> contents = new ArrayList<>();
        //Calls the normalizeString method to normalize the string.
        String inputString = normalizeString();

        //Populates an ArrayList with the input given by the user
        Scanner scanner = new Scanner(inputString);
        ArrayList<String> modifiedInput = new ArrayList<>();
        while (scanner.hasNext()) {
            modifiedInput.add(scanner.next());
        }

        //The functionality of the method. If all items in order are found, returns result. if not found in order, no file returned
        for (int i = 0; i < aList.size(); i++) {//loops through all the files in the index
            int k = 0, found = 0;
            contents = indexReader(i, aList);//file contents
            for (int j = 0; j < modifiedInput.size(); j++, k++) {
                while (k < contents.size()) {//checks to see if we reached end of file contents
                    if (contents.get(k).equalsIgnoreCase(modifiedInput.get(j))) {//checks to see if words are the same
                        found++;
                        if (found == modifiedInput.size()) {//checks to make sure all the entered items have been found in order and it is the end of inputstring
                            //JOptionPane.showMessageDialog(null, found+" "+modifiedInput.size()+"");//debug code
                            results.append("Search term found in file: " + aList.get(i) + "!\n");//adds file to results list
                        }
                        break;
                    } else {
                        found = 0;//if nth word does not match, resets the counter.
                        k++;//moves to next word in file
                    }
                }
            }
        }

        //JOPtionPane which presents the user with a list of matches or a window which
        //tells them that the program couldn't find anything.
        if (results.length() < 1) {
            JFrame resultsFrame = new JFrame("Sorry!");
            JOptionPane.showMessageDialog(resultsFrame, "No applicable file found.");
        } else {
            JFrame resultsFrame = new JFrame("Awesome!");
            JOptionPane.showMessageDialog(resultsFrame, results, "Awesome!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
