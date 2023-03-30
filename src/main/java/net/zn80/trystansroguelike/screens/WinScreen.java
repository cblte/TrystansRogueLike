/**
 * This is a Java class implementing the Screen interface for a simple game. It represents the screen that is displayed
 * when the player wins the game.
 */
package net.zn80.trystansroguelike.screens;

import net.trystan.asciipanel.AsciiPanel;

import java.awt.event.KeyEvent;

public class WinScreen implements Screen {
    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("You won!", 1, 1);
        terminal.writeCenter("Winner Screen", terminal.getHeightInCharacters() / 2);
        terminal.writeCenter("--- press [enter] to restart ---", terminal.getHeightInCharacters() - 2);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        // when the user presses enter, return a new PlayScreen, aka switch screen
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen() : this;
    }
}
