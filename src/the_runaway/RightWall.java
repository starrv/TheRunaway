package the_runaway;

import java.awt.Color;
import java.awt.Graphics;

public class RightWall extends Wall
{
	public RightWall(int x, int y, int z) {
		super(x, y, z);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double[] getAngles() {
		// TODO Auto-generated method stub
		double[] a={225};
		return a;
	}
}
