package com.rhughes.bros.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.rhughes.bros.entities.Entity;
import com.rhughes.bros.entities.Player;
import com.rhughes.bros.libs.BlockLib;
import com.rhughes.bros.libs.Reference;

public class World {
	
	ArrayList<Entity> entities = new ArrayList<Entity>();
	ArrayList<Block> blocks = new ArrayList<Block>();
	
	private int width;
	private int height;
	private int[] pixels;
	
	public World(String path) {
		imageRead(path);
	}
	
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
		for(int i = 0; i< width; i ++){
			for(int j =0; j<height;j++){
				if(pixels[i+j*width]==pixels[359])
					blocks.add(new Block(blockMap.get(1), i*32,j*32));
				if(pixels[i+j*width]==pixels[440])
					blocks.add(new Block(blockMap.get(3), i*32,j*32));
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
