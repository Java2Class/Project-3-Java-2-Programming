/*
 * Search engine UI class for COP-2805
 * Contains all the parts of the user interface
 */
package searchui;

/**
 *
 * @author Chris Howard
 * @author Jair Garcia-Varela
 * @author Jason Kerby
 * @author John Silvey
 * @author Haven Brewer
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; // quick and easy pop-ups

class UI {
    UI() {
        //main window for search UI
        Frame window1 = new Frame("eXtreme Team Search");
        window1.setSize(960,600);
        window1.setLocationRelativeTo(null);
        
        //all of the buttons needed
        //The Listeners are an anynomous class with Lambdas to shorten coding
        JPanel buttons = new JPanel();
            buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            JButton searchIndexButton = new JButton("Search Index");
            searchIndexButton.setToolTipText("Runs a search of the index.");
            searchIndexButton.addActionListener((ActionEvent e) -> {
                JOptionPane.showMessageDialog(window1, "Search Button Operational.");
            });
            
            JButton addIndexButton = new JButton("Add Files");
            addIndexButton.setToolTipText("Allows you to add files to the index.");
            addIndexButton.addActionListener((ActionEvent e) -> {
                FileDialog fd = new FileDialog(window1,"Choose files to open");
                fd.setMultipleMode(true);
                fd.getFiles();
                fd.setVisible(true);
            });
                        
            JButton removeIndexButton = new JButton("Remove Files");
            removeIndexButton.setToolTipText("Allows you to remove a file from the index.");
            removeIndexButton.addActionListener((ActionEvent e) -> {
                JOptionPane.showMessageDialog(window1, "Remove Button Operational.");
            });
                       
            JButton updateIndexButton = new JButton("Update Index");
            updateIndexButton.setToolTipText("Allows you to update the index.");
            updateIndexButton.addActionListener((ActionEvent e) -> {
                JOptionPane.showMessageDialog(window1, "Update Button Operational.");
            });
                        
            JButton clearButton = new JButton("Clear Fields");
            clearButton.setToolTipText("Clears entered fields.");
            clearButton.addActionListener((ActionEvent e) -> {
                JOptionPane.showMessageDialog(window1, "Clear Button Operational.");
            });
                       
            JButton exitButton = new JButton("Exit");
            exitButton.setToolTipText("Exits the program.");           
            exitButton.addActionListener((ActionEvent e) -> {
                System.exit(0);
            });
            
            buttons.add(searchIndexButton);
            buttons.add(addIndexButton);
            buttons.add(removeIndexButton);
            buttons.add(updateIndexButton);
            buttons.add(clearButton);
            buttons.add(exitButton);
            window1.add(buttons, BorderLayout.SOUTH);
        // end of button creation
            
        //**************************************************
        //Created panel for search bar with button 
        JPanel pSearchBar = new JPanel();
        pSearchBar.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        
        //Create and add label, textbox, and search button to panel 
        JLabel jlblSearch = new JLabel("Enter Terms to Search:");
        JTextField jtfSearch = new JTextField(30);
        JButton jbtnSearch = new JButton("Search");
        jbtnSearch.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(window1, "Search button Operational.");
        });
        
        pSearchBar.add(jlblSearch);
        pSearchBar.add(jtfSearch);
        pSearchBar.add(jbtnSearch);
        
        //************************************************************
        //Create Panel For Buttons
        JPanel pRadioButtons = new JPanel();
        pRadioButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
        
        //Create Radio Buttons For Panel
        JRadioButton jrbAll = new JRadioButton("Search ALL Terms");
        jrbAll.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(window1,"All Radio Button Operational.");
        });
        
        JRadioButton jrbAny = new JRadioButton("Search ANY Term", true);
        jrbAny.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(window1, "Any Radio Button Operational.");
        });
        
        JRadioButton jrbExact = new JRadioButton("Search Exact Phrase");
        jrbExact.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(window1, "Exact radio Button Operational.");
        });
         
        //Add Radio Buttons To Group
        ButtonGroup bgSearch = new ButtonGroup();
        bgSearch.add(jrbAny);
        bgSearch.add(jrbAll);
        bgSearch.add(jrbExact);          
        //Add Radio Buttons to Panel        
        pRadioButtons.add(jrbAny);
        pRadioButtons.add(jrbAll);
        pRadioButtons.add(jrbExact);
        
        //********************************************************
        //Create Panel to Combine Search Bar and Search Button Panels
        JPanel pSearch = new JPanel();
        pSearch.setLayout(new BorderLayout());
        //add both panels to this panel
        pSearch.add((pSearchBar), BorderLayout.NORTH);
        pSearch.add((pRadioButtons),BorderLayout.CENTER);
        
        //*********************************************************
        //Add Search Panel to Main Window in North Position
        window1.add((pSearch), BorderLayout.NORTH);
        
        //code for closing the window
        window1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
	});
        window1.setVisible(true);
    }
}
