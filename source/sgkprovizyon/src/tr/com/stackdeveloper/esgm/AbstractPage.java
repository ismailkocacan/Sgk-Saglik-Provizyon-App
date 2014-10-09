package tr.com.stackdeveloper.esgm;

import android.webkit.WebView;

public abstract class AbstractPage 
{
	protected WebView mWebView;

	public AbstractPage(WebView webView)
	{
		this.mWebView = webView;
	}
		
	public abstract void execute();
	
	public String jsElementsByName(String elementName,String elementValue)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("javascript: { document.getElementsByName('"+elementName+"')[0].value ='" + elementValue + "'; }");
		return sb.toString();
	}
	
	
	public String jsSumbitFormByName(String name)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("javascript: { document.forms['"+name+"'].submit(); }");
		return sb.toString();
	}	
	
	
	public void executeJavaScript(String javaScript)
	{
		mWebView.loadUrl(javaScript);
	}
}
