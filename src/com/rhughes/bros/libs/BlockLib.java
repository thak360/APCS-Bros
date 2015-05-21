package com.rhughes.bros.libs;

import java.util.HashMap;
import java.util.Map;

import com.rhughes.bros.gfx.Sprite;
import com.rhughes.bros.gfx.SpriteSheet;
import com.rhughes.bros.world.Block;

public class BlockLib {
	
	public static final SpriteSheet sheet = new SpriteSheet("32x32_Spritesheet.png");
	
	public static Map<Integer, Sprite> blockMap = new HashMap<Integer, Sprite>();
	
	//returns a block with the given block id
	public static Block getFromId(int id, int x, int y) {
		if (blockMap.get(id) != null)
			return new Block(blockMap.get(id), x, y);
		return null;
	}
	
	
	
	public static Map<Integer, Sprite> getBlockMap()
	{
		return blockMap;
	}
	
	// adds a block to the "library"
	public static void addBlock(int id, Sprite sprite) {
		blockMap.put(id, sprite);
	}
	
	// returns a Sprite
	public static Sprite blockAt(int x, int y) {
		return new Sprite(x, y, 32, sheet);
	}
}
