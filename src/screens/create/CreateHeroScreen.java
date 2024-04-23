package screens.create;

import global.PlayerStateGlobal;
import hero.Hero;
import main.Main;
import screens.Screen;
import screens.entry.StringEntryScreen;
import screens.shared.SeperatorScreen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.function.Function;

public class CreateHeroScreen extends Screen {
    protected HeroAuspiceSelectionScreen heroAuspiceSelectionScreen;
    protected StringEntryScreen nameScreen;
    protected SeperatorScreen seperatorScreen;
    protected SeperatorScreen indicatorScreen;
    Function<Hero, Screen> heroScreenFunction;

    public CreateHeroScreen(Function<Hero, Screen> heroScreenFunction) {
        heroAuspiceSelectionScreen = new HeroAuspiceSelectionScreen();
        nameScreen = new StringEntryScreen(7, "Enter a name.");
        seperatorScreen = new SeperatorScreen('#', Color.WHITE);
        indicatorScreen = new SeperatorScreen('^', Color.YELLOW);
        this.heroScreenFunction = heroScreenFunction;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        heroAuspiceSelectionScreen.displayOutput(sx, sy, sx + 22, my);
        seperatorScreen.displayOutput(sx + 22, sy, sx + 23, my);
        nameScreen.displayOutput(sx + 23, sy, mx, my);
        if (heroAuspiceSelectionScreen.getSelectedAuspice() == null) {
            indicatorScreen.displayOutput(sx, my - 1, sx + 22, my);
        } else {
            indicatorScreen.displayOutput(sx + 23, my - 1, mx, my);
        }
    }

    @Override
    public void processInput(int keyCode) {
        if (heroAuspiceSelectionScreen.getSelectedAuspice() == null) {
            heroAuspiceSelectionScreen.processInput(keyCode);
        } else if (heroAuspiceSelectionScreen.getSelectedAuspice() != null && keyCode == KeyEvent.VK_LEFT) {
            nameScreen.clearString();
            heroAuspiceSelectionScreen.clearAuspice();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            Hero hero = new Hero(nameScreen.getCurrentString(), heroAuspiceSelectionScreen.getSelectedAuspice(), PlayerStateGlobal.getRoster().getMinimumLevel());
            PlayerStateGlobal.getRoster().addHero(hero);
            Main.singleton.screen = heroScreenFunction.apply(hero);
        } else {
            nameScreen.processInput(keyCode);
        }
    }
}
