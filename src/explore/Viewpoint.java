package explore;

import explore.DungeonMover;

public class Viewpoint {
    protected DungeonMover mover;
    protected double relativeHeight; //the position of the camera plane, should usually be 0.66

    public Viewpoint(DungeonMover mover, double relativeHeight) {
        this.mover = mover;
        this.relativeHeight = relativeHeight;
        mover.viewpoint = this;
    }

    public double getX(){
        return mover.getX() + 0.5;
    }

    public double getY() {
        return mover.getY() + 0.5;
    }

    public double getFacingX(){
        return mover.getFacingX();
    }

    public double getFacingY(){
        return mover.getFacingY();
    }

    public double getRelativeHeight() {
        return this.relativeHeight;
    }
}
