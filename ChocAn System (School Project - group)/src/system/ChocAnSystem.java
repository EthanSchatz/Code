package system;
import java.io.*;
import java.util.*;
import java.text.*;

import exceptions.*;

/**
 * Main Controller for interfacing with file system (All saved Objects/Data)
 * @author TL
 *
 */
public class ChocAnSystem {
	public static String memberPath = "data/members/";
	public static String servicePath = "data/services/";
	public static String providerPath = "data/providers/";
	public static String claimPath = "data/claims/";
	
	public static String eftPath = "data/eft/";
	public static String reportPath = "data/reports/";
	
	private static String memberIDs = "data/memberIDs.txt";
	private static String claimIDs = "data/claimsIDs.txt";
	private static String providerIDs = "data/providerIDs.txt";
	private static String serviceIDs = "data/serviceIDs.txt";
	private static String eftIDs = "data/eftIDs.txt";
	
	private static int providerIDOffset = 100; //value of first ID, for use with class Random
	private static int memberIDOffset = 500;
	private static int numberOfProviderIDs =  memberIDOffset - providerIDOffset; 
	private static int numberOfMemberIDs = 999 - memberIDOffset;
	
	
	public static final int PROVIDER = 1;
	public static final int MEMBER = 2;
	public static final int SERVICE = 3;
	public static final int CLAIM = 4;
	public static final String EXTENSION = ".dt";
	


	/**
	 * Initializes Needed File System of
	 * 
	 * 		data/
	 * 		data/members/
	 * 		data/claims/
	 * 		data/providers/
	 * 		data/services
	 * 					  	
	 * If directory doesn't exist, it is created.
	 *
	 * @throws InvalidIDException
	 */
	
	public ChocAnSystem(){ //Constructor for Manager Operations
		checkFileSystem();
	}
	
	/**
	 * Retrieves data from file system. ID serves as filename. Throws IOException for non-existent file.
	 * @param id
	 * @return Data object
	 */
	public Data getData(int id) throws IOException,InvalidIDException{ //InvalidIDException originates in getIDType()
		Data object = null;
		String filename;
		filename = getFilePath(id);
		ObjectInputStream input = null;
		try {
			 input = new ObjectInputStream(new FileInputStream(filename));
			object = (Data)input.readObject();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		finally{
			try {
				if (input != null){
					input.close();
				}
			}
				catch(IOException e){
					e.printStackTrace();
				}
		}
		return object;	
	}
	/**
	 * returns data using a filename as a parameter
	 * @param filename location of data to be returned
	 * @return Data
	 * @throws IOException Thrown if file doesn't exist
	 */
	public Data getData(String filename) throws IOException{ //InvalidIDException originates in getIDType()
		Data object = null;
		ObjectInputStream input = null;
		try {
			 input = new ObjectInputStream(new FileInputStream(filename));
			object = (Data)input.readObject();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		finally{
			try {
				if (input != null){
					input.close();
				}
			}
				catch(IOException e){
					e.printStackTrace();
				}
		}
		return object;	
	}
	
	/**
	 * Returns a list of paths to all saved claim data
	 * @return List<String> 
	 */
	public List<String> getAllClaims(){
		List<String> deliverable = new ArrayList<String>();
		File claims = new File(claimPath);
		File[] allClaims = claims.listFiles();
		for (File file: allClaims){
			deliverable.add(file.getPath());
		}
		return deliverable;
	}
	
	/**
	 * Returns a list of paths to all saved Provided Data
	 * @return List<String>
	 */
	public List<String> getAllProviders(){
		List<String> deliverable = new ArrayList<String>();
		File providers = new File(providerPath);
		File[] allProviders = providers.listFiles();
		for (File file: allProviders){
			deliverable.add(file.getPath());
		}
		return deliverable;
	}
	
	/**
	 * Returns a list of paths to all saved Member Data
	 * @return List<String>
	 */
	public List<String> getAllMembers(){
		List<String> deliverable = new ArrayList<String>();
		File members = new File(memberPath);
		File[] allMembers = members.listFiles();
		for (File file: allMembers){
			deliverable.add(file.getPath());
		}
		return deliverable;
	}
	
	/**
	 * Returns a list of paths to all saved Service Data
	 * @return List<String>
	 */
	public List<String> getAllServices(){
		List<String> deliverable = new ArrayList<String>();
		File services = new File(servicePath);
		File[] allServices = services.listFiles();
		for (File file: allServices){
			deliverable.add(file.getPath());
		}
		return deliverable;
	}
	
	
	/**
	 * Deletes the file specified by id ("id".dt)
	 * @param id
	 */
	public void deleteData(int id){
		String filename;
		try{
			filename = getFilePath(id);
			File toDelete = new File(filename);
			if (toDelete.exists()){
				if (toDelete.delete()){
					System.out.println("DELETED");
				}
			}
		}
		catch(InvalidIDException e){
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Saves a data type, d at the appropriate location
	 * @param d
	 */
	public void submitData(Data d){
		int id = d.getID();
		try {
			String filename = getFilePath(id);
			File creator = new File(filename);
			if (!creator.exists()){
				creator.createNewFile();
			}
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename));
			output.writeObject(d);
			output.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}		
		catch(InvalidIDException e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Returns a new object of type, type
	 * @param type 
	 * @return Child of Data (ProviderData,MemberData,ServiceData)
	 */
	public Data createNewData(int type)throws IOException, InvalidTypeException{
		if (type == CLAIM){
			throw new InvalidTypeException("Can't create new blank Claim. Use submitNewCLaim");
		}
		int id = getNewID(type);
		switch (type){
			case PROVIDER:  return new ProviderData(id);

			case MEMBER:    return new MemberData(id);

			case SERVICE:   return new ServiceData(id);

			default:        return null;
		}	
	}
	
	/**
	 * Creates and saves a new, immutable claim data file
	 * @param dateOfService
	 * @param memberNumber
	 * @param providerNumber
	 * @param serviceNumber
	 * @throws IOException
	 */
	public void submitNewClaim(Calendar dateOfService, int memberNumber, int providerNumber, int serviceNumber, String comment) throws IOException{
		Calendar dateOfClaim = Calendar.getInstance();
		try{
			int id = getNewID(CLAIM);
			Claim newClaim = new Claim(id,dateOfService, dateOfClaim, memberNumber, providerNumber, serviceNumber, comment);
			submitData(newClaim);
		}		
		catch(InvalidTypeException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Helper method to determine what type a given id is
	 * @param id
	 * @return
	 */
	private int getIDType(int id) throws InvalidIDException{
		if (Integer.toString(id).length() == 9){
			if (id < (memberIDOffset * 1000000)){
				return PROVIDER;
			}
			else {
				return MEMBER;
			}
		}
		else if (Integer.toString(id).length() == 6){
			return SERVICE;
		}
		else if (Integer.toString(id).length() == 3){
			return CLAIM;
		}
		else {
			throw new InvalidIDException();
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws InvalidIDException
	 */
	private String getFilePath(int id) throws InvalidIDException{
		int type = getIDType(id);
		Calendar temp = Calendar.getInstance();
		DateFormat formatter = new SimpleDateFormat("MM_dd_yyyy");
		switch (type){
		case(PROVIDER): return providerPath + id + EXTENSION;
		case(MEMBER): 	return memberPath + id + EXTENSION;
		case(SERVICE): 	return servicePath + id + EXTENSION;
		case(CLAIM): 	return claimPath + formatter.format(temp.getTime())+" " + id + EXTENSION;
		default: throw new InvalidIDException();
		}
		
	}
	
	/**
	 * Reads and returns every line in a file as a List<String>
	 * @param filename
	 * @return List<String>, each element being a different line in file
	 * @throws IOException
	 */
	private List<String> readLinesFromFile(String filename) throws IOException{
		List<String> fileData = new ArrayList<String>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(filename));
			while (br.ready()){
				fileData.add(br.readLine());
			}
		}
		finally{
			try{
				if (br != null){
					br.close();
				}
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
		return fileData;
	}
	
	/**
	 * Writes every element in List<String> lines to a line in "filename"
	 * @param filename
	 * @param lines
	 * @throws IOException
	 */
	private void writeLinesToFile(String filename, List<String> lines) throws IOException{
		BufferedWriter bw = null;
		int numOfElements = lines.size();
		try{
			bw = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < numOfElements; i++){
				bw.write(lines.remove(0) + "\n");
			}
		}
		finally{
			try {
				if (bw != null){
					bw.close();
				}
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Produces a unique ID for any defined type of data
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws InvalidTypeException
	 */
	public int getNewID(int type) throws IOException, InvalidTypeException{
		Random generator = new Random();
		String filename;
		int range;
		int offset;
		List<String> unusedIDs;
		String id;
		switch (type){
			case PROVIDER: filename = providerIDs;
						   range = 899999;
						   offset = 100000;
			 			   break;
			case MEMBER:   filename = memberIDs;
						   range = 899999;
						   offset = 100000;
						   break;
			case CLAIM:    filename = claimIDs;
						   range = 0;
						   offset = 0;
						   break;
			case SERVICE:  filename = serviceIDs;
						   range = 899;
						   offset = 100;
						   break;
			default: 	   throw new InvalidTypeException();
						  
		}
		unusedIDs =  readLinesFromFile(filename);
		id = unusedIDs.remove(0);
		writeLinesToFile(filename, unusedIDs);
		if (filename != claimIDs){
			id = id + Integer.toString(generator.nextInt(range) + offset);
		}
		return Integer.parseInt(id);
	}
	
	/**
	 * Initialized required directory structure
	 * 		data/
	 * 		data/members/
	 * 		data/providers/
	 * 		data/claims/
	 * 		data/reports/
	 * 		data/.claimIDs.txt
	 * 		data/.providerIDs.txt
	 * 		data/.memberIDs.txt
	 * 		data/.serviceIDs.txt;
	 */
	private void checkFileSystem(){
		File FileSysChecker;
		FileSysChecker = new File(providerPath);
		if (!FileSysChecker.exists()){
			FileSysChecker.mkdirs();
		}
		FileSysChecker = new File(memberPath);
		if (!FileSysChecker.exists()){
			FileSysChecker.mkdirs();
		}
		FileSysChecker = new File(servicePath);
		if (!FileSysChecker.exists()){
			FileSysChecker.mkdirs();
		}
		FileSysChecker = new File(claimPath);
		if (!FileSysChecker.exists()){
			FileSysChecker.mkdirs();
		}
		FileSysChecker = new File(eftPath);
		if (!FileSysChecker.exists()){
			FileSysChecker.mkdirs();
		}
		FileSysChecker = new File(reportPath);
		if (!FileSysChecker.exists()){
			FileSysChecker.mkdirs();
		}
		FileSysChecker = new File(claimIDs);
		if (!FileSysChecker.exists()){
			List<String> lines = new ArrayList<String>();
			for (int i = 100; i < 1000; i++){
				lines.add(Integer.toString(i));
			}
			try{
				FileSysChecker.createNewFile();
				writeLinesToFile(claimIDs, lines);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		FileSysChecker = new File(eftIDs);
		if (!FileSysChecker.exists()){
			List<String> lines = new ArrayList<String>();
			for (int i = 100; i < 10000; i++){
				lines.add(Integer.toString(i));
			}
			try{
				FileSysChecker.createNewFile();
				writeLinesToFile(eftIDs, lines);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		FileSysChecker = new File(serviceIDs);
		if (!FileSysChecker.exists()){
			List<String> lines = new ArrayList<String>();
			for (int i = 100; i < 1000; i++){
				lines.add(Integer.toString(i));
			}
			try{
				FileSysChecker.createNewFile();
				writeLinesToFile(serviceIDs, lines);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}	
		
		FileSysChecker = new File(memberIDs);
		if (!FileSysChecker.exists()){
			if (!FileSysChecker.exists()){
				List<String> lines = new ArrayList<String>();
				for (int i = 0; i < numberOfMemberIDs; i++){
					lines.add(Integer.toString(memberIDOffset + i));
				}
				try{
					FileSysChecker.createNewFile();
					writeLinesToFile(memberIDs, lines);
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		FileSysChecker = new File(providerIDs);
		if (!FileSysChecker.exists()){
			List<String> lines = new ArrayList<String>();
			for (int i = 0; i < numberOfProviderIDs; i++){
				lines.add(Integer.toString(providerIDOffset + i));
			}
			try{
				FileSysChecker.createNewFile();
				writeLinesToFile(providerIDs, lines);
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}
