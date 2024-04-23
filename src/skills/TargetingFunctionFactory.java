package skills;

import combatants.Battle;
import combatants.Combatant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TargetingFunctionFactory {

    public static Function<Battle, Map<Integer, Map<TargetType, List<Combatant>>>> getAttackOneCombatantTargetingFunction(int minTarget, int maxTarget) {
        return battle -> {
            Map<Integer, Map<TargetType, List<Combatant>>> targetMap = new HashMap<>();
            for (int possibleTarget = minTarget; possibleTarget <= maxTarget; possibleTarget++) {
                Combatant combatant = battle.getCombatants().get(possibleTarget);
                if (combatant == null) continue;
                Map<TargetType, List<Combatant>> targets = new HashMap<>();
                targets.put(TargetType.NORMAL, List.of(combatant));
                targetMap.put(possibleTarget, targets);
            }
            return targetMap;
        };
    }
}
