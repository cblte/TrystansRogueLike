package net.zn80.trystansroguelike;

public class CreatureAi {
    protected Creature creature;

    public CreatureAi(Creature creature) {
        this.creature = creature;
        this.creature.setCreatureAi(this);
    }

    public void OnEnter(int i, int i1, Tile tile) {
    }

    public void onTakeTurn() {
    }

    public void onNotify(String format) {
    }
}
