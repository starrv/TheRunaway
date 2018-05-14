package the_runaway;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ground
{
	static int d=Game.d;
	public static void draw(Graphics g)
	{
		g.setColor(Color.green);
		double radians2=TheRunaway.camera.getAngle2()*(Math.PI/180);
		for(int z=Game.d; z<20000; z++)
		{
			int z1=z;
			int z2=z1;
			int y=Game.origin_y;
			int y1=Game.origin_y;
			
			z1=(int)(-z2*Math.cos(radians2)+y1*Math.sin(radians2));
			if(z1<=0)
			{
				//System.out.println("Ground z1 is "+z1);
				break;
			}			
			y = (int)(-z2*Math.sin(radians2)-y1*Math.cos(radians2));
			g.drawLine(((d*-8*Game.width)/z1)+Game.origin_x,((d*y)/z1)+y1,((d*8*Game.width)/z1)+Game.origin_x,((d*y)/z1)+y1);	
		//	System.out.println("draw ground "+z);
		}	
		for(int z=-Game.d; z<Game.d; z++)
		{
			int z1=z;
			int z2=z1;
			int y=Game.origin_y;
			int y1=Game.origin_y;
			
			z1=(int)(-z2*Math.cos(radians2)+y1*Math.sin(radians2));
			if(z1>0)
			{
				y = (int)(-z2*Math.sin(radians2)-y1*Math.cos(radians2));
				g.drawLine(((d*-8*Game.width)/z1)+Game.origin_x,((d*y)/z1)+y1,((d*8*Game.width)/z1)+Game.origin_x,((d*y)/z1)+y1);
				g.drawLine(((d*-8*Game.width)/z1)+Game.origin_x,((d*y)/z1+1)+y1,((d*8*Game.width)/z1)+Game.origin_x,((d*y)/z1+1)+y1);	
			}			
			//System.out.println("Ground z2 is "+z1);
		}
	}

}
