package the_runaway;
import java.awt.event.*;
import java.util.Random;
import java.util.StringTokenizer;
import java.awt.*;

import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TheRunaway extends Game
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color gameBackground2=new Color(255, 232, 44);
	Color gameBackground=new Color(255, 255, 204);
	Color titleBackground=new Color(0, 0, 102);
	Color foreground=new Color(255, 249, 254);
	Title title=new Title("The Runaway", foreground, titleBackground);
	int wait=2000;
	PreGameBackground preGame=new PreGameBackground(gameBackground);
	boolean UP=false;
	boolean DN=false;
	boolean LT=false;
	boolean RT=false;
	boolean SHIFT=false;
	String where="outdoors";
	Cube[] cubes=new Cube[16];
	String winningHouse;
	Human[] humans=new Human[cubes.length/2];
	Sun sun;
	boolean drawString=false;
	boolean drawString2=false;
	boolean drawString3=false;
	String drawName;
	boolean ENTER=false;
	boolean drawIntro=true;
	Wall wall,rightWall;
	Angela Angela;
	Cube theWinningHouse;
	
	public TheRunaway()
	{
		super.start=System.nanoTime()/1000000000;
		super.timeLimit=(60*cubes.length)/6;
	}
	
	public void paint(Graphics g)
	{
		//System.out.println("paint called");
		if(window!=null)
		{
			if(!started && preGameDraw==true)
			{
				preGame();
				
			}
			else if(!finished)
			{
				if(where.equalsIgnoreCase("outdoors"))
				{
					outdoors();
				}
				else if(!where.equalsIgnoreCase(winningHouse))
				{
					house1a();
				}
				else
				{
					winningHouse();
				}
				g.setColor(Color.black);
				g.setFont(new Font(("Arial"),Font.ITALIC,20));
				g.drawString(Long.toString(current-start), origin_x, origin_y);
			}
			else
			{
				postGame();
			}
		}
		
	}
	@Override
	public void preGame() 
	{
		// TODO Auto-generated method stub
			preGameDraw=false;
			Graphics g=window.getGraphics();
			preGame.draw(g,0,0,width,height);
			title.draw(g,origin_x,origin_y,width,height);
			title.changeTitle("CopyRight 2015");
			title.draw(g,origin_x,origin_y,width, height);	
			title.changeFontSize(45);
			title.changeTitle("All Rights Reserved");
			title.draw(g,origin_x,origin_y,width, height);	
			title.changeTitle("Click to start");
			title.draw(g,origin_x,origin_y,width, height);
	}
	@Override
	public void run()
	{
		if(numOfGames==0 && !started)
		{
			started=true;
			numOfGames++;
			System.out.println(numOfGames+" game started");
			playMusic("TheRunaway.wav");
			System.out.println("Music started");
			Graphics g=window.getGraphics();
			preview(g);
			start=System.nanoTime()/1000000000;
			while(started && !finished)
			{
				while(!finished)
				{
					mainGame();
				}
				if(finished)break;
			}
			System.out.println("game ended");
			postGame();
			repaint();
		}		
		else
		{
			System.out.println("Game already started cant start new one");
		}
	}
	private void preview(Graphics g)
	{
		outdoors(g);
		g.setColor(Color.black);
		g.setFont(new Font("Arial",Font.ITALIC,45));
		g.drawString("Where is Angela!!", width/10, origin_y);
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "An error has occurred: "+e.getMessage());
			e.printStackTrace();
		}
		g.drawString("Please find her!!  You got "+timeLimit+" seconds!!",width/10, origin_y+(width/10));
		try {
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "An error has occurred: "+e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void respondToUserInput() 
	{
		// TODO Auto-generated method stub
		System.out.println("Location: ("+camera.getX()+","+camera.getY()+","+camera.getZ()+")");
		
		
		if(UP)
		{
			if(SHIFT)camera.riseUpBy(15);	
			else 
			{
				if(camera.getZ()<((32000*(cubes.length+1)+5000)) && camera.getZ()>(-5000) && camera.getX()<(70000) && camera.getX()>(-70000))
				{
					camera.moveForwardBy(1000);
					wall.moveForwardBy(1000);
					rightWall.moveForwardBy(1000);
					Angela.moveForwardBy(1000);
				}
				else
				{
					drawString=true;
					rotateSlowlyRightBy(180);
					camera.moveForwardBy(1000);
					wall.moveForwardBy(1000);
					rightWall.moveForwardBy(1000);
					Angela.moveForwardBy(1000);
					drawString=false;
				}
			}
			
			repaint();
		}	
		if(DN)
		{
			if(SHIFT)camera.dropDownBy(15);			
			repaint();
		}	
		if(LT)
		{				
			camera.rotateLeftBy(15);
			repaint();
		}	
		if(RT)
		{
			camera.rotateRightBy(15);
			repaint();
		}
	}

	@Override
	public void respondToCollisions() 
	{
		// TODO Auto-generated method stub
		if(where=="outdoors")
		{
			for(int i=0;i<cubes.length;i++)
			{
				if(cubes[i].hasCollidedWith(camera))
				{
					
						where=cubes[i].getText();
						repaint();
				}
			}
		}
		for(int i=0;i<humans.length;i++)
		{
			if(humans[i].hasCollidedWith(camera))
			{
				//System.out.println("collided with human");
				if(ENTER==true)
				{
					drawString3=true;
					drawName=humans[i].getText();
					repaint();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					drawString3=false;
					camera.moveBackwardBy(1500);
					wall.moveBackwardBy(1500);
					rightWall.moveBackwardBy(1500);
					Angela.moveBackwardBy(1500);
					repaint();
				}
				else if(humans[i].hasSuperCollidedWith(camera))
				{
					camera.moveBackwardBy(1500);
					wall.moveBackwardBy(1500);
					rightWall.moveBackwardBy(1500);
					Angela.moveBackwardBy(1500);
					repaint();
				}
			}
		}
	}	

	@Override
	public void postGame()
	{
		// TODO Auto-generated method stub
		System.out.println("post game");
		if(lost)
		{
			System.out.println("You lost");
			preGame.draw(g,0,0,width,height);
			title.changeFontSize(36);
			title.changeTitle("Times Up!!  You Lost!!");
			title.draw(g,origin_x,origin_y,width,height);
		}
	}
	
	private void initGame()
	{
		for(int i=cubes.length-1;i>=0;i--)
		{
			if(i%2==0)
			{
				cubes[i]=new Cube(64000,Game.origin_y,32000*(i+1),new Color(204, 153, 0),"House "+i);
			}
			else
			{
				cubes[i]=new Cube(-64000,2*Game.origin_y,32000*(i+1),new Color(204, 153, 0),"House "+i);
			}
		}
		Random randomGenerator = new Random();
		theWinningHouse=cubes[randomGenerator.nextInt(cubes.length-1)];
		winningHouse=theWinningHouse.text;
		System.out.println("winning house is "+winningHouse);
		for(int i=humans.length-1;i>=0;i--)
		{
			StringTokenizer tokenizer=new StringTokenizer(winningHouse);
			String houseNumber="";
			while(tokenizer.hasMoreTokens())
			{
				houseNumber=tokenizer.nextToken();
			}
			if(i==0 || i%3==0)
			{
				if(Integer.parseInt(houseNumber)%2==0)
				{
					humans[i]=new Human(-2000,2*Game.origin_y,40000*(i+1),Color.orange, " I think I saw a little girl wondering towards one of the buildings to the left..hmm...");
				}
				else
				{
					humans[i]=new Human(-2000,2*Game.origin_y,40000*(i+1),Color.orange, " I think I saw a little girl wondering towards one of the buildings to the right..hmm...");
				}
			}
			else if(i%2==0)
			{
				
				humans[i]=new Human(-2000,2*Game.origin_y,40000*(i+1),Color.orange);
				if((theWinningHouse.getZ()-humans[i].getZ())>0)
				{
					humans[i].setText(" I saw a strange girl run behind me.  Wonder if she is lost...hmmh...");
				}
				else
				{
					humans[i]=new Human(-2000,2*Game.origin_y,40000*(i+1),Color.orange, " I saw a strange girl run ahead of me.  Wonder if she is lost...hmmh...");
				}
			}
			else
			{
				humans[i]=new Human(-2000,2*Game.origin_y,40000*(i+1),Color.orange, " What a nice sunny day!!");		
			}
		}
		sun=new Sun(-92555,-999500,999990,Color.yellow);
		wall=new Wall(64000,Game.origin_y,120000);
		rightWall=new RightWall((64000-80000),Game.origin_y,(120000-1000));
		Angela=new Angela(64000,Game.origin_y+50000,70000,Color.orange,"Hi I'm Angela");
		
		Thread game=new Thread(this);		
		game.start();	
	}

	
	@Override
	public void keyPressed(KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(event.getKeyCode()==KeyEvent.VK_ENTER && !started)
		{
			initGame();		
		}
		if(event.getKeyCode()==KeyEvent.VK_ENTER && started)
		{
			
			ENTER=true;
			
		}
		if(event.getKeyCode()==KeyEvent.VK_Q) 
		{
			//quit=true;
			exitGame();
		}

		if(event.getKeyCode()==KeyEvent.VK_UP) 	 UP=true;
		if(event.getKeyCode()==KeyEvent.VK_DOWN) DN=true;
		if(event.getKeyCode()==KeyEvent.VK_LEFT) LT=true;
		if(event.getKeyCode()==KeyEvent.VK_RIGHT) RT=true;
		if(event.getKeyCode()==KeyEvent.VK_SHIFT) SHIFT=true;
	}
	
	private void exitGame()
	{
		started=false;
		System.out.println(numOfGames+" games stopped");
		numOfGames--;
		System.out.println(numOfGames+" games playing");
		System.exit(0);
	}

	@Override
	public void keyReleased(KeyEvent event) 
	{
		// TODO Auto-generated method stub
		if(event.getKeyCode()==KeyEvent.VK_UP) UP=false;
		if(event.getKeyCode()==KeyEvent.VK_DOWN) DN=false;
		if(event.getKeyCode()==KeyEvent.VK_LEFT) LT=false;
		if(event.getKeyCode()==KeyEvent.VK_RIGHT) RT=false;
		if(event.getKeyCode()==KeyEvent.VK_SHIFT) SHIFT=false;
		if(event.getKeyCode()==KeyEvent.VK_ENTER && started)
		{
			
			ENTER=false;
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private void drawClouds()
	{
		g.setColor(Color.white);
		for(int i=100;i<width;i+=20)
		{
			for(int j=100;j<25;j+=5)
			{
				g.fillOval((d*i)/2, (d*j)/2, 20, 5);
			}
		}
	}
	
	private void drawClouds(Graphics g)
	{
		g.setColor(Color.white);
		for(int i=100;i<width;i+=20)
		{
			for(int j=100;j<200;j+=5)
			{
				g.fillOval((d*i)/2, (d*j)/2, 20, 5);
			}
		}
	}
	
	public void outdoors(Graphics g)
	{
		g.setColor(Color.cyan);
		g.fillRect(0,0,width,height);
		//Fence.draw(g);
		Ground.draw(g);
		for(int i=cubes.length-1; i>=0;i--)
		{
			if(cubes[i]!=null)
			{
				cubes[i].draw(g);
			}
		}
		for(int i=humans.length-1;i>=0;i--)
		{
			if(humans[i]!=null)
			{
				humans[i].draw(g);
			}
		}
		sun.draw(g);
		if(drawString)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Arial",Font.BOLD,30));
			g.drawString("WHERE DO YOU THINK YOU'RE GOING!! YOU CAN'T LEAVE TOWN UNTIL YOU FIND HER!!",0,(int)(0.5*Game.origin_y));
		}
		if(drawString2)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Arial",Font.BOLD,40));
			g.drawString("WATCH WERE YOU GOING!! YOU COLLIDING WITH THE BUILDINGS",0,(int)(0.5*Game.origin_y));
		}
		if(drawString3)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Times New Romans",Font.BOLD,20));
			g.drawString("HOWDY ",0,(int)(0.5*Game.origin_y));
		}
	}
	
	public void outdoors()
	{
		g.setColor(Color.cyan);
		g.fillRect(0,0,width,height);
		//Fence.draw(g);
		Ground.draw(g);
		sun.draw(g);
		for(int i=cubes.length-1; i>=0;i--)
		{
			if(cubes[i]!=null)
			{
				cubes[i].draw(g);
			}
		}
		for(int i=humans.length-1;i>=0;i--)
		{
			if(humans[i]!=null)
			{
				humans[i].draw(g);
			}
		}
		if(drawString)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Times New Romans",Font.BOLD,30));
			g.drawString("WHERE DO YOU THINK YOU'RE GOING!! YOU CANT LEAVE TOWN UNTIL YOU FIND HER!!",0,(int)(0.5*Game.origin_y));
		}
		else if(drawString2)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Times New Romans",Font.BOLD,40));
			g.drawString("WATCH WERE YOU GOING!! YOU COLLIDING WITH THE BUILDINGS!!",0,(int)(0.5*Game.origin_y));
		}
		else if(drawString3)
		{
			g.setColor(Color.black);
			g.setFont(new Font("Arial",Font.ITALIC,20));
			g.drawString(drawName,0,(int)(0.25*Game.origin_y));
		}
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent event) 
	{
		// TODO Auto-generated method stub
		if(!started)
		{
			initGame();
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void rotateSlowlyRightBy(double angle)
	{
		double angle2=15;
		for(int i=0; i<angle/angle2;i++)
		{
			camera.rotateRightBy(angle2);
			wall.rotateRightBy(angle2);
			rightWall.rotateRightBy(angle2);
			Angela.rotateRightBy(angle2);
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void house1a()
	{
		g.setColor(Color.black);
		g.fillRect(0,0,width,height);
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.ITALIC,20));
		wall.draw(g);
		rightWall.draw(g);
		g.setColor(Color.white);
		g.setFont(new Font("Arial",Font.ITALIC,20));
		//g.drawString("Welcome to House 1", origin_x, origin_y);
		g.drawString("Looks like she isn't here", origin_x, origin_y+20);
		
	}
	
	public void house1b()
	{
		g.setColor(Color.black);
		g.fillRect(0,0,width,height);
		wall.draw(g);
		drawIntro=true;
		//where="outdoors";
	}
	@Override
	public void artificialIntelligence()
	{
		// TODO Auto-generated method stub
	//	System.out.println("where is "+where);
	//	System.out.println("draw Intro is"+drawIntro);
		if(!where.equalsIgnoreCase("outdoors") && !where.equalsIgnoreCase(winningHouse))
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			where="outdoors";
			camera.moveBackwardBy(50000);
		}			
		repaint();
	}
	
	public void winningHouse()
	{
			g.setColor(Color.black);
			g.fillRect(0, 0, width, height);
			wall.draw(g);
			rightWall.draw(g);
			Angela.draw(g);
			g.setColor(Color.white);
			g.setFont(new Font("Arial",Font.ITALIC,20));
			g.drawString("You Win!! You found her in time!!",origin_x,origin_y/2);
			finished=true;
	}
	@Override
	public void idleGame() {
		// TODO Auto-generated method stub
		
	}
	
}