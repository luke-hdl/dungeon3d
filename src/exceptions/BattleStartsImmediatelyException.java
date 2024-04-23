package exceptions;

import combatants.Battle;

public class BattleStartsImmediatelyException extends RuntimeException {
    protected Battle battle;

    public BattleStartsImmediatelyException(Battle battle){
        this.battle = battle;
    }

    public Battle getBattle() {
        return battle;
    }
}
