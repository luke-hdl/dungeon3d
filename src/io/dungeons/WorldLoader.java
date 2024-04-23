package io.dungeons;

import dungeons.World;
import io.shared.FileTokenizer;

import java.io.File;

public class WorldLoader {
    public static World loadWorld() {
        World world = new World();
        world.dungeon = DungeonLoader.loadDungeon(FileTokenizer.tokenizeLines("DEF", FileTokenizer.loadFileToStrings(new File("maps/testBoxMap"))));
        return world;
    }
}
