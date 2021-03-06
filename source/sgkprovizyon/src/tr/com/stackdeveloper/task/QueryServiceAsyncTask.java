package tr.com.stackdeveloper.task;

import tr.com.stackdeveloper.esgm.MyDropboxHttpClient;

public class QueryServiceAsyncTask extends AbstractServiceAsyncTask 
{
	private String mResult;
	
	private MyDropboxHttpClient mClient;
	
	private ServiceResultCallback<String> mServiceResultCallback;
	
	public QueryServiceAsyncTask(ServiceResultCallback<String> callBack)
	{
		mServiceResultCallback = callBack;
		mClient = new MyDropboxHttpClient();
	}
	
	@Override
	protected void onPreExecute() 
	{
		super.onPreExecute();
		dialogBuilder("", "Bağlanılıyor...").show();
	}
	
	@Override
	protected Void doInBackground(Void... params) 
	{
		try 
		{
			mClient.execute();
			mResult = mClient.getResponse();	
		} catch (Exception e) 
		{
			mResult = e.getMessage();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) 
	{
		super.onPostExecute(result);
		getDialog().setMessage("Bağlanıldı...");
		mServiceResultCallback.onServiceResult(mResult);
		if (getDialog().isShowing()) getDialog().dismiss();
	}
}
