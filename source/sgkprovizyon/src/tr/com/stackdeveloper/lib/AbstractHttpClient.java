package tr.com.stackdeveloper.lib;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



public abstract class AbstractHttpClient {

	private  String url;

	private String response;
	
	private HttpClient httpClient;
	
	private Boolean httpsConnection;
	
	public abstract void processResponse();
	
	public void setHttpsConnection(Boolean value)
	{
		httpsConnection = value;
	}

	protected AbstractHttpClient() 
	{
		
	}
	
	protected AbstractHttpClient(String url) {
		this.url= url;
	}
	
	public void execute(){
		setResponse(doRequest(getUrl()));
		processResponse();
	}
	
	public String doRequest(String url)
	{
		String output =  "";
		
		if (httpsConnection) httpClient = Utils.getHttpsClient(new DefaultHttpClient());
		if (!httpsConnection) httpClient = new DefaultHttpClient();

	    HttpGet httpGet = new HttpGet(url);
	    HttpResponse httpResponse = null;
		try 
		{
			httpResponse = httpClient.execute(httpGet);
		} catch (ClientProtocolException e) {
			output ="";
		} catch (IOException e) 
		{
			output =""; 
		}
	    HttpEntity httpEntity = httpResponse.getEntity();
	    try 
	    {
			output = EntityUtils.toString(httpEntity);
		} catch (ParseException e) 
		{
			output ="";
		} catch (IOException e) 
		{
			output ="";
		}
	    return output;
	}
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}	
	

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}

}
