package screens.battle.victory;

import combatants.Battle;
import global.DisplayGlobal;
import main.Main;
import screens.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PartyWinScreen extends Screen {
    protected Battle battle;

    public PartyWinScreen(Battle battle) {
        this.battle = battle;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        DisplayGlobal.getTerminal().write("YOU WON :)", sx, sy, Color.WHITE);
    }

    @Override
    public void processInput(int keyCode) {
        if (keyCode == KeyEvent.VK_ENTER) {
            Main.singleton.screen = battle.getReturnScreen();
        }
    }
}
