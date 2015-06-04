package com.rhughes.bros.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.rhughes.bros.entities.Coin;
import com.rhughes.bros.entities.Entity;
import com.rhughes.bros.entities.Player;
import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.gfx.SpriteSheet;
import com.rhughes.bros.libs.BlockLib;
import com.rhughes.bros.libs.Reference;

public class World {
	
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public ArrayList<Block> blocks = new ArrayList<Block>();
	public ArrayList<Coin> coins = new ArrayList<Coin>();
	public ArrayList <Entity> deathBin = new ArrayList<Entity>();
	
	private int width;
	private int height;
	private static int[] pixels;
	private Map<Integer, Sprite> blockMap=BlockLib.getBlockMap();
	int color = 2;
	private int finishBlock = 0;
	private boolean doneCount=false;
	
	/* @param path the name and location of the image representing the level */
	public World(String path) {
		imageRead(path);
	}
	
	/* @return an ArrayList holding every block (for ticking the world) */
	public ArrayList<Block> getBlockArray()
	{
		return blocks;
	}
	
	/* reads image in from path, 1 pixel = 1 block */
	public void imageRead(String path)
	{
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(Reference.SPRITE_LOCATION + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, pixels, 0, width);
		for(int i = 0; i < width; i++){
			for(int j =0; j <height; j++){
				if(pixels[i + j*width] == pixels[9 * 70])
				{
					blocks.add(new Block(blockMap.get(1), i*32, j*32));
					if(!doneCount)finishBlock++;
				}
				if(pixels[i + j*width] == pixels[14 * 70])
				{
					blocks.add(new Block(blockMap.get(3), i * 32, j * 32));
					if(!doneCount) finishBlock++;
				}
				if(pixels[i + j*width] == pixels[ 69 + 8*70])
				{
					blocks.add(new Block(blockMap.get(2), i * 32, j * 32));
					doneCount = true;
				}
				if(pixels[i + j*width] == pixels[23])
				{
					Coin c = new Coin(i*32, j*32, this);
					entities.add(c);
					coins.add(c);
				}
			}
		}
	}
	
	/* @return figures out when to stop reading in from the image */
	public int getFinishBlock()
	{
		return finishBlock;
	}
	
	/*finds a block containing x and y*/
	public Block find(int x, int y){
		for (Block block : blocks){
			int x2 = block.getX();
			int y2 = block.getY();
			if (x > x2 && x < x2 + 32)
				if(y > y2 && y < y2 + 32)
					return block;
		}
		return blocks.get(0);
	}
	
	/* @param g the Graphics object that prints to the canvas */
	public void render(Graphics g) {
		for(Block block : blocks)
			block.render(g);
		for(Entity ent : entities)
			ent.render(g);
	}
	
	/* ticks everything in the World */
	public void tick() {
		for(Entity ent : entities)
			ent.tick();
		for(Entity ent : deathBin)
			entities.remove(ent);
		deathBin = new ArrayList<Entity>();
	}
	
	/* @param ent the entity to be put in world */
	public void addEntity (Entity ent) {
		entities.add(ent);
	}
	
	/* @return every entity in the world */
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	/* @return every Block in World */
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	 /* @param ent the entity you want removed -OBSOLETE- */
	public void removeEntity(Entity ent) {
		entities.remove(ent);
	}
	
	/* @return the player */
	public Player getPlayer() {
		for (Entity ent : entities)
			if(ent instanceof Player)
				return (Player)ent;
		return null;
	}

}
