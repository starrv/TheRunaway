package the_runaway;

import java.awt.*;

public class FrontWall extends Wall
{
	
	public FrontWall(int x, int y, int z) 
	{
		super(x, y, z);
		color=new Color(215, 158, 44);
		// TODO Auto-generated constructor stub
	}
	
	public FrontWall(int x, int y, int z, String text) 
	{
		super(x, y, z);
		this.text=text;
		color=new Color(215, 158, 44);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double[] getAngles() 
	{
		// TODO Auto-generated method stub
		double[] angles={0};
		return angles;
	}

	@Override
	public int[][] getXCoords() 
	{
		// TODO Auto-generated method stub
		int[][] coords= {{Game.width/10, (int)(.9*Game.width),(int)(.9*Game.width), Game.width/10}};		
		return coords;
	}

	@Override
	public int[][] getYCoords() 
	{
		// TODO Auto-generated method stub
		int[][] coords={{Game.height/10, Game.height/10, Game.height/3, Game.height/3}};
		return coords;
	}

	@Override
	public int[][] getZCoords() 
	{
		// TODO Auto-generated method stub
		int[][] coords={{350,350,350,350}};
		return coords;
	}	
	
	/*int ;
	int[] x,y;
	int z;
	Color color=new Color(215, 158, 44);
	String text;
	double angle;
	
	public Wall(int[]x, int[] y, int z,double angle, String text)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.text=text;
		this.angle=angle;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		int d=Game.d;
		int[] x1=new int[x.length];
		int[] y1=new int[y.length];
		double radians=angle*(Math.PI/180);
		for(int i=0; i<x.length;i++)
		{
			if(z!=0)
			{
				x1[i]=(d*x[i])/z;
				//System.out.println(x[i]);
				y1[i]=(d*y[i])/z;
				//System.out.println(y[i]);
				z =(int)( z*Math.cos(radians) - x[i]*Math.sin(radians));
				x[i] = (int)(z*Math.sin(radians) + x[i]*Math.cos(radians));	
			}
		}
		g.fillPolygon(x1,y1,4);
		g.setColor(Color.black);
		g.drawPolygon(x1,y1,4);
		g.setColor(Color.black);
		g.setFont(new Font("Arial",Font.BOLD,55));
		if(z!=0)
		{
			g.drawString(text,(d*x[3])/z,(d*y[3])/z);
		}
		//System.out.println("drew");
	}

	public void setColor(Color color)
	{
		this.color=color;
	}
	
*/
}
