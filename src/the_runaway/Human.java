package the_runaway;

import java.awt.Color;

public class Human extends Polygon3DModel 
{
	
	public Human(int x, int y, int z, Color color)
	{
		super(x,y,z,color);
	}
	
	public Human(int x, int y, int z, Color color, String text)
	{
		super(x,y,z,color,text);
	}

	@Override
	public double[] getAngles()
	{
		// TODO Auto-generated method stub
		double[] a={0,0,0,0,0,0,0};
		return a;
	}

	@Override
	public int[][] getXCoords()
	{
		// TODO Auto-generated method stub
		width=50;
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
	public int[][] getYCoords()
	{
		// TODO Auto-generated method stub
		height=700;
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
	public int[][] getZCoords() 
	{
		// TODO Auto-generated method stub
		length=3000;
		int[][] coords={{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
		for(int i=0; i<coords.length;i++)
		{
			for(int j=0;j<coords[i].length;j++)
			{
				coords[i][j]*=length;
			}
		}
		return coords;
	}
	
	public boolean hasCollidedWith(Camera c)
	{
		if((c.getX()<(x+25*width)) && (c.getX()>(x-25*width)) && (c.getZ()<(z+10*length)) && (c.getZ()>(z-10
				*length)))
		{
			return true;
		}
		return false;
	}
	
	public boolean hasSuperCollidedWith(Camera c)
	{
		if((c.getX()<(x+6*width)) && (c.getX()>(x-6*width)) && (c.getZ()<(z+length)) && (c.getZ()>(z-length)))
		{
			return true;
		}
		return false;
	}
}
