package net.zn80.trystansroguelike.screens;

import net.trystan.asciipanel.AsciiPanel;

import java.awt.event.KeyEvent;

public class StartScreen implements Screen {
    @Override
    public void displayOutput(AsciiPanel terminal) {
        terminal.write("rl tutorial", 1, 1);
        terminal.writeCenter("Startscreen", terminal.getHeightInCharacters()/2);
        terminal.writeCenter("--- press [enter] to continue ---", terminal.getHeightInCharacters()-2);
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        // when the user presses enter, return a new PlayScreen, aka switch screen
        return key.getKeyCode() == KeyEvent.VK_ENTER ? new PlayScreen(): this;
    }
}
