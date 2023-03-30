/**
 * This class is the main entry point for the application. It creates an instance of the AsciiPanel class and displays
 * it in a window.
 */
package net.zn80.trystansroguelike;

import net.trystan.asciipanel.AsciiPanel;
import net.zn80.trystansroguelike.screens.Screen;
import net.zn80.trystansroguelike.screens.StartScreen;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ApplicationMain extends JFrame implements KeyListener {

    AsciiPanel terminal;
    private Screen screen;

    /**
     * Creates a new instance of the ApplicationMain class, which extends JFrame and represents the game window. The
     * constructor initializes the window with an AsciiPanel, adds it to the frame, packs the window, initializes the
     * first screen of the game as the StartScreen, adds a key listener to the frame, and repaints the window to ensure
     * everything is displayed correctly.
     */
    public ApplicationMain() {
        super();
        terminal = new AsciiPanel();
        add(terminal);
        pack();
        screen = new StartScreen();
        addKeyListener(this);
        repaint();

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

    /**
     * Clears the AsciiPanel and displays the output of the current screen on the terminal. This method is responsible
     * for refreshing the contents of the game window and updating it to reflect the current game state. First, it
     * clears the AsciiPanel using the clear() method. Then, it calls the displayOutput() method of the current screen
     * to draw the current screen's output onto the terminal. Finally, it calls the repaint() method of the JFrame
     * superclass to update the game window and display the changes to the user.
     */
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        screen = screen.respondToUserInput(e);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
