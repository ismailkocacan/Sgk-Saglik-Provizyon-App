package tr.com.stackdeveloper.sgkprovizyon.test;

import tr.com.stackdeveloper.ws.WsProvizyonSorgulama;
import junit.framework.TestCase;

public class TestWsProvizyonSorgulama extends TestCase 
{

	private WsProvizyonSorgulama provizyonSorgu;
	
	public TestWsProvizyonSorgulama(String name) 
	{
		super(name);		
		provizyonSorgu = new WsProvizyonSorgulama();
	}

	public void test_provizyon_sorgu()
	{
		provizyonSorgu.execute();
		assertFalse(!provizyonSorgu.getResponseObject().getSicilNo().contains("????????????"));
	}
	
	protected void setUp() throws Exception 
	{
		super.setUp();
		provizyonSorgu.getRequestObject().setTcKimlikNo("("????????????"));");
		provizyonSorgu.getRequestObject().setGuid("5b09ca9b-62eb-492a-9fc1-a3faea102f44");
		provizyonSorgu.getRequestObject().setProvizyonTarihi("26.05.2014");
		provizyonSorgu.getRequestObject().setUygId(0);
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

}
