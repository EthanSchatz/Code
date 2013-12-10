package system;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
/**
 * Immutable Record of Services Rendered
 * @author TL
 *
 */
public class Claim extends Data {
	
	private static final long serialVersionUID = 1L;
	private Calendar dateOfService;
	private Calendar dateOfClaim;
	private int memberNumber;
	private int providerNumber;
	private int serviceNumber;
	private String comment;
	private DateFormat form2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	private DateFormat form1 = new SimpleDateFormat("MM/dd/yyyy");
	
	Claim(int id, Calendar dateOfService, Calendar dateOfClaim, int memberNumber, int providerNumber, int serviceNumber, String comment){
		super(id);
		this.dateOfService = dateOfService;
		this.dateOfClaim = dateOfClaim;
		this.memberNumber = memberNumber;
		this.providerNumber = providerNumber;
		this.serviceNumber = serviceNumber;
		this.comment = comment;
	}

	/**
	 * @return the dateOfService
	 */
	public String getDateOfService() {
		return form1.format(dateOfService.getTime());
	}

	/**
	 * @return the dateOfClaim
	 */
	public String getDateOfClaim() {
		return form2.format(dateOfClaim.getTime());
	}

	/**
	 * @return the memberNumber
	 */
	public int getMemberNumber() {
		return memberNumber;
	}

	/**
	 * @return the providerNumber
	 */
	public int getProviderNumber() {
		return providerNumber;
	}

	/**
	 * @return the serviceNumber
	 */
	public int getServiceNumber() {
		return serviceNumber;
	}
	
	public String dataToString(){
		String temp = new String();
		temp += "ID: \n";
		temp += this.getID() + "\n";
		temp += "Date Of Service: \n";
		temp += form1.format(dateOfService.getTime()) + "\n";
		temp += "Date Of Claim: \n";
		temp += form2.format(dateOfClaim.getTime()) + "\n";
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
}
