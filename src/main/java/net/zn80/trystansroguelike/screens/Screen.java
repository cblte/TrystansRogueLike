/**
 * The displayOutput method takes an AsciiPanel to display itself on and the respondToUserInput takes the KeyEvent and
 * can return the new screen. This way pressing a key can result in looking at a different screen.
 */
package net.zn80.trystansroguelike.screens;

import net.trystan.asciipanel.AsciiPanel;

import java.awt.event.KeyEvent;

public interface Screen {
    void displayOutput(AsciiPanel terminal);

    Screen respondToUserInput(KeyEvent key);
}
