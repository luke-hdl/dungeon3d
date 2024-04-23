package screens.battle;

import combatants.Battle;
import global.DisplayGlobal;
import screens.Screen;
import skills.CombatSkillResult;

import java.awt.*;
import java.util.List;

public class CombatMessageScreen extends Screen {
    protected Battle battle;

    public CombatMessageScreen(Battle battle) {
        this.battle = battle;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        List<CombatSkillResult> results = battle.getLastCombatSkillResult();
        for (int x = sx; x < mx; x++) {
            for (int y = sy; y < my; y++) {
                DisplayGlobal.getTerminal().write(' ', x, y, Color.WHITE);
            }
        }
        int y = my;
        Color color = Color.white;
        for (CombatSkillResult message : results) {
            y--;
            DisplayGlobal.getTerminal().write(message.getMessage(), sx + 1, y, color);
            color = color.darker();
            if (y <= sy) {
                break;
            }
        }
    }

    @Override
    public void processInput(int keyCode) {
    }
}
