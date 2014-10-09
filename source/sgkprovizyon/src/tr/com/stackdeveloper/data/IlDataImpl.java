package tr.com.stackdeveloper.data;

import java.util.ArrayList;
import java.util.List;

import tr.com.stackdeveloper.model.Il;

public class IlDataImpl 
{
	private List<Il> ilList;

	private ArrayList<String> ilListStr;
	
	public IlDataImpl() 
	{
		ilList = new ArrayList<Il>();
		ilList.add(new Il(1, "ADANA"));
		ilList.add(new Il(2, "ADIYAMAN"));
		ilList.add(new Il(3, "AFYONKARAHÝSAR"));
		ilList.add(new Il(4, "AÐRI"));
		ilList.add(new Il(68, "AKSARAY"));
		ilList.add(new Il(5, "AMASYA"));
		ilList.add(new Il(6, "ANKARA"));
		ilList.add(new Il(7, "ANTALYA"));
		ilList.add(new Il(75, "ARDAHAN"));
		ilList.add(new Il(8, "ARTVÝN"));
		ilList.add(new Il(9, "AYDIN"));
		ilList.add(new Il(10, "BALIKESÝR"));
		ilList.add(new Il(74, "BARTIN"));
		ilList.add(new Il(72, "BATMAN"));
		ilList.add(new Il(69, "BAYBURT"));
		ilList.add(new Il(11, "BÝLECÝK"));
		ilList.add(new Il(12, "BÝNGÖL"));
		ilList.add(new Il(13, "BÝTLÝS"));
		ilList.add(new Il(14, "BOLU"));
		ilList.add(new Il(15, "BURDUR"));
		ilList.add(new Il(16, "BURSA"));
		ilList.add(new Il(17, "ÇANAKKALE"));
		ilList.add(new Il(18, "ÇANKIRI"));
		ilList.add(new Il(19, "ÇORUM"));
		ilList.add(new Il(20, "DENÝZLÝ"));
		ilList.add(new Il(21, "DÝYARBAKIR"));
		ilList.add(new Il(81, "DÜZCE"));
		ilList.add(new Il(22, "EDÝRNE"));
		ilList.add(new Il(23, "ELAZIÐ"));
		ilList.add(new Il(24, "ERZÝNCAN"));
		ilList.add(new Il(25, "ERZURUM"));
		ilList.add(new Il(26, "ESKÝÞEHÝR"));
		ilList.add(new Il(27, "GAZÝANTEP"));
		ilList.add(new Il(28, "GÝRESUN"));
		ilList.add(new Il(29, "GÜMÜÞHANE"));
		ilList.add(new Il(30, "HAKKARÝ"));
		ilList.add(new Il(31, "HATAY"));
		ilList.add(new Il(76, "IÐDIR"));
		ilList.add(new Il(32, "ISPARTA"));
		ilList.add(new Il(34, "ÝSTANBUL"));
		ilList.add(new Il(35, "ÝZMÝR"));
		ilList.add(new Il(46, "KAHRAMANMARAÞ"));
		ilList.add(new Il(78, "KARABÜK"));
		ilList.add(new Il(70, "KARAMAN"));
		ilList.add(new Il(36, "KARS"));
		ilList.add(new Il(37, "KASTAMONU"));
		ilList.add(new Il(38, "KAYSERÝ"));
		ilList.add(new Il(71, "KIRIKKALE"));
		ilList.add(new Il(39, "KIRKLARELÝ"));
		ilList.add(new Il(40, "KIRÞEHÝR"));
		ilList.add(new Il(79, "KÝLÝS"));
		ilList.add(new Il(41, "KOCAELÝ"));
		ilList.add(new Il(42, "KONYA"));
		ilList.add(new Il(43, "KÜTAHYA"));
		ilList.add(new Il(44, "MALATYA"));
		ilList.add(new Il(45, "MANÝSA"));
		ilList.add(new Il(47, "MARDÝN"));
		ilList.add(new Il(33, "MERSÝN"));
		ilList.add(new Il(48, "MUÐLA"));
		ilList.add(new Il(49, "MUÞ"));
		ilList.add(new Il(50, "NEVÞEHÝR"));
		ilList.add(new Il(51, "NÝÐDE"));
		ilList.add(new Il(52, "ORDU"));
		ilList.add(new Il(80, "OSMANÝYE"));
		ilList.add(new Il(53, "RÝZE"));
		ilList.add(new Il(54, "SAKARYA"));
		ilList.add(new Il(55, "SAMSUN"));
		ilList.add(new Il(56, "SÝÝRT"));
		ilList.add(new Il(57, "SÝNOP"));
		ilList.add(new Il(58, "SÝVAS"));
		ilList.add(new Il(63, "ÞANLIURFA"));
		ilList.add(new Il(73, "ÞIRNAK"));
		ilList.add(new Il(59, "TEKÝRDAÐ"));
		ilList.add(new Il(60, "TOKAT"));
		ilList.add(new Il(61, "TRABZON"));
		ilList.add(new Il(62, "TUNCELÝ"));
		ilList.add(new Il(64, "UÞAK"));
		ilList.add(new Il(65, "VAN"));
		ilList.add(new Il(77, "YALOVA"));
		ilList.add(new Il(66, "YOZGAT"));
		ilList.add(new Il(67, "ZONGULDAK"));
		
		
		ilListStr = new ArrayList<String>();
	}

	public List<Il> getIlList() 
	{
		return ilList;
	}
	
	
	public Il findIlByIlKodu(int ilKodu)
	{
	   Il result = new Il(0,"");
	   for (Il il : ilList) 
	   {
		 if (il.getKodu()==ilKodu)
		 {
			 result = il;
			 break;
		 }
	   }
	   return result;
	}
	
	public ArrayList<String> toStringList()
	{
		ilListStr.clear();
		for (Il il : ilList) 
		{
			ilListStr.add(il.getAdi());	 
		}
		return ilListStr;
	}

}
