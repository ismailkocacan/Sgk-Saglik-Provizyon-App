package tr.com.stackdeveloper.model;

import java.io.Serializable;

public class ResponseProvizyonSonucu implements Serializable 
{

	private static final long serialVersionUID = 1L;

	private String sicilNo;
	private String pva;
	private Boolean online;
	private String katilimPayiMuafiyet;
	private String tescilKapsamKodu;
	private String prvBasTar;
	private String prvBitTar;
	private String tcKimlikNo;
	private String yakinTcKimlik;
	private String adi;
	private String soyadi;
	private String dogumTarihi;
	private String cinsiyet;
	private String yakinlikTuru;
	private String spKapsamKodu;
	private String sigortaliTuru;
	private String sonucKodu;
	private String sonucMesaji;
	private Boolean akf;

	public String getSicilNo() 
	{
		return sicilNo;
	}

	public void setSicilNo(String sicilNo) 
	{
		this.sicilNo = sicilNo;
	}

	public String getPva() 
	{
		return pva;
	}

	public void setPva(String pva) 
	{
		this.pva = pva;
	}

	public Boolean getOnline() 
	{
		return online;
	}

	public void setOnline(Boolean online) 
	{
		this.online = online;
	}

	public String getKatilimPayiMuafiyet() 
	{
		return katilimPayiMuafiyet;
	}

	public void setKatilimPayiMuafiyet(String katilimPayiMuafiyet) 
	{
		this.katilimPayiMuafiyet = katilimPayiMuafiyet;
	}

	public String getTescilKapsamKodu() 
	{
		return tescilKapsamKodu;
	}

	public void setTescilKapsamKodu(String tescilKapsamKodu) 
	{
		this.tescilKapsamKodu = tescilKapsamKodu;
	}

	public String getPrvBasTar() 
	{
		return prvBasTar;
	}

	public void setPrvBasTar(String prvBasTar) 
	{
		this.prvBasTar = prvBasTar;
	}

	public String getPrvBitTar() 
	{
		return prvBitTar;
	}

	public void setPrvBitTar(String prvBitTar) 
	{
		this.prvBitTar = prvBitTar;
	}

	public String getTcKimlikNo() 
	{
		return tcKimlikNo;
	}

	public void setTcKimlikNo(String tcKimlikNo) 
	{
		this.tcKimlikNo = tcKimlikNo;
	}

	public String getYakinTcKimlik() 
	{
		return yakinTcKimlik;
	}

	public void setYakinTcKimlik(String yakinTcKimlik) 
	{
		this.yakinTcKimlik = yakinTcKimlik;
	}

	public String getAdi() 
	{
		return adi;
	}

	public void setAdi(String adi) 
	{
		this.adi = adi;
	}

	public String getSoyadi() 
	{
		return soyadi;
	}

	public void setSoyadi(String soyadi) 
	{
		this.soyadi = soyadi;
	}

	public String getDogumTarihi() 
	{
		return dogumTarihi;
	}

	public void setDogumTarihi(String dogumTarihi) 
	{
		this.dogumTarihi = dogumTarihi;
	}

	public String getCinsiyet() 
	{
		return cinsiyet;
	}

	public void setCinsiyet(String cinsiyet) 
	{
		this.cinsiyet = cinsiyet;
	}

	public String getYakinlikTuru() 
	{
		return yakinlikTuru;
	}

	public void setYakinlikTuru(String yakinlikTuru) 
	{
		this.yakinlikTuru = yakinlikTuru;
	}

	public String getSpKapsamKodu() {
		return spKapsamKodu;
	}

	public void setSpKapsamKodu(String spKapsamKodu) 
	{
		this.spKapsamKodu = spKapsamKodu;
	}

	public String getSigortaliTuru() 
	{
		return sigortaliTuru;
	}

	public void setSigortaliTuru(String sigortaliTuru) 
	{
		this.sigortaliTuru = sigortaliTuru;
	}

	public String getSonucKodu() 
	{
		return sonucKodu;
	}

	public void setSonucKodu(String sonucKodu) 
	{
		this.sonucKodu = sonucKodu;
	}

	public String getSonucMesaji() 
	{
		return sonucMesaji;
	}

	public void setSonucMesaji(String sonucMesaji) 
	{
		this.sonucMesaji = sonucMesaji;
	}

	public Boolean getAkf()
	{
		return akf;
	}

	public void setAkf(Boolean akf) 
	{
		this.akf = akf;
	}

}
