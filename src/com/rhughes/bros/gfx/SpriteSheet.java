package com.rhughes.bros.gfx;

// _SpriteSheet_ holds the spritesheet for the game and returns individual sprites

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.rhughes.bros.libs.Reference;

public class SpriteSheet {
	
	private BufferedImage image;
	private String path;
	
	public SpriteSheet(String path) {
		this.path = path;
		load();
	}
	
	private void load() {
		File file = null;
		try {
			file = new File(Reference.SPRITE_LOCATION + path);
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int col, int row, int size) {
		return image.getSubimage((col*size) - size, (row*size) - size, size, size);
	}

}
