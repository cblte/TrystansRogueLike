package net.zn80.trystansroguelike;

import java.awt.*;

public class Creature {

    private int x;
    private int y;
    private World world;
    private char glyph;
    private Color color;
    private CreatureAi ai;

    /**
     * Creates a new creature with the specified attributes.
     *
     * @param world the world in which the creature exists
     * @param glyph the glyph representing the creature
     * @param color the color of the creature on the screen
     */
    public Creature(World world, char glyph, Color color) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
    }

    public void moveBy(int mx, int my) {
        ai.OnEnter(x + mx, y + my, world.getTile(x + mx, y + my));
    }

    /**
     * Returns the glyph representing the creature.
     *
     * @return the glyph representing the creature
     */
    public char getGlyph() {
        return glyph;
    }

    /**
     * Returns the color of the creature on the screen.
     *
     * @return the color of the creature on the screen
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the horizontal position of the creature.
     *
     * @return the horizontal position of the creature
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the horizontal position of the creature.
     *
     * @param x the new horizontal position of the creature
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the vertical position of the creature.
     *
     * @return the vertical position of the creature
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the vertical position of the creature.
     *
     * @param y the new vertical position of the creature
     */
    public void setY(int y) {
        this.y = y;
    }

    public void setCreatureAi(CreatureAi ai) {
        this.ai = ai;
    }

    public void dig(int wx, int wy) {
        world.dig(wx, wy);
    }

}


