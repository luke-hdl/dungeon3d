package main;

import global.DisplayGlobal;
import global.PlayerStateGlobal;
import hero.Roster;
import screens.Screen;
import screens.factories.TestScreenFactory;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {
    private static final long serialVersionUID = 590812034980912348L;
    public Screen screen;
    public static Main singleton;

    public Main() {
        super();
        DisplayGlobal.initializeTerminal();
        add(DisplayGlobal.getTerminal());
        pack();
        initializeGameState();
        screen = TestScreenFactory.getTestScreenListScreen();
        addKeyListener(this);
        repaint();
        Main.singleton = this;
    }

    public void initializeGameState(){
        PlayerStateGlobal.setRoster(new Roster());
    }

    public void repaint() {
        DisplayGlobal.getTerminal().clear();
        screen.displayOutput(0, 0, DisplayGlobal.getTerminal().getWidthInCharacters(), DisplayGlobal.getTerminal().getHeightInCharacters());
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
        screen.processInput(e.getKeyCode());
        repaint();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }
}