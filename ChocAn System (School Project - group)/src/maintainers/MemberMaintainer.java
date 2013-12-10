package maintainers;
import java.io.IOException;

import system.ChocAnSystem;
import system.MemberData;
import exceptions.*;

public class MemberMaintainer {
	
	private ChocAnSystem sys;
	private MemberData data = null;
	private boolean isNew = false;

	public MemberMaintainer(int id) throws IOException, InvalidIDException{
		sys = new ChocAnSystem();
		data = (MemberData)sys.getData(id);
	}
	
	public MemberMaintainer(){
		sys = new ChocAnSystem();
		try{
			data = (MemberData)sys.createNewData(ChocAnSystem.MEMBER);
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InvalidTypeException e){
			e.printStackTrace();
		}
		data.setStatus(1);
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
	public void setStatus(int status){
		data.setStatus(status);
	}
	public void deleteMember(){
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
		else{
			sys.submitData(data);
		}
				
	}
	
	
	
	
	
	
	
	

}
