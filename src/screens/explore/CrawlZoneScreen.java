package screens.explore;

import display.Sprite;
import display.SpriteUtils;
import dungeons.DisplayType;
import dungeons.Dungeon;
import dungeons.Tile;
import exceptions.BattleStartsImmediatelyException;
import exceptions.DungeonCollisionException;
import explore.DungeonMover;
import explore.Viewpoint;
import global.DisplayGlobal;
import main.Main;
import screens.Screen;
import screens.battle.BattleScreen;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CrawlZoneScreen extends Screen {
    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Point) {
                Point objPoint = (Point) obj;
                return x == objPoint.x && y == objPoint.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return x * 10000 + y;
        }
    }

    protected Screen parentScreen;
    protected Dungeon dungeon;
    protected DungeonMover hero;
    protected Viewpoint viewpoint;

    public CrawlZoneScreen(Dungeon dungeon, DungeonMover hero, Screen parentScreen) {
        this.dungeon = dungeon;
        this.hero = hero;
        this.viewpoint = new Viewpoint(hero, 0.66);
        this.parentScreen = parentScreen;
    }

    protected double[] getPos() {
        return new double[]{viewpoint.getX(), viewpoint.getY()};
    }

    protected double[] getFacing() {
        return new double[]{viewpoint.getFacingX(), viewpoint.getFacingY()};
    }

    protected double[] getCamPlane() {
        return new double[]{0, viewpoint.getRelativeHeight()};
    }

    public void displayOutput(int sx, int sy, int mx, int my) {
        Tile[][] floorTiles = new Tile[mx - sx + 1][my - sy + 1];
        Map<Point, List<int[]>> floorTilePositionMap = new HashMap<>(); //maps the dungeon position to the list of squares in it
        Tile[][] wallTiles = new Tile[mx - sx + 1][my - sy + 1];
        Map<Point, List<int[]>> wallTilePositionMap = new HashMap<>(); //maps the dungeon position to the list of squares in it
        int horizon = (my - sy) / 2;

        //First, floors
        for (int y = 0; y < horizon; y++) {
            double xMod = getFacing()[0] == 0 ? getCamPlane()[1] : getCamPlane()[0];
            double yMod = getFacing()[1] == 0 ? getCamPlane()[1] : getCamPlane()[0];

            double rayDirX0 = -getFacing()[0] - xMod;
            double rayDirY0 = getFacing()[1] - yMod;
            double rayDirX1 = -getFacing()[0] + xMod;
            double rayDirY1 = getFacing()[1] + yMod;

            double p = y - (my - sy) / 2d;
            double posZ = 0.5 * (my - sy);
            double rowDistance = posZ / p;
            double floorStepX = rowDistance * (rayDirX1 - rayDirX0) / (mx - sx);
            double floorStepY = rowDistance * (rayDirY1 - rayDirY0) / (mx - sx);

            double floorX = getPos()[0] + rowDistance * rayDirX0;
            double floorY = getPos()[1] + rowDistance * rayDirY0;

            for (int x = 0; x < (mx - sx); ++x) {
                if (floorY < 0) {
                    floorY = 0;
                }
                if (floorY >= dungeon.getHeight()) {
                    floorY = dungeon.getHeight() - 1;
                }
                if (floorX < 0) {
                    floorX = 0;
                }
                if (floorX >= dungeon.getWidth()) {
                    floorX = dungeon.getWidth() - 1;
                }

                int cellX = (int) (floorX);
                int cellY = (int) (floorY);
                Tile initialTile = dungeon.getTileAt(cellX, cellY);
                Tile tile = initialTile;

                while (tile.getDisplayType() != DisplayType.FLOOR) {
                    //Correct rounding errors
                    int newCellX = floorStepX < 0 ? cellX - 1 : floorStepX > 0 ? cellX + 1 : cellX;
                    int newCellY = floorStepY < 0 ? cellY - 1 : floorStepY > 0 ? cellY + 1 : cellY;
                    if (newCellX < 0 || newCellX >= dungeon.getWidth() || newCellY < 0 || newCellY >= dungeon.getHeight()) {
                        tile = initialTile;
                        break;
                    }

                    cellX = newCellX;
                    cellY = newCellY;

                    tile = dungeon.getTileAt(cellX, cellY);
                }

                floorX += floorStepX;
                floorY += floorStepY;

                int drawX = getFacing()[1] <= 0 && getFacing()[0] <= 0 ? x : mx - sx - x - 1;
                floorTiles[drawX][my - sy - y - 1] = tile;
                Point key = new Point(cellX, cellY);
                if (!floorTilePositionMap.containsKey(key)) {
                    floorTilePositionMap.put(key, new LinkedList<>());
                }
                floorTilePositionMap.get(key).add(new int[]{drawX, my - 1 - y});
            }
        }


        for (int x = 0; x < mx - sx; x++) {
            double posX = getPos()[0];
            double posY = getPos()[1];

            int drawAreaHeight = my - sy;

            //What dungeon square to look at.
            //The player's position is always an int, but it's possible for the dungeon position to be slightly different for rendering reasons.
            int dungeonX = (int) posX;
            int dungeonY = (int) posY;

            double cameraX = 2d * ((double) x / (((double) (mx - sx)))) - 1d; //relative position of the camera - at the far left this will be -1; at the far right it is 1

            double xMod = getFacing()[0] == 0 ? getCamPlane()[1] : getCamPlane()[0];
            double yMod = getFacing()[1] == 0 ? getCamPlane()[1] : getCamPlane()[0];

            double rayDirX = getFacing()[0] + xMod * cameraX;
            double rayDirY = -getFacing()[1] + yMod * cameraX;


            //How far until the ray hits a wall
            double sideDistX;
            double sideDistY;

            //length of ray from one x or y-side to next x or y-side; approaches infinity near 0, which we represent with a big ass number.
            double deltaDistX = (rayDirX == 0) ? 1e30 : Math.abs(1 / rayDirX);
            double deltaDistY = (rayDirY == 0) ? 1e30 : Math.abs(1 / rayDirY);
            double perpWallDist;

            //Whether we're going up/left or down/right.
            int stepX;
            int stepY;

            boolean hit = false;
            boolean fromTop = false; //if we're looking at the square "from the side" or "from the top"
            //How far we are from the actual edge.
            if (rayDirX < 0) {
                stepX = -1;
                sideDistX = (posX - dungeonX) * deltaDistX;
            } else {
                stepX = 1;
                sideDistX = (dungeonX + 1.0 - posX) * deltaDistX;
            }
            if (rayDirY < 0) {
                stepY = -1;
                sideDistY = (posY - dungeonY) * deltaDistY;
            } else {
                stepY = 1;
                sideDistY = (dungeonY + 1.0 - posY) * deltaDistY;
            }

            while (!hit) {
                //jump to next map square, either in x-direction, or in y-direction
                if (sideDistX < sideDistY) {
                    sideDistX += deltaDistX;
                    dungeonX += stepX;
                    fromTop = false;
                } else {
                    sideDistY += deltaDistY;
                    dungeonY += stepY;
                    fromTop = true;
                }
                //Check if ray has hit a wall
                if (dungeon.getTileAt(dungeonX, dungeonY).getDisplayType() == DisplayType.WALL) {
                    hit = true;
                }
            }

            if (fromTop) {
                perpWallDist = (sideDistY - deltaDistY);
            } else {
                perpWallDist = (sideDistX - deltaDistX);
            }

            //Calculate height of line to draw on screen
            int lineHeight = (int) (drawAreaHeight / perpWallDist);

            //calculate lowest and highest pixel to fill in current stripe
            int drawStart = -lineHeight / 2 + drawAreaHeight / 2;
            if (drawStart < 0) drawStart = 0;
            int drawEnd = lineHeight / 2 + drawAreaHeight / 2;
            if (drawEnd >= drawAreaHeight) drawEnd = drawAreaHeight - 1;

            Tile tile = dungeon.getTileAt(dungeonX, dungeonY);

            //give x and y sides different brightness
            if (fromTop) {
                tile = tile.darker();
            }

            //draw the pixels of the stripe as a vertical line
            int drawX = getFacing()[1] >= 0 && getFacing()[0] >= 0 ? x : mx - sx - x - 1;
            for (int y = sy; y < my; y++) {
                if (y <= drawEnd && y >= drawStart) {
                    wallTiles[drawX][y] = tile;
                    Point key = new Point(dungeonX, dungeonY);
                    if (!wallTilePositionMap.containsKey(key)) {
                        wallTilePositionMap.put(key, new LinkedList<>());
                    }
                    wallTilePositionMap.get(key).add(new int[]{drawX, y});
                }
            }
        }

        for (int x = sx; x < mx; x++) {
            for (int y = sy; y < my; y++) {
                if (wallTiles[x - sx][y - sy] != null) {
                    DisplayGlobal.getTerminal().write(wallTiles[x - sx][y - sy].getGlyph(), x, y, wallTiles[x - sx][y - sy].getColor());
                } else if (y >= horizon && floorTiles[x - sx][y - sy] != null) {
                    DisplayGlobal.getTerminal().write(floorTiles[x - sx][y - sy].getGlyph(), x, y, floorTiles[x - sx][y - sy].getColor());
                }
            }
        }

        //sprites
        for (Point point : floorTilePositionMap.keySet()) {
            DungeonMover mover = dungeon.getMoverAt(point.x, point.y);
            if (mover != null && mover.getSprite() != null) {

                int minX = Integer.MAX_VALUE;
                int maxX = 0;
                int minY = Integer.MAX_VALUE;
                int maxY = 0;
                for (int[] pos : floorTilePositionMap.get(point)) {
                    if (minX >= pos[0]) {
                        minX = pos[0];
                    }
                    if (maxX <= pos[0]) {
                        maxX = pos[0];
                    }
                    if (minY >= pos[1]) {
                        minY = pos[1];
                    }
                    if (maxY <= pos[1]) {
                        maxY = pos[1];
                    }
                }
                int artHorizon = (maxY - minY) / 2;
                double distance = Math.sqrt((point.x - viewpoint.getX() + 0.5) * (point.x - viewpoint.getX() + 0.5) + (point.y - viewpoint.getY() + 0.5) * (point.y - viewpoint.getY() + 0.5));
                int permittedHeight = (int) (300 / (distance + 1.5)); //slightly different calc than above but I'm too lazy to make it match; this is more efficient anyways
                int permittedWidth = (int) (150 / (distance + 1.5));
                Sprite displaySprite = SpriteUtils.fitToBox(permittedWidth, permittedHeight, mover.getSprite());
                permittedHeight = Math.min(displaySprite.getHeight(), permittedHeight);
                permittedWidth = Math.min(displaySprite.getWidth(), permittedWidth);

                int startX = minX + ((maxX - minX) - permittedWidth) / 2;
                int endX = startX + permittedWidth;

                int spriteX = 0;

                if (startX < 0) {
                    spriteX = -1 * startX + (maxX - minX) / 2;
                    startX = 0;
                }
                if (endX >= mx - sx) {
                    endX = mx - sx;
                }
                for (int x = startX; x < endX; x++) {
                    int charHeight = my - sy;
                    for (int y = sy; y < sy + permittedHeight; y++) {
                        char glyph = displaySprite.getGlyph(spriteX, y - sy);
                        if (glyph != ' ') {
                            //determine if this square is visible
                            boolean blocked = false;
                            int yDistFromHorizon = charHeight / 2 - (charHeight - y + sy - minY);
                            int terminalY = charHeight / 2 + yDistFromHorizon - permittedHeight + artHorizon - sy;
                            if (wallTiles[x][terminalY] != null) {
                                //determine if this wall is in front
                                for (Map.Entry<Point, List<int[]>> pointsList : wallTilePositionMap.entrySet()) {
                                    for (int[] points : pointsList.getValue()) {
                                        if (points[0] == x && points[1] == terminalY) {
                                            Point objectivePoint = pointsList.getKey();
                                            if (Math.min(viewpoint.getX() - 0.5, point.x) <= objectivePoint.x && Math.max(viewpoint.getX() - 0.5, point.x) >= objectivePoint.x) {
                                                if (Math.min(viewpoint.getY() - 0.5, point.y) <= objectivePoint.y && Math.max(viewpoint.getY() - 0.5, point.y) >= objectivePoint.y) {
                                                    blocked = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (!blocked)
                                DisplayGlobal.getTerminal().write(glyph, x + sx, terminalY + sy, displaySprite.getColor(spriteX, y - sy));
                        }
                    }
                    spriteX++;
                    if (spriteX >= displaySprite.getWidth()) {
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void processInput(int keyEvent) {
        switch (keyEvent) {
            case KeyEvent.VK_W:
                up();
                break;
            case KeyEvent.VK_A:
                left();
                break;
            case KeyEvent.VK_D:
                right();
                break;
        }
    }

    public void up() {
        try {
            try {
                hero.move();
            } catch (DungeonCollisionException e) {
                hero.handleCollisions(e.getCollidedWith());
            }
            dungeon.everyoneMoves();
        } catch (BattleStartsImmediatelyException e) {
            e.getBattle().setReturnScreen(this.parentScreen);
            Main.singleton.screen = new BattleScreen(e.getBattle());
        }
    }

    public void left() {
        hero.rotateLeftOneStep();
    }

    public void right() {
        hero.rotateRightOneStep();
    }
}
