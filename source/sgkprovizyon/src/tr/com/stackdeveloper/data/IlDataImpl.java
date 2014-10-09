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
		ilList.add(new Il(3, "AFYONKARAH�SAR"));
		ilList.add(new Il(4, "A�RI"));
		ilList.add(new Il(68, "AKSARAY"));
		ilList.add(new Il(5, "AMASYA"));
		ilList.add(new Il(6, "ANKARA"));
		ilList.add(new Il(7, "ANTALYA"));
		ilList.add(new Il(75, "ARDAHAN"));
		ilList.add(new Il(8, "ARTV�N"));
		ilList.add(new Il(9, "AYDIN"));
		ilList.add(new Il(10, "BALIKES�R"));
		ilList.add(new Il(74, "BARTIN"));
		ilList.add(new Il(72, "BATMAN"));
		ilList.add(new Il(69, "BAYBURT"));
		ilList.add(new Il(11, "B�LEC�K"));
		ilList.add(new Il(12, "B�NG�L"));
		ilList.add(new Il(13, "B�TL�S"));
		ilList.add(new Il(14, "BOLU"));
		ilList.add(new Il(15, "BURDUR"));
		ilList.add(new Il(16, "BURSA"));
		ilList.add(new Il(17, "�ANAKKALE"));
		ilList.add(new Il(18, "�ANKIRI"));
		ilList.add(new Il(19, "�ORUM"));
		ilList.add(new Il(20, "DEN�ZL�"));
		ilList.add(new Il(21, "D�YARBAKIR"));
		ilList.add(new Il(81, "D�ZCE"));
		ilList.add(new Il(22, "ED�RNE"));
		ilList.add(new Il(23, "ELAZI�"));
		ilList.add(new Il(24, "ERZ�NCAN"));
		ilList.add(new Il(25, "ERZURUM"));
		ilList.add(new Il(26, "ESK��EH�R"));
		ilList.add(new Il(27, "GAZ�ANTEP"));
		ilList.add(new Il(28, "G�RESUN"));
		ilList.add(new Il(29, "G�M��HANE"));
		ilList.add(new Il(30, "HAKKAR�"));
		ilList.add(new Il(31, "HATAY"));
		ilList.add(new Il(76, "I�DIR"));
		ilList.add(new Il(32, "ISPARTA"));
		ilList.add(new Il(34, "�STANBUL"));
		ilList.add(new Il(35, "�ZM�R"));
		ilList.add(new Il(46, "KAHRAMANMARA�"));
		ilList.add(new Il(78, "KARAB�K"));
		ilList.add(new Il(70, "KARAMAN"));
		ilList.add(new Il(36, "KARS"));
		ilList.add(new Il(37, "KASTAMONU"));
		ilList.add(new Il(38, "KAYSER�"));
		ilList.add(new Il(71, "KIRIKKALE"));
		ilList.add(new Il(39, "KIRKLAREL�"));
		ilList.add(new Il(40, "KIR�EH�R"));
		ilList.add(new Il(79, "K�L�S"));
		ilList.add(new Il(41, "KOCAEL�"));
		ilList.add(new Il(42, "KONYA"));
		ilList.add(new Il(43, "K�TAHYA"));
		ilList.add(new Il(44, "MALATYA"));
		ilList.add(new Il(45, "MAN�SA"));
		ilList.add(new Il(47, "MARD�N"));
		ilList.add(new Il(33, "MERS�N"));
		ilList.add(new Il(48, "MU�LA"));
		ilList.add(new Il(49, "MU�"));
		ilList.add(new Il(50, "NEV�EH�R"));
		ilList.add(new Il(51, "N��DE"));
		ilList.add(new Il(52, "ORDU"));
		ilList.add(new Il(80, "OSMAN�YE"));
		ilList.add(new Il(53, "R�ZE"));
		ilList.add(new Il(54, "SAKARYA"));
		ilList.add(new Il(55, "SAMSUN"));
		ilList.add(new Il(56, "S��RT"));
		ilList.add(new Il(57, "S�NOP"));
		ilList.add(new Il(58, "S�VAS"));
		ilList.add(new Il(63, "�ANLIURFA"));
		ilList.add(new Il(73, "�IRNAK"));
		ilList.add(new Il(59, "TEK�RDA�"));
		ilList.add(new Il(60, "TOKAT"));
		ilList.add(new Il(61, "TRABZON"));
		ilList.add(new Il(62, "TUNCEL�"));
		ilList.add(new Il(64, "U�AK"));
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
