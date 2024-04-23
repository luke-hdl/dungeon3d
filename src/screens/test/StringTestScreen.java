package screens.test;

import global.DisplayGlobal;
import screens.Screen;

import java.awt.*;

public class StringTestScreen extends Screen {
    protected String testString;

    public StringTestScreen(String testString) {
        this.testString = testString;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        DisplayGlobal.getTerminal().write(testString, sx, sy, Color.WHITE);
    }

    @Override
    public void processInput(int keyCode) {
        //lol. lmao.
    }
}
