package tr.com.stackdeveloper.model;

import java.io.Serializable;

public class Il implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int kodu;
	private String adi;

	
	public Il(int kodu,String adi)
	{
	  this.kodu	= kodu;
	  this.adi = adi;
	}
	
	public int getKodu() 
	{
		return kodu;
	}

	public void setKodu(int kodu) 
	{
		this.kodu = kodu;
	}

	public String getAdi() 
	{
		return adi;
	}

	public void setAdi(String adi) 
	{
		this.adi = adi;
	}
}
