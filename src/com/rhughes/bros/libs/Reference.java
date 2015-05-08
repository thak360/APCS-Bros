package com.rhughes.bros.libs;

import com.rhughes.bros.Game;

public class Reference {
	
				// Used to place components on screen, currently used by Menu screen
	public static final int CENTER_X = Game.WIDTH / 2;
	public static final int CENTER_Y = Game.HEIGHT / 2;
	
				// Used by a variety of classes, these give the placement of different resources to load and access
	public static final String RESOURCE_LOCATION = "./res/";
	public static final String SPRITE_LOCATION = RESOURCE_LOCATION + "sprites/";
	public static final String FONT_LOCATION = RESOURCE_LOCATION + "fonts/";
	public static final String SOUND_LOCATION = RESOURCE_LOCATION + "sounds/";
	
				// Used by "ResourceLoader", keep track of how many blocks it needs to load into 
						// BlockLib therefore  iterations through the "for" loops
	public static final int NUM_BLOCKS = 1;
	
				// Used in Player, under "tick", dictates a player's movement speed
	public static int MOVE_SPEED = 6;

}
