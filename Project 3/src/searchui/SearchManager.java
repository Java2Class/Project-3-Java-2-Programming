/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchui;

import java.text.*;
import java.util.Locale;
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
     * @param userInput A string containing the text entered in the UI text
     * field
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
    static ArrayList<String> indexReader(int indexNumber, ArrayList<String> aList) {
        String s = new String(aList.get(indexNumber));
        ArrayList<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(s));
            while (scanner.hasNext()) {
                list.add(scanner.next());
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("No such file!");
        }

        return list;
    }

    //performs an all(AND) search
    static void allSearch(ArrayList<String> aList) {

    }

    //performs an any(OR) search
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
        if(results.length() < 1){
            JFrame resultsFrame = new JFrame("Sorry!");
            JOptionPane.showMessageDialog(resultsFrame, "No applicable file found.");
        }
        else{
            JFrame resultsFrame = new JFrame("Awesome!");
            JOptionPane.showMessageDialog(resultsFrame, results, "Awesome!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //performs an exact(PHRASE) search
    static void exactSearch(ArrayList<String> aList) {

    }

}
