package skills;

import combatants.Battle;
import combatants.Combatant;
import global.RandomGlobal;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class SkillFactory {

    public static Skill getTestSkillForUI(String name) {
        return getBasicAttackingSkill(name, 0, 0, 0, 0, 0, 0, 0, Element.FAIL, 0, 11);
    }

    public static Skill getBasicAttackForAllies(Element element) {
        String name = "Attack";
        double attackMultiplier = 1;
        double specialAttackMultiplier = 0;
        double defenseMultiplier = 1;
        double specialDefenseMultiplier = 0;
        double luckMultiplier = 0.05d;
        double baseAccuracy = 0.9d;

        return getBasicAttackingSkill(name, attackMultiplier, specialAttackMultiplier, defenseMultiplier, specialDefenseMultiplier, baseAccuracy, luckMultiplier, luckMultiplier, element, 0, 11);
    }

    public static Skill getBasicAttackForEnemies(Element element) {
        String name = "Attack";
        double attackMultiplier = 1;
        double specialAttackMultiplier = 0;
        double defenseMultiplier = 1;
        double specialDefenseMultiplier = 0;
        double luckMultiplier = 0.05d;
        double baseAccuracy = 0.9d;

        return getBasicAttackingSkill(name, attackMultiplier, specialAttackMultiplier, defenseMultiplier, specialDefenseMultiplier, baseAccuracy, luckMultiplier, luckMultiplier, element, 12, 17);
    }

    public static Skill getBasicAttackingSkill(String name, double attackMultiplier, double specialAttackMultiplier, double defenseMultiplier, double specialDefenseMultiplier, double baseAccuracy, double luckModToHit, double luckModToDodge, Element element, int minTarget, int maxTarget) {
        BiFunction<Combatant, Map<TargetType, List<Combatant>>, CombatSkillResult> function = (attacker, targetTypeListMap) -> {
            Combatant target = targetTypeListMap.get(TargetType.NORMAL).get(0);
            double hitRate = baseAccuracy + attacker.getLuck() * luckModToHit - attacker.getLuck() * luckModToDodge;
            double totalAttack = attacker.getAttack() * attackMultiplier + attacker.getSpecialAttack() * specialAttackMultiplier;
            double totalDefense = target.getDefense() * defenseMultiplier + target.getSpecialDefense() * specialDefenseMultiplier;

            if (hitRate < RandomGlobal.getRandom()) {
                String message = attacker.getName() + " missed!";
                return new CombatSkillResult(message, Element.WHIFF);
            }

            int damage = (int) (totalAttack * 1.5 - totalDefense);
            if (damage < 1) damage = 1;

            return target.takeDamage(damage, element);
        };

        Function<Battle, Map<Integer, Map<TargetType, List<Combatant>>>> targetingFunction = TargetingFunctionFactory.getAttackOneCombatantTargetingFunction(minTarget, maxTarget);

        return new Skill(name, "A basic attack using your primary weapon.", "The most fundamental skill: a basic attack using your primary weapon!", function, targetingFunction);
    }
}
