/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								TaxIncome Class for calculating pay record taxes
 * 
 * 								Date Created: 4/03/2019
 * 
 * 								Saved in: TaxIncome.java
 */
package PP03;

public class TaxIncome implements Taxable{

	// 1- this class implements the Taxable interface
	// 2- implement all the unimplemented abstract methods in the Taxable Interface, 
		//income tax is computed based on state and federal taxes   
	
	//generate default constructor
	TaxIncome(){
	
	}
	
	@Override
	public double compStateTax(double grossPay) {

		double stateTax = 0.0;
		
		stateTax = grossPay * Taxable.STATE_TAX;
		
		return stateTax;
	}

	@Override
	public double compFederalTax(double grossPay) {

		double federalTax = 0.0;

		federalTax = grossPay * Taxable.FEDERAL_TAX;
		
		return federalTax;
	}

	@Override
	public double compIncomeTax(double grossPay) {

		double incomeTax = 0.0;
		
		incomeTax = compStateTax(grossPay) + compFederalTax(grossPay);
		
		return incomeTax;
	}

	@Override
	public String toString() {
		return "TaxIncome [toString()=" + super.toString() + "]";
	}

}//end of class
