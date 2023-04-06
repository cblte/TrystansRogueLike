package net.zn80.trystansroguelike;

import java.awt.*;

public class Creature {

    private final World world;
    private final char glyph;
    private final Color color;
    private final int maxHp;
    private final int attackValue;
    private final int defenseValue;
    private int x;
    private int y;
    private CreatureAi ai;
    private int hp;

    /**
     * Creates a new creature with the specified attributes.
     *
     * @param world the world in which the creature exists
     * @param glyph the glyph representing the creature
     * @param color the color of the creature on the screen
     */
    public Creature(World world, char glyph, Color color, int maxHp, int attackValue, int defenseValue) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attackValue = attackValue;
        this.defenseValue = defenseValue;
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
        Creature other = world.getCreature(x + mx, y + my);
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
     * @return void
     */
    public void attack(Creature other) {
        int amount = Math.max(0, getAttackValue() - other.getAttackValue());
        amount = (int) (Math.random() * amount) + 1;
        other.modifyHp(-amount);
    }

    public int getAttackValue() {
        return attackValue;
    }

    public void modifyHp(int amount) {
        hp += amount;
        if (hp < 1) {
            world.remove(this);
        }
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getHp() {
        return hp;
    }

    public int getDefenseValue() {
        return defenseValue;
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

    /**
     * This method instructs the creature to take its turn by calling the onTakeTurn() method of its AI.
     */
    public void takeTurn() {
        ai.onTakeTurn();
    }

    /**
     * Checks whether this creature can enter the given coordinates on the world map. A creature can enter a tile if it
     * is a ground tile and either there is no other creature occupying the tile, or the only other creature occupying
     * the tile is this creature itself.
     *
     * @param wx the x-coordinate of the tile to check
     * @param wy the y-coordinate of the tile to check
     * @return true if the creature can enter the tile, false otherwise
     */
    public boolean canEnter(int wx, int wy) {
        Tile tile = world.getTile(wx, wy);
        if (tile == null || !tile.isGround()) {
            return false;
        }
        Creature other = world.getCreature(wx, wy);
        return (other == null || other == this);
    }

    /**
     * Notifies the AI component with a formatted message and optional parameters.
     *
     * @param message the message to be formatted and passed to the AI component
     * @param params  optional parameters to be included in the formatted message
     */
    public void notify(String message, Object... params) {
        ai.onNotify(String.format(message, params));
    }
}


