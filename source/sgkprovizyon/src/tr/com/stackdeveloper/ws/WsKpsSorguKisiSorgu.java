package tr.com.stackdeveloper.ws;
import android.util.Log;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import tr.com.stackdeveloper.lib.AbstractSoapClient;
import tr.com.stackdeveloper.lib.Constant;
import tr.com.stackdeveloper.lib.XmlParser;
import tr.com.stackdeveloper.model.RequestKisiBilgi;
import tr.com.stackdeveloper.model.ResponseKisiBilgi;


public class WsKpsSorguKisiSorgu extends AbstractSoapClient 
{
	private RequestKisiBilgi requestObject;
	private ResponseKisiBilgi responseObject;
	
	public WsKpsSorguKisiSorgu()
	{
		super(Constant.WSDL_KPS_URL_PROD);
		setSoapAction("kisiSorgula");
		setRequestObject(new RequestKisiBilgi());
		setResponseObject(new ResponseKisiBilgi());
	}
 

	@Override
	public StringBuilder buildRequest() 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:kps=\"http://kps.mobile.sgk.gov.tr\">");
		sb.append(" <soapenv:Header/>");
		sb.append("  <soapenv:Body>");
		sb.append("    <kps:kisiSorgula>");
		sb.append(String.format("<tcKimlikNo>%s</tcKimlikNo>", requestObject.getTcKimlikNo()));
		sb.append(String.format("<ciltNo>%s</ciltNo>", requestObject.getCiltNo().toString()));
		sb.append(String.format("<ilKodu>%s</ilKodu>", requestObject.getNufusIlKod().toString()));
		sb.append(String.format("<dogumYili>%s</dogumYili>", requestObject.getDogumYili().toString()));
		sb.append(String.format("<uyruk>%s</uyruk>",requestObject.getUyruk().toString()));
		sb.append("<ikametTezkereNo xsi:nil=\"true\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"/>");
		sb.append(String.format("<uygKod>%s</uygKod>", requestObject.getUygulamaKodu().toString()));
		sb.append("   </kps:kisiSorgula>");
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
			NodeList nodes = parser.getNodes("kisiSorgulaReturn");
			if (nodes.getLength()==0) return;
			for (int i = 0; i < nodes.getLength(); i++) 
			{
				try 
				{
					Node nNode = nodes.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) 
					{
						responseObject.setSessionId(getElementValue(nNode, "sessionId")	);
						responseObject.setSonucAciklama(getElementValue(nNode, "sonucAciklama"));
						responseObject.setSonucKod(Integer.parseInt(getElementValue(nNode, "sonucKod")));
					}
				} 
				catch (Exception e) 
				{
					responseObject.setSessionId("");
					responseObject.setSonucAciklama("");
				}
			}
			
		} catch (Exception e) 
		{
			responseObject.setSessionId("");
			responseObject.setSonucAciklama(e.getMessage());
		}
	}

	@Override
	public void processResponseExt() 
	{ 

	}
	
	
	public RequestKisiBilgi getRequestObject() 
	{
		return requestObject;
	}


	public void setRequestObject(RequestKisiBilgi requestObject) 
	{
		this.requestObject = requestObject;
	}


	public ResponseKisiBilgi getResponseObject() 
	{
		return responseObject;
	}


	public void setResponseObject(ResponseKisiBilgi responseObject) 
	{
		this.responseObject = responseObject;
	}
}
