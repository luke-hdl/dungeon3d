package io.sprites;

import display.Sprite;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpriteLoader {
    public static Sprite load(Map<String, List<String>> defs) {
        //Define the dungeon
        int width = Integer.parseInt(defs.get("WIDTH").get(0));
        int height = Integer.parseInt(defs.get("HEIGHT").get(0));

        char[][] letters = new char[width][height];
        Color[][] colors = new Color[width][height];

        //Build maps for event flags and colors
        Map<Character, Color> colorMap = new HashMap<>();

        for (String line : defs.get("COLOR_DEFINITIONS")) {
            Color color = Color.decode(line.split(":")[1]);
            colorMap.put(line.split(":")[0].charAt(0), color);
        }

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                char glyph = ' ';
                Color color = Color.BLACK;
                if ( x < defs.get("SPRITE").size() && y < defs.get("SPRITE").get(x).length()) {
                    glyph = defs.get("SPRITE").get(x).charAt(y);
                    color = colorMap.get(defs.get("COLORS").get(x).charAt(y));
                }
                letters[y][x] = glyph;
                colors[y][x] = color;
            }
        }

        return new Sprite(letters, colors);
    }
}
