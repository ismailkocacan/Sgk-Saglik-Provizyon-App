package tr.com.stackdeveloper.esgm;

import android.webkit.WebView;

public interface PageLoadHandler 
{
	void onPageLoad(WebView webView,String url);
}
