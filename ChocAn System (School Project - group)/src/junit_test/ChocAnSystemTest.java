package junit_test;

import static org.junit.Assert.*;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import system.ChocAnSystem;
import system.MemberData;
import system.ProviderData;
import system.ServiceData;
import exceptions.InvalidIDException;

//AUTHOR Hansol Lee
public class ChocAnSystemTest {
	ChocAnSystem sys = new ChocAnSystem();
	int sdID = 111111;
	int memID = 500111111;
	int proID = 100111111;
	ServiceData sd;
	ProviderData pd;
	MemberData md;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		sd = new ServiceData(sdID);
		md = new MemberData(memID);
		pd = new ProviderData(proID);
		
		md.setAddress("ADDRESS");
		md.setCity("CITY");
		md.setEmail("EMAIL");
		md.setName("NAME");
		md.setState("STATE");
		md.setZipcode(12345);
		
		pd.setAddress("ADDRESS");
		pd.setCity("CITY");
		pd.setEmail("EMAIL");
		pd.setName("NAME");
		pd.setState("STATE");
		pd.setZipcode(12345);
		
		sd.setServiceFee(400.50);
		sd.setServiceName("SERVICE NAME");
		sys.submitData(sd);
		sys.submitData(md);
		sys.submitData(pd);
	}

	@Test
	public void test() {
		try{
			ServiceData sd2 = (ServiceData)sys.getData(sdID);
			
			assertNotNull(sd);
			assertNotNull(sd2);
			assertEquals(sd2.dataToString(),sd.dataToString());
			
			ProviderData pd2 = (ProviderData)sys.getData(proID);
			assertNotNull(pd);
			assertNotNull(pd2);
			assertEquals(pd.dataToString(), pd2.dataToString());
			
			MemberData md2 = (MemberData)sys.getData(memID);
			assertNotNull(md);
			assertNotNull(md2);
			assertEquals(md.dataToString(), md2.dataToString());
			}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InvalidIDException e){
			e.printStackTrace();
		}
	}

}
