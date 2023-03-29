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

    public static void main(String[] args) {
        ApplicationMain applicationMain = new ApplicationMain();
        applicationMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        applicationMain.setLocationRelativeTo(null); // center the window
        applicationMain.setVisible(true);
    }
}
