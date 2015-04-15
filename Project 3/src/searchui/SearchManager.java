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

    //normalizes the string entered by the user
    static String normalizeString() {
        String normalizedString = Normalizer.normalize(SearchString.getString(), Normalizer.Form.NFKC);
        //TESTING// normalizedString = normalizedString.replaceAll("[^\\p{IsAlphabetic}\\p{Digit}]", "");
        //TESTING//    System.out.println(normalizedString);
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
        ArrayList<String> contents = new ArrayList<>();
        String inputString = normalizeString();
        for (int i = 0; i < aList.size(); i++) {
            contents = indexReader(i, aList);
                if(contents.contains(inputString))
                    System.out.printf("Found in file %s!\n", aList.get(i));
            }

        }

    //performs an exact(PHRASE) search
    static void exactSearch(ArrayList<String> aList) {

    }

}
