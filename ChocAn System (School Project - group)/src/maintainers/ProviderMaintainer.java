package maintainers;

import java.io.IOException;

import system.ChocAnSystem;
import system.ProviderData;
import exceptions.IncompleteFieldException;
import exceptions.InvalidIDException;
import exceptions.InvalidTypeException;

public class ProviderMaintainer {
	
	private ProviderData data;
	private ChocAnSystem sys;
	
	private boolean isNew = false;

	public ProviderMaintainer(int id) throws IOException, InvalidIDException{
		sys = new ChocAnSystem();	
		
		data = (ProviderData)sys.getData(id);
		
		
	}
	
	public ProviderMaintainer(){
		sys = new ChocAnSystem();
		try{
			data = (ProviderData)sys.createNewData(ChocAnSystem.PROVIDER);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InvalidTypeException e){
			e.printStackTrace();
		}
		isNew = true;
	}
	
	public String getData(){
		return data.dataToString();
	}
	
	public void setName(String name){
		data.setName(name);
	}
	public void setAddress(String address){
		data.setAddress(address);
	}
	public void setCity(String city){
		data.setCity(city);
	}
	public void setZipcode(int zipcode){
		data.setZipcode(zipcode);
	}
	public void setEmail(String email){
		data.setEmail(email);
	}
	public void setState(String state){
		if (state.length() == 2){
			data.setState(state);
		}
	}
	public void setStatus(int type){
		data.setType(type);
	}
	public void deleteProvider(){
		if (!isNew){
			sys.deleteData(data.getID());
		}
	}
	
	public void submitData() throws IncompleteFieldException{
		if (!isNew){
			sys.submitData(data);
		}
		else if (data.getAddress() == null ||
			data.getName() == null ||
			data.getCity() == null ||
			data.getState() == null ||
			data.getEmail() == null ||
			data.getZipcode() == 0){
			throw new IncompleteFieldException();
		}
		sys.submitData(data);		
	}

}
