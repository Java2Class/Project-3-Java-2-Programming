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
            
            JButton addIndexButton = new JButton("Add Files to Index");
            addIndexButton.setToolTipText("Allows you to add files to the index.");
                        
            JButton removeIndexButton = new JButton("Remove Files From Index");
            removeIndexButton.setToolTipText("Allows you to remove a file from the index.");
                       
            JButton updateIndexButton = new JButton("Update Index.");
            updateIndexButton.setToolTipText("Allows you to update the index.");
                        
            JButton clearButton = new JButton("Clear Fields");
            clearButton.setToolTipText("Clears entered fields.");
                       
            JButton exitButton = new JButton("Exit");
            exitButton.setToolTipText("Exits the program.");
                       
            buttons.add(searchIndexButton);
            buttons.add(addIndexButton);
            buttons.add(removeIndexButton);
            buttons.add(updateIndexButton);
            buttons.add(clearButton);
            buttons.add(exitButton);
            window1.add(buttons, BorderLayout.SOUTH);
        // end of button creation
            
        window1.setVisible(true);
        
        //code for closing the window
        window1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
	});
    }
}
