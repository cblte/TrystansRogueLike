package net.zn80.trystansroguelike;

import java.util.List;

public class PlayerAi extends CreatureAi {
    private final List<String> messages;

    /**
     * Constructs a new PlayerAi instance with the specified creature and message list. Instead of providing a getter
     * method for the message list, this constructor expects the list to be provided through constructor injection. This
     * allows the list to be created and maintained outside the PlayerAi class, and passed to the constructor as
     * needed.
     *
     * @param creature the creature controlled by the AI
     * @param messages the list of messages to display to the player
     */
    public PlayerAi(Creature creature, List<String> messages) {
        super(creature);
        this.messages = messages;
    }


    /**
     * Called when the creature enters a new tile.
     *
     * @param x    the x-coordinate of the new tile
     * @param y    the y-coordinate of the new tile
     * @param tile the tile the creature has entered
     */
    @Override
    public void OnEnter(int x, int y, Tile tile) {
        if (tile.isGround()) {
            creature.setX(x);
            creature.setY(y);
        } else if (tile.isDiggable()) {
            creature.dig(x, y);
        }
    }


    /**
     * Receives a message and adds it to the message queue.
     *
     * @param message the message to be added to the queue
     */
    @Override
    public void onNotify(String message) {
        messages.add(message);
    }

}
