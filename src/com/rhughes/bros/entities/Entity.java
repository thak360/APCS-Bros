package com.rhughes.bros.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.world.World;

// Made to be a skeleton of any in game object
// if it is a mob, sprite must be a sprite of the mob facing left standing still

public abstract class Entity {
	
	protected int x, y;
	protected World world;
	protected Sprite sprite;
	
	public Entity(int x, int y, World world) {
		this.x = x;
		this.y = y;
		this.world = world;
		world.addEntity(this);
	}
	
	// methods to get the bounds of the entity
	public abstract Rectangle getTop();
	public abstract Rectangle getBottom();
	public abstract Rectangle getLeft();
	public abstract Rectangle getRight();
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// render method necessary to print to screen for each class
	public  void render(Graphics g) {
		sprite.render(g, x, y);
	}
	
	// the method to make the entity "do" whatever it is coded to do
	public abstract void tick();
}
