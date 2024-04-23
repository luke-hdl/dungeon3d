package screens.battle;

import combatants.Battle;
import exceptions.PartyWipedException;
import exceptions.PartyWonException;
import main.Main;
import screens.Screen;
import screens.battle.defeat.PartyWipeScreen;
import screens.battle.victory.PartyWinScreen;
import screens.shared.SeperatorScreen;

import java.awt.*;

public class BattleScreen extends Screen {
    protected Battle battle;
    protected SeperatorScreen seperatorScreen;
    protected BattleEnemyScreen fightScreen;
    protected PartyMemberBottomScreen squadScreen;
    protected BattleSkillMenuScreen skillMenuScreen;
    protected CombatMessageScreen combatMessageScreen;

    public BattleScreen(Battle battle) {
        this.battle = battle;
        this.seperatorScreen = new SeperatorScreen('#', Color.WHITE);
        this.fightScreen = new BattleEnemyScreen(battle);
        this.squadScreen = new PartyMemberBottomScreen(battle);
        this.skillMenuScreen = new BattleSkillMenuScreen(battle);
        this.combatMessageScreen = new CombatMessageScreen(battle);
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        if (battle.getCurrentFighter().isPlayerControlled()) {
            squadScreen.displayOutput(sx, my - 13, mx, my);
            seperatorScreen.displayOutput(sx + 15, sy, sx + 16, my - 13);
            fightScreen.displayOutput(sx + 16, sy + 5, mx, my - 13);
            skillMenuScreen.displayOutput(sx, sy, sx + 15, my - 13);
            combatMessageScreen.displayOutput(sx + 16, sy, mx, sy + 5);
        } else {
            squadScreen.displayOutput(sx, my - 13, mx, my);
            fightScreen.displayOutput(sx, sy + 5, mx, my - 13);
            combatMessageScreen.displayOutput(sx, sy, mx, sy + 5);
        }
    }

    @Override
    public void processInput(int keyCode) {
        try {
            if (battle.getCurrentFighter().isPlayerControlled()) {
                if (battle.getSkillBeingAimed() == null) {
                    skillMenuScreen.processInput(keyCode);
                } else {
                    fightScreen.processInput(keyCode);
                }
            } else {
                battle.haveCurrentNpcAct();
                battle.updateCurrentFighter();
            }
        } catch ( PartyWipedException partyWipedException ){
            Main.singleton.screen = new PartyWipeScreen();
        } catch ( PartyWonException partyWonException ){
            battle.runWin();
            Main.singleton.screen = new PartyWinScreen(battle);
        }
    }
}
