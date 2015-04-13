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

class UI {

    /**
     *
     * @param s the absolute path of the index
     * @param mIndex map that stores the file names and path names
     */
    UI(String s, Map mIndex, ArrayList<String> aList) {
        //main window for search UI
        JFrame window1 = new JFrame("eXtreme Team Search");
        window1.setSize(960, 600);
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
        addIndexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddFile.addFile(s, mIndex, aList);
            }
        });

        JButton removeIndexButton = new JButton("Remove Files");
        removeIndexButton.setToolTipText("Allows you to remove a file from the index.");
        removeIndexButton.setPreferredSize(new Dimension(110, 27));
        removeIndexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RemoveFile.removeFile(mIndex, aList);
            }
        });
        JButton viewIndexButton = new JButton("View Index");
        viewIndexButton.setToolTipText("Allows you to view the files in the index.");
        viewIndexButton.setPreferredSize(new Dimension(110, 27));
        viewIndexButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewIndex.viewIndex(mIndex);
            }
        });
        JButton updateIndexButton = new JButton("Update Index");
        updateIndexButton.setToolTipText("Allows you to update the index.");
        updateIndexButton.setPreferredSize(new Dimension(110, 27));
        updateIndexButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UpdateIndex.updateIndex(mIndex, aList);
            }
        });

        JButton clearButton = new JButton("Clear Fields");
        clearButton.setToolTipText("Clears entered fields.");
        clearButton.setPreferredSize(new Dimension(110, 27));

        JButton exitButton = new JButton("Exit");
        exitButton.setToolTipText("Exits the program.");
        exitButton.setPreferredSize(new Dimension(110, 27));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttons.add(addIndexButton);
        buttons.add(removeIndexButton);
        buttons.add(viewIndexButton);
        buttons.add(updateIndexButton);
        buttons.add(clearButton);
        buttons.add(exitButton);
        window1.add(buttons, BorderLayout.SOUTH);
        // end of button creation

        //**************************************************
        //Created panel for search bar with button 
        JPanel pSearchBar = new JPanel();
        pSearchBar.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        //Create and add label, textbox, and search button to panel 
        JLabel jlblSearch = new JLabel("Enter Terms to Search:");
        JTextField jtfSearch = new JTextField(30);
        JButton jbtnSearch = new JButton("Search");
        jbtnSearch.setToolTipText("Runs a search of the index.");
        URL urlSearch = getClass().getResource("../sound/ets.wav"); //search sound
        AudioClip search = Applet.newAudioClip(urlSearch); //search sound

        pSearchBar.add(jlblSearch);
        pSearchBar.add(jtfSearch);
        pSearchBar.add(jbtnSearch);
        //************************************************************
        //Create Panel For Buttons
        JPanel pRadioButtons = new JPanel();
        pRadioButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        //Create Radio Buttons For Panel
        JRadioButton jrbAll = new JRadioButton("Search ALL Terms");
        jrbAll.setToolTipText("Performs an AND search, searching all terms.");
        jrbAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        JRadioButton jrbAny = new JRadioButton("Search ANY Term", true);
        jrbAny.setToolTipText("Performs an OR search, searching any terms.");
        jrbAny.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        JRadioButton jrbExact = new JRadioButton("Search Exact Phrase");
        jrbExact.setToolTipText("Performs a search containing the exact phrase entered.");
        jrbExact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        
        // I had to move this action listener so I can refer to the radio buttons
        // This listener will now direct the user to the SearchManager class, which will
        // handle the searching of the files in the index.  -Kerb 
        jbtnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search.play(); //search sound
                SearchString.setString(jtfSearch.getText()); //saves string from user's input
                if(jrbAll.isSelected())
                    SearchManager.determineSearch(-1, aList);
                else if(jrbAny.isSelected())
                    SearchManager.determineSearch(0, aList);
                else
                    SearchManager.determineSearch(1, aList);
            }
        });

        //********************************************************
        //Create Panel to Combine Search Bar and Search Button Panels
        JPanel pSearch = new JPanel();
        pSearch.setLayout(new BorderLayout());
        //add both panels to this panel
        pSearch.add((pSearchBar), BorderLayout.NORTH);
        pSearch.add((pRadioButtons), BorderLayout.CENTER);

        //make all backgrounds the same color
        pSearch.setBackground(Color.WHITE);
        pRadioButtons.setBackground(Color.WHITE);
        jrbAny.setBackground(Color.WHITE);
        jrbAll.setBackground(Color.WHITE);
        jrbExact.setBackground(Color.WHITE);
        buttons.setBackground(Color.WHITE);
        pSearchBar.setBackground(Color.WHITE);

        //********************************************************
         //Create Directions Frame for instructions or help
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
        dTitle.setLayout(new BorderLayout());
        
        //title
        JLabel dText = new JLabel("                                        "
                + "                             Directions"); 
        dText.setFont(new Font("Serif",Font.BOLD, 24));
        
        //reading Text File
        JTextArea dCore = new JTextArea(5,20);
        try {
            dCore.read(new InputStreamReader(
                    getClass().getResourceAsStream("../text/DIRECT.txt")),
                    null);
        } catch (Exception e) {
            dCore = new JTextArea("No File Found"); 
        }
        dCore.setFont(new Font("Monospaced",Font.BOLD, 14));
        dCore.setCaretPosition(dCore.getDocument()
				.getLength());
        dCore.setEditable(false);
        dCore.setLineWrap(true);
        dCore.setWrapStyleWord(true);
        JScrollPane dScrollPane = new JScrollPane(dCore,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        //Adds Footer to South End
        JLabel dSigniture = new JLabel ("                                    "
                + "                                                          "
                + "                                   eXtreme Team Search \u00a9 2015" );
      
        //Adds frame to East and West sides so JTextArea box is smaller
        JLabel dWest = new JLabel ("                                 |");
        dWest.setForeground(Color.LIGHT_GRAY.brighter());
        JLabel dEast = new JLabel ("|                                 ");
        dEast.setForeground(Color.LIGHT_GRAY.brighter());
        
        //Displays all the items
        dTitle.add(dText, BorderLayout.NORTH);
        dTitle.add(dWest, BorderLayout.WEST);
        dTitle.add(dEast, BorderLayout.EAST);
        dTitle.add(dScrollPane, BorderLayout.CENTER);
        dTitle.add(buttons2, BorderLayout.SOUTH);
        window2.add(logo2, BorderLayout.NORTH);
        window2.add(dTitle, BorderLayout.CENTER);
        window2.add(dSigniture, BorderLayout.SOUTH);
       
      
         
        //********************************************************
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
                       
                //View Index Menu
                JMenuItem viewIndexMenu = new JMenuItem("View");
                viewIndexMenu.setMnemonic(KeyEvent.VK_V);
                viewIndexMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, 
                        ActionEvent.CTRL_MASK));
                viewIndexMenu.setToolTipText("Allows you to view the files in the index.");
                viewIndexMenu.setPreferredSize(new Dimension(110, 27));
                viewIndexMenu.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ViewIndex.viewIndex(mIndex);
                    }
                });
                viewIndexMenu.setHorizontalTextPosition(JMenuItem.RIGHT);
                file.add(viewIndexMenu);
                
                //Update Menu     
                JMenuItem updateIndexMenu = new JMenuItem("Update");
                updateIndexMenu.setMnemonic(KeyEvent.VK_U);
                updateIndexMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, 
                        ActionEvent.CTRL_MASK));
                updateIndexMenu.setToolTipText("Allows you to update the index.");
                updateIndexMenu.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                          UpdateIndex.updateIndex(mIndex, aList);
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
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jtfSearch.setText(null);

            }
        });
        window1.setVisible(true);
        //once window is visible update the index if there are files stored
        if (mIndex.isEmpty()){
            //Do nothing
        }
        else{
            UpdateIndex.updateIndex(mIndex, aList);
        }
    }
}

/**
 *
 * @author Jair Garcia-Varela
 */
class ViewIndex {

    /**
     *
     * @param mIndex Map that stores the file names and modified dates
     */
    static void viewIndex(Map mIndex) {
        Frame viewWindow = new Frame("View Index");
        java.awt.List nameList = new java.awt.List();//list that displays file names
        java.awt.List dateList = new java.awt.List();//List that displays dates
        JButton close = new JButton("Close");
        JPanel info = new JPanel();
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        info.setLayout(new BorderLayout());
        top.setLayout(new BorderLayout());
        bottom.setLayout(new GridLayout(3, 3));
        top.add(new JLabel("File"), BorderLayout.WEST);
        top.add(new JLabel("Modified Date"), BorderLayout.EAST);

        //placement of the button
        for (int i = 0; i < 4; i++) {
            bottom.add(new JLabel(""));
        }
        bottom.add(close);
        for (int i = 0; i < 4; i++) {
            bottom.add(new JLabel(""));
        }
        Set<Map.Entry<String, Long>> entrySet = mIndex.entrySet();

        //adding entries to the new window for each item in the index
        for (Map.Entry<String, Long> entry : entrySet) {
            //checking to make sure no null items are printed
            //it's "nullnull" because the filepath param from addFiles
            //can have two null concatenated strings
            if (entry.getKey().toString().equals("nullnull")) {
            } else {
                nameList.add(entry.getKey());
                dateList.add(new java.util.Date(entry.getValue()).toString());
            }
        }
        viewWindow.setSize(600, 600);
        info.add(new JScrollPane(nameList), BorderLayout.CENTER);
        info.add(new JScrollPane(dateList), BorderLayout.EAST);
        viewWindow.add(info, BorderLayout.CENTER);
        viewWindow.add(top, BorderLayout.NORTH);
        viewWindow.add(bottom, BorderLayout.SOUTH);
        viewWindow.setVisible(true);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewWindow.setVisible(false);
            }
        });
        viewWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                viewWindow.setVisible(false);
            }
        });
    }
}

/**
 *
 * @author Jair Garcia-Varela
 */
class SearchString {

    private static String search;

    /**
     *
     * @param s The String input into the search box
     * @throws Exception
     */
    static void setString(String s) {
        if (s.equals("")) {
            JOptionPane.showMessageDialog(null, "NO search word entered.");
        } else {
            search = s;
        }
    }

    /**
     *
     * @return String from the search box
     */
    static String getString() {

        if (search.isEmpty()) {
            return "";
        } else {
            return search;
        }
    }
}