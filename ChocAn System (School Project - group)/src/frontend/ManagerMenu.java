package frontend;
import java.util.Scanner;

import exceptions.*;

import java.io.IOException;

import report_generators.*;

/**
 * Initializes Manager Menu/Interactive Mode. 
 * Command Line interface
 * @author TL
 *
 */
public class ManagerMenu{
	
	/**
	 * class Scanner is used to read from command line (scanner.nextLine())
	 */
	private Scanner sn;
	
	/**
	 * Instance Variable to prevent excessive memory usage from multiple instances of Operations
	 */
	private Operations op;
	/**
	 * Instance Variable to prevent excessive memory usage from multiple instances
	 */
	private ProviderDirectoryGenerator pdg;
	/**
	 * Initializes need instance variables
	 */
	public ManagerMenu() {
		sn = new Scanner(System.in);
		op = new Operations(sn);
		options();
		System.exit(1);
	}
	/**
	 * Enters loop for interacting with system
	 */
	private void options(){
		while(true){
			System.out.println("Select An Option: ");
			System.out.println("1. Add New Provider");
			System.out.println("2. Edit/Delete Provider Data");
			System.out.println("3. Add New Service Data");
			System.out.println("4. Edit/Delete Service Data");
			System.out.println("5. Add New Member");
			System.out.println("6. Edit/Delete Member Data");
			System.out.println("7. Generate Provider Report");
			System.out.println("8. Generate Member Report");
			System.out.println("9. Generate Provider Directory");
			System.out.println("10. Exit");
			String temp = sn.nextLine();
			String tempStr;
			ProviderReportGenerator prg = new ProviderReportGenerator();;
			MemberReportGenerator mrg = new MemberReportGenerator();
			int tempInt;
			int selection = 0;
			try{
				selection = Integer.parseInt(temp);
			}
			catch(NumberFormatException e){
				System.out.println("Invalid Choice Identifier");
			}
			
			switch(selection){
			case 1: op.newProvider();
			break;
			
			case 2: 
				System.out.println("Enter Provider ID to Edit: ");
				tempStr = sn.nextLine();
				try{
					tempInt = Integer.parseInt(tempStr);
					op.editProvider(tempInt);
				}
				catch(NumberFormatException e){
					System.out.println("Number Format Error");
				}
				catch(IOException e){
					e.printStackTrace();
				}
				catch(InvalidIDException e){
					e.printStackTrace();
				}
			break;
			
			case 3: op.newService();
			break;
			
			case 4: 
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
			
			case 5: op.newMember();
			break;
			
			case 6: 
				System.out.println("Enter Member ID to Edit: ");
				tempStr = sn.nextLine();
				try{
					tempInt = Integer.parseInt(tempStr);
					op.editMember(tempInt);
				}
				catch(InvalidIDException e){
					
				}
				catch(IOException e){
					
				}
				catch(NumberFormatException e){
					
				}
			break;
			
			case 7:
				System.out.println("Enter Provider ID for which to Generate Report: ");
				tempStr = sn.nextLine();
				try{
					tempInt = Integer.parseInt(tempStr);
					tempStr = prg.generateReport(tempInt);
					System.out.println("Provider Report Generated at: " + tempStr);
					
				}
				catch(NumberFormatException e){
					System.out.println("Number Format Error");
				}
				catch(IOException e){
					System.out.println("Invalid Provider ID");
				}
			break;
			
			case 8:
				System.out.println("Enter Member ID for which to Generate Report: ");
				tempStr = sn.nextLine();
				try{
					tempInt = Integer.parseInt(tempStr);
					tempStr = mrg.generateReport(tempInt);
					System.out.println("Member Report Generated at: " + tempStr);
					
				}
				catch(NumberFormatException e){
					System.out.println("Number Format Error");
				}
				catch(IOException e){
					System.out.println("Invalid Member ID");
					e.printStackTrace();
				}
			break;
			case 9: 
				pdg = new ProviderDirectoryGenerator();
				System.out.println("New Provider Directory at: " + pdg.getProviderDirectory());
				sn.nextLine();
			break;
			case 10: return;
			
			default: break;
			}
		}
	}
}
