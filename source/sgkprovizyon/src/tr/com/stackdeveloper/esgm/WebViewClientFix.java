package tr.com.stackdeveloper.esgm;

import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewClientFix extends WebViewClient 
{
 
	private PageLoadHandler mPageLoginHandler;
	private PageLoadHandler mPageKimlikSorgulamaHandler;
	private PageLoadHandler mPageSpassSorgulaHandler;
	private PageLoadHandler mPageSpassSonucHandler;

	private Boolean mCatpchaHandled;
	
	public void setCaptchaHandled(Boolean value)
	{
		mCatpchaHandled = value;
	}
	
	public Boolean isCaptchaHandled()
	{
	  return mCatpchaHandled;	
	}
	
	
	public void addPageLoginHandler(PageLoadHandler handler)
	{
		mPageLoginHandler = handler;
	}
	
	public void addPageKimlikSorgulamaHandler(PageLoadHandler handler)
	{
		mPageKimlikSorgulamaHandler = handler;
	}
	
	public void addPageSpassSorgulaHandler(PageLoadHandler handler)
	{
		mPageSpassSorgulaHandler = handler;
	}
	
	
	public void addPageSpasSonucHandler(PageLoadHandler handler)
	{
		mPageSpassSonucHandler = handler;
	}
 
	@Override
	public void onReceivedSslError(WebView view, SslErrorHandler handler,SslError error) 
	{
		handler.proceed();
	}
	
	
	@Override
	public void onPageFinished(WebView view, String url) 
	{
		super.onPageFinished(view, url);
		
		if (url.contains(ActionFormLogin.URL_LOGIN)) 
		{ 
		   if (mPageLoginHandler != null)
			  mPageLoginHandler.onPageLoad(view, url);
		}
		
		if (url.contains(ActionSpasSorgula.URL_KIMLIKSORGULAMA))  
		{
		   if (mPageKimlikSorgulamaHandler != null)
			   mPageKimlikSorgulamaHandler.onPageLoad(view, url);
		}
		
		if (url.contains(ActionSpasSorgula.URL_SPASS)) 
		{
		   if (mPageSpassSorgulaHandler != null)
			   mPageSpassSorgulaHandler.onPageLoad(view, url);
		}
		
		if (url.contains(ActionSpasSorgula.URL_SPASS_SONUC))
		{
		    if (mPageSpassSonucHandler != null)
			    mPageSpassSonucHandler.onPageLoad(view, url);
		}
	}
	
	@Override
	public void onLoadResource(WebView view, String url) 
	{
		super.onLoadResource(view, url);
	}
}