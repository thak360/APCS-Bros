package com.rhughes.bros.world;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rhughes.bros.gfx.Sprite;

public class Block {
	
	private int x, y;
	private Sprite sprite;

	public Block(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		sprite.render(g, x, y);
	}
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }

    public Rectangle getRectangle() {
        return new Rectangle(x, y, 32, 32);
    }

}
