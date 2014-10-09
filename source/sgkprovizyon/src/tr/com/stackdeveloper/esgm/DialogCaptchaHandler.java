package tr.com.stackdeveloper.esgm;

import tr.com.stackdeveloper.lib.ImageUtil;

import tr.com.stackdeveloper.sgkprovizyon.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class DialogCaptchaHandler 
{

	private String mResult;

	private Context mContext;
	
	private AlertDialog mAlertDialog;
	
	private AlertDialog.Builder alertDialogBuilder;
	
	private DialogInterface.OnClickListener mPositiveButtonOnClickListener;
	
	private DialogInterface.OnClickListener mNegativeButtonOnClickListener;
	
	public void setPositiveButtonOnClickListener(DialogInterface.OnClickListener mPositiveButtonOnClickListener) 
	{
		this.mPositiveButtonOnClickListener = mPositiveButtonOnClickListener;
	}

	public void setNegativeButtonOnClickListener(DialogInterface.OnClickListener mNegativeButtonOnClickListener) 
	{
		this.mNegativeButtonOnClickListener = mNegativeButtonOnClickListener;
	}


	public AlertDialog getAlertDialog() 
	{
		return mAlertDialog;
	}

	public String getResult() 
	{
		return mResult;
	}

	
	public DialogCaptchaHandler(Context context)
	{
		mContext = context;
	}
	
	public void show(byte[] base64ImageData)
	{
		LayoutInflater li = LayoutInflater.from(mContext);
		View dialogView = li.inflate(R.layout.dialog_captcha_handler, null);
		alertDialogBuilder = new AlertDialog.Builder(mContext);
		
		alertDialogBuilder.setView(dialogView);
		final EditText edtCaptcha = (EditText)dialogView.findViewById(R.id.edtCaptcha);
		final ImageView imgCaptcha = (ImageView)dialogView.findViewById(R.id.imgCaptcha);
		
		ImageUtil.setImageViewWithByteArray(imgCaptcha, base64ImageData);	
		
		// set dialog message
		alertDialogBuilder
			.setCancelable(false)
			.setPositiveButton("Tamam",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
			    	mResult = edtCaptcha.getText().toString();
			    	
	 
			    	if (mPositiveButtonOnClickListener != null )
			    	  mPositiveButtonOnClickListener.onClick(dialog, id);
			    	
			    } 
			  })
			.setNegativeButton("Vazgeç",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
				  dialog.cancel();
				  
				  if ( mNegativeButtonOnClickListener != null )
					  mNegativeButtonOnClickListener.onClick(dialog, id); 
			    }
			  });
		
		mAlertDialog = alertDialogBuilder.create();
		mAlertDialog.show();
	}
}