/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA07
 * 
 * 								Faculty class extends person class and holds faculty specific values
 * 
 * 								Date Created: 3/27/2019
 * 
 * 								Saved in: Faculty.java
 */
package PA07; 

public class Faculty extends Person{

	private String rankOfFaculty; // values are Lecturer, Assistant Professor, Associate professor, Professor
	private MyDate appointmentDate;
	
	Faculty(String firstName, String lastName, Address address, String phoneNumber, String emailAddress, String rankOfFaculty, MyDate appointmentDate)
	{
		// call the super class constructor to pass the Person data fields
		super(firstName, lastName, address, phoneNumber, emailAddress);
		
		this.rankOfFaculty = rankOfFaculty; // values are Lecturer, Assistant Professor, Associate Professor, Professor
		this.appointmentDate = appointmentDate;		
	}

	// Override the abstract method from Person
	@Override
	public String toString() 
	{
		return firstName + " , "+ lastName + " , " + address.toString() + " , " + phoneNumber + " , " + emailAddress + " , " + rankOfFaculty +" , " + appointmentDate;
	}

}
