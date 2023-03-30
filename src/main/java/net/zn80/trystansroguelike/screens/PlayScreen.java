/**
 * This is a Java class implementing the Screen interface for a simple game. It represents the main screen where the
 * game is played.
 */
package net.zn80.trystansroguelike.screens;

import net.trystan.asciipanel.AsciiPanel;

import java.awt.event.KeyEvent;

public class PlayScreen implements Screen {
    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("Let's a game:", 1, 1);
        terminal.writeCenter("PlayScreen", terminal.getHeightInCharacters() / 2);
        terminal.writeCenter("--- press [escape] to loose or [enter] to continue ---", terminal.getHeightInCharacters() - 2);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        switch (key.getKeyCode()) {
            // if user presses ESCAPE: switch to loser screen
            case KeyEvent.VK_ESCAPE:
                return new LooseScreen();
            // if user presses ENTER: switch to winner screen
            case KeyEvent.VK_ENTER:
                return new WinScreen();
        }
        // every other key stays here
        System.out.println(key.getKeyCode());
        return this;

    }
}
