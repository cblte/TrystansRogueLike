/**
 * This class is the main entry point for the application.
 * It creates an instance of the AsciiPanel class and displays it
 * in a window.
 */
package net.zn80.trystansroguelike;

import net.trystan.asciipanel.AsciiPanel;

import javax.swing.*;


public class ApplicationMain extends JFrame {

    public ApplicationMain() {
        super();
        AsciiPanel terminal = new AsciiPanel();
        terminal.write("rl tutorial", 1, 1);
        add(terminal);
        pack();
    }

    /**
     * Main method that creates an instance of the ApplicationMain class and sets the window to be visible.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ApplicationMain applicationMain = new ApplicationMain();
        applicationMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        applicationMain.setLocationRelativeTo(null); // center the window
        applicationMain.setVisible(true);
    }
}
