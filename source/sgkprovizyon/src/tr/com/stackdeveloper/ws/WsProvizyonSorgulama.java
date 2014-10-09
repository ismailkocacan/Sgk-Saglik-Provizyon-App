package tr.com.stackdeveloper.ws;

import java.lang.reflect.Field;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;
import tr.com.stackdeveloper.lib.AbstractSoapClient;
import tr.com.stackdeveloper.lib.Constant;
import tr.com.stackdeveloper.lib.XmlParser;
import tr.com.stackdeveloper.model.RequestProvizyonSorgu;
import tr.com.stackdeveloper.model.ResponseProvizyonSonucu;

public class WsProvizyonSorgulama extends AbstractSoapClient 
{

	private static String SOAP_ACTION = "provizyonSorgulama";

	private RequestProvizyonSorgu requestObject;
	
	private ResponseProvizyonSonucu responseObject;
	
	
	public WsProvizyonSorgulama() 
	{
		super(Constant.WSDL_PROVIZYON_URL_PROD);
		setSoapAction(SOAP_ACTION);
		setRequestObject(new RequestProvizyonSorgu());
		setResponseObject(new ResponseProvizyonSonucu());
	}

	public RequestProvizyonSorgu getRequestObject() 
	{
		return requestObject;
	}

	public void setRequestObject(RequestProvizyonSorgu requestObject) 
	{
		this.requestObject = requestObject;
	}

	public ResponseProvizyonSonucu getResponseObject() 
	{
		return responseObject;
	}

	public void setResponseObject(ResponseProvizyonSonucu responseObject) 
	{
		this.responseObject = responseObject;
	}

	@Override
	public StringBuilder buildRequest() 
	{
		StringBuilder sb = new  StringBuilder();
		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:onl=\"http://onlinerapor.mobile.sgk.gov.tr\">");
		sb.append(" <soapenv:Header/>");
		sb.append("  <soapenv:Body>");
		sb.append("     <onl:provizyonSorgulama>");
		sb.append(String.format("<tcKimlikNo>%s</tcKimlikNo>", requestObject.getTcKimlikNo()));
		sb.append(String.format("<guid>%s</guid>", requestObject.getGuid()));
		sb.append("<kullaniciId xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>");
		sb.append(String.format("<UygId>%s</UygId>", Integer.toString(requestObject.getUygId())));
		sb.append(String.format("<provizyonTarihi>%s</provizyonTarihi>", requestObject.getProvizyonTarihi()));
		sb.append("    </onl:provizyonSorgulama>");
		sb.append(" </soapenv:Body>");
		sb.append("</soapenv:Envelope>");
		return sb;
	}

	@Override
	public void processResponse() 
	{
		if (getResponse().length() == 0) return; 
		try 
		{
			XmlParser parser = getXmlParser();
			parser.getDocument(getResponse());
			NodeList nodes = parser.getNodes("provizyonSorgulamaReturn");
			if (nodes.getLength()==0) return;
			for (int i = 0; i < nodes.getLength(); i++) 
			{
				try 
				{
					Node nNode = nodes.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) 
					{
						responseObject.setSicilNo(getElementValue(nNode, "sicilNo"));
						responseObject.setPva(getElementValue(nNode, "pva"));
						responseObject.setOnline(Boolean.parseBoolean(getElementValue(nNode, "online")));
						responseObject.setKatilimPayiMuafiyet(getElementValue(nNode, "katilimPayiMuafiyet"));
						responseObject.setTescilKapsamKodu(getElementValue(nNode, "tescilKapsamKodu"));
						responseObject.setPrvBasTar(getElementValue(nNode, "prvBasTar"));
						responseObject.setPrvBitTar(getElementValue(nNode, "prvBitTar"));
						responseObject.setTcKimlikNo(getElementValue(nNode, "tcKimlikNo"));
						responseObject.setYakinTcKimlik(getElementValue(nNode, "yakinTcKimlik"));  
						responseObject.setAdi(getElementValue(nNode, "adi"));
						responseObject.setSoyadi(getElementValue(nNode, "soyadi"));
						responseObject.setDogumTarihi(getElementValue(nNode, "dogumTarihi"));
						responseObject.setCinsiyet(getElementValue(nNode, "cinsiyet"));
						responseObject.setYakinlikTuru(getElementValue(nNode, "yakinlikTuru"));
						responseObject.setSpKapsamKodu(getElementValue(nNode, "spKapsamKodu"));
						responseObject.setSigortaliTuru(getElementValue(nNode, "sigortaliTuru"));
						responseObject.setSonucKodu(getElementValue(nNode, "sonucKodu"));
						responseObject.setSonucMesaji(getElementValue(nNode, "sonucMesaji"));
						responseObject.setAkf(Boolean.parseBoolean(getElementValue(nNode, "akf")));
					}
				} 
				catch (Exception e) 
				{
					 
				}
			}
			
		} catch (Exception e) 
		{
			 
		}
		
	}

	@Override
	public void processResponseExt() 
	{
		 

	}
}
