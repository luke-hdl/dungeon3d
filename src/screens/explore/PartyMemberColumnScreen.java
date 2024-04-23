package screens.explore;

import global.DisplayGlobal;
import hero.Hero;
import hero.Party;
import screens.Screen;

import java.awt.*;

public class PartyMemberColumnScreen extends Screen {
    protected Party party;

    public PartyMemberColumnScreen(Party party) {
        this.party = party;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        int currentDrawY = sy;

        DisplayGlobal.getTerminal().write("FRONT", sx, currentDrawY, Color.WHITE);
        currentDrawY++;

        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 3; x++) {
                Hero hero = party.getHeroes()[x][y];
                String name = hero == null ? "empty" : hero.getName();
                String shortCode = hero == null ? " " : hero.getAuspice().getShortCode();
                Color shortCodeColor = hero == null ? Color.WHITE : hero.getAuspice().getShortCodeColor();
                String hpString = hero == null ? "" : hero.getCurrentHp() + "/" + hero.getMaxHp();
                String resourceString = hero == null ? "" : hero.getCurrentResource() + "/" + hero.getMaxResource();

                DisplayGlobal.getTerminal().write(name, sx, currentDrawY, Color.WHITE);
                currentDrawY++;
                DisplayGlobal.getTerminal().write(shortCode + (hero == null ? "" : " lv " + hero.getLevel()), sx, currentDrawY, shortCodeColor);
                currentDrawY++;
                DisplayGlobal.getTerminal().write(hpString, sx, currentDrawY, Color.PINK);
                currentDrawY++;
                DisplayGlobal.getTerminal().write(resourceString, sx, currentDrawY, Color.CYAN);
                currentDrawY += 2;
            }
            if (y == 0) {
                DisplayGlobal.getTerminal().write("BACK", sx, currentDrawY, Color.WHITE);
                currentDrawY++;
            }
        }
    }

    @Override
    public void processInput(int keyCode) {
        //no-op
    }
}
