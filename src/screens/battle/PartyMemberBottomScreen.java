package screens.battle;

import combatants.Battle;
import combatants.Combatant;
import global.DisplayGlobal;
import screens.Screen;
import screens.shared.SeperatorScreen;

import java.awt.*;

public class PartyMemberBottomScreen extends Screen {
    protected Battle battle;
    protected SeperatorScreen seperatorScreen;

    public PartyMemberBottomScreen(Battle battle) {
        this.battle = battle;
        seperatorScreen = new SeperatorScreen('#', Color.WHITE);
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        int width = mx - sx;
        int height = my - sy;

        int xGridSpace = width / 3;
        int yGridSpace = height / 2;

        seperatorScreen.displayOutput(sx + xGridSpace, sy, sx + xGridSpace + 1, my);
        seperatorScreen.displayOutput(sx + xGridSpace * 2, sy, sx + xGridSpace * 2 + 1, my);
        seperatorScreen.displayOutput(sx, sy, mx, sy + 1);
        seperatorScreen.displayOutput(sx, my - yGridSpace - 1, mx, my - yGridSpace);
        seperatorScreen.displayOutput(sx, my - 1, mx, my);

        for (int gridX = 0; gridX < 3; gridX++) {
            for (int gridY = 0; gridY < 2; gridY++) {
                Combatant hero = battle.getHeroAt(gridX, 1 - gridY);
                if (hero != null) {
                    String nameString = (hero == battle.getCurrentFighter() ? "> " : "") + hero.getName();
                    String hpString = hero.getCurrentHp() + "/" + hero.getMaxHp();
                    String resourceString = hero.getCurrentResource() + "/" + hero.getCurrentResource();
                    Color color = hero == battle.getCurrentFighter() ? Color.YELLOW : Color.WHITE;

                    int nameOffset = (xGridSpace - nameString.length()) / 2;
                    int hpOffset = (xGridSpace - hpString.length()) / 2;
                    int resourceOffset = (xGridSpace - resourceString.length()) / 2;

                    int y = (yGridSpace - 3) / 2;
                    DisplayGlobal.getTerminal().write(nameString, sx + gridX * xGridSpace + nameOffset, my - ((gridY + 1) * yGridSpace - y), color);
                    y++;
                    DisplayGlobal.getTerminal().write(hpString, sx + gridX * xGridSpace + hpOffset, my - ((gridY + 1) * yGridSpace - y), color);
                    y++;
                    DisplayGlobal.getTerminal().write(resourceString, sx + gridX * xGridSpace + resourceOffset, my - ((gridY + 1) * yGridSpace - y), color);
                }
            }
        }
    }

    @Override
    public void processInput(int keyCode) {
        //todo. probably noop.
    }
}
