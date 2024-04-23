package hero;

import combatants.Battle;
import combatants.Combatant;

import java.util.HashMap;
import java.util.Map;

public class Party {
    protected Hero[][] heroes;

    public Party() {
        this.heroes = new Hero[3][2];
    }

    public Hero[][] getHeroes() {
        return heroes;
    }

    public Map<Integer, Combatant> getCombatantBattleMap(Battle battle) {
        Map<Integer, Combatant> map = new HashMap<>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                if (heroes[x][y] == null) continue;
                int index = 12 + x + y * 3;
                map.put(index, new Combatant(heroes[x][y], battle));
            }
        }
        return map;
    }
}
