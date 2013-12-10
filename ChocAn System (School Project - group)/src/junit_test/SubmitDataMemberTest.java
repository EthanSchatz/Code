/*
 * Written by: Ethan Schatzline
 * JUnit Test Case for submitnewmember() in MemberMaintainer class
 */

package junit_test;
import maintainers.*;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.IncompleteFieldException;

public class SubmitDataMemberTest {
	
	private MemberMaintainer newmember;
	private int Status = 1;
	private String state = "AL";
	private String Address = "1234 Wally Dr.";
	private String Name = "Mr. Wally";
	private String City = "WallyTown";
	private String Email = "myemail@yahoo.com";
	private int Zipcode = 12345; 

	@Test
	public void test() {
		
		MemberMaintainer newmember = new MemberMaintainer();
		newmember.setAddress(Address);
		newmember.setName(Name);
		newmember.setCity(City);
		newmember.setEmail(Email);
		newmember.setZipcode(Zipcode);
		newmember.setStatus(Status);
		newmember.setState(state);
		String actual = newmember.getData();
		System.out.println(actual);
		try {
			newmember.submitData();
		} catch (IncompleteFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
