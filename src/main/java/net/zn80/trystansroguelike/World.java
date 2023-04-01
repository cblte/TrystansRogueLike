/**
 * This is a Java code defining a World class that represents the game world in a roguelike game. The world is
 * represented by a two-dimensional array of Tile objects, and the class has methods to get the width and height of the
 * world.
 */
package net.zn80.trystansroguelike;

import java.awt.*;

public class World {

    private final Tile[][] tiles;
    private final int width;
    private final int height;

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
     * Adds a given creature to an empty location on the world map.
     *
     * @param creature the creature to be added to the world map
     * @throws RuntimeException if no empty location is found
     */
    public void addAtEmptyLocation(Creature creature) {
        int x;
        int y;
        // keep generating random coordinates until an empty location is found
        do {
            x = (int) (Math.random() * width);
            y = (int) (Math.random() * height);
        } while (!getTile(x, y).isGround());
        creature.setX(x);
        creature.setY(y);
    }
}
