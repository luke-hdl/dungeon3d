package io.dungeons;

import dungeons.events.DungeonEvent;

public class DungeonEventFactory {
    public static DungeonEvent get(String eventCode){
        return new DungeonEvent(eventCode);
    }
}
