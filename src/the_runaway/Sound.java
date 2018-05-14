package the_runaway;

import java.applet.AudioClip;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;

public class Sound implements AudioClip, Runnable
{
	boolean flag=false;
	SimpleSoundPlayer soundPlayer=null;
    FileInputStream stream = null;
    AudioInputStream audioStream=null;
    
    public Sound(String file)
    {
    	//soundPlayer=new SimpleSoundPlayer("TheRunaway.wav");
    	
        try {
			stream = new FileInputStream("TheRunaway.wav");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	

	@Override
	public void loop() 
	{
		// TODO Auto-generated method stub
		flag=true;
		Thread music=new Thread(this);
		music.start();
		
	}

	@Override
	public void play()
	{
		// TODO Auto-generated method stub
		soundPlayer.play(stream);
	}

	@Override
	public void stop() 
	{
		// TODO Auto-generated method stub
		flag=false;
	}

	@Override
	public void run() 
	{
		while(flag)
		{
			soundPlayer.play(stream);
			System.out.println("flag is "+flag);
		}
		// TODO Auto-generated method stub
		
	}

}
