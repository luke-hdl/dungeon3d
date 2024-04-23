package io.dungeons;

import dungeons.DisplayType;
import dungeons.Dungeon;
import dungeons.MoveTypeTag;
import dungeons.Tile;
import dungeons.events.DungeonEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DungeonLoader {

    public static Dungeon loadDungeon(Map<String, List<String>> defs) {
        return new Dungeon(load(defs));
    }

    protected static Tile[][] load(Map<String, List<String>> defs) {
        //Define the dungeon
        int width = Integer.parseInt(defs.get("WIDTH").get(0));
        int height = Integer.parseInt(defs.get("HEIGHT").get(0));

        Tile[][] dungeonTiles = new Tile[width][height];

        //Build maps for event flags and colors
        Map<Character, Color> colorMap = new HashMap<>();
        Map<Character, String> eventCodeMap = new HashMap<>();
        Map<Character, List<MoveTypeTag>> moveTypeTags = new HashMap<>();

        for (String line : defs.get("COLOR_DEFINITIONS")) {
            Color color = Color.decode(line.split(":")[1]);
            colorMap.put(line.split(":")[0].charAt(0), color);
        }
        for (String line : defs.get("EVENT_DEFINITIONS")) {
            String event = line.split(":")[1];
            if (!event.equals("NO_EVENT")) {
                eventCodeMap.put(line.split(":")[0].charAt(0), event);
            }
        }
        for (String line : defs.get("MOVE_TYPE_DEFINITIONS")) {
            Collection<String> tags = line.contains(",") ? Arrays.asList(line.split(":")[1].split(",")) : Collections.singletonList(line.split(":")[1]);
            char glyph = line.split(":")[0].charAt(0);
            moveTypeTags.put(glyph, new LinkedList<>());
            for (String tag : tags) {
                switch (tag) {
                    case "GROUND":
                        moveTypeTags.get(glyph).add(MoveTypeTag.GROUND);
                        break;
                    case "WALL":
                        moveTypeTags.get(glyph).add(MoveTypeTag.WALL);
                        break;
                    case "WATER":
                        moveTypeTags.get(glyph).add(MoveTypeTag.WATER);
                        break;
                }
            }
        }

        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                char glyph = defs.get("MAP").get(x).charAt(y);
                Color color = colorMap.get(defs.get("COLORS").get(x).charAt(y));
                String eventCode = eventCodeMap.get(defs.get("EVENTS").get(x).charAt(y));
                char displayTypeCode = defs.get("DISPLAY_TYPES").get(x).charAt(y);
                DisplayType displayType = displayTypeCode == '#' ? DisplayType.WALL : DisplayType.FLOOR;
                DungeonEvent event = DungeonEventFactory.get(eventCode);
                char moveType = defs.get("MOVE_TYPES").get(x).charAt(y);
                Tile tile = new Tile(glyph, color, event, displayType, moveTypeTags.get(moveType));
                dungeonTiles[y][x] = tile;
            }
        }

        return dungeonTiles;
    }
}
