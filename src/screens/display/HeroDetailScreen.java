package screens.display;

import global.DisplayGlobal;
import hero.Hero;
import screens.Screen;

import java.awt.*;

public class HeroDetailScreen extends Screen {
    protected Hero partyMember;

    public HeroDetailScreen(Hero partyMember) {
        this.partyMember = partyMember;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        DisplayGlobal.getTerminal().write(partyMember.getName() + " of the Auspice ", sx, sy, Color.WHITE);
        DisplayGlobal.getTerminal().write(partyMember.getAuspice().getName(), partyMember.getAuspice().getNameColor());
        DisplayGlobal.getTerminal().write("Lv. " + partyMember.getLevel(), sx, sy + 1, Color.WHITE);
        DisplayGlobal.getTerminal().write("HP: " + partyMember.getCurrentHp() + "/" + partyMember.getMaxHp(), sx, sy + 2, Color.WHITE);
        DisplayGlobal.getTerminal().write("Mana: " + partyMember.getCurrentResource() + "/" + partyMember.getMaxResource(), sx, sy + 3, Color.WHITE);
        DisplayGlobal.getTerminal().write("Attack: " + partyMember.getAttack(), sx, sy + 4, Color.WHITE);
        DisplayGlobal.getTerminal().write("Defense: " + partyMember.getDefense(), sx, sy + 5, Color.WHITE);
        DisplayGlobal.getTerminal().write("Magic Attack: " + partyMember.getSpecialAttack(), sx, sy + 6, Color.WHITE);
        DisplayGlobal.getTerminal().write("Magic Defense: " + partyMember.getSpecialDefense(), sx, sy + 7, Color.WHITE);
        DisplayGlobal.getTerminal().write("Agility: " + partyMember.getAgility(), sx, sy + 8, Color.WHITE);
        DisplayGlobal.getTerminal().write("Luck: " + partyMember.getLuck(), sx, sy + 9, Color.WHITE);
    }

    @Override
    public void processInput(int keyCode) {
        //No-op. Probably handled by the frame, otherwise TODO
    }
}
