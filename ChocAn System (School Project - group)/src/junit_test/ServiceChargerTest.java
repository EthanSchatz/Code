package junit_test;

import system.*;

import java.io.IOException;

import exceptions.*;
import static org.junit.Assert.*;
import maintainers.ServiceCharger;

import org.junit.Before;
import org.junit.Test;
//AUTHOR Thomas Lewallen
/**
 * JUnit test to make sure ServiceCharger is working and the date functionality in 
 * submitNewClaim() is working
 * Time from two different submission should be different
 * @author TL
 *
 */
public class ServiceChargerTest {
	int serID = 100111;
	int proID = 100111111;
	int memID = 500111111;
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		ChocAnSystem sys = new ChocAnSystem();
		try{
			ProviderData pd = new ProviderData(proID);
			pd.setAddress("ADDRESS");
			pd.setCity("CITY");
			pd.setEmail("EMAIL");
			pd.setName("NAME");
			pd.setState("STATE");
			pd.setZipcode(12345);
			pd.setType(1);
			MemberData md = new MemberData(memID);
			md.setAddress("ADDRESS");
			md.setCity("CITY");
			md.setEmail("EMAIL");
			md.setName("NAME");
			md.setState("STATE");
			md.setZipcode(12345);
			md.setStatus(1);
			ServiceData sd = new ServiceData(serID);
			sd.setServiceFee(400.50);
			sd.setServiceName("SERVICE NAME");
			sys.submitData(pd);
			sys.submitData(md);
			sys.submitData(sd);
			
			
			ServiceCharger sc = new ServiceCharger(proID);
			sc.setDateOfService(6, 10, 1992);
			sc.setMemberNumber(memID);
			sc.setServiceNumber(serID);
			sc.submitClaim();
			
			java.lang.Thread.sleep(500l);
			sc.submitClaim();
			int temp = sys.getNewID(ChocAnSystem.CLAIM);
			Claim c1 = (Claim)sys.getData(temp-1);
			Claim c2 = (Claim)sys.getData(temp-2);
			
			System.out.println(c1.dataToString());
			System.out.println(c2.dataToString());
			
			assertNotEquals(c1.getDateOfClaim(), c2.getDateOfClaim());
			assertEquals(c1.getDateOfService(), c2.getDateOfService());
			assertEquals(c1.getMemberNumber(), c2.getMemberNumber());
			assertEquals(c1.getProviderNumber(), c2.getProviderNumber());
			assertEquals(c1.getServiceNumber(), c2.getServiceNumber());
			
		}
		catch(InvalidIDException e){
			e.printStackTrace();
		}
		catch(InvalidTypeException e){
			e.printStackTrace();
		}
		catch(IncompleteFieldException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
