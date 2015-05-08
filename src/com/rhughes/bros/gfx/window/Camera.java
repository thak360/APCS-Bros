package com.rhughes.bros.gfx.window;

import com.rhughes.bros.Game;
import com.rhughes.bros.entities.Player;

public class Camera {

    private float  x = 0, y = 0;

    
     // Updates the camera's x value so it can follow the player
     // Algorithm used: Tweening = this.x += (targetX - this.x) * tweanAmount
     
    public void tick(Player player) {
    		x += ((-player.getX() + Game.WIDTH/2) - x) * 0.0275f;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}
