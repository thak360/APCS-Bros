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
	
	//returns x position of top left corner
	public int getX()
	{return x;}
	
	//returns y position of top left corner
	public int getY()
	{return y;}
	
	/* renders the block */
	public void render(Graphics g) {
		sprite.render(g, x, y);
	}
	
	//returns rectangle representing the block
	public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
	//returns rectangle representing the block
    public Rectangle getRectangle() {
        return new Rectangle(x, y, 32, 32);
    }

}
