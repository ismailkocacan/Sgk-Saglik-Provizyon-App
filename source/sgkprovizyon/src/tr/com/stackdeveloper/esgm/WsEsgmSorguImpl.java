package tr.com.stackdeveloper.esgm;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;
import tr.com.stackdeveloper.lib.ToastHelper;
import tr.com.stackdeveloper.model.ResponseProvizyonSonucu;
import tr.com.stackdeveloper.task.ServiceResultCallback;

public class WsEsgmSorguImpl implements WebViewHtmlContentHandler
{

	private Context mContext;
	private JsObject mJsObject;
	private WebView mWebView;
	
	public WebViewClientFix mWebClient;
	
	private SpasSonucParser htmlParser;
	
	private ActionFormLogin mFormLogin;
	
	private ActionSpasSorgula mFormSpass;

	private ServiceResultCallback<ResponseProvizyonSonucu> mServiceResultCallback;
	
	private ImageDataResultHandler mImageDataResultHandler;
	
	private ProgressDialog mProgressDialog;
	
	
	public ProgressDialog getProgressDialog()
	{
		return mProgressDialog;
	}
	
	public WebViewClientFix getWebViewClient()
	{
		return mWebClient;
	}
	
	public SpasSonucParser getHtmlParser() 
	{
		return htmlParser;
	}

	public WsEsgmSorguImpl(Context context,ImageDataResultHandler handler)
	{
		mContext = context;
		mWebView = new WebView(mContext);
		mImageDataResultHandler = handler;
		initializeObjects();
	}
	
	public class PageLoadHandlerLoginPage implements PageLoadHandler
	{
		@Override
		public void onPageLoad(WebView webView, String url) 
		{		 	
			try 
			{
				mWebView.loadUrl(mJsObject.jsExecute());	
			} catch (Exception e) 
			{
				 
			}
		}
	}
	
	
	public class KimlikSorgulamaWebViewHtmlContentHandler implements WebViewHtmlContentHandler
	{
		@Override
		public void onGetHtmlContent(String htmlContent) 
		{
			getProgressDialog().dismiss();
			if (htmlContent.contains("alert alert-error") ) 
			{
				ToastHelper.showFailToast(mContext, "Alert var");
				//String alertMessage = mWsEsgmSorguImpl.getHtmlParser().parseLoginFailMessage(htmlContent);
				//ToastHelper.showFailToast(MainActivity.this, alertMessage);
			}
			else
			{
				ToastHelper.showFailToast(mContext, "Alert yok");
			}
		}
	}
	
	public class PageLoadHandlerKimlikSorgulama implements PageLoadHandler
	{
		@Override
		public void onPageLoad(WebView webView, String url) 
		{
			try 
			{
				mJsObject.jsGetHtmlContent(webView, new KimlikSorgulamaWebViewHtmlContentHandler());
				
				//mWebView.loadUrl(ActionSpasSorgula.URL_SPASS);	
			} catch (Exception e) 
			{
				 
			}
		}
	}
	
	public class PageLoadHandlerSpasSorgulama implements PageLoadHandler
	{
		@Override
		public void onPageLoad(WebView webView, String url) 
		{
			try 
			{
				mFormSpass.execute();	
			} catch (Exception e) 
			{
				 
			}
		}
	}
	
	
	public class PageLoadHandlerSpasSonuc implements PageLoadHandler
	{
		@Override
		public void onPageLoad(WebView webView, String url) 
		{
			try 
			{
				webView.loadUrl(JsObject.jsGetHtmlContent());	
			} catch (Exception e) 
			{
			
			}
		}
	}
	
	private void initializeObjects()
	{
		mFormLogin = new ActionFormLogin(mWebView);
		mFormSpass = new ActionSpasSorgula(mWebView);
		mJsObject = new JsObject(mContext);
		mJsObject.addImageDataResultHandler(mImageDataResultHandler);
		mJsObject.addWebViewHtmlContentHandler(this);
		
		mWebView.getSettings().setSavePassword(false);
		mWebView.getSettings().setSaveFormData(false);
		mWebView.getSettings().setSupportZoom(true);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.setScrollbarFadingEnabled(true);
		mWebView.getSettings().setLoadsImagesAutomatically(true);

		mWebView.getSettings().setJavaScriptEnabled(true);
		mWebView.getSettings().setDomStorageEnabled(true);
		
		mWebClient = new WebViewClientFix();
		mWebView.setWebViewClient(mWebClient);
		mWebClient.addPageLoginHandler(new PageLoadHandlerLoginPage());
		mWebClient.addPageKimlikSorgulamaHandler(new PageLoadHandlerKimlikSorgulama());
		mWebClient.addPageSpassSorgulaHandler(new PageLoadHandlerSpasSorgulama());
		mWebClient.addPageSpasSonucHandler(new PageLoadHandlerSpasSonuc());
		
		mWebView.setWebChromeClient(new  WebChromeClientFix());
		mWebView.addJavascriptInterface(mJsObject, "injectedObject");
		
		htmlParser = new SpasSonucParser(mContext);
		mProgressDialog = new ProgressDialog(mContext);
		
		getWebViewClient().setCaptchaHandled(false);
	}
	
	public JsObject getJsObject() 
	{
		return mJsObject;
	}

	
	@Override
	public void onGetHtmlContent(String htmlContent) 
	{
	  try 
	  {
		if (htmlContent.length() == 0 ) return;	  
		ResponseProvizyonSonucu sonuc = htmlParser.parse(htmlContent);
		mServiceResultCallback.onServiceResult(sonuc);
	  } catch (Exception e) 
	  {
		Log.d("onGetHtmlContent",e.getMessage());
		ToastHelper.showFailToast(mContext,e.getMessage());
      }
	}
	
	// ilk olarak çaðrýlacak olan method
	public void sorgula()
	{
		getWebViewClient().setCaptchaHandled(false);
		mWebView.loadUrl(ActionFormLogin.URL_LOGIN);
	}
	
	//captcha yakalandýðýnda çaðrýlacak olan method.
	public void sorgula(EsgmKisi kisi,ServiceResultCallback<ResponseProvizyonSonucu> resultCallBack)
	{ 
		getWebViewClient().setCaptchaHandled(true);
		mServiceResultCallback = resultCallBack;
		mFormLogin.setCaptchaCode(kisi.getCaptchaCode());
		mFormLogin.setTcKimlikNo(kisi.getTcKimlikNo());
		mFormLogin.setIlKodu(kisi.getIlKodu());
		mFormLogin.setDogumYil(kisi.getDogumYil());
		mFormLogin.setCiltNo(kisi.getCiltNo());
		mFormLogin.execute();
	}
}
