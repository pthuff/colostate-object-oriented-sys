/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								Employee class for creating Employee objects, associated with Person
 * 
 * 								Date Created: 4/03/2019
 * 
 * 								Saved in: Employee.java
 */
package PP03;

// 1- The Employee class extends superclass Person
public class Employee extends Person {
	
	private int eID;
    private Status empStatus;
    private int numberOfEmployees;
	
    // 2- add the subclass Employee constructor that calls the supper Person class constructor, you should provide input data for all parent class data fields
    Employee(String fname, String lname, Address address, int eID, Status empStatus) {
		super(fname,lname,address);
		this.eID = eID;
		this.empStatus = empStatus;
		numberOfEmployees++;
		//System.out.println(toString());
		
	}//end of Employee constructor
    
    // 3- add setters/getters methods    
	public int geteID() {
		return eID;
	}

	public void seteID(int eID) {
		this.eID = eID;
	}

	public Status getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(Status empStatus) {
		this.empStatus = empStatus;
	}

	// 4- add override toString() method that overrides toString() in the superclass Person
	@Override
	public String toString() {
		return "Employee: ID: " + eID + ", Status: " + empStatus.toString() + ", First: " + fName + ", Last: " + lName
				+ ", address: " + address.toString();
	}

}
