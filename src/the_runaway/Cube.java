package the_runaway;

import java.awt.Color;
import java.awt.Graphics;

public class Cube extends Polygon3DModel
{
	
	public Cube(int x, int y, int z,Color color)
	{
		super(x,y,z,color);
	}
	
	public Cube(int x, int y, int z,Color color,String text)
	{
		super(x,y,z,color,text);
	}
	
	public Cube(int x, int y, int z, String text)
	{
		super(x,y,z);
	}

	@Override
	public double[] getAngles() 
	{
		// TODO Auto-generated method stub
		double[] angles= {0,0,0,0,0,0};
		return angles;
	}

	@Override
	public int[][] getXCoords()
	{
		// TODO Auto-generated method stub ,,{-1,1,1,-1}
		int[][] coords={{-1,-1,-1,-1},{-1,1,1,-1},{-1,1,1,-1},{1,1,1,1},{-1,1,1,-1}};
		width=32000;
		for(int i=0; i<coords.length;i++)
		{
			for(int j=0;j<coords[i].length;j++)
			{
				coords[i][j]=width*coords[i][j];
			}
		}
		return coords;
	}

	@Override
	public int[][] getYCoords() {
		// TODO Auto-generated method stub ,{1,1,1,1},{-1,-1,-1,-1}
		int[][] coords={{1,1,-1,-1,},{1,1,-1,-1},{1,1,-1,-1},{1,1,-1,-1,},{1,1,1,1}};
		height=5000;
		for(int i=0; i<coords.length;i++)
		{
			for(int j=0;j<coords[i].length;j++)
			{
				coords[i][j]=height*coords[i][j];
			}
		}
		return coords;
	}

	@Override
	public int[][] getZCoords()
	{
		// TODO Auto-generated method stub ,{-1,-1,1,1},{-1,-1,1,1}
		int[][] coords={{-1,1,1,-1},{1,1,1,1},{-1,-1,-1,-1},{-1,1,1,-1},{-1,-1,1,1}};
		length=3000;
		for(int i=0; i<coords.length;i++)
		{
			for(int j=0;j<coords[i].length;j++)
			{
				coords[i][j]=length*coords[i][j];
			}
		}
		return coords;
	}
}
