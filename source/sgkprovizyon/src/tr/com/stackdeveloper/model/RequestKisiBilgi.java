package tr.com.stackdeveloper.model;

import java.io.Serializable;

public class RequestKisiBilgi implements Serializable 
{

	private static final long serialVersionUID = 1L;

	private String tcKimlikNo;
	private Integer ciltNo;
	private Integer nufusIlKod;
	private Integer dogumYili;
	private Integer uyruk = 1;
	private String ikametTezkereNo = "";
	private Integer uygulamaKodu;

	public String getTcKimlikNo() {
		return tcKimlikNo;
	}

	public void setTcKimlikNo(String tcKimlikNo) {
		this.tcKimlikNo = tcKimlikNo;
	}

	public Integer getCiltNo() {
		return ciltNo;
	}

	public void setCiltNo(Integer ciltNo) {
		this.ciltNo = ciltNo;
	}

	public Integer getNufusIlKod() {
		return nufusIlKod;
	}

	public void setNufusIlKod(Integer nufusIlKod) {
		this.nufusIlKod = nufusIlKod;
	}

	public Integer getDogumYili() {
		return dogumYili;
	}

	public void setDogumYili(Integer dogumYili) {
		this.dogumYili = dogumYili;
	}

	public Integer getUyruk() {
		return uyruk;
	}

	public void setUyruk(Integer uyruk) {
		this.uyruk = uyruk;
	}

	public String getIkametTezkereNo() {
		return ikametTezkereNo;
	}

	public void setIkametTezkereNo(String ikametTezkereNo) {
		this.ikametTezkereNo = ikametTezkereNo;
	}

	public Integer getUygulamaKodu() {
		return uygulamaKodu;
	}

	public void setUygulamaKodu(Integer uygulamaKodu) {
		this.uygulamaKodu = uygulamaKodu;
	}
}
