package system;

public class MemberData extends Data {
	
	private static final long serialVersionUID = 1L;
	private String name = null;
	private String address = null;
	private String city = null;
	private String state = null;
	private int zipcode = 0;
	private String email = null;
	private int status = 0;
	private String statusStr = null;
	public static final int ACCEPTED = 1;
	public static final int SUSPENDED = 2;
	
	
	public MemberData(int id){
		super(id);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipcode
	 */
	public int getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the status
	 */
	public int getStatusInt() {
		return status;
	}
	
	public String getStatus(){
		return Integer.toString(status);
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
		if (status == 1){
			statusStr = "GOOD STANDING";
		}
		else if(status == 2){
			statusStr = "SUSPENDED";
		}
	}
	
	public String dataToString(){
		String temp = new String();
		temp += "ID: \n";
		temp += this.getID() + "\n";
		temp += "Name: \n";
		temp += name + "\n";
		temp += "Address: \n";
		temp += address + "\n";
		temp += "City: \n";
		temp += city + "\n";
		temp += "State: \n";
		temp += state + "\n";
		temp += "Zipcode: \n";
		temp += Integer.toString(zipcode) + "\n";
		temp += "Email: \n";
		temp += email + "\n";
		temp += "Status: \n";
		temp += statusStr + "\n";//CHANGE FROM INT TO ACCEPTED
		return temp;
	}
}
