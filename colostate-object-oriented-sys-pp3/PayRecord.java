/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								PayRecord Class for creating PayRecord objects, associated with PayRoll
 * 
 * 								Date Created: 4/03/2019
 * 
 * 								Saved in: PayRecord.java
 */
package PP03;

public class PayRecord {
	
	private int rID;
    private Employee employee;
    private PayPeriod payPeriod;
    private TaxIncome payTax;
    
    private double payHours;
    private double payRate;
    
    private double montlyIncome;
    private int numMonths;
    
    public static final int REG_HOURS = 40;
    public static final double OT_RATE = 1.25;
    
    private static int noOfRecords;
    
    // pay record constructor for hourly employee
    public PayRecord(int id, Employee e, PayPeriod period, double hours, double rate){
    	
    	this.rID = id;
    	this.employee = e;
    	this.payPeriod = period;
    	this.payHours = hours;
    	this.payRate = rate;
    	this.montlyIncome = 0;
    	this.numMonths = 0;
    	this.payTax = new TaxIncome();
    	noOfRecords++;
    	//System.out.println(toString());
    }
    
    // pay record constructor for full time employee
    public PayRecord(int id, Employee e, PayPeriod period, double mIncome, int mNum){
 	
    	this.rID = id;
    	this.employee = e;
    	this.payPeriod = period;
    	this.payHours = 0;
    	this.payRate = 0;
    	this.montlyIncome = mIncome;
    	this.numMonths = mNum;
    	this.payTax = new TaxIncome();
    	noOfRecords++;
    	//System.out.println(toString());
    	
 }
    
 	public static int getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PayPeriod getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(PayPeriod payPeriod) {
		this.payPeriod = payPeriod;
	}

	public TaxIncome getPayTax() {
		return payTax;
	}

	public void setPayTax(TaxIncome payTax) {
		this.payTax = payTax;
	}

	public double getPayHours() {
		return payHours;
	}

	public void setPayHours(double payHours) {
		this.payHours = payHours;
	}

	public double getPayRate() {
		return payRate;
	}

	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	public double getMontlyIncome() {
		return montlyIncome;
	}

	public void setMontlyIncome(double montlyIncome) {
		this.montlyIncome = montlyIncome;
	}

	public int getNumMonths() {
		return numMonths;
	}

	public void setNumMonths(int numMonths) {
		this.numMonths = numMonths;
	}

	public static int getRegHours() {
		return REG_HOURS;
	}

	public static double getOtRate() {
		return OT_RATE;
	}

	// complete the code to compute the gross pay for the employee based on the employee status
	public double grossPay(){
		
		double gross = 0.0;
		
		if (employee.getEmpStatus() == Status.Hourly) {
			
			if (payHours <= REG_HOURS) 
				gross = payHours * payRate;
			else
				gross = (REG_HOURS*payRate) + ((payHours-REG_HOURS)*payRate*OT_RATE); 			
		}
		
		else if (employee.getEmpStatus() == Status.FullTime) {
			
			gross = (numMonths * this.montlyIncome);
		}
		
		return gross;
	}//end of gross pay method
    
	// complete the code in this method to compute the net pay of the employee after taxes (state and federal)
     public double netPay(){
		  
    	 double net = 0.0;
    	 
    	 net = grossPay()-payTax.compIncomeTax(grossPay()) ;
    	 
    	 return net;
  }//end of net pay calc method

    //generate toString() method
	@Override
	public String toString() {
		
		String message = "";
		Status statii = employee.getEmpStatus();
		if (statii == Status.FullTime) {
		 message = "PayRecord: ID: " + rID  + employee.toString() + payPeriod.toString() /*+ ", payTax: " + payTax.toString() */
				+ ", Montly Income: " + String.format("%.2f",montlyIncome) + ", No. of Months: "
				+ numMonths + ", Gross Pay: " + String.format("%.2f",grossPay()) + ", Net Pay: " + String.format("%.2f",netPay());
			}
		if (statii == Status.Hourly) {
			message =  "PayRecord: ID: " + rID + employee.toString() + payPeriod.toString() /*+ ", payTax: " + payTax.toString() */
			+ ", Pay Hours: " + String.format("%.2f",payHours) + ", Pay Rate: " + String.format("%.2f",payRate) + ", Gross Pay: " + String.format("%.2f",grossPay()) + ", Net Pay: " + String.format("%.2f",netPay());
			}
		return message;
		
	}//end of toString() method
     
}//end of PayRecord class
