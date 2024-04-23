package hero;

import display.Sprite;
import hero.auspices.Auspice;
import skills.Element;
import skills.Skill;
import skills.SkillFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Hero {
    protected String name;
    protected Auspice auspice;
    protected int level;
    protected int currentHp;
    protected int maxHp;
    protected int currentResource;
    protected int maxResource;
    protected int attack;
    protected int defense;
    protected int specialAttack;
    protected int specialDefense;
    protected int agility;
    protected int luck;
    protected Map<Element, Double> elementDefenses;
    protected Sprite sprite;
    protected List<Skill> skillList;

    public Hero(String name, Auspice auspice, int startingLevel) {
        this.name = name;
        this.auspice = auspice;
        this.level = startingLevel;
        this.maxHp = auspice.getHpGrowth().apply(level);
        this.currentHp = maxHp;
        this.maxResource = auspice.getResourceGrowth().apply(level);
        this.currentResource = maxResource;
        this.attack = auspice.getAttackGrowth().apply(level);
        this.defense = auspice.getDefenseGrowth().apply(level);
        this.specialAttack = auspice.getSpecialAttackGrowth().apply(level);
        this.specialDefense = auspice.getSpecialDefenseGrowth().apply(level);
        this.agility = auspice.getAgilityGrowth().apply(level);
        this.luck = auspice.getLuckGrowth().apply(level);
        skillList = Arrays.asList(SkillFactory.getBasicAttackForAllies(Element.CUT), SkillFactory.getTestSkillForUI("Extreme Cut"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Auspice getAuspice() {
        return auspice;
    }

    public int getLevel() {
        return level;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxResource() {
        return maxResource;
    }

    public int getCurrentResource() {
        return currentResource;
    }

    public void setCurrentResource(int currentResource) {
        this.currentResource = currentResource;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int maxResource) {
        this.maxResource = maxResource;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public int getAgility() {
        return agility;
    }

    public int getLuck() {
        return luck;
    }

    public Map<Element, Double> getElementDefenses() {
        return elementDefenses;
    }

    public void setElementDefenses(Map<Element, Double> elementDefenses) {
        this.elementDefenses = elementDefenses;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public void levelUp() {
        this.level++;
        this.maxHp = auspice.getHpGrowth().apply(level);
        this.currentHp = maxHp;
        this.maxResource = auspice.getResourceGrowth().apply(level);
        this.currentResource = maxResource;
        this.attack = auspice.getAttackGrowth().apply(level);
        this.defense = auspice.getDefenseGrowth().apply(level);
        this.specialAttack = auspice.getSpecialAttackGrowth().apply(level);
        this.specialDefense = auspice.getSpecialDefenseGrowth().apply(level);
        this.agility = auspice.getAgilityGrowth().apply(level);
        this.luck = auspice.getLuckGrowth().apply(level);
    }
}
