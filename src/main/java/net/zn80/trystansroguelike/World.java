/**
 * This is a Java code defining a World class that represents the game world in a roguelike game. The world is
 * represented by a two-dimensional array of Tile objects, and the class has methods to get the width and height of the
 * world.
 */
package net.zn80.trystansroguelike;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    private final Tile[][] tiles;
    private final int width;
    private final int height;
    private List<Creature> creatures;

    /**
     * Constructs a new World object with the specified array of Tile objects.
     *
     * @param tiles the two-dimensional array of Tile objects representing the game world
     * @throws NullPointerException           if the tiles array is null
     * @throws ArrayIndexOutOfBoundsException if the tiles array is not rectangular
     */
    public World(Tile[][] tiles) {
        this.tiles = tiles;
        this.width = tiles.length;
        this.height = tiles[0].length;
        creatures = new ArrayList<>();
    }

    /**
     * Returns the character glyph representing the tile at the given coordinates.
     *
     * @param x The x-coordinate of the tile
     * @param y The y-coordinate of the tile
     * @return The character glyph representing the tile at the given coordinates.
     */
    public char getGlyph(int x, int y) {
        return getTile(x, y).getGlyph();
    }

    /**
     * Returns the Tile object at the specified coordinates in the game world.
     *
     * @param x the x-coordinate of the tile to retrieve
     * @param y the y-coordinate of the tile to retrieve
     * @return the Tile object at the specified coordinates, or Tile.BOUNDS if the coordinates are outside the bounds of
     * the world
     */
    public Tile getTile(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            // If the coordinates are within the bounds of the world, return the corresponding tile from the tiles array
            return tiles[x][y];
        } else {
            return Tile.BOUNDS;
        }
    }

    /**
     * Gets the color of the tile at the given coordinates.
     *
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     * @return the color of the tile at the given coordinates
     */
    public Color getColor(int x, int y) {
        return getTile(x, y).getColor();
    }

    /**
     * Gets the width of the world in number of tiles.
     *
     * @return the width of the world
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the world in number of tiles.
     *
     * @return the height of the world
     */
    public int getHeight() {
        return height;
    }

    /**
     * Digs out the tile at the given coordinates, replacing it with a floor tile if the tile is diggable.
     *
     * @param x the x-coordinate of the tile to dig
     * @param y the y-coordinate of the tile to dig
     */
    public void dig(int x, int y) {
        Tile tile = getTile(x, y);
        if (tile.isDiggable()) {
            tiles[x][y] = Tile.FLOOR;
        }
    }

    /**
     * Adds the given creature to a random empty location on the world map.
     *
     * @param creature The creature to be added to the world map.
     * @throws RuntimeException if no empty location is found after 1000 attempts.
     */
    public void addAtEmptyLocation(Creature creature) {
        int x;
        int y;
        // Keep generating random coordinates until an empty location is found
        for (int i = 0; i < 1000; i++) {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
            if (getTile(x, y).isGround() && creature(x, y) == null) {
                creature.setX(x);
                creature.setY(y);
                return;
            }
        }
        throw new RuntimeException("Could not find an empty location to add the creature.");
    }

    /**
     * Returns the creature at the specified coordinates.
     *
     * @param x The X coordinate of the creature.
     * @param y The Y coordinate of the creature.
     * @return The creature at the specified coordinates, or null if there is no such creature.
     */
    public Creature creature(int x, int y) {
        for (Creature c : creatures) {
            if (c.getX() == x && c.getY() == y) {
                return c;
            }
        }
        return null;
    }

    /**
     * Returns the list of creatures that belong to the world.
     *
     * @return the list of creatures in the world
     */
    public List<Creature> getCreatures() {
        return creatures;
    }

}
