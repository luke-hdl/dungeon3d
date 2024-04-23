package explore.ai;

import explore.DungeonMover;

public abstract class DungeonMoverAI {
    protected DungeonMover mover;
    public abstract void move();
    public abstract void handleCollision(DungeonMover collision);

    public DungeonMover getMover() {
        return mover;
    }

    public void setMover(DungeonMover mover) {
        this.mover = mover;
    }

}
