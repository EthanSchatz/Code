package maintainers;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import system.ChocAnSystem;
import system.MemberData;
import system.ServiceData;
import exceptions.*;

public class ServiceCharger {
	private Calendar dateOfService = null;
	private int memberNumber = 0;
	private int providerNumber;
	private int serviceNumber = 0;
	private String comment = " ";
	private ChocAnSystem sys;
	private DateFormat form1 = new SimpleDateFormat("MM/dd/yyyy");
	/**
	 * Takes a providerID and checks if valid. 
	 * @param providerID Provider's ID
	 * @throws InvalidIDException Thrown if invalid ID
	 */
	public ServiceCharger(int providerID) throws IOException,InvalidIDException{
		sys = new ChocAnSystem();
		
		sys.getData(providerID);
		providerNumber = providerID;
	}
	/**
	 * Sets Month, Day, and Year for the date the service occurred.
	 * @param month Month (MM) of date service occurred.
	 * @param day	Day (dd)of date service occurred.
	 * @param year	Year (yyyy) of date service occurred.
	 */
	public void setDateOfService(int month, int day, int year){
		dateOfService = Calendar.getInstance();
		dateOfService.set(year, month, day);
	}
	
	/**
	 * Clarifies to whom the service was given (as indicated by memberNumber)
	 * @param memberNumber Member's ID
	 */
	public void setMemberNumber(int memberNumber) throws InvalidIDException{
		try {
			MemberData temp = (MemberData)sys.getData(memberNumber);
			this.memberNumber = memberNumber;
			if(temp.getStatusInt() == 2){
				throw new InvalidIDException();
			}
			temp = null;
		}
		catch(IOException e){
			e.printStackTrace(); //INVALID MEMBER
		}
	}
	
	public String getServiceData(int serviceNumber) throws IOException, InvalidIDException{
		ServiceData temp = (ServiceData)sys.getData(serviceNumber);
		this.serviceNumber = serviceNumber;
		String deliverable = temp.dataToString();
		temp = null;
		return deliverable;
		
		
	}
	
	/**
	 * Clarifies the id of the service provided
	 * @param serviceNumber Service ID
	 */
	public void setServiceNumber(int serviceNumber) throws IOException{
		try{
			sys.getData(serviceNumber);
			this.serviceNumber = serviceNumber;
			//System.out.println(temp.dataToString());
			
		}
		catch(InvalidIDException e){
			
		}
	}
	public int getServiceNumber(){
		return serviceNumber;
	}
	/**
	 * 
	 * @param comment
	 */
	public void setComment(String comment){
		this.comment = comment;
	}
	
	public String getData(){
		String temp = new String();
		temp += "Date Of Service: \n";
		temp += form1.format(dateOfService.getTime()) + "\n";
		temp += "Provider Number: \n";
		temp += Integer.toString(providerNumber) + "\n";
		temp += "Service Number: \n";
		temp += Integer.toString(serviceNumber) + "\n";
		temp += "Member Number: \n";
		temp += Integer.toString(memberNumber) + "\n";
		temp += "Comment: \n";
		temp += comment + "\n";
		return temp;
	}
	
	public void submitClaim() throws IncompleteFieldException{
		if (serviceNumber == 0 || memberNumber == 0 || dateOfService == null){
			throw new IncompleteFieldException();
		}
		else{
			try{
				sys.submitNewClaim(dateOfService, memberNumber, providerNumber, serviceNumber,comment);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	

}
