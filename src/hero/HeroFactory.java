package hero;

import hero.auspices.Auspice;
import skills.Element;
import skills.SkillFactory;

import java.util.Arrays;
import java.util.HashMap;

public class HeroFactory {
    public static Hero getTestHeroForAuspice(String name, Auspice auspice) {
        Hero hero = new Hero(name, auspice, 10);
        hero.setElementDefenses(new HashMap<>());
        hero.getElementDefenses().put(Element.CUT, 0.5d);
        hero.setSkillList(Arrays.asList(SkillFactory.getBasicAttackForAllies(Element.CUT), SkillFactory.getTestSkillForUI("Fan of Knives")));
        return hero;
    }

}
