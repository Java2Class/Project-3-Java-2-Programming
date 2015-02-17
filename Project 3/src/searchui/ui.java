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
        Panel searchBar = new Panel(new BorderLayout());
        Button searchBtn = new Button("Search");
        
        window1.setSize(960,600);
        window1.setLocationRelativeTo(null);
        searchBar.setBackground(Color.DARK_GRAY);
        searchBar.setLayout(new GridLayout(3,3));
        
        //adding everything to the main window
        window1.add(searchBar, BorderLayout.CENTER);
        
        //centering the search button in the search bar
        for(int i=0; i < 4; i++) searchBar.add(new Label());
        searchBar.add(searchBtn);
        for(int i=0; i < 4; i++) searchBar.add(new Label());
        
        window1.setVisible(true);
        
        //code for closing the window
        window1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
	});
    }
}
