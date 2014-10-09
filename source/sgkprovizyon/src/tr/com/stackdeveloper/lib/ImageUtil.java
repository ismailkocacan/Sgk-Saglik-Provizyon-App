package tr.com.stackdeveloper.lib;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.ImageView;

public class ImageUtil 
{
	 public static void setImageViewWithByteArray(ImageView view, byte[] data) 
	 {
		try 
		{
		  Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
		  if (bitmap == null) return;
		  view.setImageBitmap(bitmap);
		  view.setBackgroundColor(Color.WHITE);
		  view.getLayoutParams().height = bitmap.getHeight();
	      view.getLayoutParams().width  = bitmap.getWidth(); 
		} catch (Exception e) 
		{
			
		}
	 }
}
