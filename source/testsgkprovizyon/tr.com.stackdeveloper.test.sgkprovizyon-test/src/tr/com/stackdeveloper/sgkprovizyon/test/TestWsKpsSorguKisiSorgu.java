package tr.com.stackdeveloper.sgkprovizyon.test;

import tr.com.stackdeveloper.ws.WsKpsSorguKisiSorgu;
import junit.framework.TestCase;

public class TestWsKpsSorguKisiSorgu extends TestCase 
{

	private WsKpsSorguKisiSorgu sorgu;
	
	public TestWsKpsSorguKisiSorgu(String name) 
	{
		super(name);
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
		sorgu = new WsKpsSorguKisiSorgu();
		sorgu.getRequestObject().setTcKimlikNo("0000000000");
		sorgu.getRequestObject().setCiltNo(000);
		sorgu.getRequestObject().setDogumYili(000);
		sorgu.getRequestObject().setNufusIlKod(10);
		sorgu.getRequestObject().setUyruk(1);
		sorgu.getRequestObject().setIkametTezkereNo("");
		sorgu.getRequestObject().setUygulamaKodu(0);
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}
	
	
	public void test_get_session_id()
	{
		sorgu.execute();
		assertFalse(sorgu.getResponseObject().getSessionId().length()==0);
	}
}
