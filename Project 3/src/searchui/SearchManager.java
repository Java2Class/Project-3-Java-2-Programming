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
        //TESTING//    userInput = userInput.replaceAll("[^\\p{IsAlphabetic}\\p{Digit}]", "");   
        return normalizedString;
    }

    //this is mainly a test method that reads in the contents of all files in the index
    static void indexReader(ArrayList<String> aList) {
        for (int i = 0; i < aList.size(); i++) {
            //TESTING//   System.out.println(aList.get(i).toString());
            String s = new String(aList.get(i));
            File indexedFile = new File(s);
            BufferedReader inputStream = null;
            try{
                inputStream = new BufferedReader(new FileReader(indexedFile));
                String contents;
                while((contents = inputStream.readLine())!=null){
            //TESTING//        System.out.println(contents);
                }
            }
            catch(Exception e){
            //TESTING//
            }
        }
    }
    

    //performs an all(AND) search
    static void allSearch(ArrayList<String> aList) {

    }

    //performs an any(OR) search
    static void anySearch(ArrayList<String> aList) {
        String inputString = normalizeString();
    //TESTING//    System.out.println(inputString);
    }

    //performs an exact(PHRASE) search
    static void exactSearch(ArrayList<String> aList) {

    }

}
