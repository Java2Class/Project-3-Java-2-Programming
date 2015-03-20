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
import java.applet.*; //search sound
import java.net.*; //search sound
import java.nio.file.*;

class UI {
    /**
     * 
     * @param s the absolute path of the index
     * @param mIndex map that stores the file names and path names
     */
    UI(String s, Map mIndex) throws FileNotFoundException, IOException {
        //main window for search UI
        JFrame window1 = new JFrame("eXtreme Team Search");
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
                    AddFile.addFile(s, mIndex, aList);
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
        URL urlSearch = getClass().getResource("../sound/ets.wav"); //search sound
        AudioClip search = Applet.newAudioClip(urlSearch); //search sound
        jbtnSearch.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    search.play(); //search sound
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
        
        //Directions Frame
        JFrame window2 = new JFrame("eXtreme Team Search Directions");
        window2.setSize(960,600);
        window2.setLocationRelativeTo(null);
        
        ImageIcon image2 = new ImageIcon(getClass().getResource("../images/EXTS.png"));
        JLabel logo2 = new JLabel(image2);
        
        window2.setIconImage(image2.getImage());
        
        JPanel buttons2 = new JPanel();
            buttons2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
            JButton okButton = new JButton("OK");
            okButton.setToolTipText("Exit the Directions menu.");
            okButton.setPreferredSize(new Dimension(110, 27));
            okButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){      
                    window2.dispose();
                }
            });
        buttons2.add(okButton);    
        
        JPanel dTitle = new JPanel();
        dTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
            
        JLabel dText = new JLabel("Directions:"); 
        JLabel dCore = new JLabel("Here will go the instructions.");
      
        dTitle.add(dText, BorderLayout.NORTH);
        dTitle.add(dCore, BorderLayout.SOUTH);
        window2.add(logo2, BorderLayout.NORTH);
        window2.add(dTitle, BorderLayout.CENTER);
        window2.add(buttons2, BorderLayout.SOUTH);
         
        //MenuBar Setup
        JMenuBar menuBar = new JMenuBar();
        
            //Menu Item 1 - File
            JMenu file = new JMenu("File");
            file.setMnemonic(KeyEvent.VK_F);
              
                //Add Files Menu
                JMenuItem addIndexMenu = new JMenuItem("Add");
                addIndexMenu.setMnemonic(KeyEvent.VK_A);
                addIndexMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, 
                        ActionEvent.CTRL_MASK));
                addIndexMenu.setToolTipText("Allows you to add files to the index.");
                addIndexMenu.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){      
                        AddFile.addFile(s, mIndex, aList);
                    }
                }); 
                addIndexMenu.setHorizontalTextPosition(JMenuItem.RIGHT);
                file.add(addIndexMenu);
        
                //Remove Files Menu
                JMenuItem removeIndexMenu = new JMenuItem("Remove");
                removeIndexMenu.setMnemonic(KeyEvent.VK_R);
                removeIndexMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, 
                        ActionEvent.CTRL_MASK));
                removeIndexMenu.setToolTipText("Allows you to remove a file from the index.");
                removeIndexMenu.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        RemoveFile.removeFile(mIndex, aList);
                    }
                });
                removeIndexMenu.setHorizontalTextPosition(JMenuItem.RIGHT);
                file.add(removeIndexMenu);
                       
                //Update Menu     
                JMenuItem updateIndexMenu = new JMenuItem("Update");
                updateIndexMenu.setMnemonic(KeyEvent.VK_U);
                updateIndexMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, 
                        ActionEvent.CTRL_MASK));
                updateIndexMenu.setToolTipText("Allows you to update the index.");
                updateIndexMenu.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        JOptionPane.showMessageDialog(window1, "Update Menu Operational.");
                    }
                });
                updateIndexMenu.setHorizontalTextPosition(JMenuItem.RIGHT);
                file.add(updateIndexMenu);
                
                //Clear
                JMenuItem clearIndexMenu = new JMenuItem("Clear");
                clearIndexMenu.setMnemonic(KeyEvent.VK_C);
                clearIndexMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, 
                        ActionEvent.CTRL_MASK));
                clearIndexMenu.setToolTipText("Clears entered fields.");
                clearIndexMenu.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        JOptionPane.showMessageDialog(window1, "Clear Menu Operational.");
                    }
                });
                clearIndexMenu.setHorizontalTextPosition(JMenuItem.RIGHT);
                file.add(clearIndexMenu);
                
                //Exit
                JMenuItem quitMenu = new JMenuItem("Quit");
                quitMenu.setMnemonic(KeyEvent.VK_Q);
                quitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 
                        ActionEvent.CTRL_MASK));
                quitMenu.setToolTipText("Exits the program.");
                quitMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                });
                quitMenu.setHorizontalTextPosition(JMenuItem.RIGHT);
                file.add(quitMenu);
            //end of Menu Item 1  
            
            //Menu Item 2 - Help
            JMenu help = new JMenu("Help");
            help.setMnemonic(KeyEvent.VK_H); 
            
                //Directions
                JMenuItem dirMenu = new JMenuItem("Directions");
                dirMenu.setMnemonic(KeyEvent.VK_D);
                dirMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, 
                        ActionEvent.CTRL_MASK));
                dirMenu.setToolTipText("Opens the Directions window.");
                dirMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        window2.setVisible(true);
                    }
                });
                dirMenu.setHorizontalTextPosition(JMenuItem.RIGHT);
                help.add(dirMenu);
            //end of Menu Item 2 
                
        //adding menus to menubar        
        menuBar.add(file);
        menuBar.add(help);
        window1.setJMenuBar(menuBar);
        
        
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
        //Clear search text field when clear button is clicked.... JS
        clearButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    jtfSearch.setText(null);
                    
                }
            });
        window1.setVisible(true);
    }
}
