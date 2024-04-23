package global;

import asciiPanel.AsciiPanel;
import screens.Screen;

import static asciiPanel.AsciiFont.CP437_10x10;

public class DisplayGlobal {
    protected static AsciiPanel terminal;
    protected static Screen currentScreen; //a screen can contain sub-screens

    public static AsciiPanel getTerminal() {
        return terminal;
    }

    public static Screen getCurrentScreen() {
        return currentScreen;
    }

    public static void changeScreen(Screen screen) {
        currentScreen = screen;
    }

    public static void initializeTerminal() {
        terminal = new AsciiPanel(100, 60, CP437_10x10);
    }
}
