package com.rhughes.bros.gfx.window;

import javax.swing.JFrame;

import com.rhughes.bros.Game;
import com.rhughes.bros.input.MouseInput;

public class Window {
	
	private static JFrame frame;
	
	public static void createWindow(Game game, String title) {
		frame = new JFrame(title);
		frame.add(game);
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.addMouseListener(new MouseInput());
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.pack();
	}
	
	public static void setTitle(String title) {
		frame.setTitle(title);
	}

}
