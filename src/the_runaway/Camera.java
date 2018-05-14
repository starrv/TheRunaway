package the_runaway;

import java.awt.Color;
import java.awt.Graphics;

public class Camera
{
	int x;
	int y;
	int z;
	double angle=0;
	double angle2=180;

	public Camera(int x, int y, int z) 
	{
		this.x=x;
		this.y=y;
		this.z=z;
		//System.out.println("Angle is "+angle);
		//System.out.println("Camera X is "+x);
		//System.out.println("Camera Y is "+y);
	//	System.out.println("Camera Z is "+z);
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
	//context.getContextResolver();
	public void moveForwardBy(int dz)
	{
		double radians=angle*((Math.PI/180));
		z+=(int)(dz*Math.cos(radians));
		x+=(int)(dz*Math.sin(radians));
	//	System.out.println("Angle is "+angle);	
	//	System.out.println("X is "+x);
	//	System.out.println("Z is "+z);
	}
	public void moveBackwardBy(int dz)
	{
		double radians=angle*((Math.PI/180));
		z-=(int)(dz*Math.cos(radians));
		x-=(int)(dz*Math.sin(radians));
	//	System.out.println("Angle is "+angle);	
	//	System.out.println("X is "+x);
	//	System.out.println("Z is "+z);
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
		if(angle2>90)
		{
			angle2-=da;
			if(angle2<0)angle2+=360;
			//System.out.println("New Angle2 is "+angle2);
		}	
	}
	public void riseUpBy(double da)
	{
		if(angle2<270)
		{
			angle2+=da;
			if(angle2>360)angle2-=360;
		//	System.out.println("Angle 2 is :"+angle2);
		}
	}
	
	public void rotateLeftBy(double da)
	{
		angle-=da;
		if(angle<0)angle+=360;
		//System.out.println("New Angle is "+angle);
	}
	public void rotateRightBy(double da)
	{
		angle+=da;
		if(angle>360)angle-=360;
		//System.out.println("New Angle is "+angle);
	}
	
	public double getAngle()
	{
		return angle;
	}
	
	
	public double getAngle2()
	{
		return angle2;
	}
	
}
