package net.zn80.trystansroguelike;

import net.trystan.asciipanel.AsciiPanel;

public class CreatureFactory {
    private World world;

    public CreatureFactory(World world) {
        this.world = world;
    }

    public Creature newPlayer() {
        Creature player = new Creature(world, '@', AsciiPanel.brightWhite);
        world.addAtEmptyLocation(player);
        new PlayerAi(player);
        return player;
    }

    public Creature newFungus() {
        Creature fungus = new Creature(world, 'F', AsciiPanel.green);
        world.addAtEmptyLocation(fungus);
        new FungusAi(fungus, this);
        return fungus;
    }

    public Creature newFungusChild() {
        Creature fungusChild = new Creature(world, 'f', AsciiPanel.green);
        world.addAtEmptyLocation(fungusChild);
        new FungusAi(fungusChild, this);
        return fungusChild;
    }
}
