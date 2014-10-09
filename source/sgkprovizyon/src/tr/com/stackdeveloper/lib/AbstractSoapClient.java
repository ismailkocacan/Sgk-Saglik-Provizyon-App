/*
 * http://stackoverflow.com/questions/9064051/android-httppost-request-timeout
 * 
 * 
 */
package tr.com.stackdeveloper.lib;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public abstract class AbstractSoapClient 
{

	private String request;
	
	private String response;

	private String requestUrl;
	
	private String soapAction;

	private int connectionTimeout = 3000;
	
	private int soTimeOut = 2000;
	
	public abstract StringBuilder buildRequest();
	
	public abstract void processResponse();
	
	public abstract void processResponseExt();
	
	public String doRequest(String envolope)
	{
		HttpPost httppost = new HttpPost(getRequestUrl());
		StringEntity se;
		try 
		{
			se = new StringEntity(envolope, HTTP.UTF_8);
			
			se.setContentType("text/xml");
			httppost.setHeader("Content-Type","application/soap+xml;charset=UTF-8");
			httppost.setHeader("Accept-Charset","utf-8");
			httppost.setHeader("Accept","text/xml,application/text+xml,application/soap+xml");
			if (this.soapAction!=null) httppost.setHeader("SOAPAction", this.soapAction);
			httppost.setEntity(se);
	
			HttpClient httpclient = Utils.getHttpsClient(new DefaultHttpClient());	
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setSoTimeout(httpParameters, this.soTimeOut);
			HttpConnectionParams.setConnectionTimeout(httpParameters, this.connectionTimeout);
	        HttpProtocolParams.setVersion(httpParameters, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(httpParameters, HTTP.UTF_8);
	  
			String strResponse = "";
			if (httpclient != null)
			{
				BasicHttpResponse httpResponse = (BasicHttpResponse) httpclient.execute(httppost);	 
				HttpEntity resEntity = httpResponse.getEntity();	
				strResponse = EntityUtils.toString(resEntity);
				Log.d("Response", strResponse);
			}
			
			return strResponse;

		} catch (Exception e) 
		{
			Log.d("Response", e.getMessage());
			return e.getMessage();
		}
	}
	
	protected AbstractSoapClient(String requestUrl) 
	{
		setRequestUrl(requestUrl);
	}
	
	public void execute()
	{
		setRequest(buildRequest().toString());
		setResponse(doRequest(getRequest()));
		processResponse();
		processResponseExt();
	}
	
	public String getResponse()
	{
		return response;
	}
	

	public String getRequest()
	{
		return request;
	}

	public void setRequest(String request)
	{
		this.request = request;
	}

	public String getRequestUrl()
	{
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl)
	{
		this.requestUrl = requestUrl;
	}

	public String getSoapAction() {
		return soapAction;
	}

	public void setSoapAction(String soapAction) {
		this.soapAction = soapAction;
	}
	
	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getSoTimeOut() {
		return soTimeOut;
	}

	public void setSoTimeOut(int soTimeOut) {
		this.soTimeOut = soTimeOut;
	}

	public String getElementValue(Node n,String name)
	{
		Element e=(Element)n;
		return e.getElementsByTagName(name).item(0).getTextContent();
	}
		
	public NodeList getSubNodes(Node n,String name)
	{
		 Element token = (Element)n;
		 return token.getElementsByTagName(name);
	}

	public void setResponse(String response) {
		this.response = response;
	}	
 
	public XmlParser getXmlParser()
	{
		return XmlParser.create();
	}
}
