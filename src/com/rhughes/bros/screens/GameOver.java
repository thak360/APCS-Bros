package com.rhughes.bros.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.rhughes.bros.Game;
import com.rhughes.bros.enums.GameState;
import com.rhughes.bros.libs.Images;
import com.rhughes.bros.libs.Reference;
import com.rhughes.bros.utils.Button;

public class GameOver {
	
	public  Button restart,quit;
	
	public  GameOver() {
		quit = new Button(Reference.CENTER_X - 112, 370, 225, 50).setText("Quit");
		restart=new Button(Reference.CENTER_X-112,295,225,50, GameState.Menu, "much wow").setText("Home");
	}
	
	public  void render(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.drawImage(Images.gameOver, (int)(Reference.CENTER_X - 115), 2, null);
		
		Font liner = new Font("Liner", Font.PLAIN, 32);
		g.setFont(liner);
		
		restart.drawButton(g, 47);
		quit.drawButton(g, 62);
	}

}
