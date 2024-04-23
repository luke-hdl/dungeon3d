package explore;

import display.Sprite;
import dungeons.Dungeon;
import dungeons.MoveTypeTag;
import exceptions.DungeonCollisionException;
import explore.ai.DungeonMoverAI;

import java.util.HashSet;
import java.util.List;

public class DungeonMover {
    Viewpoint viewpoint;
    Dungeon currentDungeon;
    Sprite sprite;
    DungeonMoverAI ai;
    List<MoveTypeTag> allowedMoveTypes;
    protected int x;
    protected int y;
    protected double facingX;
    protected double facingY;

    public DungeonMover(int x, int y, double facingX, double facingY, Dungeon currentDungeon, List<MoveTypeTag> allowedMoveTypes, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.facingX = facingX;
        this.facingY = facingY;
        this.currentDungeon = currentDungeon;
        if (currentDungeon != null) {
            currentDungeon.addMover(this);
        }
        this.allowedMoveTypes = allowedMoveTypes;
        this.sprite = sprite;
    }

    public DungeonMoverAI getAi() {
        return ai;
    }

    public void setAi(DungeonMoverAI ai) {
        this.ai = ai;
        ai.setMover(this);
    }

    public void handleCollisions(DungeonMover collision) {
        if (ai == null) {
            collision.handleCollisions(this);
        } else {
            ai.handleCollision(collision);
        }
    }

    public void die(){
        getDungeon().killMover(this);
    }

    public boolean move() {
        int targetX = x;
        int targetY = y;
        if (facingX > 0.5) {
            targetX += 1;
        }
        if (facingX < -0.5) {
            targetX -= 1;
        }
        if (facingY > 0.5) {
            targetY -= 1;
        }
        if (facingY < -0.5) {
            targetY += 1;
        }

        if (targetX < 0 || targetX >= currentDungeon.getWidth() || targetY < 0 || targetY >= currentDungeon.getHeight()) {
            return false;
        }

        if (!new HashSet<>(allowedMoveTypes).containsAll(currentDungeon.getTileAt(targetX, targetY).getTraits())) {
            return false;
        }

        if ( currentDungeon.getMoverAt(targetX, targetY) != null){
            throw new DungeonCollisionException(currentDungeon.getMoverAt(targetX, targetY));
        }

        x = targetX;
        y = targetY;
        return true;
    }

    public void rotateLeftOneStep() {
        if (facingX == 0d) {
            if (facingY == 1d) {
                facingX = -1d;
            } else {
                facingX = 1d;
            }
            facingY = 0d;
        } else if (facingX == -1d) {
            facingX = 0d;
            facingY = -1d;
        } else {
            facingX = 0d;
            facingY = 1d;
        }
    }

    public void rotateRightOneStep() {
        if (facingX == 0d) {
            if (facingY == 1d) {
                facingX = 1d;
            } else {
                facingX = -1d;
            }
            facingY = 0d;
        } else if (facingX == -1d) {
            facingX = 0d;
            facingY = 1d;
        } else {
            facingX = 0d;
            facingY = -1d;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getFacingX() {
        return facingX;
    }

    public double getFacingY() {
        return facingY;
    }

    public Viewpoint getViewpoint() {
        return viewpoint;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public Dungeon getDungeon() {
        return currentDungeon;
    }
}
