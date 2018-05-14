package the_runaway;

import java.awt.Color;
import java.awt.Graphics;

public class PreGameBackground 
{
	Color color;

	public PreGameBackground(Color c)
	{
		color=c;
	}
	
	public void draw(Graphics g, int x, int y, int w, int h)
	{
		g.setColor(color);
		g.fillRect(x,y,w,h);
	}
	
	public void changeBackground(Color color)
	{
		this.color=color;
	}
}
