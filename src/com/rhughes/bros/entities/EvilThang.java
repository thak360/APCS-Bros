package com.rhughes.bros.entities;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import com.rhughes.bros.enums.Direction;
import com.rhughes.bros.gfx.Animation;
import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.gfx.SpriteSheet;
import com.rhughes.bros.input.KeyInput;
import com.rhughes.bros.world.Block;
import com.rhughes.bros.world.World;

public class EvilThang extends Mob{
	
	private static SpriteSheet sheet = new SpriteSheet("Enemy.png");

	public EvilThang(int x, int y, World world) {
		super(x, y, world);
		sprite = new Sprite(2, 1, 50, sheet);
		sprite2 = new Sprite(1, 1, 50, sheet);
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
		animeRight = new Animation(5, rights);
		animeLeft = new Animation(5, lefts);
	}

	@Override
	public boolean hasHorizontalCollision() {
			for(int i = 0; i < world.getBlocks().size(); i ++) {
				Block block = world.getBlocks().get(i);
				if(getLeft().intersects(block.getRectangle())){
					setPosition(x + 1, y);
					return true;
				}
				if(getRight().intersects(block.getRectangle())){
					setPosition(x - 1, y);
					return true;
				}
			}
			return false;
	}

	public Rectangle getRectangle() {
        return new Rectangle(x, y, 32, 50);
    }
	
	@Override
	public boolean hasVerticalCollision() {
		for(int i = 0; i < world.getBlocks().size(); i++) {
			Block block = world.getBlocks().get(i);
			if(getTop().intersects(block.getRectangle())){
				setPosition(x, y + 1);
				dy=0;
				return true;
			}
			if(getBottom().intersects(block.getRectangle())){
				setPosition(x, y - 1);
				dy=0;
				falling=false;
			}
		}
		return false;
	}
	
	public void tick() {
		if(direction == Direction.Right)
			dx = 2;
		if(direction == Direction.Left)
			dx = -2;
		if(hasHorizontalCollision()){
			if(direction == Direction.Right){
				direction = Direction.Left;
			}
			else{
				direction=Direction.Right;
			}
		}
		super.tick();
	}

	@Override
    public Rectangle getTop() {
        return new Rectangle(x + 16, y + 4, 12, 4);
    }

    @Override
    public Rectangle getBottom() {
        return new Rectangle(x + 16, y + 46, 12, 4);
    }

    @Override
    public Rectangle getRight() {
        return new Rectangle(x + 37, y + 4, 4, 40);
    }

    @Override
    public Rectangle getLeft() {
        return new Rectangle(x + 10, y + 8, 4, 40);
    }

}
