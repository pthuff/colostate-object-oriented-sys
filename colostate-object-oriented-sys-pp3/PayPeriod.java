/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								PayPeriod class for creating PayPeriod objects, associated with PayRecord
 * 
 * 								Date Created: 4/03/2019
 * 
 * 								Saved in: PayPeriod.java
 */
package PP03;

import java.util.Date;


public class PayPeriod {
	
	private int pID;
    private Date pStartDate, pEndDate;
	
    /** 1- add the class constructor */
    public PayPeriod(int pID, Date pStartDate, Date pEndDate) {
		super();
		this.pID = pID;
		this.pStartDate = pStartDate;
		this.pEndDate = pEndDate;
		//System.out.println(toString());
	}//end of class constructor

    /** 2- add the setters/getters methods */
    public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public Date getpStartDate() {
		return pStartDate;
	}

	public void setpStartDate(Date pStartDate) {
		this.pStartDate = pStartDate;
	}

	public Date getpEndDate() {
		return pEndDate;
	}

	public void setpEndDate(Date pEndDate) {
		this.pEndDate = pEndDate;
	}

	/** 3- add override method toString() */	
	@Override
	public String toString() {
		return " PayPeriod: ID: " + pID + ", StartDate: " + pStartDate + ", EndDate: " + pEndDate;
	}
    
}//end of class
