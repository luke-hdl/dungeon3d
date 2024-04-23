package screens.create;

import display.StringUtils;
import global.DisplayGlobal;
import hero.auspices.Auspice;
import hero.auspices.AuspiceManager;
import screens.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class HeroAuspiceSelectionScreen extends Screen {
    protected int pointer;
    protected List<Auspice> auspiceList = AuspiceManager.getAllAuspices();
    protected Auspice selectedAuspice;

    public Auspice getSelectedAuspice() {
        return selectedAuspice;
    }

    public void clearAuspice() {
        selectedAuspice = null;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        for (int auspice = 0; auspice < auspiceList.size(); auspice++) {
            if (auspice == pointer) {
                DisplayGlobal.getTerminal().write("> " + auspiceList.get(auspice).getName(), sx, sy + auspice, auspiceList.get(auspice).getNameColor());
            } else {
                DisplayGlobal.getTerminal().write(auspiceList.get(auspice).getName(), sx, sy + auspice, auspiceList.get(auspice).getNameColor());
            }
        }

        String[] description = StringUtils.wrap(auspiceList.get(pointer).getDescription(), mx-sx-2, 10);
        for ( int x = 0; x < description.length; x++){
            DisplayGlobal.getTerminal().write(description[x], sx + 1, my-11+x, Color.WHITE);
        }
    }

    @Override
    public void processInput(int keyCode) {
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
            pointer--;
            if (pointer < 0) {
                pointer = auspiceList.size() - 1;
            }
        }
        if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
            pointer++;
            if (pointer >= auspiceList.size()) {
                pointer = 0;
            }
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_ENTER || keyCode == KeyEvent.VK_RIGHT) {
            selectedAuspice = auspiceList.get(pointer);
        }
    }
}
