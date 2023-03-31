/**
 * This is a Java code defining an enumeration type called Tile, which represents the different types of tiles in a
 * roguelike game. Each tile has a glyph (a character representing its appearance) and a color.
 */
package net.zn80.trystansroguelike;

import net.trystan.asciipanel.AsciiPanel;

import java.awt.*;

public enum Tile {
    FLOOR((char) 250, AsciiPanel.yellow),
    WALL((char) 177, AsciiPanel.yellow),
    BOUNDS('x', AsciiPanel.brightBlack);

    private final char glyph;
    private final Color color;

    Tile(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }

    public char getGlyph() {
        return glyph;
    }

    public Color getColor() {
        return color;
    }
}
