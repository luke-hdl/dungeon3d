package screens.explore;

import dungeons.Dungeon;
import dungeons.MoveTypeTag;
import explore.DungeonMover;
import hero.Party;
import screens.Screen;
import screens.shared.SeperatorScreen;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CrawlUiScreen extends Screen {
    Screen crawlZoneScreen;
    Screen seperatorScreen;
    Screen partyMemberScreen;
    Screen minimapScreen;

    Party party;

    public CrawlUiScreen(Dungeon dungeon, Party party) {
        this.party = party;
        partyMemberScreen = new PartyMemberColumnScreen(party);
        DungeonMover hero = new DungeonMover(3, 4, 0, -1, dungeon, List.of(MoveTypeTag.GROUND), null);
        crawlZoneScreen = new CrawlZoneScreen(dungeon, hero, this);
        seperatorScreen = new SeperatorScreen('#', Color.WHITE);
        minimapScreen = new MinimapScreen(dungeon, hero.getViewpoint());
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        int partyZoneX = sx + 8;
        int sepZoneX = sx + 9;
        int crawlZoneX = sx + 10;
        minimapScreen.displayOutput(sx, sy, sx + 9, sy + 9);
        partyMemberScreen.displayOutput(sx, sy + 11, partyZoneX, my);
        seperatorScreen.displayOutput(sepZoneX, sy, sepZoneX+1, my);
        seperatorScreen.displayOutput(sx, sy+9, partyZoneX+1, sy+10);
        crawlZoneScreen.displayOutput(crawlZoneX, sy, mx, my);
    }

    @Override
    public void processInput(int keyCode) {
        crawlZoneScreen.processInput(keyCode);
    }
}
