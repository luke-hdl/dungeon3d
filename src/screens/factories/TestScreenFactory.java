package screens.factories;

import combatants.Battle;
import display.SpriteUtils;
import dungeons.MoveTypeTag;
import dungeons.World;
import enemies.Enemy;
import enemies.EnemyFactory;
import explore.DungeonMover;
import explore.ai.DungeonCirclingAI;
import global.PlayerStateGlobal;
import hero.Hero;
import hero.HeroFactory;
import hero.Party;
import hero.auspices.AuspiceManager;
import io.dungeons.WorldLoader;
import screens.Screen;
import screens.battle.BattleScreen;
import screens.battle.menus.ScreenListMenu;
import screens.create.CreateHeroScreen;
import screens.display.HeroDetailScreen;
import screens.display.RosterInspectScreen;
import screens.entry.StringEntryScreen;
import screens.explore.CrawlUiScreen;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class TestScreenFactory {

    public static ScreenListMenu getTestScreenListScreen() {
        List<String> labels = new LinkedList<>();
        List<Supplier<Screen>> suppliers = new LinkedList<>();

        labels.add("String Entry Test");
        suppliers.add(TestScreenFactory::getTestStringEntryScreen);
        labels.add("Dungeon Crawl Test");
        suppliers.add(TestScreenFactory::getTestCrawlUiScreen);
        labels.add("Battle Test");
        suppliers.add(TestScreenFactory::getTestBattleScreen);
        labels.add("Create Hero Test");
        suppliers.add(TestScreenFactory::getCreateHeroTestScreen);
        labels.add("Hero Stat Test");
        suppliers.add(TestScreenFactory::getTestPartyMemberDetailScreen);
        labels.add("Roster Inspect Test");
        suppliers.add(TestScreenFactory::getRosterInspectScreen);

        return new ScreenListMenu(suppliers, labels);
    }

    public static StringEntryScreen getTestStringEntryScreen() {
        return new StringEntryScreen(10, "enter a string lmao");
    }

    public static RosterInspectScreen getRosterInspectScreen() {
        Party party = new Party();

        party.getHeroes()[0][0] = HeroFactory.getTestHeroForAuspice("Sixer", AuspiceManager.getHare());
        party.getHeroes()[1][0] = HeroFactory.getTestHeroForAuspice("Buzz", AuspiceManager.getFly());
        party.getHeroes()[2][0] = HeroFactory.getTestHeroForAuspice("James", AuspiceManager.getRat());
        party.getHeroes()[0][1] = HeroFactory.getTestHeroForAuspice("Raf", AuspiceManager.getHyena());
        party.getHeroes()[1][1] = HeroFactory.getTestHeroForAuspice("Toony", AuspiceManager.getTuna());
        party.getHeroes()[2][1] = HeroFactory.getTestHeroForAuspice("Bomber", AuspiceManager.getFalcon());

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                PlayerStateGlobal.getRoster().getHeroes().add(party.getHeroes()[x][y]);
            }
        }

        return new RosterInspectScreen(PlayerStateGlobal.getRoster());
    }

    public static HeroDetailScreen getTestPartyMemberDetailScreen() {
        return new HeroDetailScreen(HeroFactory.getTestHeroForAuspice("James", AuspiceManager.getRat()));
    }

    public static CreateHeroScreen getCreateHeroTestScreen() {
        Function<Hero, Screen> testStringFunction = HeroDetailScreen::new;
        return new CreateHeroScreen(testStringFunction);
    }

    public static CrawlUiScreen getTestCrawlUiScreen() {
        World world = WorldLoader.loadWorld();
        DungeonMover testMover = new DungeonMover(15, 14, 1, 0, world.dungeon, List.of(MoveTypeTag.GROUND), SpriteUtils.getTestSprite());

        Map<Integer, Enemy> map = new HashMap<>();
        map.put(1, EnemyFactory.getTatzelwyrm());
        map.put(3, EnemyFactory.getTatzelwyrm());
        map.put(7, EnemyFactory.getTatzelwyrm());

        testMover.setAi(new DungeonCirclingAI(new int[]{15, 13}, map));
        Party party = new Party();

        party.getHeroes()[0][0] = HeroFactory.getTestHeroForAuspice("Sixer", AuspiceManager.getHare());
        party.getHeroes()[1][0] = HeroFactory.getTestHeroForAuspice("Buzz", AuspiceManager.getFly());
        party.getHeroes()[2][0] = HeroFactory.getTestHeroForAuspice("James", AuspiceManager.getRat());
        party.getHeroes()[0][1] = HeroFactory.getTestHeroForAuspice("Raf", AuspiceManager.getHyena());
        party.getHeroes()[1][1] = HeroFactory.getTestHeroForAuspice("Toony", AuspiceManager.getTuna());
        party.getHeroes()[2][1] = HeroFactory.getTestHeroForAuspice("Bomber", AuspiceManager.getFalcon());

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                PlayerStateGlobal.getRoster().getHeroes().add(party.getHeroes()[x][y]);
            }
        }

        world.dungeon.setParty(party);

        return new CrawlUiScreen(world.dungeon, party);
    }

    public static BattleScreen getTestBattleScreen() {
        Party party = new Party();

        party.getHeroes()[0][0] = HeroFactory.getTestHeroForAuspice("Sixer", AuspiceManager.getHare());
        party.getHeroes()[1][0] = HeroFactory.getTestHeroForAuspice("Buzz", AuspiceManager.getFly());
        party.getHeroes()[2][0] = HeroFactory.getTestHeroForAuspice("James", AuspiceManager.getRat());
        party.getHeroes()[0][1] = HeroFactory.getTestHeroForAuspice("Raf", AuspiceManager.getHyena());
        party.getHeroes()[1][1] = HeroFactory.getTestHeroForAuspice("Toony", AuspiceManager.getTuna());
        party.getHeroes()[2][1] = HeroFactory.getTestHeroForAuspice("Bomber", AuspiceManager.getFalcon());

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 2; y++) {
                PlayerStateGlobal.getRoster().getHeroes().add(party.getHeroes()[x][y]);
            }
        }

        Map<Integer, Enemy> map = new HashMap<>();
        map.put(1, EnemyFactory.getTatzelwyrm());
        map.put(3, EnemyFactory.getTatzelwyrm());
        map.put(7, EnemyFactory.getTatzelwyrm());

        return new BattleScreen(new Battle(map, party, () -> {

        }));
    }
}
