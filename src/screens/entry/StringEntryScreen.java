package screens.entry;

import global.DisplayGlobal;
import screens.Screen;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StringEntryScreen extends Screen {
    protected int maxStringLength;
    protected String explanation;
    protected String currentString = "";
    protected boolean caps = false;
    protected boolean shift = false;

    public StringEntryScreen(int maxStringLength, String explanation) {
        this.maxStringLength = maxStringLength;
        this.explanation = explanation;
    }

    @Override
    public void displayOutput(int sx, int sy, int mx, int my) {
        int middleY = (my - sy) / 2;
        int entryStartX = sx + (mx - sx - maxStringLength) / 2;
        int messageStartX = sx + (mx - sx - explanation.length()) / 2;
        DisplayGlobal.getTerminal().write(explanation, messageStartX, middleY - 1, Color.WHITE);


        DisplayGlobal.getTerminal().write(currentString, entryStartX, middleY, Color.WHITE);
        if (currentString.length() < maxStringLength) {
            int currentPointer = entryStartX + currentString.length();
            DisplayGlobal.getTerminal().write('#', currentPointer, middleY, Color.YELLOW);
        }

        DisplayGlobal.getTerminal().write(currentString.length() + "/" + maxStringLength, messageStartX, middleY + 1, Color.WHITE);
    }

    @Override
    public void processInput(int keyCode) {
        if (keyCode == KeyEvent.VK_BACK_SPACE && currentString.length() > 0) {
            currentString = currentString.substring(0, currentString.length() - 1);
        } else if (keyCode == KeyEvent.VK_CAPS_LOCK) {
            caps = !caps;
        } else if (keyCode == KeyEvent.VK_SHIFT) {
            caps = !caps;
            if (caps) {
                shift = true;
            }
        } else {
            String letter = "" + (char) keyCode;
            if (currentString.length() < maxStringLength && letter.matches("[A-Za-z]")) {
                if (caps) {
                    letter = letter.toUpperCase();
                    if (shift) {
                        shift = false;
                        caps = false;
                    }
                } else {
                    letter = letter.toLowerCase();
                }
                currentString += letter;
            }
        }
    }

    public void clearString() {
        currentString = "";
    }

    public String getCurrentString() {
        return currentString;
    }
}
