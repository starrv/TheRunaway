package the_runaway;

import java.awt.Color;

import javax.sound.sampled.*;

import java.awt.Font;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Title 
{
	String title;
	int fontSize=60;
	Font font=new Font("Arial",Font.ITALIC,fontSize);
	Color fontColor;
	Color backgroundColor;
	
	public Title(String title, Color fontColor, Color backgroundColor)
	{
		this.title=title;	
		this.fontColor=fontColor;
		this.backgroundColor=backgroundColor;
	}
	
	private void drawTitle(Graphics g, int x, int y)
	{
		String letter;
		int x1=x;
		for(int i=0; i<title.length();i++)
		{
			letter=""+title.charAt(i);
			g.drawString(letter,x1,y);
			x1+=fontSize;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void draw(Graphics g, int x, int y, int w, int h)
	{	
		int w2=w/2;
		int h2=h/2;
		int w4=w2/2;
		g.setColor(backgroundColor);
		g.fillOval(x-w2,y-h2,w,h);
		g.setColor(fontColor);
		g.setFont(font);	
		drawTitle(g,x-w4,y);
	}
	
	public void changeTitle(String title)
	{
		this.title=title;
	}
	
	public void changeFontSize(int fontSize)
	{
		this.fontSize=fontSize;
		font=new Font("Georgia",Font.ITALIC,fontSize);
	}
	
	public void changeFontColor(Color fontColor)
	{
		this.fontColor=fontColor;
	}
	
	public void changeBackgroundColor(Color backgroundColor)
	{
		this.backgroundColor=backgroundColor;
	}
	
	public void draw2(Graphics g, int x, int y, int w, int h)
	{	
		int w2=w/2;
		int h2=h/2;
		g.setColor(backgroundColor);
		g.fillOval(x-w2,y-h2,w,h);
		g.setColor(fontColor);
		g.setFont(font);	
		drawTitle(g,w/10,y);
	}
}
