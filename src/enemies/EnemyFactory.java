package enemies;

import display.SpriteUtils;
import skills.Element;
import skills.SkillFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EnemyFactory {
    public static Enemy getTatzelwyrm() {
        Enemy enemy = new Enemy("Tatzelwyrm", SpriteUtils.getTestSprite("dragon"));
        enemy.setMaxHp(30);
        enemy.setMaxResource(20);
        enemy.setAttack(2);
        enemy.setDefense(4);
        enemy.setSpecialAttack(15);
        enemy.setSpecialDefense(15);
        enemy.setAgility(1);
        enemy.setLuck(15);
        enemy.setElementDefenses(new HashMap<>());
        enemy.getElementDefenses().put(Element.FIRE, 0.5d);
        enemy.setSkillList(List.of(SkillFactory.getBasicAttackForEnemies(Element.FIRE)));
        return enemy;
    }
}
