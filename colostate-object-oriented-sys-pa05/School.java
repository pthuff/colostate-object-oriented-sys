/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA05
 * 
 * 								School class for user input to create student/course/grade objects and display information back to user.
 * 
 * 								Date Created: 2/28/2019
 * 
 * 								Saved in: School.java
 */
package PA05;

import javax.swing.JOptionPane;

public class School {
	
	private static Course course;
	private static int studentCount;

	public static void main(String[] args) 
	{
		// 1 - prompt the user to provide the number of students, integer n value
		
		//Declare variables
		String students = null;
		int numberOfStudents = 0;
				
		//Try/Catch for number of students input
		try
		{
			//Input number of skiers
			students = JOptionPane.showInputDialog("Enter the number of students in whole number, e.g., 3");
			
			//Convert to integer
			numberOfStudents = Integer.parseInt(students);
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Value must be a whole number.");
			
			//Close application
		    System.exit(0);
		}
		
		// 2 - Instantiates the course object
		course = new Course(611, "Object-Oriented Systems", numberOfStudents);
		
		// 3 - Prompt the user to input the student data, and calls addStudentGrade() to add the student to course, for n students
		// 4 - Prompt the user to input the grade data, and calls aaddStudentGradeToCourse() to add the grade to course, for n grades
		
		//Declare variables
		int studentId = 0;
		//int gradeId = 0;
		String studentName = null;
		String assName = null;
		double studentScore = 0.00;
		
		//Loop for inputs from user
		for (int i = 0; i < numberOfStudents; i++)
		{
			try
			{
				//Declare local (loop) variables
				boolean nameValid = false;
						
				//Input student id and convert
				studentId = Integer.parseInt(JOptionPane.showInputDialog("Enter the student's ID#: "));
				
				//Input student name with validation
				while (nameValid != true)
				{
					studentName = JOptionPane.showInputDialog("Enter student's name, e.g., John Doe: ");
					nameValid = (studentName != null) && studentName.matches("^[A-Z][a-z]*(\\s)+[A-Z][a-z]*$");
				}
				
				//Input assignment name with validation
				assName = JOptionPane.showInputDialog("Enter assignment name: ");
				while (assName.isEmpty())
				{
					assName = JOptionPane.showInputDialog("Enter assignment name: ");
				}
				
				//Input score and convert
				studentScore = Double.parseDouble(JOptionPane.showInputDialog("Enter the student's score: "));
				
				//Pass values to objects
				addStudentToCourse(studentId, studentName);
				addStudentGradeToCourse(studentId, assName, course.getStudent(i), studentScore);			
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Exiting.");
				
				//Close application
			    System.exit(0);
			}	
		}
		
		// 5 - Displays the course statistics by calling displayCourseStat()
		
		//Update counter for displayCourseStat() method
		studentCount = numberOfStudents;
		
		//Call displayCourseStat()
		displayCourseStat();
		
		//Close application
	    System.exit(0);
	}
	
	// it uses the course object to add a new student to the course
	public static void addStudentToCourse(int id, String name)
	{
		course.addStudent(id, name);
	}
	
	// it uses the course object to add a new grade object to the course
	public static void addStudentGradeToCourse(int id, String assignment, Student student, double score)
	{
		course.addGrade(id, assignment, student, score);
	}
	
	// it should display the grade average and the highest student grade, student name 
	public static void displayCourseStat()
	{
		//Parse values to display message
		String display = "Course: " + course.getCourseId() + " " + course.getCourseDescription() + "\n\nStudent grades:\n";
		
		for (int i=0; i < studentCount; i++)
	    {
			display += course.getStudent(i).getName() + " - " + course.getGrade(i).getAssignment() + " Grade: " + course.getGrade(i).getScore() + "\n";
	    }
		
		display += "\nAverage grade for the course: " + String.format("%.2f", course.getGradeAVG()) + "\nStudent with the highest grade: " + course.getHiegestStudentGrade(); 
		
		//Display results
		JOptionPane.showMessageDialog(null, display);
	}

}
