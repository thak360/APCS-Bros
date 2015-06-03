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
	private int distance;
	private int travelled;

	public EvilThang(int x, int y, World world, int dis) {
		super(x, y, world);
		travelled = 0;
		distance = dis;
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
			return false;
	}

	//returns rectangle representing bounds
	public Rectangle getRectangle() {
        return new Rectangle(x, y, 32, 50);
    }
	
	//returns true if the EvilThang has a collision vertically
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
	
	//moves EvilThang back and forward
	public void tick() {
		if(direction == Direction.Right){
			if (travelled < distance){
				travelled++;
				x += 1;
			}
			else{
				direction = Direction.Left;
				travelled = 0;
			}
			
		}
		else{
			if (travelled < distance){
				travelled++;
				x -= 1;
			}
			else{
				direction = Direction.Right;
				travelled = 0;
			}
		}
		super.tick();
	}

	//returns the rectangle representing the top of the EvilThang
	@Override
    public Rectangle getTop() {
        return new Rectangle(x + 16, y + 4, 12, 4);
    }

	//returns the rectangle representing the bottom of the EvilThang
    @Override
    public Rectangle getBottom() {
        return new Rectangle(x + 16, y + 46, 12, 4);
    }

	//returns the rectangle representing the right of the EvilThang
    @Override
    public Rectangle getRight() {
        return new Rectangle(x + 37, y + 4, 4, 40);
    }

	//returns the rectangle representing the left of the EvilThang
    @Override
    public Rectangle getLeft() {
        return new Rectangle(x + 10, y + 8, 4, 40);
    }

}
