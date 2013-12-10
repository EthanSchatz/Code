/*
 * Written by: Levy Averette
 * Tests submitData() in ProviderMaintainer
 */
package junit_test;
import maintainers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.IncompleteFieldException;

public class SubmitDataProviderTest {
	private ProviderMaintainer provider;
	private int Type = 1;
	private String state = "NC";
	private String Address = "1234 MyStreet";
	private String Name = "Levy Averette";
	private String City = "Fayetteville";
	private String Email = "someEmail@gmail.com";
	private int Zipcode = 11111; 

	@Test
	public void test() {
		
		ProviderMaintainer provider = new ProviderMaintainer();
		provider.setAddress(Address);
		provider.setName(Name);
		provider.setCity(City);
		provider.setEmail(Email);
		provider.setZipcode(Zipcode);
		provider.setStatus(Type);
		provider.setState(state);
		String actual = provider.getData();
		System.out.println(actual);
		try {
			provider.submitData();
		} catch (IncompleteFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
