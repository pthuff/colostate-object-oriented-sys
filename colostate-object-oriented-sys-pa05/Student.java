/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA05
 * 
 * 								Student class for creating a student object and values
 * 
 * 								Date Created: 2/28/2019
 * 
 * 								Saved in: Student.java
 */
package PA05;

public class Student 
{
	
	private int id;
	private String name;
	private static int noOfStudents;

	// complete student object constructor
	public Student (int id, String name)
	{
		this.id = id;
		this.name = name;
		
		// increment noOfStudents by one for each new instantiated Student object
		Student.noOfStudents++;
	}
	
	// compete public getter and setter methods
	public int getId() 
	{
		return id;
	}

	public String getName() 
	{
		return name;
	}

	public static int getNoOfStudents() 
	{
		return noOfStudents;
	}
	
	public static void setNoOfStudents(int noOfStudents) 
	{
		Student.noOfStudents = noOfStudents;
	}
}
