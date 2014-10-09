package tr.com.stackdeveloper.sgkprovizyon;

import android.content.Context;
import tr.com.stackdeveloper.lib.ToastHelper;
import com.google.android.gms.ads.AdListener;


public class MyAdListener extends AdListener 
{
	private Context context;

	public MyAdListener(Context context) 
	{
		this.context = context;
	}

	@Override
	public void onAdClosed() 
	{
		//ToastHelper.showSuccessToast(context, "onAdClosed");
	}

	public void onAdOpened() 
	{
		//ToastHelper.showSuccessToast(context, "onAdOpened");
	};

	@Override
	public void onAdLoaded() 
	{
		//ToastHelper.showSuccessToast(context, "onAdLoaded");
	}

	public void onAdFailedToLoad(int errorCode) 
	{
		//ToastHelper.showFailToast(context, "onAdFailedToLoad");
	};

	@Override
	public void onAdLeftApplication() 
	{
		//ToastHelper.showFailToast(context, "onAdLeftApplication");
	}
}
