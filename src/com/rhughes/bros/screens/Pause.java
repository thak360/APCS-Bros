package com.rhughes.bros.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.rhughes.bros.Game;
import com.rhughes.bros.enums.GameState;
import com.rhughes.bros.libs.Images;
import com.rhughes.bros.libs.Reference;
import com.rhughes.bros.utils.Button;

public class Pause {
	
	public  Button resume, restart, quit;
	
	public  Pause() {
		int fillerY = 220;
		resume = new Button(Reference.CENTER_X - 112, fillerY, 225, 50, GameState.Game, "powerup").setText("Play");
		restart = new Button(Reference.CENTER_X - 125, fillerY += 75, 250, 50).setText("Restart");
		quit = new Button(Reference.CENTER_X - 112	, fillerY += 75, 225, 50).setText("Quit");
	}
	
	public  void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		
		Font liner = new Font("Liner", Font.PLAIN, 32);
		g.setFont(liner);
		
		resume.drawButton(g, 50);
		restart.drawButton(g, 12);
		quit.drawButton(g, 62);
	}

}
