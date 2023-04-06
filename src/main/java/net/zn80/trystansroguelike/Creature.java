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

        doAction("attack the '%s'for %d damage", other.glyph, amount);

        other.modifyHp(-amount);
    }

    public int getAttackValue() {
        return attackValue;
    }

    /**
     * Performs an action within a radius around the creature, sending a message to all creatures affected by the
     * action. The action message is displayed in second person for the player creature and in third person for other
     * creatures.
     *
     * @param message the message to send to affected creatures
     * @param params  any additional parameters to include in the message
     */
    public void doAction(String message, Object... params) {
        int radius = 9;
        for (int ox = -radius; ox < radius + 1; ox++) {
            for (int oy = -radius; oy < radius + 1; oy++) {
                // Check if the coordinates are within the radius
                if (ox * ox + oy * oy > radius * radius) {
                    continue;
                }

                // Get the creature at the current coordinates
                Creature other = world.getCreature(x + ox, y + oy);

                // If there's no creature at the current coordinates, continue to the next set of coordinates
                if (other == null) {
                    continue;
                }

                // Send the appropriate message to the affected creature(s)
                if (other == this) {
                    // If the affected creature is the same, as the one performaing the action, send the message
                    other.notify("You " + message + ".", params);
                } else {
                    // If the affected creature is not the same as the one performing the action, send the message in second person
                    other.notify(String.format("The '%s' %s.", glyph, makeSecondPerson(message)), params);
                }
            }
        }
    }

    public void modifyHp(int amount) {
        hp += amount;
        if (hp < 1) {
            doAction("die");
            world.remove(this);
        }
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

    /**
     * Takes a String of text and modifies the first word by adding an "s" at the end to make it second person. The
     * modified String is returned.
     *
     * @param text the text to be modified
     * @return the modified text with the first word in second-person form
     */
    private String makeSecondPerson(String text) {
        String[] words = text.split(" ", 2);
        if (words.length < 2) {
            return text;
        }
        String verb = words[0] + "s";
        String rest = words[1];
        return String.format("%s %s", verb, rest);
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
        doAction("dig field %d.%d", wx, wy);
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


}


