package com.rhughes.bros.entities;

import java.awt.Graphics;

import com.rhughes.bros.enums.Direction;
import com.rhughes.bros.gfx.Animation;
import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.world.World;

// Any non object being i.e. enemies, players, Finn
// sprite2 must be the mob standing still facing right

public abstract class Mob extends Entity {
	
	protected float dx, dy, gravity = 0.5f;
	protected int maxdy = 7;
	protected Direction direction;
	protected boolean falling = true, jumping, moving;
	protected Animation animeLeft, animeRight;
	protected Sprite sprite2;

	public Mob(int x, int y, World world) {
		super(x, y, world);
	}
	
	public void tick() {
					// if moving left/right update "direction" to reflect that
		if(dx > 0)
			direction = Direction.Right;
		else if(dx < 0)
			direction = Direction.Left;
					// if it isn't hitting anything, update position (move)
		if(!hasHorizontalCollision())
			x += dx;
		falling = true;
		if(!hasVerticalCollision() || falling || jumping) {
			y += dy;
		}
					
		fall();
					// if moving side to side, make sure "moving" is true
		if(dx != 0)
			moving = true;
		else
			moving = false;
	}
	
	@Override
	public void render(Graphics g) {
		if(moving) 
			getAnimation().runAnimation(g, x, y);
		else
			getStandingStill().render(g, x, y);
	}
	
	public abstract boolean hasHorizontalCollision();
	public abstract boolean hasVerticalCollision();
	
	// updates "dy" to reflect gravity, but not faster than a certain  speed
	protected void fall() {
		if(falling) {
			dy += gravity;
			if(dy > maxdy)
				dy = maxdy;
		}
	}
	
	// if moving right, returns animeRight, else returns animeLeft
	public Animation getAnimation() {
		if(direction == Direction.Right)
			return animeRight;
		return animeLeft;
	}
	
	// returns the sprite facing the correct direction standing still
	public Sprite getStandingStill() {
		return direction == Direction.Left ? sprite : sprite2;
	}
	
	public boolean isMoving() { return moving; }
	public Direction getDirection() { return direction; }

}
