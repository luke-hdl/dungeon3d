package combatants;

import enemies.Enemy;
import exceptions.PartyWipedException;
import exceptions.PartyWonException;
import hero.Party;
import screens.Screen;
import screens.factories.TestScreenFactory;
import skills.CombatSkillResult;
import skills.Element;
import skills.Skill;
import skills.TargetType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Battle {
    protected Map<Integer, Combatant> combatants;
    protected Skill skillBeingAimed;
    protected int turnCount;
    protected Screen returnScreen;
    List<Combatant> roundOrder;
    Combatant currentCombatant;
    protected List<CombatSkillResult> skillResults;

    protected Runnable onWin;

    public Battle(Map<Integer, Enemy> map, Party party, Runnable onWin) {
        combatants = new HashMap<>();
        for (int point : map.keySet()) {
            combatants.put(point, new Combatant(map.get(point), this));
        }

        combatants.putAll(party.getCombatantBattleMap(this));
        turnCount = 1;
        roundOrder = new ArrayList<>();
        roundOrder.addAll(combatants.values());
        roundOrder.sort(Comparator.comparing(o -> -o.agility));
        currentCombatant = roundOrder.remove(0);
        skillResults = new LinkedList<>();
        this.onWin = onWin;

    }
    //0-5: Top enemies
    //6-11: Bottom enemies
    //12-14: Top allies
    //15-17: Bottom allies


    public Screen getReturnScreen() {
        if ( returnScreen == null ){
            return TestScreenFactory.getTestScreenListScreen();
        }
        return returnScreen;
    }

    public void setReturnScreen(Screen returnScreen) {
        this.returnScreen = returnScreen;
    }

    public Map<Integer, Combatant> getCombatants() {
        return combatants;
    }

    public Combatant getHeroAt(int x, int y) {
        int index = 12 + x + y * 3;
        return combatants.get(index);
    }

    public void setSkillBeingAimed(Skill skillBeingAimed) {
        this.skillBeingAimed = skillBeingAimed;
    }

    public Skill getSkillBeingAimed() {
        return skillBeingAimed;
    }

    public List<CombatSkillResult> getLastCombatSkillResult() {
        return skillResults;
    }

    public Combatant getCurrentFighter() {
        return currentCombatant;
    }

    public void fireSkillBeingAimed(Map<TargetType, List<Combatant>> targetTypeListMap) {
        skillResults.add(0, skillBeingAimed.useInCombat(getCurrentFighter(), targetTypeListMap));
        skillBeingAimed = null;
    }

    public void updateCurrentFighter() {
        if (roundOrder.isEmpty()) {
            finishRound();
            nextRound();
        } else {
            currentCombatant = roundOrder.remove(0);
        }
    }

    protected void finishRound() {
        //Not yet implemented.
    }

    protected void nextRound() {
        turnCount++;
        roundOrder = new ArrayList<>();
        roundOrder.addAll(combatants.values());
        roundOrder.sort(Comparator.comparing(o -> -o.agility));
        currentCombatant = roundOrder.remove(0);
    }

    public void haveCurrentNpcAct() {
        Skill action = currentCombatant.getSkillToUse();
        if (action != null) {
            Map<TargetType, List<Combatant>> targetTypeListMap = currentCombatant.getTargetsForSkill(action);
            skillBeingAimed = action;
            fireSkillBeingAimed(targetTypeListMap);
        } else {
            skillResults.add(0, new CombatSkillResult(currentCombatant.getName() + " loafed around!", Element.FAIL));
        }
    }

    public void alertOfDeath(Combatant combatant) {
        roundOrder.remove(combatant);
        List<Integer> keysToRemove = new LinkedList<>();
        boolean livingPlayers = false;
        boolean livingEnemies = false;
        for (Map.Entry<Integer, Combatant> entry : combatants.entrySet()) {
            if (entry.getValue() == combatant) {
                keysToRemove.add(entry.getKey());
            } else if (entry.getKey() <= 11) {
                livingEnemies = true;
            } else if (entry.getKey() > 11) {
                livingPlayers = true;
            }
        }
        for (Integer key : keysToRemove) {
            combatants.remove(key);
        }

        if (!livingPlayers) {
            throw new PartyWipedException();
        }

        if (!livingEnemies) {
            throw new PartyWonException();
        }

    }

    public void runWin() {
        onWin.run();
        for ( Combatant combatant : combatants.values() ){
            combatant.applyDamageToHero();
        }
    }
}
