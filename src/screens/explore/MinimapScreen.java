package screens.explore;

import explore.DungeonMover;
import explore.Viewpoint;
import dungeons.Dungeon;
import dungeons.Tile;
import global.DisplayGlobal;
import screens.Screen;

import java.awt.*;

public class MinimapScreen extends Screen {
    protected Dungeon dungeon;
    protected Viewpoint viewpoint;

    public MinimapScreen(Dungeon dungeon, Viewpoint viewpoint) {
        super();
        this.dungeon = dungeon;
        this.viewpoint = viewpoint;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        int totalXSize = mx - sx;
        int totalYSize = my - sy;

        int centerX = totalXSize / 2 - 1;
        int centerY = totalYSize / 2 - 1;

        for (int x = sx; x < mx; x++) {
            for (int y = sy; y < my; y++) {
                int ox = (int) ((x - sx) + viewpoint.getX() - centerX) - 1; //the objective X on the map
                int oy = (int) ((y - sy) + viewpoint.getY() - centerY) - 1; //the objective X on the map
                if ((int) viewpoint.getX() == ox && (int) viewpoint.getY() == oy) {
                    char player = '?';
                    if ((int) viewpoint.getFacingY() == -1 && (int) viewpoint.getFacingX() == 0) {
                        player = 'v';
                    } else if ((int) viewpoint.getFacingY() == 1 && (int) viewpoint.getFacingX() == 0) {
                        player = '^';
                    } else if ((int) viewpoint.getFacingY() == 0 && (int) viewpoint.getFacingX() == -1) {
                        player = '<';
                    } else if ((int) viewpoint.getFacingY() == 0 && (int) viewpoint.getFacingX() == 1) {
                        player = '>';
                    }
                    DisplayGlobal.getTerminal().write(player, x, y, Color.WHITE);
                } else if (dungeon.getMoverAt(ox, oy) != null) {
                    DungeonMover mover = dungeon.getMoverAt(ox, oy);
                    char glyph = 'o';
                    if ((int) mover.getFacingY() == -1 && (int) mover.getFacingX() == 0) {
                        glyph = 'v';
                    } else if ((int) mover.getFacingY() == 1 && (int) mover.getFacingX() == 0) {
                        glyph = '^';
                    } else if ((int) mover.getFacingY() == 0 && (int) mover.getFacingX() == -1) {
                        glyph = '<';
                    } else if ((int) mover.getFacingY() == 0 && (int) mover.getFacingX() == 1) {
                        glyph = '>';
                    }
                    DisplayGlobal.getTerminal().write(glyph, x, y, Color.RED);
                } else {
                    if (ox < 0 || oy < 0 || ox >= dungeon.getWidth() || oy >= dungeon.getHeight()) {
                        DisplayGlobal.getTerminal().write('.', x, y, Color.BLACK);
                    } else {
                        Tile tile = dungeon.getTileAt(ox, oy);
                        DisplayGlobal.getTerminal().write(tile.getGlyph(), x, y, tile.getColor());
                    }
                }
            }
        }
    }

    @Override
    public void processInput(int keyCode) {

    }
}
