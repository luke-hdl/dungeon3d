package screens.display;

import global.DisplayGlobal;
import hero.Hero;
import hero.Roster;
import screens.Screen;
import screens.shared.SeperatorScreen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class RosterInspectScreen extends Screen {
    protected int pointer = 0;
    protected HeroDetailScreen focusedMember;
    protected SeperatorScreen seperator = new SeperatorScreen('#', Color.WHITE);

    protected Roster roster;

    public RosterInspectScreen(Roster roster) {
        this.roster = roster;
        focusedMember = new HeroDetailScreen(roster.getHeroes().get(pointer));
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        int currentDrawY = sy;
        for (int count = 0; count < roster.getHeroes().size(); count++) {
            Hero hero = roster.getHeroes().get(count);
            String name = hero.getName();
            String shortCode = hero.getAuspice().getShortCode();
            Color shortCodeColor = hero.getAuspice().getShortCodeColor();
            String hpString = hero.getCurrentHp() + "/" + hero.getMaxHp();
            String resourceString = hero.getCurrentResource() + "/" + hero.getMaxResource();

            DisplayGlobal.getTerminal().write((pointer == count ? " >" : "") + name, sx, currentDrawY, (pointer == count ? Color.YELLOW : Color.WHITE));
            currentDrawY++;
            DisplayGlobal.getTerminal().write(shortCode + " lv " + hero.getLevel(), sx, currentDrawY, shortCodeColor);

            currentDrawY++;
            DisplayGlobal.getTerminal().write(hpString, sx, currentDrawY, Color.PINK);

            currentDrawY++;
            DisplayGlobal.getTerminal().write(resourceString, sx, currentDrawY, Color.CYAN);
            currentDrawY += 2;
        }

        seperator.displayOutput(sx+15, sy, sx+16, my);
        focusedMember.displayOutput(sx+17, sy, mx, my);
    }

    @Override
    public void processInput(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            pointer--;
            if (pointer < 0) {
                pointer = roster.getHeroes().size() - 1;
            }
            focusedMember = new HeroDetailScreen(roster.getHeroes().get(pointer));
        }
        if (keyCode == KeyEvent.VK_S) {
            pointer++;
            if (pointer >= roster.getHeroes().size()) {
                pointer = 0;
            }
            focusedMember = new HeroDetailScreen(roster.getHeroes().get(pointer));
        }

    }
}
