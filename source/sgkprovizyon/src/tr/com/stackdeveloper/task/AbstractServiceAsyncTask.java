package tr.com.stackdeveloper.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public abstract class AbstractServiceAsyncTask extends AsyncTask<Void, Void, Void> 
{

	private Context context;
	private ProgressDialog dialog;
	
	public ProgressDialog dialogBuilder(String title,String message)
	{
		setDialog(new ProgressDialog(getContext()));
		getDialog().setTitle(title);
		getDialog().setMessage(message);
		return getDialog();
	}
	
	
	public ProgressDialog dialogBuilder()
	{
		setDialog(new ProgressDialog(getContext()));
		return getDialog();
	}
	
	public void setDialog(ProgressDialog dialog) 
	{
		this.dialog = dialog;
	}

 	
	public Context getContext() 
	{
		return context;
	}

	public void setContext(Context context) 
	{
		this.context = context;
	}

	public ProgressDialog getDialog() 
	{
		return dialog;
	}
}
