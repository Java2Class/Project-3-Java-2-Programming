/*
 * Search engine UI class for COP-2805
 * Contains all the parts of the user interface
 */
package searchui;

/**
 *
 * @author Jair Garcia-Varela
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*; // quick and easy pop-ups

class UI {
    UI() {
        //main window for search UI
        Frame window1 = new Frame("eXtreme Team Search");
        window1.setSize(700,580);
        window1.setLocationRelativeTo(null);
        
        //all of the buttons needed -Kerb
        JPanel buttons = new JPanel();
            buttons.setLayout(new GridLayout(3,3, 10, 10));
            JButton searchIndexButton = new JButton("Search Index");
            searchIndexButton.setToolTipText("Runs a search of the index.");
            searchIndexButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(window1, "Search Button Operational.");
                }
            });
            
            JButton addIndexButton = new JButton("Add Files to Index");
            addIndexButton.setToolTipText("Allows you to add files to the index.");
            addIndexButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(window1, "Add Index Button Operational.");
                }
            });
                        
            JButton removeIndexButton = new JButton("Remove Files From Index");
            removeIndexButton.setToolTipText("Allows you to remove a file from the index.");
            removeIndexButton.addActionListener(new removeListener());
                       
            JButton updateIndexButton = new JButton("Update Index.");
            updateIndexButton.setToolTipText("Allows you to update the index.");
            updateIndexButton.addActionListener(new updateListener());
                        
            JButton clearButton = new JButton("Clear Fields");
            clearButton.setToolTipText("Clears entered fields.");
            clearButton.addActionListener(new clearListener());
                       
            JButton exitButton = new JButton("Exit");
            exitButton.setToolTipText("Exits the program.");           
            exitButton.addActionListener(new exitListener());
            
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
        jrbAll.addActionListener(new jrbAllListener());
        JRadioButton jrbAny = new JRadioButton("Search ANY Term", true);
        jrbAny.addActionListener(new jrbAnyListener());
        JRadioButton jrbExact = new JRadioButton("Search Exact Phrase");
        jrbExact.addActionListener(new jrbExactListener());
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
        
        
        
        
            
        window1.setVisible(true);
        
        //code for closing the window
        window1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
	});
    }
    
    //By Haven Brewer
    //Adds a listeners to the buttons

    private static class removeListener implements ActionListener {
        private Component frame;
        @Override
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(frame, "Remove Button Operational.");
        }
    }

    private static class updateListener implements ActionListener {
        private Component frame;
        @Override
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(frame, "Update Button Operational.");
        }
    }
    
    private static class clearListener implements ActionListener {
        private Component frame;
        @Override
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(frame, "Clear Button Operational.");
        }
    }
    
    private static class jrbAllListener implements ActionListener {
        private Component frame;
        @Override
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(frame,"All Radio Button Operational.");
        }
    }
    
    private static class jrbAnyListener implements ActionListener {
        private Component frame;
        @Override
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(frame, "Any Radio Button Operational.");
        }
    }
    
    private static class jrbExactListener implements ActionListener {
        private Component frame;
        @Override
        public void actionPerformed(ActionEvent e){
            JOptionPane.showMessageDialog(frame, "Exact radio Button Operational.");
        }
    }
    //There is no frame for the exit button. Exit button will close the window
    private static class exitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
    //End Action Listeners 
   
    
}
