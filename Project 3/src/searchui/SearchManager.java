/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchui;

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
     */
    static void determineSearch(String userInput, int searchType){
        System.out.println(userInput);
        System.out.println(searchType);
        
        String normalizedString = normalizeString(userInput);
        
        if(searchType == -1)
            allSearch(normalizedString);
        else if(searchType == 0)
            anySearch(normalizedString);
        else
            exactSearch(normalizedString);
            
    } 
    
    //normalizes the string entered by the user
    static String normalizeString(String userInput){
        
        return "Test";
    }
    
    //performs an all search
    static void allSearch(String s){
        
    }
    
    //performs an any search
    static void anySearch(String s){
        
    }
    
    //performs an exact search
    static void exactSearch(String s){
        
    }
    
}
