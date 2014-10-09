package tr.com.stackdeveloper.model;

import java.io.Serializable;

public class ResponseKisiBilgi implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private String sessionId;
	
	private int sonucKod;
	
	private String sonucAciklama;
	
	
	public int getSonucKod() 
	{
		return sonucKod;
	}

	public void setSonucKod(int sonucKod) 
	{
		this.sonucKod = sonucKod;
	}

	public String getSonucAciklama() 
	{
		return sonucAciklama;
	}

	public void setSonucAciklama(String sonucAciklama) 
	{
		this.sonucAciklama = sonucAciklama;
	}

	public String getSessionId() 
	{
		return sessionId;
	}

	public void setSessionId(String sessionId) 
	{
		this.sessionId = sessionId;
	}
}
