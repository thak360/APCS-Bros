package com.rhughes.bros.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.rhughes.bros.Game;
import com.rhughes.bros.enums.GameState;

@SuppressWarnings("serial")
public class Button extends Rectangle {

    private String    text, sound;
    private GameState state;
    private boolean  pressed;
    private int hoverX, hoverY, clickX, clickY, pressX, pressY;

    /**
     * Creates a button from a specified top-left corner and a specified width and height
     * @param x The far-left x-value of the button
     * @param y The highest y-value of the button
     * @param width The width of the button
     * @param height The height of the button
     */
    public Button(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public Button(int x, int y, int width, int height, GameState state, String sound) {
        this(x, y, width, height);
        this.state = state;
        this.sound = sound;
    }

    /**
     * Sets the text to be displayed on the button
     * @param text the text displayed on the button
     * @return this
     */
    public Button setText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Utility method to facilitate the drawing of rectangles for our buttons
     * @param g the Graphics context of our <strong> <code> Game class </strong> </code>
     * @param offset  The horizontal offset determines how to far or near to the left of the button to start drawing the rectangle
     */
    public void drawButton(Graphics g, int offset) {
        int xx = x + offset;
        int yy = y + 38;

        if (this.contains(hoverX, hoverY))  //show our buttons in cyan when our mouse hovers over them
            g.setColor(Color.cyan);
        else  //other wise, show the button in white
            g.setColor(Color.WHITE);

        if (!pressed && this.contains(pressX, pressY)) g.drawRect(x, y, width, height);
        else if (pressed && this.contains(pressX, pressY)) { //fills in the button when we press it
            g.fillRect(x, y, width, height);
        }
        else g.drawRect(x, y, width, height);

        g.setColor(Color.cyan);
        g.drawString(text, xx, yy);
    }
    
    public void clickButton() {
    	Game.state = state;
    	pressed = true;
    	AudioPlayer.playSoundEffect(sound);
    }

}
