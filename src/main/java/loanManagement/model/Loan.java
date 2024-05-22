package loanManagement.model;

public class Loan {
	
	private int loanID;
	private String loanAmount;
	private String interestRate;
	private String loanTerm;
	private String totalPay;
	private String monthlyPay;
	
	public Loan(int loanID, String loanAmount, String interestRate, String loanTerm, String totalPay,
			String monthlyPay) {
		super();
		this.loanID = loanID;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.totalPay = totalPay;
		this.monthlyPay = monthlyPay;
	}

	public Loan(String loanAmount, String interestRate, String loanTerm, String totalPay, String monthlyPay) {
		super();
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.totalPay = totalPay;
		this.monthlyPay = monthlyPay;
	}

	public int getLoanID() {
		return loanID;
	}

	public void setLoanID(int loanID) {
		this.loanID = loanID;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(String loanTerm) {
		this.loanTerm = loanTerm;
	}

	public String getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(String totalPay) {
		this.totalPay = totalPay;
	}

	public String getMonthlyPay() {
		return monthlyPay;
	}

	public void setMonthlyPay(String monthlyPay) {
		this.monthlyPay = monthlyPay;
	}
	
	
	

}
