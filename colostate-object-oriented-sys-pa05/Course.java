/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA05
 * 
 * 								Course Class for calling a student and grade object method and assigning to object array.
 * 
 * 								Date Created: 2/28/2019
 * 
 * 								Saved in: Course.java
 */
package PA05;

public class Course 
{
	
	private int id;
	private String description;
	private Student[] students;
	private Grade[] grades;
	
	// complete course object constructor
	public Course (int id, String description, int n)
	{
		this.id = id;
		this.description = description;
		
		// you should initialize the students and grades arrays here
		// these array should be of size n
		students = new Student[n];
		grades = new Grade[n];
		
		//Set number of students and grades to 0 for initial index
		Student.setNoOfStudents(0);
		Grade.setNoOfGrades(0);
	}

	// Creates and adds a student object to the  students array
	public void addStudent(int id, String name)
	{
		//Declare local variable
		int index = Student.getNoOfStudents();
		
		students[index] = new Student(id, name);
	}

	// adds a grade object to the  grades array
   public void addGrade(int id, String assignment, Student student, double score)
   	{
	   //Declare local variable
	   int index = Grade.getNoOfGrades();
	   
	   grades[index] = new Grade(id, assignment, student, score);   	   
	}   
   
   
   public double getGradeAVG()
   	{
	   //Declare local variables
	   double avg = 0.00;
	   double total = 0.00;
	   int index = Grade.getNoOfGrades();
	   
	   //Loop for total
	   for (int i = 0; i < index; i++)
	   {
		   total += grades[i].getScore();
	   }
	   
	   //Calculate the average
	   avg = total / index;
	   
	   //Return the value
	   return avg;
	}
   
   
  public String getHiegestStudentGrade()
  	{
	  //Declare local variables
	  int index = Grade.getNoOfGrades();
	  int position = 0;
	  double highestGrade = 0.00;
	  
	  //Loop for highest grade
	  for (int i = 0; i < index; i++)
	  {
		  if (grades[i].getScore() > highestGrade)
		  {
			  highestGrade = grades[i].getScore();
			  position = i;
		  }	  
	  }
	  
	  //Return the value
	  return students[position].getName() + ": " + String.format("%.1f", highestGrade);
	}

  	//Public getters
	public int getCourseId() 
	{
		return id;
	}

	public String getCourseDescription() 
	{
		return description;
	}

	public Student getStudent(int i) 
	{
		return students[i];
	}

	public Grade getGrade(int i) 
	{
		return grades[i];
	}
}
