package com.rhughes.bros.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.rhughes.bros.Game;
import com.rhughes.bros.enums.GameState;
import com.rhughes.bros.libs.Images;
import com.rhughes.bros.libs.Reference;
import com.rhughes.bros.utils.Button;

public class Menu {
	
	public  Button play, options, quit;
	
	public  Menu() {
		play = new Button(Reference.CENTER_X - 125, 250, 225, 50, GameState.Game, "powerup").setText("Play");
		quit = new Button(Reference.CENTER_X - 125, 370, 225, 50).setText("Quit");
	}
	
	public  void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.drawImage(Images.title, (int)(Reference.CENTER_X - 390), 5, null);
		
		Font liner = new Font("Liner", Font.PLAIN, 32);
		g.setFont(liner);
		
		play.drawButton(g, 50);
		quit.drawButton(g, 62);
	}

}
