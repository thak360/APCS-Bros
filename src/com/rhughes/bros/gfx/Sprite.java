package com.rhughes.bros.gfx;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sprite {
	
	private int x, y, size;
	private SpriteSheet spriteSheet;
	private BufferedImage image;
	
	public Sprite(int x, int y, int size, SpriteSheet ss) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.spriteSheet = ss;
		image = spriteSheet.getSprite(x, y, size);
	}
	
	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}

}
