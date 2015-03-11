/*
 * Search engine UI class for COP-2805
 * Contains all the parts of the user interface
 * Version: 1.0
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
import java.io.*;
import java.util.*;

class UI {
    /**
     * 
     * @param s the absolute path of the index
     * @param mIndex map that stores the file names and path names
     */
    UI(String s, Map mIndex) {
        //main window for search UI
        Frame window1 = new Frame("eXtreme Team Search");
        window1.setSize(960,600);
        window1.setLocationRelativeTo(null);
        
        
        // adds Extreme Team Search engine photo
        ImageIcon image = new ImageIcon(getClass().getResource("../images/EXTS.png"));
        JLabel logo = new JLabel(image);
        // end of the picture code.
        
        //sets custom icon for application
        window1.setIconImage(image.getImage());
        
        //all of the buttons needed
        JPanel buttons = new JPanel();
            buttons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
            
            JButton addIndexButton = new JButton("Add Files");
            addIndexButton.setToolTipText("Allows you to add files to the index.");
            addIndexButton.setPreferredSize(new Dimension(110, 27));
            ArrayList<String> aList = new ArrayList<String>();
            addIndexButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
// Modified some of this.  Removed your ability to select more than one file at
// a time. I think keeping it simple is much easier to manage, especially when I
// have to send pathnames to AddFile. -Kerb
                    FileDialog fd = new FileDialog(window1,"Choose file to open");
                    fd.setVisible(true);
                    String fileName = fd.getFile();  //fileName = the literal name of the file
//Testing                    System.out.println(fileName);
                    String file = fd.getDirectory();  //file = the actual path of the file
                    String filePath = file + fileName;  //filePath = the full pathname of the file
 //Testing                   System.out.println(filePath);
 //made a new class to handle this, since it would be too cumbersome otherwise (line 56)                   
                    AddFile.addFile(filePath, s, mIndex);
                    if (aList.contains(filePath)) {
                    } else {
                        aList.add(filePath);
                    }
                }
            });
                        
            JButton removeIndexButton = new JButton("Remove Files");
            removeIndexButton.setToolTipText("Allows you to remove a file from the index.");
            removeIndexButton.setPreferredSize(new Dimension(110, 27));
            removeIndexButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    RemoveFile.removeFile(mIndex, aList);
                }
            });
                       
            JButton updateIndexButton = new JButton("Update Index");
            updateIndexButton.setToolTipText("Allows you to update the index.");
            updateIndexButton.setPreferredSize(new Dimension(110, 27));
            updateIndexButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(window1, "Update Button Operational.");
                }
            });
                        
            JButton clearButton = new JButton("Clear Fields");
            clearButton.setToolTipText("Clears entered fields.");
            clearButton.setPreferredSize(new Dimension(110, 27));
            clearButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(window1, "Clear Button Operational.");
                }
            });
                       
            JButton exitButton = new JButton("Exit");
            exitButton.setToolTipText("Exits the program.");
            exitButton.setPreferredSize(new Dimension(110, 27));
            exitButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e){
                    System.exit(0);
            	}
            });
            
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
        jbtnSearch.setToolTipText("Runs a search of the index.");
        jbtnSearch.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(window1, "Search button Operational.");
                }
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
        jrbAll.setToolTipText("Performs an AND search, searching all terms.");
        jrbAll.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(window1,"All Radio Button Operational.");
            }
        });
        JRadioButton jrbAny = new JRadioButton("Search ANY Term", true);
        jrbAny.setToolTipText("Performs an OR search, searching any terms.");
        jrbAny.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(window1, "Any Radio Button Operational.");
            }
        });
        JRadioButton jrbExact = new JRadioButton("Search Exact Phrase");
        jrbExact.setToolTipText("Performs a search containing the exact phrase entered.");
        jrbExact.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(window1, "Exact radio Button Operational.");
            }
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
        
        //make all backgrounds the same color
        pSearch.setBackground(Color.WHITE);
        pRadioButtons.setBackground(Color.WHITE);
        jrbAny.setBackground(Color.WHITE);
        jrbAll.setBackground(Color.WHITE);
        jrbExact.setBackground(Color.WHITE);
        buttons.setBackground(Color.WHITE);
        pSearchBar.setBackground(Color.WHITE);
        
        //*********************************************************
        //Add Search Panel to Main Window in Center Position and logo in North
        window1.add(logo, BorderLayout.NORTH);
        window1.add(pSearch, BorderLayout.CENTER);
        //code for closing the window
        window1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
        });
        window1.setVisible(true);
    }
}