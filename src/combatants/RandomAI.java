package combatants;

import global.RandomGlobal;
import skills.Skill;
import skills.TargetType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RandomAI extends  CombatantAI {
    public RandomAI(Combatant holder) {
        super(holder);
    }

    public Skill decideAction() {
        int skill = (int) ((RandomGlobal.getRandom() - 0.01d) * holder.getSkills().size());
        return holder.getSkills().get(skill);
    }

    public Map<TargetType, List<Combatant>> decideTargets(Skill action) {
        Map<Integer, Map<TargetType, List<Combatant>>> targets = action.getTargetingMap(holder.battle);
        List<Integer> keys = new LinkedList<>(targets.keySet());
        int target = (int) ((RandomGlobal.getRandom() - 0.01d) * targets.size());
        return targets.get(keys.get(target));
    }
}
