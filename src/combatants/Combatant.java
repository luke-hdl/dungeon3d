package combatants;

import display.Sprite;
import enemies.Enemy;
import hero.Hero;
import skills.CombatSkillResult;
import skills.Element;
import skills.Skill;
import skills.TargetType;

import java.util.List;
import java.util.Map;

public class Combatant {
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
    protected String name;
    protected Sprite sprite;
    protected List<Skill> skillList;
    protected CombatantAI ai;
    protected Battle battle;
    protected Hero hero;

    public Combatant(Hero hero, Battle battle) {
        this.hero = hero;
        this.maxHp = hero.getMaxHp();
        this.currentHp = hero.getCurrentHp();
        this.maxResource = hero.getMaxResource();
        this.currentResource = hero.getCurrentResource();
        this.name = hero.getName();
        this.attack = hero.getAttack();
        this.defense = hero.getDefense();
        this.specialAttack = hero.getSpecialAttack();
        this.specialDefense = hero.getSpecialDefense();
        this.agility = hero.getAgility();
        this.luck = hero.getLuck();
        this.elementDefenses = hero.getElementDefenses();
        this.skillList = hero.getSkillList();
        this.name = hero.getName();
        this.sprite = hero.getSprite();
        this.battle = battle;
    }

    public Combatant(Enemy enemy, Battle battle) {
        this.maxHp = enemy.getMaxHp();
        this.currentHp = enemy.getMaxHp();
        this.maxResource = enemy.getMaxResource();
        this.currentResource = enemy.getMaxResource();
        this.name = enemy.getName();
        this.attack = enemy.getAttack();
        this.defense = enemy.getDefense();
        this.specialAttack = enemy.getSpecialAttack();
        this.specialDefense = enemy.getSpecialDefense();
        this.agility = enemy.getAgility();
        this.luck = enemy.getLuck();
        this.elementDefenses = enemy.getElementDefenses();
        this.skillList = enemy.getSkillList();
        this.name = enemy.getName();
        this.sprite = enemy.getSprite();
        this.ai = new RandomAI(this);
        this.battle = battle;
    }

    public void applyDamageToHero() {
        if (hero != null) {
            hero.setCurrentHp(getCurrentHp());
            hero.setCurrentResource(getCurrentResource());
        }
    }

    public int getCurrentResource() {
        return currentResource;
    }

    public void setCurrentResource(int currentResource) {
        this.currentResource = currentResource;
    }

    public int getMaxResource() {
        return maxResource;
    }

    public void setMaxResource(int maxResource) {
        this.maxResource = maxResource;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void die() {
        battle.alertOfDeath(this);
    }

    public CombatSkillResult takeDamage(int damage, Element element) {
        this.currentHp -= damage;
        if (this.currentHp < 0) {
            die();
            return new CombatSkillResult(name + " took " + damage + " damage and was struck down!", element);
        } else {
            return new CombatSkillResult(name + " took " + damage + " damage!", element);
        }
    }

    public List<Skill> getSkills() {
        return skillList;
    }

    public boolean isPlayerControlled() {
        return ai == null;
    }

    public Skill getSkillToUse() {
        return ai == null ? null : ai.decideAction();
    }

    public Map<TargetType, List<Combatant>> getTargetsForSkill(Skill action) {
        return ai == null ? null : ai.decideTargets(action);
    }
}
