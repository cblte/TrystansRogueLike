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

    private Color color;
    private char glyph;
    private boolean isDiggable;


    Tile(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;

    }

    public Color getColor() {
        return color;
    }

    /**
     * Returns whether this tile can be dug out or not.
     *
     * @return true if this tile is a wall and can be dug out, false otherwise
     */
    public boolean isDiggable() {
        return this == Tile.WALL;
    }

    public boolean isGround() {
        return this != WALL && this != BOUNDS;
    }

    public char getGlyph() {
        return glyph;
    }
}
