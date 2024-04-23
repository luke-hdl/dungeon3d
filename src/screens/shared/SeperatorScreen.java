package screens.shared;

import global.DisplayGlobal;
import screens.Screen;

import java.awt.*;

public class SeperatorScreen extends Screen {
    protected char glyph;
    protected Color color;

    public SeperatorScreen(char glyph, Color color) {
        this.glyph = glyph;
        this.color = color;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        for ( int x = sx; x < mx; x++){
            for ( int y = sy; y < my; y++){
                DisplayGlobal.getTerminal().write(glyph, x, y, color);
            }
        }
    }

    @Override
    public void processInput(int keyCode) {

    }
}
