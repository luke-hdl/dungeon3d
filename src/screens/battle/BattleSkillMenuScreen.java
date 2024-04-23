package screens.battle;

import combatants.Battle;
import global.DisplayGlobal;
import screens.Screen;
import skills.Skill;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class BattleSkillMenuScreen extends Screen {
    protected int pointer = 0;
    protected Battle battle;

    public BattleSkillMenuScreen(Battle battle) {
        this.battle = battle;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        DisplayGlobal.getTerminal().write("Skills: ", sx, sy, Color.WHITE);
        int y = sy + 2;
        List<Skill> skillList = battle.getCurrentFighter().getSkills();
        for (int skill = 0; skill < skillList.size(); skill++) {
            Skill skillToShow = skillList.get(skill);
            DisplayGlobal.getTerminal().write((skill == pointer ? "> " : "") + skillToShow.getName(), sx, y, skill == pointer ? Color.YELLOW : Color.WHITE);
            y++;
            if (y >= my) break;
        }
    }

    @Override
    public void processInput(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            pointer--;
            if (pointer < 0) {
                pointer = battle.getCurrentFighter().getSkills().size() - 1;
            }
        }
        if (keyCode == KeyEvent.VK_S) {
            pointer++;
            if (pointer >= battle.getCurrentFighter().getSkills().size()) {
                pointer = 0;
            }
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_ENTER) {
            battle.setSkillBeingAimed(battle.getCurrentFighter().getSkills().get(pointer));
        }
    }
}
