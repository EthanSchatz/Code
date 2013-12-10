package frontend;

import java.io.IOException;

import maintainers.*;
import exceptions.*;
import java.util.Scanner;

/**
 * Holds interactive subroutines used by @class ManagerMenu and @class ProviderMenu
 * @author TL
 *
 */
public class Operations {
	private Scanner sn;
	
	/**
	 * uses same scanner as @class ManagerMenu @class ProviderMenu to prevent any
	 * possible conflicts that could arise from multiple scanner instances
	 * @param sn
	 */
	public Operations(Scanner sn) {
		this.sn = sn;
	}
	/**
	 * Interactive algorithm for adding a new @class ServiceData instance
	 * Polls user for all information needed
	 */
	protected void newService(){
		double tempDb;
		String tempStr;
		ServiceMaintainer sm;
		sm = new ServiceMaintainer();
		
		while(true){
			System.out.println("Enter Service Name: ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 20){
				sm.setServiceName(tempStr);
				break;
			}
			else{
				System.out.println("Service Name must be <= 20 Characters");
			}
		}
		while(true){
			System.out.println("Enter Service Fee: ");
			tempDb = Double.parseDouble(sn.nextLine());
			if (tempStr.length() <= 20){
				sm.setServiceFee(tempDb);
				break;
			}
			else{
				//System.out.println("Service Name must be <= 20 Characters");
			}
		}
		System.out.println("Submit the following New Service?");
		System.out.println(sm.getData());
		System.out.println("Enter \"Y\" for to confirm or \"N\" to cancel delete operation");
		tempStr = sn.nextLine();
		if (tempStr.toUpperCase().equals("Y")){
			try{
				sm.submitData();
			}
			catch(IncompleteFieldException e){
				System.out.println("Cannot submit: One or more fields are incomplete");
			}
		}
	}
	/**
	 * Interactive algorithm for adding a new @class Claim instance
	 * Polls user for all information needed
	 */
	protected void newClaim(ServiceCharger sc,int id)throws InvalidIDException, IOException{
		String temp;
		int month,day,year,memberNumber,serviceNumber,tempInt;
		
		if (sc == null){
			sc = new ServiceCharger(id);
		}
		
		while(true){
			while(true){
				System.out.println("Enter recipient's MEMBER NUMBER: ");
				temp = sn.nextLine();
				if(temp.length() == 9){
					memberNumber = Integer.parseInt(temp);
					try{
						sc.setMemberNumber(memberNumber);
						System.out.println("VALIDATED");
						break;
					}
					catch(InvalidIDException e){
						System.out.println("SUSPENDED");
					}
				}
			}
			while(true){
				System.out.println("Enter the MONTH service was rendered (01-12): ");
				temp = sn.nextLine();
				try{
					tempInt = Integer.parseInt(temp);
					if(temp.length() == 2 && Integer.parseInt(temp) > 0 && Integer.parseInt(temp) < 13){
						month = Integer.parseInt(temp);
						break;
					}
					else{
						System.out.println("Incorrect MONTH format");
					}
				}
				catch(NumberFormatException e){
					System.out.println("Incorrect MONTH format");
				}
				
			}
			while(true){
				System.out.println("Enter the DAY the service was rendered (01-31): ");
				temp = sn.nextLine();
				try{
					tempInt = Integer.parseInt(temp);
					if(temp.length() == 2 && Integer.parseInt(temp) > 0 && Integer.parseInt(temp) < 32){
						day = Integer.parseInt(temp);
						break;
					}
					else{
						System.out.println("Incorrect DAY format");
					}
				}
				catch(NumberFormatException e){
					System.out.println("Incorrect DAY format");
				}
			}
			while(true){
				System.out.println("Enter the YEAR the service was rendered (yyyy): ");
				temp = sn.nextLine();
				try{
					tempInt = Integer.parseInt(temp);
					if(temp.length() == 4){
						year = Integer.parseInt(temp);
						break;
					}
					else{
						System.out.println("Incorrect YEAR format");
					}
				}
				catch(NumberFormatException e){
					System.out.println("Incorrect YEAR format");
				}
			}
			sc.setDateOfService(month, day, year);
			while(true){
				System.out.println("Enter SERVICE NUMBER for service rendered:");
				temp = sn.nextLine();
				if(temp.length() == 6){
					serviceNumber = Integer.parseInt(temp);
					try{
						System.out.println("Is the following correct? ");
						System.out.println(sc.getServiceData(serviceNumber));
						System.out.println("Enter \"Y\" for to confirm or \"N\" to cancel");
						temp = sn.nextLine();
						if (temp.toUpperCase().equals("Y")){
							sc.setServiceNumber(serviceNumber);
							break;
						}
					}
					catch(IOException e){
						System.out.println("Invalid Service");
					}
					catch(InvalidIDException e){
						System.out.println("Invalid Service");
					}
				}
				else{
					System.out.println("Invalid Service");
				}
			}
			while(true){
				System.out.println("(Optional) Enter a comment: ");
				temp = sn.nextLine();
				if (temp.length() < 200){
					sc.setComment(temp);
					break;
				}
				else{
					System.out.println("Comment must be less than 200 characters");
				}
			}
			try{
				System.out.println("Is the following correct?");
				System.out.println(sc.getData());
				System.out.println("Enter \"Y\" for to confirm or \"N\" to cancel");
				temp = sn.nextLine();
				if (temp.toUpperCase().equals("Y")){
					sc.submitClaim();
					System.out.println("Success");
					System.out.println(sc.getServiceData(sc.getServiceNumber()));
					sn.nextLine();
					break;
				}
				sc = null;
				break;
			}
			catch(IncompleteFieldException e){
				e.printStackTrace();
			}
			catch(InvalidIDException e){
				
			}
			catch(IOException e){
				
			}
		}
	}
	
	/**
	 * Interactive algorithm for editing a current @class ProviderData instance
	 * Polls user for information user selects to modify
	 */
	protected void editProvider(int id) throws IOException, InvalidIDException{
		String tempStr;
		int tempInt;
		ProviderMaintainer pm;
		int choice;	
		pm = new ProviderMaintainer(id);
		
		while(true){
			System.out.println("Select An Option: ");
			System.out.println("1. Change Name");
			System.out.println("2. Change Address");
			System.out.println("3. Change City");
			System.out.println("4. Change State");
			System.out.println("5. Change Email");
			System.out.println("6. Change Zipcode");
			System.out.println("7. Print Current Data");
			System.out.println("8. Delete Data");
			System.out.println("9. Save and Exit");
			choice = Integer.parseInt(sn.nextLine());
			switch(choice){
			case 1:
				while(true){
				System.out.println("Enter New Name: ");
				tempStr = sn.nextLine();
				if (tempStr.length() <= 25){
					pm.setName(tempStr);
					break;
				}
				else{
					System.out.println("Name Must be <= 25 Characters");
				}
			}
				break;
			case 2:
				while(true){
					System.out.println("Enter New Address: ");
					tempStr = sn.nextLine();
					if (tempStr.length() <= 25){
						pm.setAddress(tempStr);
						break;
					}
					else{
						System.out.println("Address must be <= 25 Characters");
					}
				}
				break;
			case 3:
				while(true){
					System.out.println("Enter New City: ");
					tempStr = sn.nextLine();
					if (tempStr.length() <= 14){
						pm.setCity(tempStr);
						break;
					}
					else{
						System.out.println("City must be <= 14 Characters");
					}
				}
				break;
			case 4:
				while(true){
					System.out.println("Enter New State (AL,AK,WA, etc.): ");
					tempStr = sn.nextLine();
					if (tempStr.length() <= 2){
						pm.setState(tempStr);
						break;
					}
					else{
						System.out.println("State must be 2 Characters (AL,AK,WA, etc.)");
					}
				}
				break;
			case 5:
				while(true){
					System.out.println("Enter New Email: ");
					tempStr = sn.nextLine();
					if (tempStr.length() <= 25){
						pm.setEmail(tempStr);
						break;
					}
					else{
						System.out.println("Email must be <= 25 Characters");
					}
				}
				break;
			case 6:
				while(true){
					System.out.println("Enter New Zipcode: ");
					tempInt = Integer.parseInt(sn.nextLine());
					if (Integer.toString(tempInt).length() <= 5){
						pm.setZipcode(tempInt);
						break;
					}
					else{
						System.out.println("Zipcode must be <= 5 Digits");
					}
				}
				break;
			case 7:
				System.out.println(pm.getData());
				sn.nextLine();
				break;
				
			case 8:
				System.out.println("Are you sure you want to delete the following data: ");
				System.out.println(pm.getData());
				System.out.println("Enter \"Y\" for to confirm or \"N\" to cancel delete operation");
				tempStr = sn.nextLine();
				if (tempStr.toUpperCase().equals("Y")){
					pm.deleteProvider();
					return;
				}
				else{
					break;
				}
			case 9:
				try{
					pm.submitData();
					return;
				}
				catch(IncompleteFieldException e){
					System.out.println("Cannot submit: One or more fields are incomplete");
				}
				break;
			default: break;
			}
		}
	}
	
	/**
	 * Interactive algorithm for adding a new @class ProviderData instance
	 * Polls user for all information needed
	 */
	protected void newProvider(){
		String tempStr;
		int tempInt;
		ProviderMaintainer pm;
		pm = new ProviderMaintainer();
		while(true){
			System.out.println("Enter New Name: ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 25){
				pm.setName(tempStr);
				break;
			}
			else{
				System.out.println("Name Must be <= 25 Characters");
			}
		}

		while(true){
			System.out.println("Enter New Address: ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 25){
				pm.setAddress(tempStr);
				break;
			}
			else{
				System.out.println("Address must be <= 25 Characters");
			}
		}

		while(true){
			System.out.println("Enter New City: ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 14){
				pm.setCity(tempStr);
				break;
			}
			else{
				System.out.println("City must be <= 14 Characters");
			}
		}

		while(true){
			System.out.println("Enter New State (AL,AK,WA, etc.): ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 2){
				pm.setState(tempStr);
				break;
			}
			else{
				System.out.println("State must be 2 Characters (AL,AK,WA, etc.)");
			}
		}

		while(true){
			System.out.println("Enter New Email: ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 25){
				pm.setEmail(tempStr);
				break;
			}
			else{
				System.out.println("Email must be <= 25 Characters");
			}
		}

		while(true){
			System.out.println("Enter New Zipcode: ");
			tempInt = Integer.parseInt(sn.nextLine());
			if (Integer.toString(tempInt).length() <= 5){
				pm.setZipcode(tempInt);
				break;
			}
			else{
				System.out.println("Zipcode must be <= 5 Digits");
			}
		}
		System.out.println("Is the following correct?");
		System.out.println(pm.getData());
		System.out.println("Enter \"Y\" for to confirm or \"N\" to cancel");
		tempStr = sn.nextLine();
		if (tempStr.toUpperCase().equals("Y")){
			try{
				pm.submitData();
				System.out.println("SUBMITTED");
				return;
			}
			catch(IncompleteFieldException e){
				System.out.println("Cannot submit: One or more fields are incomplete");
			}
		}
	}
	
	/**
	 * Interactive algorithm for editing a current @class MemberData instance
	 * Polls user for information selected for modification
	 */
	protected void editMember(int id) throws IOException,InvalidIDException{
		String tempStr;
		int tempInt;
		MemberMaintainer mm;
		int choice;
		mm = new MemberMaintainer(id);
		while(true){
			System.out.println("Select An Option: ");
			System.out.println("1. Change Name");
			System.out.println("2. Change Address");
			System.out.println("3. Change City");
			System.out.println("4. Change State");
			System.out.println("5. Change Zipcode");
			System.out.println("6. Change Email");
			System.out.println("7. Print Current Data");
			System.out.println("8. Delete Data");
			System.out.println("9. Save and Exit");
			choice = Integer.parseInt(sn.nextLine());
			switch(choice){
			case 1:
				while(true){
				System.out.println("Enter New Name: ");
				tempStr = sn.nextLine();
				if (tempStr.length() <= 25){
					mm.setName(tempStr);
					break;
				}
				else{
					System.out.println("Name Must be <= 25 Characters");
				}
			}
				break;
			case 2:
				while(true){
					System.out.println("Enter New Address: ");
					tempStr = sn.nextLine();
					if (tempStr.length() <= 25){
						mm.setAddress(tempStr);
						break;
					}
					else{
						System.out.println("Address must be <= 25 Characters");
					}
				}
				break;
			case 3:
				while(true){
					System.out.println("Enter New City: ");
					tempStr = sn.nextLine();
					if (tempStr.length() <= 14){
						mm.setCity(tempStr);
						break;
					}
					else{
						System.out.println("City must be <= 14 Characters");
					}
				}
				break;
			case 4:
				while(true){
					System.out.println("Enter New State (AL,AK,WA, etc.): ");
					tempStr = sn.nextLine();
					if (tempStr.length() <= 2){
						mm.setState(tempStr);
						break;
					}
					else{
						System.out.println("State must be 2 Characters (AL,AK,WA, etc.)");
					}
				}
				break;
			case 5:
				while(true){
					System.out.println("Enter New Email: ");
					tempStr = sn.nextLine();
					if (tempStr.length() <= 25){
						mm.setEmail(tempStr);
						break;
					}
					else{
						System.out.println("Email must be <= 25 Characters");
					}
				}
				break;
			case 6:
				while(true){
					System.out.println("Enter New Zipcode: ");
					tempInt = Integer.parseInt(sn.nextLine());
					if (Integer.toString(tempInt).length() <= 5){
						mm.setZipcode(tempInt);
						break;
					}
					else{
						System.out.println("Zipcode must be <= 5 Digits");
					}
				}
				break;
			case 7:
				System.out.println(mm.getData());
				sn.nextLine();
				break;
			case 8:
				System.out.println("Are you sure you want to delete the following data: ");
				System.out.println(mm.getData());
				System.out.println("Enter \"Y\" for to confirm or \"N\" to cancel delete operation");
				tempStr = sn.nextLine();
				if (tempStr.toUpperCase().equals("Y")){
					mm.deleteMember();
					return;
				}
				else{
					break;
				}
			case 9:
				try{
					mm.submitData();
					return;
				}
				catch(IncompleteFieldException e){
					System.out.println("Cannot submit: One or more fields are incomplete");
				}
				break;
			default: break;
			}
		}
	}
	
	/**
	 * Interactive algorithm for adding a new @class MemberData instance
	 * Polls user for all information needed
	 */
	protected void newMember(){
		String tempStr;
		int tempInt;
		MemberMaintainer mm;
		mm = new MemberMaintainer();
		while(true){
			System.out.println("Enter New Name: ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 25){
				mm.setName(tempStr);
				break;
			}
			else{
				System.out.println("Name Must be <= 25 Characters");
			}
		}

		while(true){
			System.out.println("Enter New Address: ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 25){
				mm.setAddress(tempStr);
				break;
			}
			else{
				System.out.println("Address must be <= 25 Characters");
			}
		}

		while(true){
			System.out.println("Enter New City: ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 14){
				mm.setCity(tempStr);
				break;
			}
			else{
				System.out.println("City must be <= 14 Characters");
			}
		}

		while(true){
			System.out.println("Enter New State (AL,AK,WA, etc.): ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 2){
				mm.setState(tempStr);
				break;
			}
			else{
				System.out.println("State must be 2 Characters (AL,AK,WA, etc.)");
			}
		}

		while(true){
			System.out.println("Enter New Email: ");
			tempStr = sn.nextLine();
			if (tempStr.length() <= 25){
				mm.setEmail(tempStr);
				break;
			}
			else{
				System.out.println("Email must be <= 25 Characters");
			}
		}

		while(true){
			System.out.println("Enter New Zipcode: ");
			tempInt = Integer.parseInt(sn.nextLine());
			if (Integer.toString(tempInt).length() <= 5){
				mm.setZipcode(tempInt);
				break;
			}
			else{
				System.out.println("Zipcode must be <= 5 Digits");
			}
		}
		System.out.println("Is the following correct?");
		System.out.println(mm.getData());
		System.out.println("Enter \"Y\" for to confirm or \"N\" to cancel");
		tempStr = sn.nextLine();
		if (tempStr.toUpperCase().equals("Y")){
			try{
				mm.submitData();
				System.out.println("SUBMITTED");
				return;
			}
			catch(IncompleteFieldException e){
				System.out.println("Cannot submit: One or more fields are incomplete");
				sn.nextLine();
			}
		}
	}
	/**
	 * Interactive algorithm for editing a current @class ServiceData instance
	 * Polls user for all information requested for modification
	 */
	protected void editService(int id)throws IOException, InvalidIDException{
		String tempStr;
		int choice;
		double tempDb;
			
		ServiceMaintainer sm = new ServiceMaintainer(id);
		
			
		
		while(true){
			System.out.println("Select An option: ");
			System.out.println("1. Change Service Name");
			System.out.println("2. Change Service Fee");
			System.out.println("3. Print Data");
			System.out.println("4. Delete Service Data");
			System.out.println("5. Save and Exit");
			choice = Integer.parseInt(sn.nextLine());
			switch(choice){
			case 1: 
				while(true){
					System.out.println("Enter New Service Name: ");
					tempStr = sn.nextLine();
					if (tempStr.length() <= 20){
						sm.setServiceName(tempStr);
						break;
					}
					else{
						System.out.println("Service Name must be <= 20 Characters");
					}
				}
				break;
			case 2:
				while(true){
					System.out.println("Enter New Service Fee: ");
					tempDb = Double.parseDouble(sn.nextLine());
					sm.setServiceFee(tempDb);
					break;
					}
				break;
			case 3:
				System.out.println(sm.getData());
				sn.nextLine();
				break;
				
			case 4:
				System.out.println("Are you sure you want to delete the following data: ");
				System.out.println(sm.getData());
				System.out.println("Enter \"Y\" for to confirm or \"N\" to cancel delete operation");
				tempStr = sn.nextLine();
				if (tempStr.toUpperCase().equals("Y")){
					sm.deleteData();
					return;
				}
				else{
					break;
				}
				
			case 5:
				try{
					sm.submitData();
					return;
				}
				catch(IncompleteFieldException e){
					System.out.println("Cannot submit: One or more fields are incomplete");
				}
				break;
			default:
				break;
			}
		}
	}

}
