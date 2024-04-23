package explore.ai;

import combatants.Battle;
import enemies.Enemy;
import exceptions.BattleStartsImmediatelyException;
import explore.DungeonMover;

import java.util.Map;

public class DungeonCirclingAI extends DungeonMoverAI {
    int[] pillarLocation;
    Map<Integer, Enemy> enemies;

    public DungeonCirclingAI(int[] pillarLocation, Map<Integer, Enemy> enemies){
        this.pillarLocation = pillarLocation;
        this.enemies = enemies;
    }

    @Override
    public void move() {
        if (mover.getX() == pillarLocation[0] || mover.getY() == pillarLocation[1]) {
            mover.move();
        } else {
            mover.rotateLeftOneStep();
            mover.move();
        }
    }

    @Override
    public void handleCollision(DungeonMover collision) {
        Battle battle = new Battle(enemies, mover.getDungeon().getParty(), () -> mover.die());
        throw new BattleStartsImmediatelyException(battle);
    }
}
