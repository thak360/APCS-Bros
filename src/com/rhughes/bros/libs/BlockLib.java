package com.rhughes.bros.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import com.rhughes.bros.entities.Entity;
import com.rhughes.bros.entities.Player;
import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.libs.BlockLib;
import com.rhughes.bros.libs.Reference;

public class World {
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<Block> blocks = new ArrayList<Block>();
	
	private int width;
	private int height;
	private int[] pixels;
	private Map<Integer, Sprite> blockMap=BlockLib.getBlockMap();
	int color = 4;
	
	public World(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(Reference.SPRITE_LOCATION + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		width = image.getWidth();
		height = image.getHeight();
		pixels = image.getRGB(0, 0, width, height, pixels, 0, width);
		for(int i = 0; i < 4000; i++) {
				if(blockMap.get(color) != null) {      //change first parameter for different color blocks
					blocks.add(new Block(blockMap.get(color),i+500,500));
				}
			}
		for(int j = 100; j>=0; j--)
		{
			if(blockMap.get(color) != null) {      //change first parameter for different color blocks
				blocks.add(new Block(blockMap.get(color),500,500-j));
			}
		}
		for(int j = 100; j>=0; j--)
		{
			if(blockMap.get(color) != null) {      //change first parameter for different color blocks
				blocks.add(new Block(blockMap.get(color),4500,500-j));
			}
		}
	}
	
	public void render(Graphics g) {
		for(Block block : blocks)
			block.render(g);
		for(Entity ent : entities)
			ent.render(g);
	}
	
	public void tick() {
		for(Entity ent : entities)
			ent.tick();
	}
	
	public void addEntity (Entity ent) {
		entities.add(ent);
	}
	
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	
	public void removeEntity(int index) {
		entities.remove(index);
	}
	
	public Player getPlayer() {
		for (Entity ent : entities)
			if(ent instanceof Player)
				return (Player)ent;
		return null;
	}

}
