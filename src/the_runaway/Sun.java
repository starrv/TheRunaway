package the_runaway;

import java.awt.Color;
import java.awt.Graphics;

public class Sun extends Polygon3DModel
{
	
	public Sun(int x, int y, int z,Color color)
	{
		super(x,y,z,color);
	}
	

	@Override
	public double[] getAngles() {
		// TODO Auto-generated method stub
		double[] a={0};
		return a;
	}

	@Override
	public int[][] getXCoords()
	{
		// TODO Auto-generated method stub
		int[][] coords={{-1,-2,-2,-1,1,2,2,1}};
		for(int i=0; i<coords.length;i++)
		{
			for(int j=0;j<coords[i].length;j++)
			{
				coords[i][j]=194000*coords[i][j];
			}
		}
		return coords;
	}

	@Override
	public int[][] getYCoords() 
	{
		// TODO Auto-generated method stub
		int[][] coords={{-2,-1,1,2,2,1,-1,-2}};
		for(int i=0; i<coords.length;i++)
		{
			for(int j=0;j<coords[i].length;j++)
			{
				coords[i][j]=194000*coords[i][j];
			}
		}
		return coords;
	}

	@Override
	public int[][] getZCoords() 
	{
		// TODO Auto-generated method stub
		int[][] coords={{1,1,1,1,1,1,1,1}};
		for(int i=0; i<coords.length;i++)
		{
			for(int j=0;j<coords[i].length;j++)
			{
				coords[i][j]=194000*coords[i][j];
			}
		}
		return coords;
	}
}
