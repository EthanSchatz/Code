package system;

public class EFTData extends Data {
	private static final long serialVersionUID = 1L;
	private String providerName;
	private String providerNumber;
	private double weeklyTotal;
	public EFTData(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String dataToString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @return the providerName
	 */
	public String getProviderName() {
		return providerName;
	}

	/**
	 * @param providerName the providerName to set
	 */
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	/**
	 * @return the providerNumber
	 */
	public String getProviderNumber() {
		return providerNumber;
	}

	/**
	 * @param providerNumber the providerNumber to set
	 */
	public void setProviderNumber(String providerNumber) {
		this.providerNumber = providerNumber;
	}

	/**
	 * @return the weeklyTotal
	 */
	public double getWeeklyTotal() {
		return weeklyTotal;
	}

	/**
	 * @param weeklyTotal the weeklyTotal to set
	 */
	public void setWeeklyTotal(double weeklyTotal) {
		this.weeklyTotal = weeklyTotal;
	}

}
