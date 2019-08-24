/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								Taxable class for State/Federal tax rates
 * 
 * 								Date Created: 4/03/2019
 * 
 * 								Saved in: Taxable.java
 */
package PP03;

public interface Taxable {
	
	public static final double STATE_TAX = 0.07;
	public static final double FEDERAL_TAX = 0.28;
	
	// an abstract method computes the state tax in its implementation by the implementer class
	public abstract double compStateTax(double grossPay);
	
	// an abstract method computes the federal tax in its implementation by the implementer class
	public abstract double compFederalTax(double grossPay);
	
	// an abstract method computes the income tax based on the state and federal taxes in its implementation by the implementer class
	public abstract double compIncomeTax(double grossPay);

}
