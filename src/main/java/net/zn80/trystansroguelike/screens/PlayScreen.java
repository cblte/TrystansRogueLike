/**
 * This is a Java class implementing the Screen interface for a simple game. It represents the main screen where the
 * game is played.
 */
package net.zn80.trystansroguelike.screens;

import net.trystan.asciipanel.AsciiPanel;
import net.zn80.trystansroguelike.Creature;
import net.zn80.trystansroguelike.CreatureFactory;
import net.zn80.trystansroguelike.World;
import net.zn80.trystansroguelike.WorldBuilder;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayScreen implements Screen {
    private final int screenWidth;
    private final int screenHeight;
    private World world;
    private Creature player;
    private List<String> messages;

    public PlayScreen() {
        this.screenWidth = 80;
        this.screenHeight = 21;
        this.messages = new ArrayList<String>();

        createWorld();

        CreatureFactory creatureFactory = new CreatureFactory(world);
        createCreatures(creatureFactory);
    }

    /**
     * Creates a new world with a size of 90x31 and generates caves.
     */
    private void createWorld() {
        this.world = new WorldBuilder(90, 31).makeCaves().build();
    }

    /**
     * Creates a player and 8 fungus creatures using the specified creature factory.
     *
     * @param creatureFactory The creature factory to use for creating the creatures.
     */
    private void createCreatures(CreatureFactory creatureFactory) {
        player = creatureFactory.newPlayer(messages);
        System.out.format("Player %c added at %d/%d%n", player.getGlyph(), player.getX(), player.getY());

        for (int i = 0; i < 8; i++) {
            Creature f = creatureFactory.newFungus();
            System.out.format("Creature %c added at %d/%d%n", f.getGlyph(), f.getX(), f.getY());
        }
    }

    /**
     * Displays the game output on the specified AsciiPanel terminal. This method first calculates the left and top
     * offsets of the viewport by calling the getScrollX and getScrollY methods, respectively. It then passes these
     * values to the displayTiles method to show the appropriate section of the world. Next, it calls the
     * displayMessages method to show any messages that have been generated since the last call to displayOutput.
     * Finally, it displays the player character at its current position and shows the player's health status at the
     * bottom of the screen.
     *
     * @param terminal the AsciiPanel on which to display the game output
     */
    @Override
    public void displayOutput(AsciiPanel terminal) {
        // Calculate the left and top offsets of the viewport
        int left = getScrollX();
        int top = getScrollY();

        // Display the appropriate section of the world
        displayTiles(terminal, left, top);

        // Display any messages that have been generated
        displayMessages(terminal, messages);

        // Display the player character at its current position
        terminal.write(player.getGlyph(), player.getX() - left, player.getY() - top, player.getColor());

        // Show the player's health status at the bottom of the screen
        String stats = String.format(" %3d/%3d hp", player.getHp(), player.getMaxHp());
        terminal.write(stats, 1, 23);
    }


    /**
     * Calculates the horizontal scroll position of the viewport based on the center point and screen size. Ensures that
     * the viewport does not scroll outside the boundaries of the world.
     * <p>
     * See CodeExplanation.md for more info.
     *
     * @return The horizontal scroll position of the viewport.
     */
    public int getScrollX() {
        return Math.max(0, Math.min(player.getX() - screenWidth / 2, world.getWidth() - screenWidth));
    }

    /**
     * Calculates the vertical scroll position of the viewport based on the center point and screen size. Ensures that
     * the viewport does not scroll outside the boundaries of the world.
     * <p>
     * See CodeExplanation.md for more info.
     *
     * @return The vertical scroll position of the viewport.
     */
    public int getScrollY() {
        return Math.max(0, Math.min(player.getY() - screenHeight / 2, world.getHeight() - screenHeight));
    }

    /**
     * Displays the tiles of the world on the given AsciiPanel, starting at the specified top-left coordinates. The
     * method iterates through a rectangular region of tiles of size screenWidth by screenHeight, and for each tile,
     * retrieves its glyph and color from the World object, and writes them to the corresponding cell on the
     * AsciiPanel.
     *
     * @param terminal the AsciiPanel to display the tiles on.
     * @param left     the x-coordinate of the left edge of the viewport, in world coordinates.
     * @param top      the y-coordinate of the top edge of the viewport, in world coordinates.
     */
    private void displayTiles(AsciiPanel terminal, int left, int top) {
        for (int y = 0; y < screenHeight; y++) {
            int wy = y + top;
            for (int x = 0; x < screenWidth; x++) {
                int wx = x + left;
                terminal.write(world.getGlyph(wx, wy), x, y, world.getColor(wx, wy));
            }
        }
        // draw creatures after the tiles
        for (Creature creature : world.getCreatures()) {
            if (creature.getX() >= left && creature.getX() < left + screenWidth && creature.getY() >= top && creature.getY() < top + screenHeight) {
                terminal.write(creature.getGlyph(), creature.getX() - left, creature.getY() - top, creature.getColor());
            }
        }
    }

    /**
     * Displays the messages in the specified list on the specified terminal. The messages are centered vertically in
     * the terminal, starting at the bottom of the screen and working their way up. After the messages are displayed,
     * the list is cleared.
     *
     * @param terminal the AsciiPanel on which to display the messages
     * @param messages the list of messages to display
     */
    public void displayMessages(AsciiPanel terminal, List<String> messages) {
        int top = screenHeight - messages.size();
        for (int i = 0; i < messages.size(); i++) {
            terminal.writeCenter(messages.get(i), top + i);
        }
        messages.clear();
    }

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        switch (key.getKeyCode()) {
            // if user presses ESCAPE: switch to loser screen
            case KeyEvent.VK_ESCAPE:
                return new LooseScreen();
            // if user presses ENTER: switch to winner screen
            case KeyEvent.VK_ENTER:
                return new WinScreen();
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_H:
                player.moveBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
                player.moveBy(1, 0);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
                player.moveBy(0, -1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
                player.moveBy(0, 1);
                break;
            case KeyEvent.VK_Y:
                player.moveBy(-1, -1);
                break;
            case KeyEvent.VK_U:
                player.moveBy(1, -1);
                break;
            case KeyEvent.VK_B:
                player.moveBy(-1, 1);
                break;
            case KeyEvent.VK_N:
                player.moveBy(1, 1);
                break;
        }

        // after handling player input, update all creatures in the world
        world.updateCreatures();
        return this;
    }

}
