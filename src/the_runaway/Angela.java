package the_runaway;

import java.awt.Color;
import java.awt.Graphics;

public class Angela extends Human
{

	public Angela(int x, int y, int z, Color color, String text) 
	{
		super(x, y, z, color, text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double[] getAngles()
	{
		double[] a={135,135,135,135,135,135,135};
		return a;
	}
	
	@Override
	public int[][] getYCoords()
	{
		// TODO Auto-generated method stub
		height=15000;
		int[][] coords={{-6,-6,-4,-4},{-4,-4,-3,-3},{-3,-3,-1,-1},{-1,-1,1,1},{-1,-1,1,1},{-3,-3,-2,-2},{-3,-3,-2,-2}};
		for(int i=0;i<coords.length;i++)
		{
			for(int j=0;j<coords[i].length;j++)
			{
				coords[i][j]*=height;
			}
		}
		return coords;
	}
	
	@Override
	public int[][] getXCoords()
	{
		// TODO Auto-generated method stub
		width=1000;
		int[][] a={{-2,2,2,-2},{-1,1,1,-1},{-3,3,3,-3},{-2,-1,-1,-2},{1,2,2,1},{-4,-3,-3,-4},{3,4,4,3}};
		for(int i=0;i<a.length;i++)
		{
			for(int j=0;j<a[i].length;j++)
			{
				a[i][j]*=width;
			}
		}
		//System.out.println("width="+width);
		return a;
	}
	
	@Override
	public void draw(Graphics g)
	{
		int[][] listx=new int[points_x.length][points_x[0].length];
		int[][] listy=new int[points_x.length][points_x[0].length];
		for(int poly=0; poly<points_x.length; poly++)
		{
			int[] xs=new int[points_x[poly].length];
			int[] ys=new int[points_y[poly].length];
			double radians=angles[poly]*(Math.PI/180);
			int zs=0;
			for(int i=0;i<points_x[poly].length;i++)
			{
				zs=points_z[poly][i];
				
				ys[i]=points_y[poly][i];
				xs[i]=points_x[poly][i];
				int xs2=xs[i];
				int zs2=zs;
				xs[i]=(int)(xs2*Math.cos(radians)-zs2*Math.sin(radians));
				zs = (int)(zs2*Math.cos(radians)+xs2*Math.sin(radians));
				xs[i]+=x;
				ys[i]+=y;
				zs+=z;
				
				xs[i]=xs[i]-Game.camera.getX();
				ys[i]=ys[i]-Game.camera.getY();	
				zs=zs-Game.camera.getZ();
				
				xs2=xs[i];
				zs2=zs;
				int ys2=ys[i];
				
			/* double radians2=TheRunaway.camera.getAngle()*(Math.PI/180);
				
				xs[i]=(int)(xs2*Math.cos(radians2)-zs2*Math.sin(radians2));
				zs = (int)(zs2*Math.cos(radians2)+xs2*Math.sin(radians2));
				
				xs2=xs[i];
				ys2=ys[i];
				zs2=zs;*/
				
				double radians2=TheRunaway.camera.getAngle2()*(Math.PI/180);
				
				zs=(int)(-zs2*Math.cos(radians2)+ys2*Math.sin(radians2));
				ys[i] = (int)(-zs2*Math.sin(radians2)-ys2*Math.cos(radians2));
				
				if(zs<=0)
				{
					return;
				}		
				xs[i]=((d*xs[i])/(zs))+Game.origin_x;
				ys[i]=((d*ys[i])/(zs))+Game.origin_y;
			}					
			listx[poly]=xs;
			listy[poly]=ys;								
		}
		for(int i=0; i<listx.length;i++)
		{
			g.setColor(color);
			g.fillPolygon(listx[i],listy[i],listx[i].length);
			
		}
	}
}
