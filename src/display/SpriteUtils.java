package display;

import io.shared.FileTokenizer;
import io.sprites.SpriteLoader;

import java.awt.*;
import java.io.File;

public class SpriteUtils {
    public static Sprite fitToBox(int width, int height, Sprite sprite) {
        double shrinkFactorWidth = (double) width / (double) sprite.getWidth();
        double shrinkFactorHeight = (double) height / (double) sprite.getHeight();

        double shrinkFactor = Math.min(shrinkFactorHeight, shrinkFactorWidth);
        height = (int) Math.min(height, sprite.getHeight() * shrinkFactor);
        width = (int) Math.min(width, sprite.getWidth() * shrinkFactor);

        Color[][] newColors = new Color[Math.min(sprite.getWidth(), width)][Math.min(sprite.getHeight(), height)];
        char[][] newGlyphs = new char[Math.min(sprite.getWidth(), width)][Math.min(sprite.getHeight(), height)];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pointX = shrinkFactor >= 1 ? x : (int) (1d / shrinkFactor * x);
                int pointY = shrinkFactor >= 1 ? y : (int) (1d / shrinkFactor * y);
                if (!(pointX >= sprite.getWidth() || pointY >= sprite.getHeight())) {
                    newColors[x][y] = sprite.getColor(pointX, pointY);
                    newGlyphs[x][y] = sprite.getGlyph(pointX, pointY);
                }
            }
        }
        return new Sprite(newGlyphs, newColors);
    }

    public static Sprite getTestSprite() {
        return SpriteLoader.load(FileTokenizer.tokenizeLines("DEF", FileTokenizer.loadFileToStrings(new File("sprites/dragon"))));
    }

    public static Sprite getTestSprite(String pick) {
        return SpriteLoader.load(FileTokenizer.tokenizeLines("DEF", FileTokenizer.loadFileToStrings(new File("sprites/testSprites/" + pick))));
    }
}
