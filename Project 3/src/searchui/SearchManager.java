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
     * @param userInput A string containing the text entered in the UI text field
     * @param searchType An int which determines the type of search you want to do:
     *                   -1: An ALL search
     *                    0: An ANY search
     *                    1: An EXACT search
     * @param aList An array list used to identify files in the index for searching
     */
    static void determineSearch(int searchType, ArrayList<String> aList){
    //TESTING//    System.out.println(searchType);
    //TESTING//    System.out.println(aList);
     
        try {
            String normalizedString = normalizeString();
    //TESTING//            System.out.println(normalizedString);  
            if (searchType == -1) {
                allSearch(normalizedString, aList);
            } else if (searchType == 0) {
                anySearch(normalizedString, aList);
            } else {
                exactSearch(normalizedString, aList);
            }
        } catch (Exception e) {
            JFrame errorFrame = new JFrame();
                JOptionPane.showMessageDialog(errorFrame, "Please enter a search string!");
        }
    } 
   
    //normalizes the string entered by the user
    static String normalizeString(){
        String normalizedString = Normalizer.normalize(SearchString.getString(), Normalizer.Form.NFKC);
    //TESTING//    userInput = userInput.replaceAll("[^\\p{IsAlphabetic}\\p{Digit}]", "");   
        return normalizedString;
    }
    
    //performs an all search
    static void allSearch(String s, ArrayList<String> aList){
        
    }
    
    //performs an any search
    static void anySearch(String s, ArrayList<String> aList){
        
    }
    
    //performs an exact search
    static void exactSearch(String s, ArrayList<String> aList){
        
    }
    
}
