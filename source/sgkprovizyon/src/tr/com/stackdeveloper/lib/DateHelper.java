package tr.com.stackdeveloper.lib;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import android.annotation.SuppressLint;
import android.net.ParseException;
import android.util.Log;

public class DateHelper {


	/**
	 * Günün index'inde günün ismini döndürür.
	 * 1 = Pazartesi
	 * 2 = Salý
	 * .
	 * .
	 * .
	 * @param dayIndex
	 * @return
	 */
	@SuppressLint("UseSparseArrays") 
	public static String getDateName(int dayIndex)
	{
		HashMap<Integer, String> dateMap = new HashMap<Integer, String>();
		dateMap.put(Calendar.SUNDAY, Constant.PAZAR);
		dateMap.put(Calendar.MONDAY, Constant.PAZARTESI);
		dateMap.put(Calendar.TUESDAY, Constant.SALI);
		dateMap.put(Calendar.WEDNESDAY, Constant.CARSAMBA);
		dateMap.put(Calendar.THURSDAY, Constant.PERSEMBE);
		dateMap.put(Calendar.FRIDAY, Constant.CUMA);
		dateMap.put(Calendar.SATURDAY, Constant.CUMARTESI);
		return dateMap.get(dayIndex);
	}
	

	/**
	 * Ýçinde bulunduðumuz haftanýn pazartesi gününün tarihi verir.
	 * @return
	 */
	public static Date getDateOfMonday()
	{
		Calendar cal = Calendar.getInstance();
		while(cal.get(Calendar.DAY_OF_WEEK)!=Calendar.MONDAY){
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		Date tarih = new Date(cal.getTimeInMillis());
		return tarih;
	}
	

	/**
	 * Ýçinde bulunduðumuz haftanýn cuma günün tarihi döndürür.
	 * @return
	 */
	public static Date getDateOfFriday()
	{
		Date d = getDateOfMonday();
		d.setDate(d.getDate()+4);
		return d;
	}

	
 
	/**
	 * Tarihin gün olarak karþýlýðýný döndürür.
	 * @param date
	 * @return
	 */
	public static String getDateName(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayIndex = c.get(Calendar.DAY_OF_WEEK);
		return getDateName(dayIndex);
	}
	
	/**
	 * Günün tarihini döndürü.
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getCurrentDate() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		String formattedDate = df.format(c.getTime());
		return formattedDate;
	}

	/**
	 * String tipteki tarih parametresini Date tipine döndürür.
	 * @param date dd.MM.yyyy formatýnda olmalý
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date stringToDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date pDate = format.parse(date);
			return pDate;
		} catch (ParseException e) {
			Log.d("stringToDate", "stringToDate parselenemedi");
		} catch (java.text.ParseException e) {
			Log.d("stringToDate", "stringToDate parselenemedi2");
		}
		return null;
	}

	/**
	 * Gün,Ay,Yýl parametresinden dd.MM.yyyy formatýnda tarih döndürür.
	 * @param sDay Gün
	 * @param sMonth Ay
	 * @param sYear Yýl
	 * @return
	 */
	public static String getDateFrom(int sDay,int sMonth,int sYear){
		// ay sýfýr tabanlý olduðu için 1 ekle...
	   StringBuilder sb = new StringBuilder();
	   String formattedDay= sDay < 10 ?  "0"+Integer.toString(sDay):Integer.toString(sDay);	
	   String formattedMonth= sMonth < 10 ?  "0"+Integer.toString(sMonth+1):Integer.toString(sMonth+1);	
	   String selectedDate=sb.append(formattedDay).append(".").append(formattedMonth).append(".").append(sYear).toString();
	   return selectedDate;
	}
	
	
	/**
	 * Date tipindeki parametreyi,dd.MM.yyyy String olarak döndürür.
	 * @param date tarih
	 * @return
	 */
	@SuppressLint("SimpleDateFormat") 
	public static String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		try {
			String stringDate = dateFormat.format(date);
			return stringDate;
		} catch (ParseException e) {
			Log.d("stringToDate", "stringToDate parselenemedi");
		}
		return null;
	}
	
	
	/**
	 * Tarihe belirtilen gün kadar ekler.
	 * @param date tarih
	 * @param incValue tarihe gün olarak eklenecek deðer
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date dateInc(String date,int incValue) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date pDate = format.parse(date);
			pDate.setDate(pDate.getDate()+incValue);
			return pDate;
		} catch (ParseException e) {
			Log.d("stringToDate", "stringToDate parselenemedi");
		} catch (java.text.ParseException e) {
			Log.d("stringToDate", "stringToDate parselenemedi2");
		}
		return null;
	}
	
	
	/**
	 * date parametresi ile belirtilen tarihe,decValue deðeri kadar ekler.
	 * @param date
	 * @param decValue
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date dateDec(String date,int decValue) {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		try {
			Date pDate = format.parse(date);
			pDate.setDate(pDate.getDate()-decValue);
			return pDate;
		} catch (ParseException e) {
			Log.d("stringToDate", "stringToDate parselenemedi");
		} catch (java.text.ParseException e) {
			Log.d("stringToDate", "stringToDate parselenemedi2");
		}
		return null;
	}
	
	/**
	 * dd.MM.yyyy formatýndaki tarihin gün olarak karþýlýðýný bulur 
	 * @param date
	 * @return
	 */
	public static String getDateName(String date)
	{
		Date cDate = stringToDate(date);
		return getDateName(cDate);
	}
	
	/**
	 * Ýki Saat arasýndaki farký,saat cinsinden hesaplar.
	 * Kullaným : DateHelper.hourSpan("08:00", "12:00");
	 * @param startTime baþlangýç saati
	 * @param finishTime bitiþ saati
	 * @return
	 */
	@SuppressLint("SimpleDateFormat") 
	public static double hourSpan(String startTime,String finishTime)
	{
		SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		double fark = 0;
		double saat = 0;
		try 
		{
		   Date dStart = parser.parse(startTime);
		   Date dFinish = parser.parse(finishTime);
		   fark = dFinish.getTime() - dStart.getTime();
		   saat = fark / (1000 * 60 * 60);
		} catch (java.text.ParseException e) 
		{
			saat = 0;
		}
		return saat;
	}
	
}
