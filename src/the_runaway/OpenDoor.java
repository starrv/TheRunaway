package the_runaway;

import java.awt.*;

public class OpenDoor 
{
	int x,y,w,h;
	
	public OpenDoor(int x, int y, int w, int h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x,y,w,h);
	}

}
