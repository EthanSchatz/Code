package report_generators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import system.ChocAnSystem;
import system.ProviderData;
import system.ServiceData;
import system.Claim;
import exceptions.*;

/**
 * produces ProviderReports for a specific provider or all providers
 * @author TL
 *
 */
public class ProviderReportGenerator extends ReportGenerator{
	private DateFormat form1 = new SimpleDateFormat("MM-dd-yyyy");

	public ProviderReportGenerator() {
		super();
	}
	
	public String generateReport(int id) throws IOException {
		Calendar date = Calendar.getInstance();
		BufferedWriter bw = null;
		Claim claim;
		ProviderData data;
		List<String> claims = sys.getAllClaims();
		int counter = 0;
		float feeTotal = 0;
		String filename = null;
		try{
			data = (ProviderData)sys.getData(id);
			filename = ChocAnSystem.reportPath + data.getName() + "_" + form1.format(date.getTime()) + ".txt";
			bw = new BufferedWriter(new FileWriter(filename));
			bw.write(data.dataToString());
			for (String file: claims){
				claim = (Claim)sys.getData(file);
				if (claim.getProviderNumber() == id){
					counter++;
					ServiceData temp = (ServiceData)sys.getData(claim.getServiceNumber());
					feeTotal += temp.getServiceFee();
					bw.write(claim.dataToString());
				}
			}
			bw.write("Total Number of Conslultations with members: " + counter);
			bw.write("Total Fee for week: " + feeTotal);
		}
		catch(InvalidIDException e){
			
		}
		finally{
			try{
				if (bw != null){
					bw.close();
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
		return filename;
	}
	
	public void generateAllReports(){
		ProviderData data;
		for (String path : providers){
			try{
				data = (ProviderData)sys.getData(path);
				generateReport(data.getID());
			}
			catch(IOException e){
				System.out.println("ERROR WITH REPORT FOR PROVIDER " + path);
				e.printStackTrace();
			}
		}
	}
}
