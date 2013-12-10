package system;

public class ServiceData extends Data{
	private static final long serialVersionUID = 1L;
	private String serviceName = null;
	private double serviceFee = 0;
	
	public ServiceData(int id){
		super(id);
	}
	public String getServiceName(){
		return serviceName;	
	}
	
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}
	
	public double getServiceFee(){
		return serviceFee;
	}
	
	public void setServiceFee(double serviceFee){
		this.serviceFee = serviceFee;
	}
	
	public String dataToString(){
		String temp = new String();
		temp += "ID: \n";
		temp += this.getID() + "\n";
		temp += "Service Name: \n";
		temp += serviceName + "\n";
		temp += "Service Fee: \n";
		temp += "$" + Double.toString(serviceFee) + "\n";
		return temp;
	}
}
