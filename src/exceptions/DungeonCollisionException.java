package exceptions;

import explore.DungeonMover;

public class DungeonCollisionException extends RuntimeException {
    protected DungeonMover collidedWith;

    public DungeonCollisionException(DungeonMover collidedWith) {
        this.collidedWith = collidedWith;
    }

    public DungeonMover getCollidedWith() {
        return collidedWith;
    }
}
