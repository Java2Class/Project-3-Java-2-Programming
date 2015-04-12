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
    } 
}
