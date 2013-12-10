package system;
//import java.io.IOException;
//
//import report_generators.*;
//
//import java.util.*;
//
//import frontend.AutoReportGenerator;
//import frontend.ProviderMenu;
//import frontend.ManagerMenu;
//import exceptions.IncompleteFieldException;
//import exceptions.InvalidIDException;
//import exceptions.InvalidTypeException;
//import maintainers.*;


public class Debug {	
//	public static void main(String[] args) {
//		if (args.length > 0){
//			if (args[0].equals("password")){
//				(new Thread(new AutoReportGenerator())).start();
//				ManagerMenu mm = new ManagerMenu();
//			}
//			else{
//				ProviderMenu pm = new ProviderMenu();
//				//pm.init();
//			}
//		}
//		else{
//			ProviderMenu pm = new ProviderMenu();
//			//pm.init();
//		}
//
//	}
//	public static void main(String[] args){
//		if (args.length > 0){
//			if (args[0].equals("1")){
//				(new Thread(new AutoReportGenerator())).start();
//				ManagerMenu mm = new ManagerMenu();
//			}
//		}
//		else{
//			ProviderMenu pm = new ProviderMenu();
//			pm.init();
//		}
// 		AutoReportGenerator arg = new AutoReportGenerator();
		
//		ChocAnSystem sys = new ChocAnSystem();
//		MemberReportGenerator mrg = new MemberReportGenerator();
//		try{
//			MemberData data = (MemberData)sys.getData(501990685);
//			mrg.generateReport(data.getID());
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
//		catch(InvalidIDException e){
//			e.printStackTrace();
//		}
//		
//		ChocAnSystem sys = new ChocAnSystem();
//		try{
//			ProviderData pd = (ProviderData)sys.createNewData(ChocAnSystem.PROVIDER);
//			pd.setAddress("ADDRESS");
//			pd.setCity("CITY");
//			pd.setEmail("EMAIL");
//			pd.setName("NAME");
//			pd.setState("STATE");
//			pd.setZipcode(12345);
//			pd.setType(1);
//			MemberData md = (MemberData)sys.createNewData(ChocAnSystem.MEMBER);
//			md.setAddress("ADDRESS");
//			md.setCity("CITY");
//			md.setEmail("EMAIL");
//			md.setName("NAME");
//			md.setState("STATE");
//			md.setZipcode(12345);
//			md.setStatus(1);
//			ServiceData sd = (ServiceData)sys.createNewData(ChocAnSystem.SERVICE);
//			sd.setServiceFee(400.50);
//			sd.setServiceName("SERVICE NAME");
//			sys.submitData(pd);
//			sys.submitData(md);
//			sys.submitData(sd);
//		}
//		catch(InvalidTypeException e){
//			e.printStackTrace();
//		}
//		catch(IOException e){
//			e.printStackTrace();
//		}
//		try{
//			ServiceCharger sc = new ServiceCharger(100392222);
//			sc.setDateOfService(6, 10, 1992);
//			sc.setMemberNumber(501990685);
//			sc.setServiceNumber(100198);
//			sc.submitClaim();
//		}
//		catch(InvalidIDException e){
//			e.printStackTrace();
//		}
//		catch(IncompleteFieldException e){
//			e.printStackTrace();
//		}
		
//	}
}
