package tr.com.stackdeveloper.model;

import java.io.Serializable;

public class RequestProvizyonSorgu implements Serializable 
{
	private static final long serialVersionUID = 1L;

	private String tcKimlikNo;
	private String guid;
	private int kullaniciId;
	private int UygId;
	private String provizyonTarihi;

	public String getTcKimlikNo() 
	{
		return tcKimlikNo;
	}

	public void setTcKimlikNo(String tcKimlikNo) 
	{
		this.tcKimlikNo = tcKimlikNo;
	}

	public String getGuid() 
	{
		return guid;
	}

	public void setGuid(String guid) 
	{
		this.guid = guid;
	}

	public int getKullaniciId() {
		return kullaniciId;
	}

	public void setKullaniciId(int kullaniciId) 
	{
		this.kullaniciId = kullaniciId;
	}

	public int getUygId() 
	{
		return UygId;
	}

	public void setUygId(int uygId) 
	{
		UygId = uygId;
	}

	public String getProvizyonTarihi() 
	{
		return provizyonTarihi;
	}

	public void setProvizyonTarihi(String provizyonTarihi) 
	{
		this.provizyonTarihi = provizyonTarihi;
	}
}
