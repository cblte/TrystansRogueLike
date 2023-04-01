package net.zn80.trystansroguelike;


import net.trystan.asciipanel.AsciiPanel;

public class PerformanceTest {

    int screenWidth = 100;
    int screenHeight = 100;
    private World world;

    public PerformanceTest() {
        createWorld();

        int n = 1000000;

        long start1 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            world.addAtEmptyLocation(new Creature(world, '@', AsciiPanel.brightWhite));
        }
        long end1 = System.nanoTime();
        System.out.println("addAtEmptyLocation method took " + (end1 - start1) / 1000000.0 + " ms");

        long start2 = System.nanoTime();
        for (int i = 0; i < n; i++) {
            world.addAtEmptyLocationOptimized(new Creature(world, '@', AsciiPanel.brightWhite));
        }
        long end2 = System.nanoTime();
        System.out.println("addAtEmptyLocationOptimized method took " + (end2 - start2) / 1000000.0 + " ms");
    }

    private void createWorld() {
        this.world = new WorldBuilder(50, 50).makeCaves().build();
    }

    public static void main(String[] args) {
        PerformanceTest pt = new PerformanceTest();
    }


}


