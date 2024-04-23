package hero;

import java.util.ArrayList;
import java.util.List;

public class Roster {
    protected List<Hero> playerHeroes;
    protected int minimumLevel = 1;

    public Roster() {
        playerHeroes = new ArrayList<>();
    }

    public int getMinimumLevel() {
        return this.minimumLevel;
    }

    public void incrementMinimumLevel(int toValue) {
        while (minimumLevel < toValue) {
            minimumLevel++;
            for (Hero hero : playerHeroes) {
                while (hero.getLevel() < minimumLevel) {
                    hero.levelUp();
                }
            }
        }
    }

    public void addHero(Hero hero) {
        playerHeroes.add(hero);
    }

    public List<Hero> getHeroes() {
        return playerHeroes;
    }
}
