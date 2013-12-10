package system;

public class ProviderData extends Data{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String city;
	private String state;
	private int zipcode;
	private String email;
	private int type;
	
	public ProviderData(int id){
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
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	public String dataToString(){
		String temp = new String();
		temp += "ID: \n";
		temp += this.getID() + "\n";
		temp += "Name: ";
		temp += name + "\n";
		temp += "Address: ";
		temp += address + "\n";
		temp += "City: ";
		temp += city + "\n";
		temp += "State: ";
		temp += state + "\n";
		temp += "Zipcode: ";
		temp += Integer.toString(zipcode) + "\n";
		temp += "Email: ";
		temp += email + "\n";
		temp += "Type: ";
		temp += (Integer.toString(type) + "\n");//CHANGE FROM INT TO ACCEPTED
		return temp;
	}

}
