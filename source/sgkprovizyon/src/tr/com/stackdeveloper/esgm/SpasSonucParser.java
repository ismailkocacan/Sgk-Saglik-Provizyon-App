package tr.com.stackdeveloper.esgm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tr.com.stackdeveloper.lib.ToastHelper;
import tr.com.stackdeveloper.model.ResponseProvizyonSonucu;
import android.content.Context;
import android.content.res.Resources.Theme;
import android.widget.Toast;

public class SpasSonucParser 
{
	private Context mContext;
	
	public SpasSonucParser(Context context)
	{
		mContext = context;
	}
	
	public String parseLoginFailMessage(String html)
	{
		String result = "";
		try 
		{
		  Document doc = Jsoup.parse(html);	
		  Elements elements = doc.select("div.alert[class=alert alert-error]");
		  result = elements.text();
		} catch (Exception e) 
		{
		  result = e.getMessage();
		}
		return result;
	}
	
	public ResponseProvizyonSonucu parse(String html)
	{
		ResponseProvizyonSonucu sonuc = new ResponseProvizyonSonucu();
		try 
		{
			Document doc = Jsoup.parse(html);
			Element elementsByTag = doc.getElementsByTag("table").get(1);
		    Elements rows = elementsByTag.getElementsByTag("tr");
		    rows.remove(0);
		    
		    String thHead  = "";
		    String tdValue = "";
		    
		    for(Element row : rows) 
		    {
		    	try 
		    	{
		    	   thHead  = row.getElementsByTag("th").get(0).text();
		    	   tdValue = row.getElementsByTag("td").get(0).text();
		    	   
		    	   if (thHead.contains("T.C Kimlik No")) sonuc.setTcKimlikNo(tdValue);
		    	   if (thHead.contains("Adý")) sonuc.setAdi(tdValue);
		    	   if (thHead.contains("Soyadý")) sonuc.setSoyadi(tdValue);
		    	   if (thHead.contains("Cinsiyet")) sonuc.setCinsiyet(tdValue);
		    	   if (thHead.contains("Doðum Tarihi")) sonuc.setDogumTarihi(tdValue);
		       	   if (thHead.contains("Yakýnlýk Türü")) sonuc.setYakinlikTuru(tdValue);
		       	   if (thHead.contains("Tescil Kapsamý")) sonuc.setTescilKapsamKodu(tdValue);
		       	   if (thHead.contains("Yararlandýðý TC Kimlik No")) sonuc.setYakinTcKimlik(tdValue);
		       	   if (thHead.contains("GSS Kapsamý")) sonuc.setSpKapsamKodu(tdValue);
		       	   if (thHead.contains("Sicil No")) sonuc.setSicilNo(tdValue);
		           if (thHead.contains("Provizyon Tarihi")) sonuc.setPrvBasTar(tdValue);
		           if (thHead.contains("Sigortalý Türü")) sonuc.setSigortaliTuru(tdValue);
		           if (thHead.contains("Katýlým Payý Muafiyet")) sonuc.setKatilimPayiMuafiyet(tdValue);
		           if (thHead.contains("Sorgu Sonucu")) sonuc.setSonucMesaji(tdValue);
			       
				} catch (Exception e) 
				{
					 
				}
		    }  
		} catch (Exception e)
		{
		   ToastHelper.showFailToast(mContext, "Provizyon Sonucu Okunamadý \n"+e.getMessage());
		}
		return sonuc;
	}
}