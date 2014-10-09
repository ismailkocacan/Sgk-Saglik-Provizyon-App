package tr.com.stackdeveloper.sgkprovizyon.test;

import tr.com.stackdeveloper.data.IlDataImpl;
import junit.framework.TestCase;

public class TestIlDataImpl extends TestCase {

	private IlDataImpl ilDataImpl;
	
	public TestIlDataImpl(String name) 
	{
		super(name);
		ilDataImpl = new IlDataImpl();
	}

	protected void setUp() throws Exception 
	{
		super.setUp();
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}
	
	
	public void test_find_il_by_il_kodu()
	{
		assertFalse(ilDataImpl.findIlByIlKodu(10).getKodu() != 10);
	}


}
