package com.rhughes.bros.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.rhughes.bros.gfx.Animation;
import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.world.World;
import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.gfx.SpriteSheet;

public class Coin extends Entity
{
	private static SpriteSheet sheet = new SpriteSheet("coin.png");
	private Animation anime;
	
	public Coin(int x, int y, World world)
	{
		super(x, y, world);
		Sprite[] spins = new Sprite[] 
			{
				new Sprite(1, 1, 32, sheet),
				new Sprite(2, 1, 32, sheet),
				new Sprite(3, 1, 32, sheet),
				new Sprite(4, 1, 32, sheet),
				new Sprite(5, 1, 32, sheet),
				new Sprite(6, 1, 32, sheet),
				new Sprite(7, 1, 32, sheet),
				new Sprite(8, 1, 32, sheet),
			};
		anime = new Animation(4, spins);
	}
	
	public void tick()
		{}
	
	public void render(Graphics g)
	{
		anime.runAnimation(g, x, y);
	}
	
	public  Rectangle getTop()
	{
		return new Rectangle(x + 6, y, 18, 4);
	}
	
	public Rectangle getBottom()
	{
		return new Rectangle(x + 6, y + 28, 18, 4);
	}
	
	public Rectangle getLeft()
	{
		return new Rectangle(x, y + 6, 4, 20);
	}
	
	public Rectangle getRight()
	{
		return new Rectangle(x + 26, y + 6, 20, 4);
	}
	
}
