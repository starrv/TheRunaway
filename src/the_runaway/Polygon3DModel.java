package the_runaway;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public abstract class Polygon3DModel 
{
	int x;
	int y;
	int z;
	int width=5000;
	int height=5000; 
	int length=5000;
	double[] angles=getAngles();
	int[][] points_x=getXCoords();
	int[][] points_y=getYCoords();
	int[][] points_z=getZCoords();
	int d=Game.d;
	Color color;
	String text;
	final int scale=50;
	
	public Polygon3DModel(int x, int y, int z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public Polygon3DModel(int x, int y, int z,String text)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.text=text;
	}
	
	public Polygon3DModel(int x, int y, int z, Color color)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.color=color;
	}
	
	public Polygon3DModel(int x, int y, int z, Color color, String text)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.color=color;
		this.text=text;
	}
	
	public abstract double[] getAngles();
	public abstract int[][] getXCoords();
	public abstract int[][] getYCoords();
	public abstract int[][] getZCoords();
	
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
				
				double radians2=TheRunaway.camera.getAngle()*(Math.PI/180);
				
				xs[i]=(int)(xs2*Math.cos(radians2)-zs2*Math.sin(radians2));
				zs = (int)(zs2*Math.cos(radians2)+xs2*Math.sin(radians2));
				
				xs2=xs[i];
				ys2=ys[i];
				zs2=zs;
				
				radians2=TheRunaway.camera.getAngle2()*(Math.PI/180);
				
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
	
	public void moveLeftBy(double da)
	{
		x-=da;
		
		//for(int i=0;i<angles.length;i++)		angles[i]+=da;
	}
	public void moveRightBy(double da)
	{
		x+=da;

		//for(int i=0;i<angles.length;i++)		angles[i]-=da;
	}
	public void dropDownBy(double da)
	{
		y-=da;
	}
	public void riseUpBy(double da)
	{
		y+=da;
		
	}
	public void rotateLeftBy(double da)
	{
		for(int i=0;i<angles.length;i++)
		{
			angles[i]-=da;
		}
	}
	public void rotateRightBy(double da)
	{
		for(int i=0;i<angles.length;i++)
		{
			angles[i]+=da;
		}
	}
	
	public int getScale()
	{
		return scale;
	}
	
	public boolean hasCollidedWith(Camera c)
	{
		if((c.getX()<(x+width)) && (c.getX()>(x-width)) && (c.getY()<(y+height)) && (c.getY()>(y-height)) && (c.getZ()<(z+length)) &&  (c.getZ()>(z-length)))
		{
			return true;
		}
		return false;
	
	}
	
	//context.getContextResolver();
		public void moveForwardBy(int dz)
		{
			double radians=Game.camera.getAngle()*((Math.PI/180));
			z+=(int)(dz*Math.cos(radians));
			x+=(int)(dz*Math.sin(radians));
		//	System.out.println("Angle is "+angle);	
		//	System.out.println("X is "+x);
		//	System.out.println("Z is "+z);
		}
		public void moveBackwardBy(int dz)
		{
			double radians=Game.camera.getAngle()*((Math.PI/180));
			z-=(int)(dz*Math.cos(radians));
			x-=(int)(dz*Math.sin(radians));
		//	System.out.println("Angle is "+angle);	
		//	System.out.println("X is "+x);
		//	System.out.println("Z is "+z);
		}
		
		public int getZ()
		{
			return z;
		}
		
		public void setText(String text)
		{
			this.text=text;
		}
		
		public String getText()
		{
			return text;
		}
}
