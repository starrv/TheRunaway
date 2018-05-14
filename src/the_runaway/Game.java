package the_runaway;
import java.awt.*;

import javax.swing.*;
import javax.sound.sampled.*;

import java.awt.event.*;
import java.awt.image.VolatileImage;
import java.io.File;
import java.net.URL;

public abstract class Game extends JFrame implements Runnable, KeyListener, WindowListener, MouseListener
{
	long start;
	long current;
	int timeLimit;
	static int numOfGames=0;
	GraphicsEnvironment environment=GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice device=environment.getDefaultScreenDevice();
	boolean started=false;
	boolean finished=false;
	static int origin_x=100;
	static int origin_y=100;
	static int d=400;
	static int width=2000;
	static int height=2000;
	Window window;
	Image offscreen;
	Graphics g;
	static Camera camera;
	boolean lost=false;	
	boolean preGameDraw=true;
	
	public Game()
	{
		URL url=getClass().getClassLoader().getResource("TheRunaway.png");
		System.out.println(url);
		ImageIcon icon=new ImageIcon(getClass().getClassLoader().getResource("TheRunaway.png"));
		setIconImage(icon.getImage());	
	}
	
	public final void startGame()
	{
		addKeyListener(this);
		addMouseListener(this);
		setAutoRequestFocus(true);
		setFocusable(true);
		try
		{
			setUndecorated(true);
		}
		catch(IllegalComponentStateException icste){System.exit(0);}
		setResizable(false);
		device.setFullScreenWindow(this);
		origin_x=(device.getFullScreenWindow().getWidth())/2;
		origin_y=(device.getFullScreenWindow().getHeight())/2;
		window=device.getFullScreenWindow();
		width=window.getWidth();
		System.out.println("width: "+width);
		height=window.getHeight();
		System.out.println("height: "+height);
		offscreen = createImage(width, height);
		g=offscreen.getGraphics();
		camera=new Camera(0,Game.origin_y,0);	
	}
	
	public abstract void run();	
	public abstract void preGame();
	public abstract void respondToUserInput();
	public abstract void respondToCollisions();
	public abstract void idleGame();
	public abstract void artificialIntelligence();
	
	public void mainGame()
	{
		current=(System.nanoTime()/1000000000);
		if(current-start>timeLimit)
		{
			finished=true;
			lost=true;
		}
		if(finished)return;
		respondToUserInput();
		respondToCollisions();
		artificialIntelligence();
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public abstract void postGame();
	
	@Override
	public void update(Graphics g)
	{
		paint(g);
		window.getGraphics().drawImage(offscreen,0,0,this);
	}
	
	@Override
	public void repaint()
	{
		update(g);
	}
	
	
	public static void playMusic(String file)
	{
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Game.class.getClassLoader().getResource(file));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch(Exception ex) {
			
			ex.printStackTrace();
		}
	}
	
	public static void playSound(String file)
	{
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} 
		catch(Exception ex) 
		{

			ex.printStackTrace();
		}
	}
	
	
}
