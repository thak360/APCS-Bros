package com.rhughes.bros;

// _Game_ runs all the display things for the game and holds that which will be displayed

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import org.lwjgl.openal.AL;

import com.rhughes.bros.entities.Player;
import com.rhughes.bros.enums.GameState;
import com.rhughes.bros.gfx.window.Camera;
import com.rhughes.bros.gfx.window.Window;
import com.rhughes.bros.input.KeyInput;
import com.rhughes.bros.input.MouseInput;
import com.rhughes.bros.screens.GameOver;
import com.rhughes.bros.screens.Menu;
import com.rhughes.bros.screens.Pause;
import com.rhughes.bros.utils.ResourceLoader;
import com.rhughes.bros.world.World;

public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 5891488287201426516L;
	
	public static final int WIDTH = 1024;
	public static final int HEIGHT = WIDTH/16*9;
	public static final String TITLE = "Super APCS Bros.";
	public static GameState state = GameState.Loading;
	private boolean running = false;
	private Thread thread;
	public static Menu menu;
	public static GameOver gameOver;
	private Camera camera;
	private Player player;
	private World world;
	private static Game game;
	public static Pause pause;
	
	public Game() {
		load();
		menu = new Menu();
		pause = new Pause();
		world = new World("Level1.png");
		gameOver = new GameOver();
		player = new Player(50, 100, world);
		MouseInput mouse = new MouseInput();
		this.addMouseListener(mouse);
		this.addMouseMotionListener(mouse);
		camera = new Camera();
		this.addKeyListener(new KeyInput());
	}
	
	// initializes all the needed variables and loads the resources with ResourceLoader
	public void load() {
		ResourceLoader.loadSounds();
		ResourceLoader.loadFonts();
		ResourceLoader.loadImages();
		ResourceLoader.loadBlocks();
	}
	
	// updates the game (60 times per second)
	public void tick() {
		if(state == GameState.Loading){
			state = GameState.Menu;
		}
		if(state == GameState.Game){
			if(world != null) {
				world.tick();
				camera.tick(world.getPlayer());
			}
			if(player.getY()>600)
			{
				state=GameState.GameOver;
			}
		}
		if(state==GameState.GameOver)
		{
			player.setPosition(50, 100);
		}
		if(KeyInput.getKey(KeyEvent.VK_P))
		{
			state=GameState.Pause;
		}
	}
	
	// paints the frame as quick as possible
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.darkGray);
		g2.fillRect(0, 0, WIDTH, HEIGHT);

        ///////////////////////////////////////////////////
        if (state == GameState.Loading) 
        	g.fillRect(0, 0, WIDTH, HEIGHT);
        else if(state == GameState.Menu) {
        	menu.render(g);
        }
        else if(state==GameState.GameOver){
        	gameOver.render(g);
        }
        else if(state==GameState.Pause)
        	pause.render(g);
        //render splashscreen
        else if (world != null && state == GameState.Game) {
            //renderBackground(g);
            g2.translate(camera.getX(), camera.getY()); //do this before the foreground and after the background
            world.render(g);
            g2.translate(-camera.getX(), -camera.getY()); //do this after the foreground
            //Hud.render(g, world.getPlayer());
        }

        ///////////////////////////////////////////////////

        g.dispose(); //Disposes our graphics context (if we did not do this, animations would not work properly, it would also eat up a lot of memory
        bs.show(); //Shows whatever graphics were just disposed of

    }

	// handles the actual running of the frame logic
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		final double numTicks = 60.0;
		double n = 1000000000 / numTicks;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		long timer = System.currentTimeMillis();
		
		while (running) {
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / n;
			lastTime = currentTime;
		
			if (delta >= 1) {  
				tick();
				ticks ++;
				delta--;
			}

			render();
			frames++;
					// FPS and update counter that displays in the title
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				Window.setTitle(TITLE + "   |   FPS: " + frames + " Ticks: " + ticks);
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	// makes the frame the game will run in and starts the game
	public static void main(String[]args) {
		game = new Game();
		Window.createWindow(game, TITLE);
		game.start();
	}
	
	// if running, does nothing, otherwise starts the game
	private synchronized void start() {
		if(running)
			return;
		else
			running = true;
		
		thread = new Thread(this);
		thread.start();
	}
	
	// kills the game when done
	private synchronized void stop() {
		if(!running)
			return;
		running = false;
		cleanUp();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1); 
	}
	
	private static void cleanUp() {
		AL.destroy();
	}
	
	public static void exit() {
		cleanUp();
		System.exit(0);
	}

}
