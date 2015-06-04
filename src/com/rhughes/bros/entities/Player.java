package com.rhughes.bros.entities;

// _Player_ is an extension of the CoreObject which will be controlled by the player

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.rhughes.bros.Game;
import com.rhughes.bros.enums.GameState;
import com.rhughes.bros.gfx.Animation;
import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.gfx.SpriteSheet;
import com.rhughes.bros.input.KeyInput;
import com.rhughes.bros.world.Block;
import com.rhughes.bros.world.World;

public class Player extends Mob {
	
	private static SpriteSheet sheet = new SpriteSheet("player.png");
	public int score;
	private boolean canJumpAgain;
	private boolean jumped;
	private ArrayList<Block> blocks = Game.world.getBlockArray();
	public ArrayList<Coin> eatenCoins = new ArrayList<Coin>();
	
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
		animeRight = new Animation(5, rights);
		animeLeft = new Animation(5, lefts);
	}
	
	//updates the logic of player (60 times per second)
	public void tick() {
		if(getRectangle().intersects(blocks.get(world.getFinishBlock()).getRectangle()))
		{
			Game.state=GameState.youWin;
		}
		for(int i = Game.world.entities.size() - 1; i >= 0; i--){
			Entity ent = Game.world.entities.get(i);
			if(ent.getRectangle().intersects(getRectangle()) && ent instanceof Coin){
				Game.player.eatCoin(ent);
				Coin c = (Coin)(ent);
				eatenCoins.add(c);
			}
		}
		dx = 0;
		if(KeyInput.getKey(KeyEvent.VK_D)) dx += 4;
		if(KeyInput.getKey(KeyEvent.VK_A)) dx -= 4;
		if(KeyInput.getKey(KeyEvent.VK_W) && !jumped) jump();
		if(!KeyInput.getKey(KeyEvent.VK_W) && !jumped && jumping) {
			canJumpAgain = true;
		}
		super.tick();
	}
	
	// makes the player jump. Note: moving UP in the y direction is negative
	private void jump() {
		if(!jumping){
			jumping = true;
			falling = true;
			dy -= 7;
		}
		if(jumping && canJumpAgain){
			jumped = true;
			dy = -7;
		}
	}
	
	public void setPosition(int x, int y)
	{
		super.setPosition(x, y);
	}
	
	
	@Override
	public boolean hasVerticalCollision() {
		for(int i = 0; i < world.getBlocks().size(); i ++) {
			Block block = world.getBlocks().get(i);
			if(getTop().intersects(block.getRectangle())){
				setPosition(getX(), getY()+1);
				dy=0;
				return true;
			}
			if(getBottom().intersects(block.getRectangle())){
				setPosition(getX(),getY()-1);
				dy=0;
				falling=false;
				jumping=false;
				canJumpAgain = false;
				jumped = false;
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
				if(getLeft().intersects(block.getRectangle())){
					setPosition(getX()+1, getY());
					return true;
				}
				if(getRight().intersects(block.getRectangle())){
					setPosition(getX()-1,getY());
					return true;
				}
			}
			return false;
	}
	// returns 0 if bottom collision, 1 if top collision, and -1 if no collision
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
	
    public void eatCoin(Entity ent){
	if(ent instanceof Coin){
		Coin c = (Coin)(ent);
		c.die();
		if(!eatenCoins.contains(ent)) score++;
		System.out.println(score);
	}
    }
}
