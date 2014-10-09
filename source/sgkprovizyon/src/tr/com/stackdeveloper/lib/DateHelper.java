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
	 * G�n�n index'inde g�n�n ismini d�nd�r�r.
	 * 1 = Pazartesi
	 * 2 = Sal�
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
	 * ��inde bulundu�umuz haftan�n pazartesi g�n�n�n tarihi verir.
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
	 * ��inde bulundu�umuz haftan�n cuma g�n�n tarihi d�nd�r�r.
	 * @return
	 */
	public static Date getDateOfFriday()
	{
		Date d = getDateOfMonday();
		d.setDate(d.getDate()+4);
		return d;
	}

	
 
	/**
	 * Tarihin g�n olarak kar��l���n� d�nd�r�r.
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
	 * G�n�n tarihini d�nd�r�.
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
	 * String tipteki tarih parametresini Date tipine d�nd�r�r.
	 * @param date dd.MM.yyyy format�nda olmal�
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
	 * G�n,Ay,Y�l parametresinden dd.MM.yyyy format�nda tarih d�nd�r�r.
	 * @param sDay G�n
	 * @param sMonth Ay
	 * @param sYear Y�l
	 * @return
	 */
	public static String getDateFrom(int sDay,int sMonth,int sYear){
		// ay s�f�r tabanl� oldu�u i�in 1 ekle...
	   StringBuilder sb = new StringBuilder();
	   String formattedDay= sDay < 10 ?  "0"+Integer.toString(sDay):Integer.toString(sDay);	
	   String formattedMonth= sMonth < 10 ?  "0"+Integer.toString(sMonth+1):Integer.toString(sMonth+1);	
	   String selectedDate=sb.append(formattedDay).append(".").append(formattedMonth).append(".").append(sYear).toString();
	   return selectedDate;
	}
	
	
	/**
	 * Date tipindeki parametreyi,dd.MM.yyyy String olarak d�nd�r�r.
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
	 * Tarihe belirtilen g�n kadar ekler.
	 * @param date tarih
	 * @param incValue tarihe g�n olarak eklenecek de�er
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
	 * date parametresi ile belirtilen tarihe,decValue de�eri kadar ekler.
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
	 * dd.MM.yyyy format�ndaki tarihin g�n olarak kar��l���n� bulur 
	 * @param date
	 * @return
	 */
	public static String getDateName(String date)
	{
		Date cDate = stringToDate(date);
		return getDateName(cDate);
	}
	
	/**
	 * �ki Saat aras�ndaki fark�,saat cinsinden hesaplar.
	 * Kullan�m : DateHelper.hourSpan("08:00", "12:00");
	 * @param startTime ba�lang�� saati
	 * @param finishTime biti� saati
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
