/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA07
 * 
 * 								Person class for holding abstract person values and methods 
 * 
 * 								Date Created: 3/27/2019
 * 
 * 								Saved in: Person.java
 */
package PA07; 

public abstract class Person {
	String firstName; // example, Jim
	String lastName; // example, Miller
	Address address;  
	String phoneNumber; // example, (970) 421-1212. No need to code to verify the format of the phone number, other than it not being blank, or not empty
	String emailAddress; // example, jim@gmail.com. No need to code to verify the format of the email address, other than it not being blank, or not empty

	public static int numberOfPersons=0;
	
	// add the constructor with data fields
	public Person(String firstName, String lastName, Address address, String phoneNumber, String emailAddress) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		
		// increment the numberOfPersons by 1 for each time the constructor is called from the subclases
		Person.numberOfPersons++;
	}

	// This is an abstract method
	public abstract String toString();

	public String getLastName() {
		return lastName;
	}
	
}
