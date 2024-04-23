package enemies;

import display.Sprite;
import skills.Element;
import skills.Skill;

import java.util.List;
import java.util.Map;

public class Enemy {
    protected Sprite sprite;
    protected String name;
    protected int maxHp;
    protected int maxResource;
    protected int attack;
    protected int defense;
    protected int specialAttack;
    protected int specialDefense;
    protected int agility;
    protected int luck;
    protected Map<Element, Double> elementDefenses;
    protected List<Skill> skillList;


    public Enemy(String name, Sprite sprite) {
        this.name = name;
        this.sprite = sprite;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getMaxResource() {
        return maxResource;
    }

    public void setMaxResource(int maxResource) {
        this.maxResource = maxResource;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecialAttack() {
        return specialAttack;
    }

    public void setSpecialAttack(int specialAttack) {
        this.specialAttack = specialAttack;
    }

    public int getSpecialDefense() {
        return specialDefense;
    }

    public void setSpecialDefense(int specialDefense) {
        this.specialDefense = specialDefense;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getLuck() {
        return luck;
    }

    public void setLuck(int luck) {
        this.luck = luck;
    }

    public Map<Element, Double> getElementDefenses() {
        return elementDefenses;
    }

    public void setElementDefenses(Map<Element, Double> elementDefenses) {
        this.elementDefenses = elementDefenses;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }
}
