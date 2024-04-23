package display;

import java.awt.*;

public class Sprite {
    protected char[][] glyphs;
    protected Color[][] foregrounds;

    public Sprite(char[][] glyphs, Color[][] foregrounds) {
        this.glyphs = glyphs;
        this.foregrounds = foregrounds;
    }

    public char getGlyph(int x, int y) {
        return glyphs[x][y];
    }

    public Color getColor(int x, int y) {
        return foregrounds[x][y];
    }

    public int getWidth() {
        return glyphs.length;
    }

    public int getHeight() {
        return glyphs[0].length;
    }

    public Sprite darker() {
        Color[][] newForegrounds = new Color[foregrounds.length][foregrounds[0].length];
        for (int x = 0; x < foregrounds.length; x++) {
            for (int y = 0; y < foregrounds[0].length; y++) {
                newForegrounds[x][y] = foregrounds[x][y].darker();
            }
        }

        return new Sprite(glyphs, newForegrounds);
    }
}
