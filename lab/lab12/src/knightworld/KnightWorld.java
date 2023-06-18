package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {

    private TETile[][] tiles;
    private int width;
    private int height;
    private Random random;


    public KnightWorld(int width, int height, int holeSize) {
        this.width = width;
        this.height = height;
        this.tiles = new TETile[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tiles[i][j] = Tileset.LOCKED_DOOR;
            }
        }

        for (int x = 0; x < width; x += 5*holeSize) {
            for (int y = 0; y < height; y += 5*holeSize) {
                griding5(x, y, holeSize);
            }
        }

    }

    /** Returns the tiles associated with this KnightWorld. */
    public TETile[][] getTiles() {
        return tiles;
    }

    public void griding5(int x, int y, int holeSize) {
        marking(x + 1*holeSize, y, holeSize);
        marking(x, y + 3*holeSize, holeSize);
        marking(x + 2*holeSize, y + 2*holeSize, holeSize);
        marking(x + 3*holeSize, y + 4*holeSize, holeSize);
        marking(x + 4*holeSize, y + holeSize*1, holeSize);
    }

    public void marking(int x, int y, int holeSize) {
        for (int i = 0; i < holeSize; i++) {
            for (int j = 0; j < holeSize; j++) {
                if ((x + i >= width) || (y + j >= height)) {
                    return;
                }
                tiles[x + i][y + j] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 50;
        int height = 30;
        int holeSize = 2;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
