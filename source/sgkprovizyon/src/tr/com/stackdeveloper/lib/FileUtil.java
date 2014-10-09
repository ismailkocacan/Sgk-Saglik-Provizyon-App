package tr.com.stackdeveloper.lib;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class FileUtil 
{
	public static String getContent(Context context,String fileName) 
	{
		String tContents = "";
		try 
		{
		  InputStream stream = context.getAssets().open(fileName);
		  int size = stream.available();
		  byte[] buffer = new byte[size];
		  stream.read(buffer);
		  stream.close();
		  tContents = new String(buffer);
		} catch (IOException e) 
		{
		  tContents  = e.getMessage();	
		}
		return tContents;
	}
	
	
	
	public static void playSound(Context context,String fileName) 
	{
		AssetFileDescriptor afd = null;
		try 
		{
			afd = context.getAssets().openFd(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    MediaPlayer player = new MediaPlayer();
	    
	    try 
	    {
			player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
		} catch (IllegalArgumentException e) 
		{

		} catch (IllegalStateException e) 
		{

		} catch (IOException e) 
		{

		}
	    
	    try 
	    {
			player.prepare();
		} catch (IllegalStateException e) 
		{	 

		} catch (IOException e) 
		{

		}
	    player.start();
	}
}
