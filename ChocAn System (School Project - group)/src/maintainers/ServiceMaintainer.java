package maintainers;
import java.io.IOException;

import system.ChocAnSystem;
import system.ServiceData;
import exceptions.*;

public class ServiceMaintainer {
	
	private ServiceData data;
	private ChocAnSystem sys;
	
	private boolean isNew = false;
	/**
	 * Attempts to create new ServiceMaintainer with a new ChocAnSystem. Checks ID for match in filesystem 
	 * Used to edit an exiting ServiceData instance
	 * @param id
	 * @throws IOException thrown if id doesn't exist
	 * @throws InvalidIDException thrown if id doesn't match a recognized format in ChocAnSystem
	 */
	public ServiceMaintainer(int id) throws IOException, InvalidIDException{
		sys = new ChocAnSystem();
		
		data = (ServiceData)sys.getData(id);
		
	}
	
	/**
	 * Constructor that creates a new ServiceData instance
	 */
	public ServiceMaintainer(){
		sys = new ChocAnSystem();
		try{
			data = (ServiceData)sys.createNewData(ChocAnSystem.SERVICE);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InvalidTypeException e){
			e.printStackTrace();
		}
		isNew = true;
	}
	
	/**
	 * Returns data in ServiceData instance in a readable string
	 * @return String
	 */
	public String getData(){
		return data.dataToString();
	}
	
	/**
	 * Setter for name field in SeviceData
	 * @param name sets field serviceName to name
	 */
	public void setServiceName(String name){
		data.setServiceName(name);
	}
	
	/**
	 * Setter for serviceFee field in ServiceData
	 * @param fee double to set serviceFee to
	 */
	public void setServiceFee(double fee){
		data.setServiceFee(fee);
	}
	
	/**
	 * Passes message to ChocAnSystem to delete the current data
	 */
	public void deleteData(){
		if (!isNew){
			sys.deleteData(data.getID());
		}
	}
	/**
	 * Submits ServiceData to ChocAnSystem to serialize and store in filesystem
	 * @throws IncompleteFieldException Thrown if any fields are incomplete
	 */
	public void submitData() throws IncompleteFieldException{
		if (!isNew){
			sys.submitData(data);
		}
		else if(data.getServiceFee() == 0 ||
				data.getServiceName() == null){
			throw new IncompleteFieldException();
		}
		else{
			sys.submitData(data);
		}
	}

}
