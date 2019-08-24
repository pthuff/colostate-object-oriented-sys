/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA05
 * 
 * 								Grade class for creating a grade object and values
 * 
 * 								Date Created: 2/28/2019
 * 
 * 								Saved in: Grade.java
 */
package PA05;

public class Grade 
{
	private int id;
	private String assignment;
	private Student student;
	private double score;
	private static int noOfGrades;
	
	// complete grade object constructor
	public Grade (int id, String assignment, Student student, double score)
	{		
		this.id = id;
		this.assignment = assignment;
		this.student = student;
		this.score = score;
		
		// increment noOfGrades by one for each new instantiated Grade object
		Grade.noOfGrades++;
	}
	
	// compete public getter and setter methods 
	public int getId() {
		return id;
	}

	public String getAssignment() {
		return assignment;
	}

	public Student getStudent() {
		return student;
	}

	public double getScore() {
		return score;
	}

	public static int getNoOfGrades() {
		return noOfGrades;
	}

	public static void setNoOfGrades(int noOfGrades) {
		Grade.noOfGrades = noOfGrades;
	}
}
