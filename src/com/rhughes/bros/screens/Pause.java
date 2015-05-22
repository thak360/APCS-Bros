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
	
	public  Button resume,quit,restart;
	
	public  Pause() {
		quit = new Button(Reference.CENTER_X - 112, 370, 225, 50).setText("Quit");
		restart = new Button(Reference.CENTER_X - 112, 150, 225, 50).setText("Menu");
		resume=new Button(Reference.CENTER_X-112,295,225,50, GameState.Menu, "powerup").setText("Home");
	}
	
	public  void render(Graphics g){
		resume.drawButton(g, 47);
		quit.drawButton(g, 62);
		restart.drawButton(g, 47);
	}

}
