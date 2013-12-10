package frontend;
import report_generators.ProviderReportGenerator;
import report_generators.MemberReportGenerator;
import java.util.Calendar;

/**
 * Thread to Check if its 12:59 on a Friday night. If so, Member and Provider reports are 
 * automatically generated
 * @author TL
 *
 */
public class AutoReportGenerator implements Runnable{
	Calendar date;
	MemberReportGenerator mrg;
	ProviderReportGenerator prg;
	
	public void run(){
		while(true){
			date = Calendar.getInstance();
			if (date.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY &&
				date.get(Calendar.HOUR_OF_DAY) == 23 &&
				date.get(Calendar.MINUTE) == 59){
				mrg = new MemberReportGenerator();
				prg = new ProviderReportGenerator();
				mrg.generateAllReports();
				prg.generateAllReports();
				System.out.println("REPORTS AUTO GENERATED");
				try{
					java.lang.Thread.sleep(60000l);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
			else{
				try{
					java.lang.Thread.sleep(1000l);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}
	}

}
