package tr.com.stackdeveloper.task;

import tr.com.stackdeveloper.model.ResponseProvizyonSonucu;
import tr.com.stackdeveloper.ws.WsProvizyonSorgulama;

public class AsyncTaskWsProvizyonSorgulama extends AbstractServiceAsyncTask 
{

	private WsProvizyonSorgulama sorgu;
	
	private ServiceResultCallback<ResponseProvizyonSonucu> serviceResultCallback;
	
	
	
	public AsyncTaskWsProvizyonSorgulama()
	{
		sorgu = new WsProvizyonSorgulama();
	}
	
	public WsProvizyonSorgulama getSorgu() 
	{
		return sorgu;
	}

	public void addServiceResultCallback(ServiceResultCallback<ResponseProvizyonSonucu> serviceResultCallback)
	{
		this.serviceResultCallback = serviceResultCallback;	
	}
	
	
	@Override
	protected void onPreExecute() 
	{
		super.onPreExecute();
		dialogBuilder("Sorgulanýyor","Provizyon Bilgileri").show();
	}
	
	
	@Override
	protected Void doInBackground(Void... params) 
	{
		try 
		{
		   sorgu.execute();
		} catch (Exception e) 
		{
			 
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Void result) 
	{
		super.onPostExecute(result);
		getDialog().dismiss();
		if (serviceResultCallback!=null) serviceResultCallback.onServiceResult(sorgu.getResponseObject());
	}
}
