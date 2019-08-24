/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA07
 * 
 * 								Student class extends person class and holds student specific values
 * 
 * 								Date Created: 3/27/2019
 * 
 * 								Saved in: Student.java
 */
package PA07; 

public class Student extends Person{
	
	private String status; // Values are Freshman, Sophomore, Junior, Senior 
	
	Student(String firstName, String lastName, Address address, String phoneNumber, String emailAddress, String status)
	{
		// call the super class constructor to pass the Person data fields
		super(firstName, lastName, address, phoneNumber, emailAddress);
		
		this.status = status;
	}

	// Override the abstract method from Person
	@Override
	public String toString() 
	{
		return firstName + " , "+ lastName + " , " + address.toString() + " , " + phoneNumber + " , " + emailAddress + " , " + status;
	}

}
