package hero.auspices;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class AuspiceManager {
    protected static Auspice rat;
    protected static Auspice hyena;
    protected static Auspice wolf;
    protected static Auspice cat;
    protected static Auspice hippo;
    protected static Auspice hare;
    protected static Auspice falcon;
    protected static Auspice crocodile;
    protected static Auspice gecko;
    protected static Auspice fly;
    protected static Auspice locust;
    protected static Auspice tuna;

    public static List<Auspice> getAllAuspices() {
        List<Auspice> auspices = new LinkedList<>();
        auspices.add(getRat());
        auspices.add(getHyena());
        auspices.add(getWolf());
        auspices.add(getCat());
        auspices.add(getHippo());
        auspices.add(getHare());
        auspices.add(getFalcon());
        auspices.add(getCrocodile());
        auspices.add(getGecko());
        auspices.add(getFly());
        auspices.add(getLocust());
        auspices.add(getTuna());
        return auspices;
    }

    public static Function<Integer, Integer> getBasicGrowthFunction(int start, double growthRate) {
        return (level) -> (int) (start + level * growthRate);
    }

    public static Auspice getRat() {
        if (rat == null) {
            rat = new Auspice("Rat", "r", Color.WHITE, Color.WHITE, "Veterans of a great war who uses ailments and debilitates to get the upper hand.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return rat;
    }

    public static Auspice getHyena() {
        if (hyena == null) {
            hyena = new Auspice("Hyena", "h", Color.ORANGE, Color.ORANGE, "Devout mage-priests whose magic knows no equal, waxing and waning with the turn cycle.",
                    getBasicGrowthFunction(25, 2.5), getBasicGrowthFunction(30, 2.75),
                    getBasicGrowthFunction(4, 0.75), getBasicGrowthFunction(9, 0.45),
                    getBasicGrowthFunction(9, 0.4), getBasicGrowthFunction(9, 0.5),
                    getBasicGrowthFunction(2, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return hyena;
    }

    public static Auspice getWolf() {
        if (wolf == null) {
            wolf = new Auspice("Wolf", "w", Color.YELLOW, Color.YELLOW, "Mighty warriors, loyal to the Betrayer-Queen, who enchant their claws to destroy the mighty and greedy.",
                    getBasicGrowthFunction(10, 2.5), getBasicGrowthFunction(23, 2.75),
                    getBasicGrowthFunction(4, 0.75), getBasicGrowthFunction(7, 0.45),
                    getBasicGrowthFunction(6, 0.4), getBasicGrowthFunction(7, 0.5),
                    getBasicGrowthFunction(5, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return wolf;
    }

    public static Auspice getCat() {
        if (cat == null) {
            cat = new Auspice("Cat", "c", Color.LIGHT_GRAY, Color.LIGHT_GRAY, "Brave rogues who flirt with death, becoming stronger when their health is low.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return cat;
    }

    public static Auspice getHippo() {
        if (hippo == null) {
            hippo = new Auspice("Hippo", "o", Color.GRAY, Color.GRAY, "Mighty protectors and shields for the vulnerable - and dangerous when angered.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return hippo;
    }

    public static Auspice getHare() {
        if (hare == null) {
            hare = new Auspice("Hare", "b", Color.RED, Color.RED, "Defenders of the weak who have shaken off a cruel king - and will dodge anyone who glances at them.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return hare;
    }

    public static Auspice getFalcon() {
        if (falcon == null) {
            falcon = new Auspice("Falcon", "f", Color.CYAN.darker(), Color.CYAN.darker(), "Fleet warriors, grounded for the sins of the father, who dive to the front and then hide in the back.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return falcon;
    }

    public static Auspice getCrocodile() {
        if (crocodile == null) {
            crocodile = new Auspice("Crocodile", "d", Color.GREEN.darker(), Color.GREEN.darker(), "Omens of death who hit hard - and take advantage of openings other warriors create.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return crocodile;
    }

    public static Auspice getGecko() {
        if (gecko == null) {
            gecko = new Auspice("Gecko", "g", Color.GREEN, Color.GREEN, "Devout healers who will sacrifice their own tail to protect their allies. Not afraid to wield the same holy force against foes.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return gecko;
    }

    public static Auspice getFly() {
        if (fly == null) {
            fly = new Auspice("Fly", "y", Color.CYAN, Color.CYAN, "Loyalists to the cycles of life and death, healing allies while inflicting ailments on foes.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return fly;
    }

    public static Auspice getLocust() {
        if (locust == null) {
            locust = new Auspice("Locust", "l", Color.MAGENTA, Color.MAGENTA, "Explorers who survive on scraps, easing travels in the dungeon and using items to their best potential.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return locust;
    }

    public static Auspice getTuna() {
        if (tuna == null) {
            tuna = new Auspice("Tuna", "t", Color.PINK, Color.PINK, "Bearers of the blessing of the seas, who share that blessing to aid their allies.",
                    getBasicGrowthFunction(15, 2.5), getBasicGrowthFunction(20, 2.75),
                    getBasicGrowthFunction(8, 0.75), getBasicGrowthFunction(5, 0.45),
                    getBasicGrowthFunction(5, 0.4), getBasicGrowthFunction(5, 0.5),
                    getBasicGrowthFunction(8, 0.68), getBasicGrowthFunction(9, 0.75));
        }
        return tuna;
    }

}
