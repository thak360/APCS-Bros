package com.rhughes.bros.entities;

// _Player_ is an extension of the CoreObject which will be controlled by the player

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.rhughes.bros.gfx.Animation;
import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.gfx.SpriteSheet;
import com.rhughes.bros.input.KeyInput;
import com.rhughes.bros.world.Block;
import com.rhughes.bros.world.World;

public class Player extends Mob {
	
	private static SpriteSheet sheet = new SpriteSheet("player.png");
	private int score;

	public Player(int x, int y, World world) {
		super(x, y, world);
		score = 0;
		sprite = new Sprite(2, 1, 50, sheet);
		sprite2 = new Sprite(1, 1, 50, sheet);
		Sprite[] rights = new Sprite[]
		        {
		                new Sprite(1, 2, 50, sheet),
		                new Sprite(2, 2, 50, sheet),
		                new Sprite(3, 2, 50, sheet),
		                new Sprite(4, 2, 50, sheet),
		                new Sprite(5, 2, 50, sheet),
		                new Sprite(1, 3, 50, sheet),
		                new Sprite(2, 3, 50, sheet),
		                new Sprite(3, 3, 50, sheet)
		        };
		Sprite[] lefts = new Sprite[]
		        {
		                new Sprite(1, 4, 50, sheet),
		                new Sprite(2, 4, 50, sheet),
		                new Sprite(3, 4, 50, sheet),
		                new Sprite(4, 4, 50, sheet),
		                new Sprite(5, 4, 50, sheet),
		                new Sprite(1, 5, 50, sheet),
		                new Sprite(2, 5, 50, sheet),
		                new Sprite(3, 5, 50, sheet)
		        };
		animeRight = new Animation(3, rights);
		animeLeft = new Animation(3, lefts);
	}
	
	public void tick() {
		dx = 0;
		if(KeyInput.getKey(KeyEvent.VK_D)) dx += 3;
		if(KeyInput.getKey(KeyEvent.VK_A)) dx -= 3;
		if(KeyInput.getKey(KeyEvent.VK_W) && !jumping) {
			jumping = true;
			falling = true;
			dy -= 10;
		}
		super.tick();
	}
	public void setPosition(int x, int y)
	{
		super.setPosition(x, y);
	}

	// returns 0 if bottom collision, 1 if top collision, and -1 if no collision
	@Override
	public boolean hasVerticalCollision() {
		for(int i = 0; i < world.getBlocks().size(); i ++) {
			Block block = world.getBlocks().get(i);
				if(getRectangle().intersects(block.getRectangle())) {
					dy = 0;
					Rectangle inter = getRectangle().intersection(block.getRectangle());
					if(inter.contains(getX(),getY()))
					{
						setPosition(getX(), (int)inter.getMaxY());
						jumping=false;
					}
					else
					{
						setPosition(getX(), (int)inter.getMinY()-50);
						jumping=false;
						falling=false;
					}
					return true;
				}
		}
		return false;
	}

	@Override
	public boolean hasHorizontalCollision() {
		if(KeyInput.getKey(KeyEvent.VK_D)||KeyInput.getKey(KeyEvent.VK_A))
			for(int i = 0; i < world.getBlocks().size(); i ++) {
				Block block = world.getBlocks().get(i);
				if(getRectangle().intersects(block.getRectangle())) 
					{
					Rectangle inter = getRectangle().intersection(block.getRectangle());
					if(inter.contains(getX(), getY()))
						setPosition((int)inter.getMaxX(), getY());
					else
						setPosition((int)inter.getMinX(),getY());
					return true;
					}
			}
		return false;
	}

	public Rectangle getRectangle() {
        return new Rectangle(x, y, 32, 50);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 50, 50);
    }
    
    
	
	public int getScore() {return score;}
	public int getX() {return x;}
	public int getY() {return y;}

		@Override
    public Rectangle getTop() {
        return new Rectangle(x + 16, y + 4, 12, 4);
    }

    @Override
    public Rectangle getBottom() {
        return new Rectangle(x + 16, y + 46, 23, 4);
    }

    @Override
    public Rectangle getRight() {
        return new Rectangle(x + 41, y + 4, 4, 40);
    }

    @Override
    public Rectangle getLeft() {
        return new Rectangle(x + 14, y + 4, 4, 40);
    }
	
}
