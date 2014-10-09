package tr.com.stackdeveloper.esgm;

import android.webkit.WebView;


public class ActionFormLogin extends AbstractPage 
{

	public final static String URL_LOGIN = "https://esgm.sgk.gov.tr/Esgm/LoginT.action";
	
	private String mCaptchaCode;
	
	private String tcKimlikNo;
	
	private String ilKodu;
	
	private String dogumYil;
	
	private String ciltNo;
	
	public void setTcKimlikNo(String tcKimlikNo) 
	{
		this.tcKimlikNo = tcKimlikNo;
	}

	public void setIlKodu(String ilKodu) 
	{
		this.ilKodu = ilKodu;
	}

	public void setDogumYil(String dogumYil) 
	{
		this.dogumYil = dogumYil;
	}


	public void setCiltNo(String ciltNo) 
	{
		this.ciltNo = ciltNo;
	}
	
	public ActionFormLogin(WebView webView) 
	{
		super(webView);
	}
	
	
	public void setCaptchaCode(String captchaCode)
	{
		mCaptchaCode = captchaCode;
	}

	@Override
	public void execute() 
	{
		executeJavaScript(jsElementsByName("tcKimlikNo", tcKimlikNo));
		executeJavaScript(jsElementsByName("ilDeger",ilKodu));
		executeJavaScript(jsElementsByName("dogumYil", dogumYil));
		executeJavaScript(jsElementsByName("ciltNo", ciltNo));
		executeJavaScript(jsElementsByName("guvenlikKodu", mCaptchaCode));
		executeJavaScript(jsSumbitFormByName("KimlikSorgulamaT"));
	}
}
