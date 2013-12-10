package system;

public abstract class Data implements java.io.Serializable{

	/**
	 * Serial ID to ensure consistent reading from serialized objects in files
	 */
	private static final long serialVersionUID = 1L;
	private int ID;
	
	/**
	 * Every Data object has an ID. This is used to, what else, identify it
	 * @param id
	 */
	Data(int id){
		ID = id;
	}
	
	/** 
	 * Getter for ID
	 * @return int
	 */
	public int getID(){
		return ID;
	}
	
	public abstract String dataToString();
}
