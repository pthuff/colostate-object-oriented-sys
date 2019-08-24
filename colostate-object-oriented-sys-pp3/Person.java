/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								Person class for creating (abstract) Person objects
 * 
 * 								Date Created: 4/03/2019
 * 
 * 								Saved in: Person.java
 */
package PP03;

public abstract class Person {
	
	protected String fName ;
    protected String lName ;
    protected Address address;
    public static int noOfPersons = 0;
    
    public Person(){
    	
    }
    
	public Person(String fName, String lName, Address address) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		noOfPersons++;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static int getNoOfPersons() {
		return noOfPersons;
	}

	public static void setNoOfPersons(int noOfPersons) {
		Person.noOfPersons = noOfPersons;
	}

	@Override
	public String toString() {
		return "Person [fName=" + fName + ", lName=" + lName + ", address=" + address.toString() + "]";
	}
    

}
