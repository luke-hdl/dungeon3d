package hero.auspices;

import java.awt.*;
import java.util.function.Function;

public class Auspice {
    protected String name;
    protected String shortCode;
    protected Color nameColor;
    protected Color shortCodeColor;
    protected String description;

    protected Function<Integer, Integer> hpGrowth;
    protected Function<Integer, Integer> resourceGrowth;
    protected Function<Integer, Integer> attackGrowth;
    protected Function<Integer, Integer> defenseGrowth;
    protected Function<Integer, Integer> specialAttackGrowth;
    protected Function<Integer, Integer> specialDefenseGrowth;
    protected Function<Integer, Integer> agilityGrowth;
    protected Function<Integer, Integer> luckGrowth;

    public Auspice(String name, String shortCode, Color nameColor, Color shortCodeColor, String description, Function<Integer, Integer> hpGrowth, Function<Integer, Integer> resourceGrowth, Function<Integer, Integer> attackGrowth, Function<Integer, Integer> defenseGrowth, Function<Integer, Integer> specialAttackGrowth, Function<Integer, Integer> specialDefenseGrowth, Function<Integer, Integer> agilityGrowth, Function<Integer, Integer> luckGrowth) {
        this.name = name;
        this.shortCode = shortCode;
        this.nameColor = nameColor;
        this.shortCodeColor = shortCodeColor;
        this.description = description;
        this.hpGrowth = hpGrowth;
        this.resourceGrowth = resourceGrowth;
        this.attackGrowth = attackGrowth;
        this.defenseGrowth = defenseGrowth;
        this.specialAttackGrowth = specialAttackGrowth;
        this.specialDefenseGrowth = specialDefenseGrowth;
        this.agilityGrowth = agilityGrowth;
        this.luckGrowth = luckGrowth;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getShortCode() {
        return shortCode;
    }

    public Color getNameColor() {
        return nameColor;
    }

    public Color getShortCodeColor() {
        return shortCodeColor;
    }

    public Function<Integer, Integer> getHpGrowth() {
        return hpGrowth;
    }

    public Function<Integer, Integer> getResourceGrowth() {
        return resourceGrowth;
    }

    public Function<Integer, Integer> getAttackGrowth() {
        return attackGrowth;
    }

    public Function<Integer, Integer> getDefenseGrowth() {
        return defenseGrowth;
    }

    public Function<Integer, Integer> getSpecialAttackGrowth() {
        return specialAttackGrowth;
    }

    public Function<Integer, Integer> getSpecialDefenseGrowth() {
        return specialDefenseGrowth;
    }

    public Function<Integer, Integer> getAgilityGrowth() {
        return agilityGrowth;
    }

    public Function<Integer, Integer> getLuckGrowth() {
        return luckGrowth;
    }
}
