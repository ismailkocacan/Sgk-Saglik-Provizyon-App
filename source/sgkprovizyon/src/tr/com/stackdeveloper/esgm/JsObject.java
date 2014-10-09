package tr.com.stackdeveloper.esgm;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

public class JsObject 
{
	private Context mContext;
	private byte[] mDecodedString;
	private ImageDataResultHandler mResultHandler;
	private WebViewHtmlContentHandler mWebViewHtmlContentHandler;
	private WebViewHtmlContentHandler mWebViewHtmlContentHandler2;
	
	public JsObject(Context context)
	{
		this.mContext = context;		
	}

	public void addImageDataResultHandler(ImageDataResultHandler handler)
	{
		this.mResultHandler = handler;
	}
	
	public void addWebViewHtmlContentHandler(WebViewHtmlContentHandler handler)
	{
		this.mWebViewHtmlContentHandler = handler;
	}

	@JavascriptInterface
    public void showBase64Image(String base64Image) 
	{ 
		String cleanBase64Image = base64Image.replace("data:image/png;base64,", "");
		try 
		{
		  mDecodedString = android.util.Base64.decode(cleanBase64Image, android.util.Base64.DEFAULT);	
		  if (mDecodedString.length == 0) return;
		  if (mResultHandler != null) mResultHandler.onConvertComplete(mDecodedString);
		} catch (Exception e) 
		{
		  Toast.makeText(mContext, "Byte Çevirimde Hata \n"+e.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
	
	@JavascriptInterface
	public void processHtml(String htmlContent)
	{
		if (mWebViewHtmlContentHandler != null) 
			mWebViewHtmlContentHandler.onGetHtmlContent(htmlContent);
	}
	
	@JavascriptInterface
	public void onGetHtmlContent(String htmlContent)
	{
		 if (mWebViewHtmlContentHandler2 != null)
			 mWebViewHtmlContentHandler2.onGetHtmlContent(htmlContent);
	}
	
	public static String jsGetHtmlContent()
	{
		StringBuilder sb = new StringBuilder();
	    sb.append("javascript:");
	    sb.append("{ ");
	    sb.append(" injectedObject.processHtml(document.documentElement.innerHTML);");
		sb.append("}");
	    return sb.toString();
	}
	
	
	public void jsGetHtmlContent(WebView webView,WebViewHtmlContentHandler handler)
	{
		mWebViewHtmlContentHandler2 = handler;
		StringBuilder sb = new StringBuilder();
	    sb.append("javascript:");
	    sb.append("{ ");
	    sb.append(" injectedObject.onGetHtmlContent(document.documentElement.innerHTML);");
		sb.append("}");
		webView.loadUrl(sb.toString());
	}
	

	public String jsElementsByAttributeName()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("function getElementsByAttributeName(tagName, attributeName, attributeValue) { ");
		sb.append("  var i, n, objs=[], els=document.getElementsByTagName(tagName), len=els.length;");
		sb.append("  for (i=0; i<len; i++) { ");
		sb.append("    n = els[i][attributeName]; ");
		sb.append("    if (n && (n==attributeValue)) { ");
		sb.append("      objs.push(els[i]);");
		sb.append("    }");
		sb.append("  }");
		sb.append("  return objs;");
		sb.append("}");
		return sb.toString();
	}
	
 
	public String jsExecute()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("javascript: {");
 		
		sb.append(JsToDataUrl.buidScript());
 		
		sb.append(jsElementsByAttributeName());
		sb.append("var img = getElementsByAttributeName('img', 'src', 'https://esgm.sgk.gov.tr/Esgm/Captcha.jpg')[0];");
		
		sb.append("var canvas = document.createElement(\"canvas\");");
		sb.append("document.body.appendChild(canvas);");
		sb.append("canvas.width = img.width;");
		sb.append("canvas.height = img.height;");
		sb.append("var ctx = canvas.getContext(\"2d\");");
		sb.append("ctx.drawImage(img,0,0,img.clientWidth,img.clientHeight);");
		
		sb.append("var dataURL = canvas.toDataURL();");
		sb.append("injectedObject.showBase64Image(dataURL.toString());");		
		sb.append("}");
		return sb.toString();
	}
}
