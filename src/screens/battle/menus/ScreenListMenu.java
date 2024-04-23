package screens.battle.menus;

import global.DisplayGlobal;
import main.Main;
import screens.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Supplier;

public class ScreenListMenu extends Screen {
    protected List<Supplier<Screen>> screens;
    protected List<String> labels;
    protected int pointer;

    public ScreenListMenu(List<Supplier<Screen>> screens, List<String> labels) {
        this.screens = screens;
        this.labels = labels;
    }


    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        for ( int screen = 0; screen < screens.size(); screen++){
            if ( screen == pointer){
                DisplayGlobal.getTerminal().write("> " + labels.get(screen), sx, sy + screen, Color.YELLOW);
            } else {
                DisplayGlobal.getTerminal().write(labels.get(screen), sx, sy + screen, Color.WHITE);
            }
        }
    }


    @Override
    public void processInput(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            pointer--;
            if (pointer < 0) {
                pointer = labels.size() - 1;
            }
        }
        if (keyCode == KeyEvent.VK_S) {
            pointer++;
            if (pointer >= labels.size()) {
                pointer = 0;
            }
        }
        if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_ENTER) {
            Main.singleton.screen = screens.get(pointer).get();
        }
    }
}
