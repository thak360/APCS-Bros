package com.rhughes.bros.world;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rhughes.bros.gfx.Sprite;

public class Block {
	
	private int x, y;
	private Sprite sprite;
	
	/* @param sprite the 1 pixel image representing th block
	@param x, y the location of the block */
	public Block(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}
	
	public int getX()
	{return x;}
	
	public int getY()
	{return y;}
	
	/* renders the block */
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
