package net.zn80.trystansroguelike;

public class FungusAi extends CreatureAi {

    private final CreatureFactory factory;
    private int spreadCount = 0;

    public FungusAi(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }

    /**
     * Called when it's time for the fungus to take its turn. The fungus may reproduce and spread to an adjacent tile
     * with a small chance of 2%, as long as it hasn't spread more than 5 times already.
     */
    @Override
    public void onTakeTurn() {
        if (spreadCount < 5 && Math.random() < 0.02) {
            reproduce();
        }
    }

    /**
     * Creates a new fungus creature at a random location near the parent fungus creature. The new creature must be able
     * to enter the tile at the selected location.
     */
    private void reproduce() {
        // Generate random coordinates within a 3-tile radius of the parent fungus
        int x = creature.getX() + (int) (Math.random() * 11) - 3;
        int y = creature.getY() + (int) (Math.random() * 11) - 3;

        // Check if the new tile is valid for the new fungus
        if (!creature.canEnter(x, y)) {
            return;
        }

        // Create a new fungus creature at the selected location
        Creature child = factory.newFungusChild();
        child.setX(x);
        child.setY(y);
        spreadCount++;
    }
}
