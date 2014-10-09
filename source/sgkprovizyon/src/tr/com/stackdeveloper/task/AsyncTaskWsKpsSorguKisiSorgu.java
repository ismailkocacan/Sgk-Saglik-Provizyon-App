package tr.com.stackdeveloper.task;

import tr.com.stackdeveloper.model.ResponseKisiBilgi;
import tr.com.stackdeveloper.ws.WsKpsSorguKisiSorgu;

public class AsyncTaskWsKpsSorguKisiSorgu extends AbstractServiceAsyncTask 
{

	private WsKpsSorguKisiSorgu sorgu;
	
	private ServiceResultCallback<ResponseKisiBilgi> serviceResultCallback;
	
	public WsKpsSorguKisiSorgu getSorgu() 
	{
		return sorgu;
	}

	
	public AsyncTaskWsKpsSorguKisiSorgu()
	{
		sorgu = new WsKpsSorguKisiSorgu();
		
	}
	
	public void addServiceResultCallback(ServiceResultCallback<ResponseKisiBilgi> serviceResultCallback)
	{
	   this.serviceResultCallback = serviceResultCallback;
	}
	
	@Override
	protected void onPreExecute() 
	{
		super.onPreExecute();
		dialogBuilder("Sorgulanýyor","Kiþi Bilgileri").show();
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
		if (serviceResultCallback != null) 
		   serviceResultCallback.onServiceResult(sorgu.getResponseObject());
	}
}
