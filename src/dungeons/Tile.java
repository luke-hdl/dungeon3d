package dungeons;

import dungeons.events.DungeonEvent;

import java.awt.Color;
import java.util.List;

public class Tile {
    protected char glyph;
    protected Color color;
    protected DungeonEvent event;
    protected DisplayType displayType;

    protected List<MoveTypeTag> traits;

    public Tile(char glyph, Color color, DungeonEvent event, DisplayType displayType, List<MoveTypeTag> moveTypeTags) {
        this.glyph = glyph;
        this.color = color;
        this.event = event;
        this.displayType = displayType;
        this.traits = moveTypeTags;
    }

    public char getGlyph() {
        return glyph;
    }

    public Color getColor() {
        return color;
    }

    public DungeonEvent getEvent() {
        return event;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public List<MoveTypeTag> getTraits() {
        return traits;
    }

    public Tile darker() {
        return new Tile(glyph, color.darker(), event, displayType, traits);
    }
}
