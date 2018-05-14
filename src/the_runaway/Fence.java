package the_runaway;

import java.awt.Color;
import java.awt.Graphics;

public class Fence 
{

	static int d=Game.d;
	
	public static void draw(Graphics g)
	{
		g.setColor(Color.orange);
		double radians2=TheRunaway.camera.getAngle2()*(Math.PI/180);
		for(int y=Game.origin_x; y<15*Game.origin_y; y++)
		{
			int z=90000;
			int z1=z;
			int y1=y;
			int y2=y;
			z=(int)(-z1*Math.cos(radians2)+y2*Math.sin(radians2));
			if(z<=0)
			{
				System.out.println("Fence z is "+z);
				break;
			}			
			y1 = (int)(-z1*Math.sin(radians2)-y2*Math.cos(radians2));
			g.drawLine(((d*-200*Game.width)/z)+Game.origin_x,((d*y1)/z)+Game.origin_y,200*(((d*8*Game.width)/z))+Game.origin_x,((d*y1)/z)+Game.origin_y);	
			//System.out.println("draw fence "+z);
		}	
	}
}
