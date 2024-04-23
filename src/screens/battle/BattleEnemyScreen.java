package screens.battle;

import combatants.Battle;
import combatants.Combatant;
import display.Sprite;
import global.DisplayGlobal;
import screens.Screen;
import skills.Skill;
import skills.TargetType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

public class BattleEnemyScreen extends Screen {
    protected Battle battle;
    protected int targetIndex = -1;
    protected Integer[] validTargets;
    protected Skill skill;
    protected Map<Integer, Map<TargetType, List<Combatant>>> skillMap;

    //The integer is the position. Valid positions are:
    // 01234
    // 56789

    public BattleEnemyScreen(Battle battle) {
        this.battle = battle;
    }

    public void initTargeting() {
        skill = battle.getSkillBeingAimed();
        skillMap = skill.getTargetingMap(battle);
        if (skillMap.isEmpty()) {
            return;
        }

        validTargets = skillMap.keySet().toArray(new Integer[]{skillMap.size()});
        targetIndex = validTargets.length - 1;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        if (battle.getSkillBeingAimed() != null && targetIndex < 0) {
            initTargeting();
        }
        if (battle.getSkillBeingAimed() == null) {
            skill = null;
            targetIndex = -1;
            skillMap = null;
            validTargets = null;
        }
        int width = mx - sx;
        int height = my - sy;

        int xSquareSize = width / 5;
        int ySquareSize = height / 2;

        for (int row = 0; row <= 1; row++) {
            for (int pos = 0; pos < 5; pos++) {
                int index = pos + (row * 5);
                Combatant enemy = battle.getCombatants().get(index);
                if (enemy == null) {
                    continue;
                }
                Color selectionColor = null;
                if (skillMap != null) {
                    for (Map.Entry<TargetType, List<Combatant>> entry : skillMap.get(validTargets[targetIndex]).entrySet()) {
                        if (entry.getValue().contains(enemy)) {
                            switch (entry.getKey()) {
                                case NORMAL:
                                case PRIMARY:
                                    selectionColor = Color.YELLOW;
                                    break;
                                default:
                                    selectionColor = Color.YELLOW.darker();
                                    break;
                            }
                        }
                    }
                }

                int xSquare = pos * xSquareSize;
                int ySquare = (row) * ySquareSize;
                Sprite sprite = enemy.getSprite();
                if (row == 0) {
                    sprite = sprite.darker().darker();
                }
                int xSquareStart = sx + xSquare + (xSquareSize - sprite.getWidth()) / 2;
                int ySquareStart = (ySquare + ySquareSize / 4) - (row == 1 ? (DisplayGlobal.getTerminal().getHeightInCharacters() - my) : 0);

                for (int x = xSquareStart; x < xSquareStart + sprite.getWidth(); x++) {
                    if (x < sx) continue;
                    for (int y = ySquareStart; y < ySquareStart + sprite.getHeight(); y++) {
                        if (x >= mx || y >= my || x < 0 || y < 0) break;
                        char glyph = sprite.getGlyph(x - xSquareStart, y - ySquareStart);
                        if (glyph == ' ') continue;
                        if (selectionColor != null) glyph = '>';
                        Color color = selectionColor != null ? selectionColor : sprite.getColor(x - xSquareStart, y - ySquareStart);
                        DisplayGlobal.getTerminal().write(glyph, x, y, color);
                    }
                }
            }
        }
    }

    @Override
    public void processInput(int keyCode) {
        if (targetIndex >= 0) {
            if (keyCode == KeyEvent.VK_A) {
                targetIndex--;
                if (targetIndex < 0) {
                    battle.setSkillBeingAimed(null);
                }
            }
            if (keyCode == KeyEvent.VK_D) {
                targetIndex++;
                if (targetIndex >= validTargets.length) {
                    targetIndex = 0;
                }
            }
            if (keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_E) {
                battle.fireSkillBeingAimed(skillMap.get(validTargets[targetIndex]));
                battle.updateCurrentFighter();
            }
        }
    }
}
