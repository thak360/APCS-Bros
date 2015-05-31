package com.rhughes.bros.utils;

import java.io.IOException;

import com.rhughes.bros.libs.Audio;
import com.rhughes.bros.libs.BlockLib;
import com.rhughes.bros.libs.Fonts;
import com.rhughes.bros.libs.Images;
import com.rhughes.bros.libs.ObjectIDs;
import com.rhughes.bros.libs.Reference;
import com.rhughes.bros.world.Block;

public class ResourceLoader {
	
	private static BufferedImageLoader loader = new BufferedImageLoader();
	
	public static void loadImages() {
		try {
			Images.title = loader.loadImage("title.png");
			Images.spritesheet = loader.loadImage("32x32_Spritesheet.png");
			Images.gameOver = loader.loadImage("gameOver.png");
			Images.enemy = loader.loadImage("Enemy.png");
			Images.youWin = loader.loadImage("youWin.png");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadFonts() {
		Fonts.addFont(new Fonts("LINER.TTF"));
	}
	
	public static void loadSounds() {
		AudioPlayer.addSound(Audio.POWERUP, "powerup.ogg");
		AudioPlayer.addMusic(Audio.BACKGROUND_1, "background1.ogg");
	}
	
	public static void loadBlocks() {
		BlockLib.addBlock(1, BlockLib.blockAt(1, 1));
		BlockLib.addBlock(2, BlockLib.blockAt(2, 1));
		BlockLib.addBlock(3, BlockLib.blockAt(3, 1));
		BlockLib.addBlock(4, BlockLib.blockAt(4, 1));
	}

}
