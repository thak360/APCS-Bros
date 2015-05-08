package com.rhughes.bros.gfx;

// Animation handles the animation for any given entity or mob given the images in sequence to run

import java.awt.Graphics;

public class Animation {
	
	private int count;
				// count keeps track of where you are in the animation sequence
	private int index;
				// index acts as a counter to limit speed of animation
	private int frames;
				// frames holds the total number of frames in the animation
	private int speed;
				// speed holds the limit on how many run calls it takes before the picture changes
	private Sprite currentSprite;
	private Sprite[] sprites;
	
	public Animation(int speed, Sprite... sprites) {
		this.speed = speed;
		this.sprites = sprites;
		frames = sprites.length;
		count = 0;
		index = 0;
	}
	
	// handles the speed of the animation for how many times this method is called
	public void runAnimation(Graphics g, int x, int y) {
		index++;
		if(index > speed) {
			index = 0;
			nextFrame();
		}
		drawAnimation(g, x, y);
	}
	
	public void nextFrame() {
														// gets the sprite for the current count
		for(int i = 0; i < frames; i++) {
			if(count == i)
				currentSprite = sprites[i];
		}
														// then updates count for the next go round
		count++;
		if(count > frames)
			count = 0;
	}
	
	// the actual drawing of each individual frame to the screen
	public void drawAnimation(Graphics g, int x, int y) {
		if(currentSprite != null)
			currentSprite.render(g, x, y);
	}

}
