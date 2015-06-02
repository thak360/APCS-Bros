package com.rhughes.bros.screens;

import java.awt.Color;
import java.awt.Graphics;

import com.rhughes.bros.Game;

public class Hud {
	
	public static void render(Graphics g) {
		g.setColor(Color.yellow);
		g.drawString("Score: " + (Game.player.score*100), 50, 50);
	}

}
