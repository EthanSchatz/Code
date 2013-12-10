package frontend;
import java.io.IOException;
import java.util.Scanner;

import exceptions.InvalidIDException;
import maintainers.ServiceCharger;
import report_generators.ProviderDirectoryGenerator;

/**
 * Provides an interface (with limited permissions) to simulate the Provider Terminal
 * Providers can only: edit services, submit claims, and edit their OWN provider data
 * @author TL
 *
 */
public class ProviderMenu {
	private int id;
	private Scanner sn;
	private ServiceCharger sc;
	private Operations op;
	private ProviderDirectoryGenerator pdg;

	/**
	 * Initializes interface loop for user to execute permitted operations
	 */
	public ProviderMenu() {
		sn = new Scanner(System.in);
		op = new Operations(sn);
		init();
	}
	
	private void init(){
		String tempStr;
		while(true){
			System.out.println("Enter Provider ID or \"exit\" to close program: ");
			tempStr = sn.nextLine();
			if (tempStr.toUpperCase().equals("EXIT")){
				System.exit(1);
			}
			try{
				id = Integer.parseInt(tempStr);
				sc = new ServiceCharger(id);
				options();

			}
			catch(IOException e){
				System.out.println("Invalid ID");
			}
			catch(InvalidIDException e){
				System.out.println("Invalid ID");
			}
			catch(NumberFormatException e){
				System.out.println("Invalid ID");
			}
		}
	}
	private void options(){
		while(true){
			System.out.println("Select An Option: ");
			System.out.println("1. Enter a new Service Claim");
			System.out.println("2. Edit Provider Data");
			System.out.println("3. Edit/Delete Service Data");
			System.out.println("4. Add New Service Data");
			System.out.println("5. Get Provider Directory");
			System.out.println("6. Exit");
			String temp = sn.nextLine();
			int tempInt;
			int selection = 0;
			try{
				selection = Integer.parseInt(temp);
			}
			catch(NumberFormatException e){
				System.out.println("Invalid Choice Identifier");
			}
			switch(selection){
			case 1: 
				try{
				op.newClaim(sc,id);
				}
				catch(IOException e){
					e.printStackTrace();
				}
				catch(InvalidIDException e){
					e.printStackTrace();
				}
			break;
			case 2: 
				try{
					op.editProvider(id);
				}
				catch(IOException e){
					e.printStackTrace();
				}
				catch(InvalidIDException e){
					e.printStackTrace();
				}
			break;
			case 3: 
				System.out.println("Enter Service ID of Service to edit: ");
				
				try{
					tempInt = Integer.parseInt(sn.nextLine());
					op.editService(tempInt);
				}
				catch(InvalidIDException e){
					
				}
				catch(IOException e){
					
				}
				catch(NumberFormatException e){
					
				}
			break;
			case 4: op.newService();
			break;
			case 5:
				pdg = new ProviderDirectoryGenerator();
				System.out.println("New Provider Directory at: " + pdg.getProviderDirectory());
				sn.nextLine();
			case 6: return;
			default: break;
			}
		}
	}
}
	
	