package tr.com.stackdeveloper.lib;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Özelleþtirilmiþ toast mesajlarýný gösterilmek üzere tasarlanmýþ sýnýftýr.
 * @author Ýsmail KOCACAN
 *
 */
public class ToastHelper {

	/**
	 * Baþarýlý bir bir toast mesajý gösterilmesini saðlar.
	 * @param activity Activity nesnesi.
	 * @param message Göstrilecek olan mesaj metnidir.
	 */
	public static void showSuccessToast(Activity activity,String message){
		LayoutInflater inflater = activity.getLayoutInflater();
		
        View layout = inflater.inflate(tr.com.stackdeveloper.sgkprovizyon.R.layout.toast_success, (ViewGroup) activity.findViewById(tr.com.stackdeveloper.sgkprovizyon.R.id.toast_message));
        ((TextView) layout.findViewById(tr.com.stackdeveloper.sgkprovizyon.R.id.toast_message)).setText(message);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        //toast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL);
        toast.setMargin(0, 1);
        toast.show();
	}

	/**
	 * Baþarýlý bir bir toast mesajý gösterilmesini saðlar.
	 * @param context Context nesnesi.
	 * @param message Göstrilecek olan mesaj metnidir.
	 */
	public static void showSuccessToast(Context context,String message){
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(tr.com.stackdeveloper.sgkprovizyon.R.layout.toast_success, null);
		TextView toast_message =(TextView)view.findViewById(tr.com.stackdeveloper.sgkprovizyon.R.id.toast_message);
		toast_message.setText(message);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        //toast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL);
        toast.setMargin(0, 1);
        toast.show();
	}
	
	/**
	 * Baþarýsýz bir toast mesajý gösterilmesini saðlar.
	 * @param activity Activity nesnesi.
	 * @param message Göstrilecek olan mesaj metnidir.
	 */
	public static void showFailToast(Activity activity,String message){
		LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(tr.com.stackdeveloper.sgkprovizyon.R.layout.toast_fail, (ViewGroup) activity.findViewById(tr.com.stackdeveloper.sgkprovizyon.R.id.toast_layout_fail));
        ((TextView) layout.findViewById(tr.com.stackdeveloper.sgkprovizyon.R.id.toast_fail_message)).setText(message);
        Toast toast = new Toast(activity.getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        //toast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL);
        toast.setMargin(0, 1);
        toast.show();
	}

	/**
	 * Baþarýsýz bir toast mesajý gösterilmesini saðlar.
	 * @param context Context nesnesi
	 * @param message Göstrilecek olan mesaj metnidir.
	 */
	public static void showFailToast(Context context,String message){
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(tr.com.stackdeveloper.sgkprovizyon.R.layout.toast_fail, null);
		TextView toast_message =(TextView)view.findViewById(tr.com.stackdeveloper.sgkprovizyon.R.id.toast_fail_message);
		toast_message.setText(message);
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        //toast.setGravity(Gravity.CENTER, Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL);
        toast.setMargin(0, 1);
        toast.show();
	}
}
