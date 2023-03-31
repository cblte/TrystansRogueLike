package net.zn80.trystansroguelike;

public class WorldBuilder {
    private final int width;
    private final int height;
    private Tile[][] tiles;

    public WorldBuilder(int width, int height) {
        this.height = height;
        this.width = width;
        this.tiles = new Tile[width][height];
    }

    /**
     * Creates randomized tiles and applies smoothing to create natural cave-like structures.
     *
     * @return the current WorldBuilder instance
     */
    public WorldBuilder makeCaves() {
        return randomizeTiles().smooth(8);
    }

    /**
     * Applies a smoothing algorithm to the tile data, using a Moore neighborhood to determine whether to assign a floor
     * or wall tile to each position.
     *
     * @param times the number of times to apply the smoothing algorithm
     * @return the current WorldBuilder instance
     */
    private WorldBuilder smooth(int times) {
        if (times == 0) {
            return this;
        }

        Tile[][] tiles2 = new Tile[width][height];
        for (int time = 0; time < times; time++) {
            // Iterate over all tiles to calculate their new state based on the tiles around them
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int floors = 0;
                    int rocks = 0;

                    // Iterate over all tiles around the current tile to count how many are floors and how many are walls
                    for (int ox = -1; ox < 2; ox++) {
                        for (int oy = -1; oy < 2; oy++) {
                            // Make sure we're not going out of bounds of the tile array
                            if (x + ox < 0 || x + ox >= width || y + oy < 0 || y + oy >= height)
                                continue;

                            // Count how many floors and how many walls there are in the surrounding tiles
                            if (tiles[x + ox][y + oy] == Tile.FLOOR)
                                floors++;
                            else
                                rocks++;
                        }
                    }
                    // Set the new state of the tile based on the number of floors and walls around it
                    tiles2[x][y] = floors >= rocks ? Tile.FLOOR : Tile.WALL;
                }
            }
            // Set the old tile array to the new one, so that we can continue to smooth it
            tiles = tiles2;
        }
        return this;
    }

    /**
     * Randomizes the tiles in the WorldBuilder's tiles array by setting each tile to either Tile.FLOOR or Tile.WALL
     * with equal probability.
     *
     * @return this WorldBuilder instance, for method chaining
     */
    private WorldBuilder randomizeTiles() {
        // Loop through every tile in the tiles array
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Set the tile at (x,y) to Tile.FLOOR or Tile.WALL with equal probability
                tiles[x][y] = Math.random() < 0.5 ? Tile.FLOOR : Tile.WALL;
            }
        }
        // Return this WorldBuilder instance for method chaining
        return this;
    }

    /**
     * Builds a new World instance from the tile data stored in the WorldBuilder instance.
     *
     * @return a new World instance
     */
    public World build() {
        return new World(tiles);
    }

}
