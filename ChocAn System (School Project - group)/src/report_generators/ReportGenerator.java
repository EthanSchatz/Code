package report_generators;
import exceptions.InvalidIDException;
import java.util.List;
import java.io.IOException;

import system.ChocAnSystem;

public abstract class ReportGenerator {
	protected ChocAnSystem sys;
	protected List<String> providers;
	protected List<String> members;
	public ReportGenerator() {
		sys = new ChocAnSystem();
		providers = sys.getAllProviders();
		members = sys.getAllMembers();
	}
	
	public abstract String generateReport(int id) throws InvalidIDException, IOException;
	
	public abstract void generateAllReports();
}
