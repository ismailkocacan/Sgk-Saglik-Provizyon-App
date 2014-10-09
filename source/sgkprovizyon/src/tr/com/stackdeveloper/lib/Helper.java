package tr.com.stackdeveloper.lib;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Helper 
{
	public static String getParolaSha1(String text) 
	{
		String parolaSha1 = "";
		try 
		{
			parolaSha1 = Sha1Encryption.getSha1(text);
		} catch (NoSuchAlgorithmException e) 
		{
			parolaSha1 = "";
		} catch (UnsupportedEncodingException e) 
		{
			parolaSha1 = "";
		}
		return parolaSha1;
	}

	
	/**
	 * Dip birimindeki deðeri pixcel deðerine çevirmeye yarar.
	 * http://stackoverflow.com/questions/8399184/convert-dip-to-px-in-android
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static float dipToPixels(Context context, float dipValue) 
	{
	    DisplayMetrics metrics = context.getResources().getDisplayMetrics();
	    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dipValue, metrics);
	}
	
	/**
	 * Cihazýn internete baðlý olup,olmadýðý kontrolünü saðlar.
	 * @param context
	 * @return
	 */
	public static boolean isOnline(Context context) 
	{
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;
	    ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	}
	

	/**
	 *  Cihazýn internete baðlý olup,olmadýðý kontrolünü saðlar.
	 *  Eðer internet baðlantýsý yoksa,bir toast mesajý gösterilir.
	 * @param context
	 * @return
	 */
	public static boolean checkConnection(Context context)
	{
		boolean result = isOnline(context);
		if (!result) ToastHelper.showFailToast(context, Constant.CONNECTION_NOT_FOUND);
		return result;
	}
	
	
	/**
	 * Cihazýn internete baðlý olup,olmadýðý kontrolünü saðlar.
	 * Ýnternet baðlantýsý olmamasý durumunda,toast mesajýnýn gösterilip gösterilmeyeceði seçeneði belirlenebilir
	 * @param context
	 * @param 
	 *  showToast true  => toast mesajý gösterilir.
	 *  showToast false => toast mesajý gösterilmez.      
	 * @return
	 */
	public static boolean checkConnection(Context context,Boolean showToast)
	{
		boolean result = isOnline(context);
		if (!result && showToast) ToastHelper.showFailToast(context, Constant.CONNECTION_NOT_FOUND);
		return result;
	}
	
	/**
	 * Linkleri tarayýcýda açmak için kullanýlýr.
	 * @param activity
	 * @param 
	 */
	public static void openLink(Activity activity,String url)
	{
		final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
		activity.startActivity(intent);
	}
}