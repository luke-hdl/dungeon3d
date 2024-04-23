package skills;

import combatants.Battle;
import combatants.Combatant;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Skill {
    protected String name;
    protected String shortDescription;
    protected String longDescription;
    protected BiFunction<Combatant, Map<TargetType, List<Combatant>>, CombatSkillResult> combatUse;
    protected Function<Battle, Map<Integer, Map<TargetType, List<Combatant>>>> validCombatTargets; //the int is the targeting position: see Battle

    public Skill(String name, String shortDescription, String longDescription, BiFunction<Combatant, Map<TargetType, List<Combatant>>, CombatSkillResult> combatUse, Function<Battle, Map<Integer, Map<TargetType, List<Combatant>>>> validCombatTargets) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.combatUse = combatUse;
        this.validCombatTargets = validCombatTargets;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Map<TargetType, List<Combatant>>> getTargetingMap(Battle battle) {
        return validCombatTargets.apply(battle);
    }

    public CombatSkillResult useInCombat(Combatant attacker, Map<TargetType, List<Combatant>> targets) {
        return combatUse.apply(attacker, targets);
    }
}
