package tr.com.stackdeveloper.esgm;

import tr.com.stackdeveloper.lib.AbstractHttpClient;

public class MyDropboxHttpClient extends AbstractHttpClient 
{
	public final static String URL ="https://www.dropbox.com/s/vms45svu2h0l2pc/t.api?dl=1";
	
	public MyDropboxHttpClient()
	{
		super(URL);
		setHttpsConnection(true);
	}
	
	@Override
	public void processResponse() 
	{
		 
	}
}
