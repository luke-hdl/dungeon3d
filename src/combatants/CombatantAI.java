package combatants;

import skills.Skill;
import skills.TargetType;

import java.util.List;
import java.util.Map;

public abstract class CombatantAI {
    protected Combatant holder;

    public CombatantAI(Combatant holder) {
        this.holder = holder;
    }

    public abstract Skill decideAction();

    public abstract Map<TargetType, List<Combatant>> decideTargets(Skill action);
}
