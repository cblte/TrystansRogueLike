package net.zn80.trystansroguelike;

import java.awt.*;

public class Creature {

    private final World world;
    private final char glyph;
    private final Color color;
    private int x;
    private int y;
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

    /**
     * Moves the player by the specified x and y distance, attacking any creature at the new location. If there is no
     * creature at the new location, the player's AI's OnEnter method is called.
     *
     * @param mx the distance to move the player in the x direction
     * @param my the distance to move the player in the y direction
     */
    public void moveBy(int mx, int my) {
        // Check if there is a creature at the new location
        Creature other = world.creature(x + mx, y + my);
        if (other == null) {
            // If there is no creature, call the OnEnter method of the creature's (player's) AI with the new location
            ai.OnEnter(x + mx, y + my, world.getTile(x + mx, y + my));
        } else {
            // If there is a creature, attack it
            attack(other);
        }
    }


    /**
     * Attacks the specified creature, removing it from the world.
     *
     * @param other the creature to attack and remove
     */
    public void attack(Creature other) {
        world.remove(other);
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


