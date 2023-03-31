/**
 * This is a Java class implementing the Screen interface for a simple game. It represents the main screen where the
 * game is played.
 */
package net.zn80.trystansroguelike.screens;

import net.trystan.asciipanel.AsciiPanel;
import net.zn80.trystansroguelike.World;
import net.zn80.trystansroguelike.WorldBuilder;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayScreen implements Screen {

    private final int screenWidth;
    private final int screenHeight;
    private World world;
    private int centerX;
    private int centerY;

    public PlayScreen() {
        this.screenWidth = 80;
        this.screenHeight = 21;
        createWorld();
    }

    private void createWorld() {
        this.world = new WorldBuilder(90, 31).makeCaves().build();
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {
        // Here, we first calculate the left and top offsets of the viewport by calling the getScrollX and getScrollY
        // methods, respectively. We then pass these values to the displayTiles method to show the appropriate section
        // of the world. Finally, we add some text to the top and bottom of the screen to provide instructions to the
        // player.
        int left = getScrollX();
        int top = getScrollY();

        displayTiles(terminal, left, top);

        terminal.write('X', centerX - left, centerY - top);

        terminal.write("Let's a game:", 1, 1);
        terminal.writeCenter("PlayScreen", terminal.getHeightInCharacters() / 2);
        terminal.writeCenter("--- press [escape] to loose or [enter] to continue ---", terminal.getHeightInCharacters() - 2);
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
        return Math.max(0, Math.min(centerX - screenWidth / 2, world.getWidth() - screenWidth));
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
        return Math.max(0, Math.min(centerY - screenHeight / 2, world.getHeight() - screenHeight));
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
                Color color = world.getColor(wx, wy);
                char glyph = world.getGlyph(wx, wy);
                terminal.write(glyph, x, y, color);
            }
        }
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
                scrollBy(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
                scrollBy(1, 0);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_K:
                scrollBy(0, -1);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_J:
                scrollBy(0, 1);
                break;
            case KeyEvent.VK_Y:
                scrollBy(-1, -1);
                break;
            case KeyEvent.VK_U:
                scrollBy(1, -1);
                break;
            case KeyEvent.VK_B:
                scrollBy(-1, 1);
                break;
            case KeyEvent.VK_N:
                scrollBy(1, 1);
                break;
        }

        return this;
    }

    /**
     * Scrolls the screen by a given amount along the x and y axes.
     *
     * @param mx the amount to scroll along the x-axis
     * @param my the amount to scroll along the y-axis
     */
    private void scrollBy(int mx, int my) {
        centerX = Math.min(Math.max(centerX + mx, 0), world.getWidth() - 1);
        centerY = Math.min(Math.max(centerY + my, 0), world.getHeight() - 1);
    }
}
