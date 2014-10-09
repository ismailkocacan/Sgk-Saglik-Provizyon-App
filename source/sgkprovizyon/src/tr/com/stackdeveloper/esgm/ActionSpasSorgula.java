package tr.com.stackdeveloper.esgm;

import tr.com.stackdeveloper.lib.DateHelper;
import android.webkit.WebView;

public class ActionSpasSorgula extends AbstractPage 
{

	public final static String URL_SPASS = "https://esgm.sgk.gov.tr/Esgm/SpasSorgula.action";
	
	public final static String URL_KIMLIKSORGULAMA = "https://esgm.sgk.gov.tr/Esgm/KimlikSorgulamaT.action";
	
	public final static String URL_SPASS_SONUC = "https://esgm.sgk.gov.tr/Esgm/SpasSonuc.action";
	
	
	public ActionSpasSorgula(WebView webView) 
	{
		super(webView);
	}


	@Override
	public void execute() 
	{
		executeJavaScript(jsElementsByName("spasSorguTarihi", DateHelper.getCurrentDate()));
		executeJavaScript(jsSumbitFormByName("SpasSonuc"));
	}
}
