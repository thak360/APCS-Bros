package com.rhughes.bros.input;

// _MouseInput_ handles all of the mouse input

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.rhughes.bros.Game;
import com.rhughes.bros.enums.GameState;

/**
 * <strong>Project:</strong> CataclysmicBattles <br>
 *
 * <strong>Class:</strong> MouseInput
 *
 * @author <a href = "http://youtube.com/BossLetsPlays"> BossLetsPlays</a>
 *
 */
public class MouseInput extends MouseAdapter {
    
    public static final int  BUTTON_LEFT  = 0x1;
    public static final int  BUTTON_RIGHT = 0x3;

    private static boolean[] buttons      = new boolean[5];
    public static int clickX, clickY, hoverX, hoverY, pressX, pressY;
    
    

    @Override
    /**
     * This method is called whenever a mouse button is clicked
     */
    public void mouseClicked(MouseEvent e) {
        int mouse = e.getButton(); //used to check what button was clicked
        clickX = e.getX();
        clickY = e.getY();
        if((Game.menu.play.contains(clickX, clickY)&&Game.state==GameState.Menu)||((Game.pause.resume.contains(clickX, clickY)&&Game.state==GameState.Pause)))
        	Game.state = GameState.Game;
        else if((Game.gameOver.restart.contains(clickX, clickY)&&Game.state==GameState.GameOver)||((Game.pause.restart.contains(clickX, clickY)&&Game.state==GameState.Pause))||(Game.youWin.restart.contains(clickX, clickY)&&(Game.state==GameState.youWin))){
        	Game.state = GameState.Menu;
        	Game.player.setPosition(50,100);
        }
        else if((Game.gameOver.quit.contains(clickX, clickY)&&Game.state==GameState.GameOver)||(Game.menu.quit.contains(clickX, clickY)&&Game.state==GameState.Menu)||(Game.pause.quit.contains(clickX, clickY)&&Game.state==GameState.Pause)||(Game.youWin.quit.contains(clickX, clickY)&&(Game.state==GameState.youWin)))
        	Game.exit();
        else
        	return;
    }

    @Override
    /**
     * This is called while we have a mouse button held down
     */
    public void mousePressed(MouseEvent e) {
        pressX = e.getX();
        pressY = e.getY();
        buttons[e.getButton()] = true;
    }

    @Override
    /**
     * This is called whenever we release the mouse button
     */
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }
}
