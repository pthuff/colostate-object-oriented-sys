/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 2
 * 
 * 								CreditCard class for creating creditcard objects and holding card information.
 * 
 * 								Date Created: 3/07/2019
 * 
 * 								Saved in: CreditCard.java
 */
package PP2;

public class CreditCard {
	
	private long number;
	private String expDate;
	
	public CreditCard(long number, String expDate)
	{
		this.number = number;
		this.expDate = expDate;
	}
	
	// add public setter/getter methods, and also the toString method
	@Override
	public String toString() 
	{
		//Override toString method for Credit Card info
		return "Card Number: " + number + "\nExp: " + expDate;
	}
	
	//Getters and setters
	public long getNumber() 
	{
		return number;
	}
		
}
