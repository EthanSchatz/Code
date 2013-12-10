package report_generators;
import system.*;

import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class ProviderDirectoryGenerator {
	private ChocAnSystem sys;
	
	public ProviderDirectoryGenerator() {
		sys = new ChocAnSystem();
	}
	
	public String getProviderDirectory(){
		List<String> providers = sys.getAllProviders();
		List<String> services = sys.getAllServices();
		ProviderData data1;
		int counter = 0;
		ServiceData data2;
		BufferedWriter bw = null;
		String filename = "data/ProviderDirectory.txt";
		File creator = new File(filename);
		try{
			if (!creator.exists()){
				creator.createNewFile();
			}
			bw = new BufferedWriter(new FileWriter(creator));
			bw.write("PROVIDERS \n\n");
			for (String provider: providers){
				data1 = (ProviderData)sys.getData(provider);
				bw.write(data1.dataToString() + "\n");
				counter++;
			}
			bw.write("Total Providers: "+ counter + "\n\n");
			counter = 0;
			bw.write("SERVICES \n\n");
			for (String service: services){
				data2 = (ServiceData)sys.getData(service);
				bw.write(data2.dataToString() + "\n");
				counter++;
			}
			bw.write("Total Services: " + counter+ "\n");
		}
		catch(IOException e){
			System.out.println("ERROR IN IO");
		}
		finally{
			try{
				if (bw!= null){
					bw.close();
				}
			}
			catch(IOException e){

			}
		}
		return filename;
	}	
}
	


