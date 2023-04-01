package net.zn80.trystansroguelike;

public class PlayerAi extends CreatureAi {
    public PlayerAi(Creature creature) {
        super(creature);
    }

    @Override
    public void OnEnter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            creature.setX(x);
            creature.setY(y);
        } else if (tile.isDiggable()) {
            creature.dig(x, y);
        }
    }
}
