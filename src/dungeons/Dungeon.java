package dungeons;

import exceptions.DungeonCollisionException;
import explore.DungeonMover;
import hero.Party;

import java.util.LinkedList;
import java.util.List;

public class Dungeon {
    Tile[][] tiles;
    List<DungeonMover> explorers = new LinkedList<>();
    Party party;

    public Dungeon(Tile[][] dungeonTiles) {
        this.tiles = dungeonTiles;
    }

    public int getWidth() {
        return tiles.length;
    }

    public int getHeight() {
        return tiles[0].length;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public Tile getTileAt(int x, int y) {
        return tiles[x][y];
    }

    public DungeonMover getMoverAt(int x, int y) {
        for (DungeonMover mover : explorers) {
            if (mover.getX() == x && mover.getY() == y) {
                return mover;
            }
        }
        return null;
    }

    public void addMover(DungeonMover dungeonMover) {
        explorers.add(dungeonMover);
    }

    public void everyoneMoves() {
        for (DungeonMover mover : explorers) {
            if (mover.getAi() != null) {
                try {
                    mover.getAi().move();
                } catch ( DungeonCollisionException e ) {
                    mover.handleCollisions(e.getCollidedWith());
                }
            }
        }
    }

    public void killMover(DungeonMover mover) {
        explorers.remove(mover);
    }
}
