import java.io.Serializable;

/*
 * CSample is an object class for each participant in the survey.
 * When CSample is initialized, the participant is given a number,
 * from there it can modify the initially blank fields to update the participant
 * CS350
 * Project #3
 * Ethan Schatzline
 */

public class CSample implements Serializable{

	private int ParticipantNo;
	private String ZipCode="";
	private String Employment="";
	private String Impact="";
	private String Cause="";
	
	
	public CSample(int participantNo){
		ParticipantNo = participantNo;
		
		
	}


	public int getParticipantNo() {
		return ParticipantNo;
	}


	public void setParticipantNo(int participantNo) {
		ParticipantNo = participantNo;
	}


	
	


	public String getZipCode() {
		return ZipCode;
	}


	public void setZipCode(String zip) {
		ZipCode = zip;
	}


	public String getEmployment() {
		return Employment;
	}


	public void setEmployment(String employment) {
		Employment = employment;
	}


	public String getImpact() {
		return Impact;
	}


	public void setImpact(String impact) {
		Impact = impact;
	}


	public String getCause() {
		return Cause;
	}


	public void setCause(String cause) {
		Cause+=cause;
	}


	public String getInfo() {
		// TODO Auto-generated method stub
		return String.format("%08d",ParticipantNo)+"         "+String.format("%-14s",ZipCode)+ String.format("%-17s",Employment)+String.format("%-6s",Impact)+String.format("%12s",Cause);
		}


	public void clearCause() {
		// TODO Auto-generated method stub
		Cause="";
	}
	
	
	
	
}
