package screens.test;

import display.Sprite;
import global.DisplayGlobal;
import screens.Screen;

import java.awt.*;

public class SpriteTestScreen extends Screen {
    protected Sprite sprite;

    public SpriteTestScreen(Sprite sprite) {
        this.sprite = sprite;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        for (int x = sx; x < mx; x++) {
            int ox = x - sx;
            if (ox >= sprite.getWidth()) {
                break;
            }
            for (int y = sy; y < my; y++) {
                int oy = y - sy;

                if (oy >= sprite.getHeight()) {
                    break;
                }

                char glyph = sprite.getGlyph(ox, oy);
                Color color = sprite.getColor(ox, oy);

                DisplayGlobal.getTerminal().write(glyph, x, y, color);
            }
        }
    }

    @Override
    public void processInput(int keyCode) {

    }
}
