package screens.battle.defeat;

import global.DisplayGlobal;
import screens.Screen;

import java.awt.*;

public class PartyWipeScreen extends Screen {
    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        DisplayGlobal.getTerminal().write("YOU DIED :(", sx, sy, Color.WHITE);
    }

    @Override
    public void processInput(int keyCode) {

    }
}
