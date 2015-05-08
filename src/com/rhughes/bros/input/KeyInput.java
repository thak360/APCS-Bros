package com.rhughes.bros.input;

// _KeyInput_ serves as the key listener for the game

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.rhughes.bros.Game;
import com.rhughes.bros.entities.Player;
import com.rhughes.bros.libs.ObjectIDs;

public class KeyInput extends KeyAdapter {
	
	private static boolean[] keys = new boolean[256];
	
	// called every time a key is pressed, this will make the key in "keys" true while down
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		keys[key] = true;
	}
	
	// called every time a key is released, this will return keys[key] to false when not down
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		keys[key] = false;
	}
	
	// we can check which keys are down with every iteration through the game loop (tick) 
			//by checking for the keys we need
	public static boolean getKey(int key) {
		return keys[key];
	}
	
}
