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
<<<<<<< HEAD
    UI() {
        //main window for search UI
        Frame window1 = new Frame("eXtreme Team Search");
        window1.setSize(700,580);
        window1.setLocationRelativeTo(null);
        window1.setVisible(true);
        
        //code for closing the window
        window1.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				System.exit(0);
			}
	});
=======
    UI (){
        JOptionPane.showMessageDialog(null,"This is a test popup"); //testing to see if imports worked
>>>>>>> 78a22e4db3b423c708d381d5984d1d2cf204f374
    }
}
