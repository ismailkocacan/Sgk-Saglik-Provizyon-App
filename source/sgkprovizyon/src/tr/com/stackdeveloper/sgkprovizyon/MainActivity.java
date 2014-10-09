package tr.com.stackdeveloper.sgkprovizyon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import tr.com.stackdeveloper.data.IlDataImpl;
import tr.com.stackdeveloper.esgm.ActionFormLogin;
import tr.com.stackdeveloper.esgm.ActionSpasSorgula;
import tr.com.stackdeveloper.esgm.DialogCaptchaHandler;
import tr.com.stackdeveloper.esgm.EsgmKisi;
import tr.com.stackdeveloper.esgm.ImageDataResultHandler;
import tr.com.stackdeveloper.esgm.JsObject;
import tr.com.stackdeveloper.esgm.PageLoadHandler;
import tr.com.stackdeveloper.esgm.SpasSonucParser;
import tr.com.stackdeveloper.esgm.WebChromeClientFix;
import tr.com.stackdeveloper.esgm.WebViewClientFix;
import tr.com.stackdeveloper.esgm.WebViewHtmlContentHandler;
import tr.com.stackdeveloper.esgm.WsEsgmSorguImpl;
import tr.com.stackdeveloper.lib.Constant;
import tr.com.stackdeveloper.lib.DateHelper;
import tr.com.stackdeveloper.lib.FileUtil;
import tr.com.stackdeveloper.lib.Helper;
import tr.com.stackdeveloper.lib.ToastHelper;
import tr.com.stackdeveloper.model.ResponseKisiBilgi;
import tr.com.stackdeveloper.model.ResponseProvizyonSonucu;
import tr.com.stackdeveloper.task.AsyncTaskWsKpsSorguKisiSorgu;
import tr.com.stackdeveloper.task.AsyncTaskWsProvizyonSorgulama;
import tr.com.stackdeveloper.task.QueryServiceAsyncTask;
import tr.com.stackdeveloper.task.ServiceResultCallback;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.android.gms.ads.*;

public class MainActivity extends Activity implements ImageDataResultHandler
{

	private EditText mTcKimlikNo;
	private EditText mCiltNo;
	private Spinner mDogumYili;
	private Spinner mNufusIl;
	private Button mSorgula;
	
	private IlDataImpl ilDataImpl;
	private ArrayList<Integer> listYil;	
	
	
	private Integer mSeciliDogumYili;
	private Integer mSeciliNufusIlKod;
	
	private AsyncTaskWsKpsSorguKisiSorgu mTaskKisiSorgu;
	private AsyncTaskWsProvizyonSorgulama mTaskProvizyonSorgu;
	
	private DialogCaptchaHandler mDialogCaptchaHandler = new DialogCaptchaHandler(this);
		
	
	public ProgressDialog mDialog;
	private ActionFormLogin mFormLogin;
	private ActionSpasSorgula mFormSpass;
	private WebView mWebView;
	private JsObject mJsObject; 
	private SpasSonucParser parser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		initViews();
		initAdvertise();
		initListYil();
		initListIl();
		initButtonListener();		
		setDefaults();
		initEsgmSettings();
	}
	
	private void initEsgmSettings()
	{
		mDialogCaptchaHandler = new DialogCaptchaHandler(this);
		
		mWebView = new WebView(this);
		
		mDialog = new ProgressDialog(this);
		mFormLogin = new ActionFormLogin(mWebView);
		mFormSpass = new ActionSpasSorgula(mWebView);
	
		mJsObject = new JsObject(this);
		mJsObject.addImageDataResultHandler(this);
	 

		mWebView.getSettings().setSupportZoom(true);
		mWebView.getSettings().setSavePassword(false);
		mWebView.getSettings().setSaveFormData(false);
		mWebView.getSettings().setBuiltInZoomControls(true);
		mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		mWebView.setScrollbarFadingEnabled(true);
		mWebView.getSettings().setLoadsImagesAutomatically(true);

		mWebView.getSettings().setDomStorageEnabled(true);
		mWebView.getSettings().setJavaScriptEnabled(true);
		
		WebViewClientFix webClient = new WebViewClientFix();
		mWebView.setWebViewClient(webClient);
	 
		webClient.addPageLoginHandler(new PageLoadHandlerLoginPage());
		webClient.addPageKimlikSorgulamaHandler(new PageLoadHandlerKimlikSorgulama());
		webClient.addPageSpassSorgulaHandler(new PageLoadHandlerSpasSorgulama());
		webClient.addPageSpasSonucHandler(new PageLoadHandlerSpasSonuc());
	 
		mWebView.setWebChromeClient(new  WebChromeClientFix());
		mWebView.addJavascriptInterface(mJsObject, "injectedObject");
		
		parser = new SpasSonucParser(this);
	}
	
	private void setDefaults() 
	{
		findViewById(R.id.scvResult).setVisibility(View.GONE);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
	}

	private Boolean isValidForm()
	{
		Boolean result = true;
		
		StringBuilder messages = new StringBuilder();
		if (mTcKimlikNo.getText().toString().length() != 11) 
		  messages.append("T.C Kimlik No 11 Haneli Olmalýdýr !\n");
		
		if (mCiltNo.getText().toString().length() == 0) 
		  messages.append("Cilt Numarasý Hatalý Girdiniz ! \n");
		
		if (!Helper.checkConnection(MainActivity.this))
		  messages.append(Constant.CONNECTION_NOT_FOUND);

		if (messages.length() > 0)
		{
			result = false;
			ToastHelper.showFailToast(getApplicationContext(), messages.toString());
		}
		return result;
	}

	private void sorgulaWebServis()
	{
		try 
		{
			mSorgula.setEnabled(false);
			findViewById(R.id.scvResult).setVisibility(View.GONE);
			mTaskKisiSorgu = new AsyncTaskWsKpsSorguKisiSorgu();
			mTaskKisiSorgu.setContext(MainActivity.this);
			mTaskKisiSorgu.getSorgu().getRequestObject().setTcKimlikNo(mTcKimlikNo.getText().toString());
			mTaskKisiSorgu.getSorgu().getRequestObject().setCiltNo(Integer.parseInt(mCiltNo.getText().toString()));
			mTaskKisiSorgu.getSorgu().getRequestObject().setDogumYili(mSeciliDogumYili);
			mTaskKisiSorgu.getSorgu().getRequestObject().setNufusIlKod(mSeciliNufusIlKod);
			mTaskKisiSorgu.getSorgu().getRequestObject().setUyruk(1);
			mTaskKisiSorgu.getSorgu().getRequestObject().setIkametTezkereNo("");
			mTaskKisiSorgu.getSorgu().getRequestObject().setUygulamaKodu(10);
			WsKisiBilgiServiceResultCallback kisiBilgiServiceResultCallback = new WsKisiBilgiServiceResultCallback();
			mTaskKisiSorgu.addServiceResultCallback(kisiBilgiServiceResultCallback);
			mTaskKisiSorgu.execute();	
		} catch (Exception e) 
		{
			ToastHelper.showFailToast(getApplicationContext(),e.getMessage());
		}
	}
	
	private void sorgulaEsgm()
	{
	  try 
	  {
		 mDialog.show();
		 mDialog.setTitle("Sorgulanýyor");
		 mDialog.setMessage("Kiþi Bilgileri...");
		 mWebView.loadUrl(ActionFormLogin.URL_LOGIN); 	
	  } catch (Exception e) 
	  {
		 ToastHelper.showFailToast(getApplicationContext(),e.getMessage());
	  }
	}
		
	private void sorgula()
	{
		if (!isValidForm()) return;
		try 
		{
			/*
			DrobBoxSorguSonuc sonuc = new DrobBoxSorguSonuc();
			QueryServiceAsyncTask query=new QueryServiceAsyncTask(sonuc);
			query.setContext(this);
			query.execute();
			*/
			sorgulaEsgm();
			
		} catch (Exception e) 
		{
			ToastHelper.showFailToast(MainActivity.this, e.getMessage());
		}
	}
	
	private void initButtonListener() 
	{	
		mSorgula.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) 
			{
				sorgula();
			}
		});
	}

	private void initListIl() 
	{
		ilDataImpl = new IlDataImpl();
		ilDataImpl.toStringList();
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, ilDataImpl.toStringList());
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mNufusIl.setAdapter(adapter);
		
		mNufusIl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) 
	        {
	        	mSeciliNufusIlKod = ilDataImpl.getIlList().get(pos).getKodu();
	        }

	        public void onNothingSelected(AdapterView parent) 
	        {

	        }
	    });
		
	}

	private void initListYil() 
	{
		listYil = new ArrayList<Integer>();		
		for (int i = Calendar.getInstance().get(Calendar.YEAR); i >= 1900 ; i--) 
		{
			listYil.add(i);
		}
		
		ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, listYil);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mDogumYili.setAdapter(adapter);
		mDogumYili.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
	        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) 
	        {
	        	mSeciliDogumYili = Integer.parseInt(parent.getItemAtPosition(pos).toString());
	        }

	        public void onNothingSelected(AdapterView parent) 
	        {

	        }
	    });
	}

	private void initViews()
	{
		mTcKimlikNo = (EditText)findViewById(R.id.edtQTcKimlikNo);
		mCiltNo = (EditText)findViewById(R.id.edtQCiltNo);
		mDogumYili = (Spinner)findViewById(R.id.spnDogumYili);
		mNufusIl = (Spinner)findViewById(R.id.spnNufusIl);
		mSorgula = (Button)findViewById(R.id.btnSorgula);
		
		mTcKimlikNo.setOnKeyListener(new OnKeyListener() 
		{			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
				if (mTcKimlikNo.getText().length() == 11)
					mCiltNo.requestFocus();
				return false;
			}
		});
	}

 
	private void initAdvertise()
	{
		try 
		{
			LinearLayout layout = (LinearLayout)findViewById(R.id.reklam);
			
	        AdView adView = new AdView(this);
	        adView.setAdUnitId(Constant.REKLAM_BIRIM_KIMLIGI);
	        adView.setAdSize(AdSize.BANNER);
	        layout.addView(adView);
	        
	        AdRequest request = new AdRequest.Builder().build();
	        
	        adView.setAdListener(new MyAdListener(this));
	        
	        adView.loadAd(request);
	        
		} catch (Exception e) 
		{
			
		}
	}

	private void provizyonSorgula(String guid)
	{
		mTaskProvizyonSorgu = new AsyncTaskWsProvizyonSorgulama();
		mTaskProvizyonSorgu.setContext(this);
		mTaskProvizyonSorgu.getSorgu().getRequestObject().setGuid(guid);
		mTaskProvizyonSorgu.getSorgu().getRequestObject().setUygId(10);
		mTaskProvizyonSorgu.getSorgu().getRequestObject().setTcKimlikNo(mTcKimlikNo.getText().toString());
		mTaskProvizyonSorgu.getSorgu().getRequestObject().setProvizyonTarihi(DateHelper.getCurrentDate());
		mTaskProvizyonSorgu.addServiceResultCallback(new ServiceResultCallback<ResponseProvizyonSonucu>() {
			@Override
			public void onServiceResult(ResponseProvizyonSonucu r) 
			{
				findViewById(R.id.scvResult).setVisibility(View.VISIBLE);
				((ScrollView)findViewById(R.id.scvResult)).fullScroll(ScrollView.FOCUS_UP);
			    ((EditText)findViewById(R.id.edtAdiSoyadi)).setText(r.getAdi()+" "+r.getSoyadi());
			    ((EditText)findViewById(R.id.edtDogumTarihi)).setText(r.getDogumTarihi());
			    ((EditText)findViewById(R.id.edtCinsiyet)).setText(r.getCinsiyet());   
			    ((EditText)findViewById(R.id.edtKatiliPayiMuafiyeti)).setText(r.getKatilimPayiMuafiyet());   
			    ((EditText)findViewById(R.id.edtSgkKapsami)).setText(r.getSpKapsamKodu());   
			    ((EditText)findViewById(R.id.edtSgkKapsami)).setTypeface(null, Typeface.BOLD);
			    ((EditText)findViewById(R.id.edtSonuc)).setText(r.getSonucMesaji());
			    ((EditText)findViewById(R.id.edtSonuc)).setTypeface(null, Typeface.BOLD);
			    ((EditText)findViewById(R.id.edtYakinlikTuru)).setText(r.getYakinlikTuru());
			    ((EditText)findViewById(R.id.edtYararlandigiKisi)).setText(r.getYakinTcKimlik());
			    ((EditText)findViewById(R.id.edtSigorataliTuru)).setText(r.getSigortaliTuru());
			    ((EditText)findViewById(R.id.edtSicilNo)).setText(r.getSicilNo());
			    
			    String result = "";
			    if (r.getTescilKapsamKodu().contains("4/a"))  result = "(SSK)";
			    if (r.getTescilKapsamKodu().contains("4/b"))  result = "(BAÐKUR)";
			    if (r.getTescilKapsamKodu().contains("4/c"))  result = "(Emekli Sandýðý)";
			    ((EditText)findViewById(R.id.edtSaglikKapsamKodu)).setText(result);
			}
		});
		mTaskProvizyonSorgu.execute();
	}
	
	@Override
	public void onConvertComplete(final byte[] imageData) 
	{
		try 
		{
			mDialogCaptchaHandler.setPositiveButtonOnClickListener
			(
				new DialogInterface.OnClickListener() 
				{
				    public void onClick(DialogInterface dialog,int id) 
					{
				    	eSgmProvizyonSorgula();
					} 
				}
			);

			runOnUiThread(new Runnable() 
			{
			    @Override
			    public void run() 
			    {
			    	mDialog.dismiss();
			    	mDialogCaptchaHandler.show(imageData);
			    }
			});
		 
		} catch (Exception e) 
		{
			ToastHelper.showFailToast(this, "Dönüþüm Hatasý \n "+e.getMessage());
		}
	}
	
	private void eSgmProvizyonSorgula()
	{
		try 
		{
			String captchaCode = mDialogCaptchaHandler.getResult();
	    	if (captchaCode.trim().length() == 0)
	    	{
	    		ToastHelper.showFailToast(getApplicationContext(), "Güvenlik Kodu Girmeden \n Sorgulama Yapýlamaz !");
	    		return;
	    	}
	    	mFormLogin.setCaptchaCode(captchaCode);
	    	mFormLogin.setTcKimlikNo(mTcKimlikNo.getText().toString());
	    	mFormLogin.setIlKodu(Integer.toString(mSeciliNufusIlKod));
	    	mFormLogin.setCiltNo(mCiltNo.getText().toString());
	    	mFormLogin.setDogumYil(Integer.toString(mSeciliDogumYili));
	    	mFormLogin.execute();
	    	mDialog.show();		
		} catch (Exception e) 
		{
			ToastHelper.showFailToast(getApplicationContext(), e.getMessage());
		}
	}
	
	public class PageLoadHandlerLoginPage implements PageLoadHandler
	{

		@Override
		public void onPageLoad(WebView webView, String url) 
		{
			try 
			{
				webView.loadUrl(mJsObject.jsExecute());	
			} catch (Exception e) 
			{
				
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
			if (htmlContent.contains("alert alert-error") ) 
			{
				if ( mDialog.isShowing() ) mDialog.dismiss(); 
				String message = parser.parseLoginFailMessage(htmlContent);
				if (message.length() > 0) ToastHelper.showFailToast(getApplicationContext(), message);
			}
			else
			{
				mWebView.loadUrl(ActionSpasSorgula.URL_SPASS);
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
				mDialog.setMessage("Provizyon Bilgileri...");
				mDialog.show();
				mFormSpass.execute();	
			} catch (Exception e) 
			{
			 
			}
		}
	}
	
	public class SpasSonucWebViewHtmlContentHandler implements WebViewHtmlContentHandler
	{
		@Override
		public void onGetHtmlContent(String htmlContent) 
		{
			try 
			{
				runOnUiThread(new Runnable() 
				{
					public void run() 
					{
						mDialog.setMessage("Bilgiler Sorgulandý...");
						if ( mDialog.isShowing() ) mDialog.dismiss();	
					}
				});
				ResponseProvizyonSonucu sonuc = parser.parse(htmlContent);
				EsgmProvizyonSorguSonucu eSgmSonucGoster = new EsgmProvizyonSorguSonucu();
				eSgmSonucGoster.onServiceResult(sonuc); 	
			} catch (Exception e) 
			{
			  ToastHelper.showFailToast(getApplicationContext(), e.getMessage());
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
		       mJsObject.jsGetHtmlContent(webView, new SpasSonucWebViewHtmlContentHandler());	
			} catch (Exception e) {
			
			}
		}
	}
	
	public class DrobBoxSorguSonuc implements ServiceResultCallback<String>
	{
		@Override
		public void onServiceResult(String result) 
		{
		    try 
		    {
		      boolean sonuc = result.contains("0");
			  if (sonuc) sorgulaEsgm();
			  if (!sonuc) sorgulaWebServis();	   
			} catch (Exception e) 
			{
			  sorgulaEsgm();
			}
		}
	}
	
	public class WsKisiBilgiServiceResultCallback implements ServiceResultCallback<ResponseKisiBilgi>
	{
		@Override
		public void onServiceResult(ResponseKisiBilgi result) 
		{
			mSorgula.setEnabled(true);
			String guid = result.getSessionId();
			if (result.getSonucKod() == 1) ToastHelper.showFailToast(MainActivity.this, result.getSonucAciklama());
			if (guid.length() == 0) return;
			provizyonSorgula(guid);
		}
	}
	
	public class EsgmProvizyonSorguSonucu implements ServiceResultCallback<ResponseProvizyonSonucu>
	{
		@Override
		public void onServiceResult(final ResponseProvizyonSonucu r) 
		{
			runOnUiThread(new Runnable() 
			{
				public void run() 
				{
					findViewById(R.id.scvResult).setVisibility(View.VISIBLE);
					((ScrollView)findViewById(R.id.scvResult)).fullScroll(ScrollView.FOCUS_UP);
				    ((EditText)findViewById(R.id.edtAdiSoyadi)).setText(r.getAdi()+" "+r.getSoyadi());
				    ((EditText)findViewById(R.id.edtDogumTarihi)).setText(r.getDogumTarihi());
				    ((EditText)findViewById(R.id.edtCinsiyet)).setText(r.getCinsiyet());   
				    ((EditText)findViewById(R.id.edtKatiliPayiMuafiyeti)).setText(r.getKatilimPayiMuafiyet());   
				    ((EditText)findViewById(R.id.edtSgkKapsami)).setText(r.getSpKapsamKodu());   
				    ((EditText)findViewById(R.id.edtSgkKapsami)).setTypeface(null, Typeface.BOLD);
				    ((EditText)findViewById(R.id.edtSonuc)).setText(r.getSonucMesaji());
				    ((EditText)findViewById(R.id.edtSonuc)).setTypeface(null, Typeface.BOLD);
				    ((EditText)findViewById(R.id.edtYakinlikTuru)).setText(r.getYakinlikTuru());
				    ((EditText)findViewById(R.id.edtYararlandigiKisi)).setText(r.getYakinTcKimlik());
				    ((EditText)findViewById(R.id.edtSigorataliTuru)).setText(r.getSigortaliTuru());
				    ((EditText)findViewById(R.id.edtSicilNo)).setText(r.getSicilNo());
				    ((EditText)findViewById(R.id.edtSaglikKapsamKodu)).setText(r.getTescilKapsamKodu()); 
				}
			});
		}
	}
}
