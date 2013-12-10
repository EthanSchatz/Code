package report_generators;

import java.io.BufferedWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import system.ChocAnSystem;
import system.MemberData;
import system.Claim;
import exceptions.*;

/**
 * Generates Member Reports for a specific member or all members
 * @author TL
 *
 */
public class MemberReportGenerator extends ReportGenerator{
	private DateFormat form1 = new SimpleDateFormat("MM-dd-yyyy");

	public MemberReportGenerator() {
		super();
	}
	
	public String generateReport(int id) throws IOException{
		BufferedWriter bw = null;
		Claim claim;
		Calendar date = Calendar.getInstance();
		MemberData data;
		String filename = null;
		List<String> claims = sys.getAllClaims();
		try{
			data = (MemberData)sys.getData(id);
			filename = ChocAnSystem.reportPath + data.getName() + "_" + form1.format(date.getTime()) + ".txt";
			bw = new BufferedWriter(new FileWriter(filename));
			bw.write(data.dataToString());
			for (String afile: claims){
				claim = (Claim)sys.getData(afile);
				if (claim.getMemberNumber() == data.getID()){
					bw.write(claim.dataToString());
				}
			}
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
		MemberData data;
		for (String path : members){
			try{
				data = (MemberData)sys.getData(path);
				generateReport(data.getID());
			}
			catch(IOException e){
				System.out.println("ERROR WITH REPORT FOR MEMBER " + path);
				e.printStackTrace();
			}
		}
	}
}
