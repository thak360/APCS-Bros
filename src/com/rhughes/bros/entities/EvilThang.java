package com.rhughes.bros.entities;

import java.awt.Rectangle;

import com.rhughes.bros.enums.Direction;
import com.rhughes.bros.gfx.Animation;
import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.gfx.SpriteSheet;
import com.rhughes.bros.world.Block;
import com.rhughes.bros.world.World;

public class EvilThang extends Mob{
	
	private static SpriteSheet sheet = new SpriteSheet("Mex.png");

	public EvilThang(int x, int y, World world) {
		super(x, y, world);
		Sprite[] rights = new Sprite[]
		        {
		                new Sprite(1, 1, 46, sheet),
		                new Sprite(2, 1, 46, sheet),
		                new Sprite(3, 1, 46, sheet),
		                new Sprite(4, 1, 46, sheet)
		                
		        };
		Sprite[] lefts = new Sprite[]
		        {
		                new Sprite(1, 2, 46, sheet),
		                new Sprite(2, 2, 46, sheet),
		                new Sprite(3, 2, 46, sheet),
		                new Sprite(4, 2, 46, sheet)
		                
		        };
		animeLeft = new Animation(4, lefts);
		animeRight = new Animation(4, rights);
	}

	@Override
	public boolean hasHorizontalCollision() {
		for(int i = 0; i < world.getBlocks().size(); i ++) {
			Block block = world.getBlocks().get(i);
			if(getRight().intersects(block.getBounds())) return true;
			if(getLeft().intersects(block.getBounds())) return true;
		}
		return false;
	}

	@Override
	public boolean hasVerticalCollision() {
		for(int i = 0; i < world.getBlocks().size(); i ++) {
			Block block = world.getBlocks().get(i);
			if(getBottom().intersects(block.getBounds()) && dy > 0){
				falling = false;
				jumping = false;
				return true;
			}
			if(getTop().intersects(block.getBounds()) && dy < 0) {
				dy = 0;
				return true;
			}
		}
		return false;
	}
	
	public void tick() {
		dx = 0;
		if (super.getDirection() == Direction.Right)
			dx += 15;
		else
			dx -= 15;
		if(direction == Direction.Left)
			if (world.find(x - 1, y + 47).equals(null))
				dx *= -1;
			else if(world.find(x + 47, y + 47).equals(null))
				dx *= -1;
		else if (hasHorizontalCollision())
			dx *= -1;
		if (dx > 0)
			direction = Direction.Right;
		if (dx < 0)
			direction = Direction.Left;
		if(!hasHorizontalCollision())
			x += dx;
	}

	@Override
	public Rectangle getTop() {
		return new Rectangle(x + 15, y + 3, 12, 4);
	}

	@Override
	public Rectangle getBottom() {
		return new Rectangle(x + 19, y + 42, 10, 4);
	}

	@Override
	public Rectangle getLeft() {
		return new Rectangle(x + 34, y + 3, 4, 43);
	}

	@Override
	public Rectangle getRight() {
		return new Rectangle(x + 5, y + 3, 4, 43);
	}
	
	public void die() {
		world.removeEntity(this);
	}

}
