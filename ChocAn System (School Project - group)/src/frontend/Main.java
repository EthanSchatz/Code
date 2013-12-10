package frontend;

public class Main {
	
	public static void main(String[] args) {
		if (args.length > 0){
			if (args[0].equals("admin")){
				(new Thread(new AutoReportGenerator())).start();
				new ManagerMenu();
			}
			else{
				new ProviderMenu();
			}
		}
		else{
			new ProviderMenu();
		}

	}

}
