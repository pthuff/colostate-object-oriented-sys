/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA07
 * 
 * 								UserGUI class for inputting/outputting person values and populating person array 
 * 
 * 								Date Created: 3/27/2019
 * 
 * 								Saved in: UserGUI.java
 */
package PA07;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class UserGUI extends JFrame implements ActionListener{
	
	static Person[] personArray;
	private static int personCount=0;
	
	// declare all GUI components below
	private JLabel lblLN;
	private JLabel lblFN;
	private JLabel lblAddress;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JTextField fldLN;
	private JTextField fldFN;
	private JTextField fldAddress;
	private JTextField fldPhone;
	private JTextField fldEmail;
	private JRadioButton btnFaculty;
	private JRadioButton btnStudent;
	private ButtonGroup bg;
	private JButton btnAdd;
	private JButton btnSort;
	private JButton btnDisplay;
	private JList listSelect;
	private DefaultListModel<String> listValues;
	private JTextArea txtDisplay;
	private JScrollPane jspDisplay;
	  
				// constructor
				UserGUI(int nPersons)
				{
					
					
					// create person array of size nPersons
					personArray = new Person[nPersons];
					
					//Initialize the components
					initComponenet();

					//Organize the GUI components
					doTheLayout();
					
					/*Add the action listeners GUI buttons(add, sort, and display)
					Example: addButton.addActionListener(this);*/
					btnAdd.addActionListener(this);
					btnSort.addActionListener(this);
					btnDisplay.addActionListener(this);
					btnStudent.addActionListener(this);
					btnFaculty.addActionListener(this);
									
				}//End of constructor

				private void initComponenet()
				{
				
					// initialize all user interface components
					lblLN = new JLabel("Last Name");
					lblFN = new JLabel("First Name");
					lblAddress = new JLabel("Address (Seperate each value by a comma)");
					lblPhone = new JLabel("Phone");
					lblEmail = new JLabel("eMail");
					fldLN = new JTextField("Doe", 50);
					fldFN = new JTextField("Jane", 50);
					fldAddress = new JTextField("1062,Campus Delivery,Fort Collins,CO,80523", 50);
					fldPhone = new JTextField("(970)491-6909", 50);
					fldEmail = new JTextField("admissions@colostate.edu", 50);
					listValues = new DefaultListModel<String>();
					listSelect = new JList(listValues);
					listSelect.setPreferredSize(new Dimension(150, 75));
					txtDisplay = new JTextArea(10, 100);
					txtDisplay.setEditable(false);
					jspDisplay = new JScrollPane(txtDisplay);
					
					//Buttons
					btnFaculty = new JRadioButton("Faculty");
					btnStudent = new JRadioButton("Student");
					bg = new ButtonGroup( );
					bg.add(btnFaculty);
					bg.add(btnStudent);
					btnAdd = new JButton("Add Student/Faculty");
					btnSort = new JButton("Sort Student & Faculty");
					btnDisplay = new JButton("Display Student & Faculty");
				}

			   private void doTheLayout(){
					// Organize the components into GUI window
				   
				   //Instantiate some panels
				   JPanel info = new JPanel();
				   JPanel select = new JPanel();
				   JPanel display = new JPanel();
				   
				   // Add info controls
				   info.setLayout(new GridLayout(5, 2, 5, 5));
				   info.add(lblLN);
				   lblLN.setHorizontalAlignment(JLabel.RIGHT);
				   info.add(fldLN);
				   info.add(lblFN);
				   lblFN.setHorizontalAlignment(JLabel.RIGHT);
				   info.add(fldFN);
				   info.add(lblAddress);
				   lblAddress.setHorizontalAlignment(JLabel.RIGHT);
				   info.add(fldAddress);
				   info.add(lblPhone);
				   lblPhone.setHorizontalAlignment(JLabel.RIGHT);
				   info.add(fldPhone);
				   info.add(lblEmail);
				   lblEmail.setHorizontalAlignment(JLabel.RIGHT);
				   info.add(fldEmail);
				   info.setBorder(new TitledBorder("Enter Person's Information"));
				   
				   //Add Student/Lecture controls
				   select.setLayout(new FlowLayout(FlowLayout.CENTER));
				   select.add(btnFaculty);
				   select.add(btnStudent);
				   select.add(listSelect);
				   select.setBorder(new TitledBorder("Select Person Type"));
				   
				   //Add display area controls
				   display.setLayout(new FlowLayout(FlowLayout.CENTER));
				   display.add(btnAdd);
				   display.add(btnSort);
				   display.add(btnDisplay);
				   display.add(txtDisplay);
				   
				   //Add panels to frame
				   this.add(info, BorderLayout.NORTH);
				   this.add(select, BorderLayout.CENTER);
				   this.add(display, BorderLayout.SOUTH);
				   	
				}

				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					// Call the required button action method according to the action event
					
					//Student radio button fills list
					if (e.getSource() == btnStudent)
				    {
						listValues.removeAllElements();
				    	listValues.addElement("Freshman");
				    	listValues.addElement("Junior");
				    	listValues.addElement("Sophmore");
				    	listValues.addElement("Senior");			    	
				    }
					
					//Faculty radio button fills list
					if (e.getSource() == btnFaculty)
					{
						listValues.removeAllElements();
				    	listValues.addElement("Lecturer");
				    	listValues.addElement("Assistant Professor");
				    	listValues.addElement("Associate Professor");
				    	listValues.addElement("Professor");					
				    }
					
					//Add button adds new person object and checks array max size
					if (e.getSource() == btnAdd)
					{
						if (personArray.length <= Person.numberOfPersons)
						{
							JOptionPane.showMessageDialog(null, "Person count would exceed your set amount.");
						}
						else
						{
							addButtonClicked();
						}					
					}
					
					//Sort button sorts person objects by last name
					if (e.getSource() == btnSort)
					{
						sortBnttonClicked();
					}
				    
					//Display button displays array objects
					if (e.getSource() == btnDisplay)
					{
						try 
						{
							txtDisplay.setText("");
							displaynttonClicked();
						} 
						catch (FileNotFoundException e1) 
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			   
				private void addButtonClicked()
				{				
					//Method to implement add button action
					
					//Declare local variables
					String firstName, lastName, street, city, state, phoneNumber, emailAddress, status, rankOfFaculty;
					int houseNumber, zipCode, year, month, day;
					
					//Declare objects
					Address address;
					MyDate appointmentDate;
					Person person;
					
					//Try/Catch for name input
					try 
					{
						firstName = fldFN.getText();
						lastName = fldLN.getText();
						
						if(firstName.isEmpty() || lastName.isEmpty()) 
						{
							throw new Exception();
						}
					}
					catch(Exception ex) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a first and last name.");
						return;
					}
					
					//Try/Catch for address input
					try 
					{
						String addressSplit[] = new String[5];
						String toSplit = fldAddress.getText();
						addressSplit = toSplit.split(",");
						
						houseNumber = Integer.parseInt(addressSplit[0]);
						street = addressSplit[1];
						city = addressSplit[2];
						state = addressSplit[3];
						zipCode = Integer.parseInt(addressSplit[4]);
						
						if(street.isEmpty() || city.isEmpty() || state.isEmpty())
						{
							throw new Exception();
						}
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Please enter a complete address. Seperate each value by a comma.");
						return;
					}
					
					//Try/Catch for phone number and email input
					try 
					{
						phoneNumber = fldPhone.getText();
						emailAddress = fldEmail.getText();
						
						if(phoneNumber.isEmpty() || emailAddress.isEmpty()) 
						{
							throw new Exception();
						}
					}
					catch(Exception ex) 
					{
						JOptionPane.showMessageDialog(null, "Please enter a phone number and email address.");
						return;
					}
					
					try
					{
						//If nothing is selected
						if (bg.getSelection() == null)
						{
							throw new Exception();
						}
						
						//If person is a student
					    if (btnStudent.isSelected())
					    {
					    	status = (String)listSelect.getSelectedValue();
				    	    
				    	    //Instantiate objects and pass values
				    	    address = new Address(street, houseNumber, city, state, zipCode);
				    	    person = new Student(firstName, lastName, address, phoneNumber, emailAddress, status);
				    	    
				    	    //Assign new student to index value
				    	    personArray [personCount] = person;
				    	    personCount++;
					    }
					    
					    //If person is faculty
					    if (btnFaculty.isSelected())
					    {
					    	rankOfFaculty = (String)listSelect.getSelectedValue();
					    	
					    	//Instantiate objects and pass values
				    	    address = new Address(street, houseNumber, city, state, zipCode);
				    	    appointmentDate = new MyDate(2019,03,27); //Nothing was indicated in instructions regarding date input so I'm filling with today's date
				    	    person = new Faculty(firstName, lastName, address, phoneNumber, emailAddress, rankOfFaculty, appointmentDate);
				    	    
				    	    //Assign new faculty to index value
				    	    personArray [personCount] = person;
				    	    personCount++;
					    }
						
					}
					catch(Exception ex) 
					{
						JOptionPane.showMessageDialog(null, "Please select student or faculty.");
						return;
					}
					
//					//Reset inputs for next person
					fldLN.setText("");
					fldFN.setText("");
					fldAddress.setText("");
					fldPhone.setText("");
					fldEmail.setText("");
					listValues.clear();
					listSelect.clearSelection();
					bg.clearSelection();
					
				}
						
				private void sortBnttonClicked() 
				{
					selectionSort();
				}

				private void displaynttonClicked() throws FileNotFoundException
				{
					// Method to implement display button action
					
					//Setup the persons display
					String display = "";
					
					for (int i=0; i <Person.numberOfPersons; i++)
				    {
						display += personArray[i].toString() + "\n";
				    }
					
					//Display results
					txtDisplay.setText(display);
				}
				
				
				private void selectionSort() 
				{
					//Method to sort person objects based on last name
					for(int i = 1; i < personArray.length; i++) 
					{
					    for(int j = 0; j < personArray.length - 1; j++) 
					    {
					    	if (personArray[j + 1].getLastName().compareToIgnoreCase(personArray[j].getLastName()) < 0)
					        {
					        	Person temp = personArray[j];
					            personArray[j] = personArray[j+1];
					            personArray[j+1] = temp;
					        }					            
					    }
					}
					
					//Update the display
					//Setup the persons display
					String display = "";
					
					for (int i=0; i <Person.numberOfPersons; i++)
				    {
						display += personArray[i].toString() + "\n";
				    }
					
					//Display results
					txtDisplay.setText(display);
				}
				
						
			public static void main(String[] args) throws FileNotFoundException 
			{
						
				//declare variables
				int numberOfPersons = 0;
				
				//Try/Catch for number of persons input
				try
				{
					//Input number of persons
					numberOfPersons = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of people in whole number, e.g., 3"));
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Value must be a whole number.");
					
					//Close application
				    System.exit(0);
				}

				//Create a new GUI
				UserGUI frame = new UserGUI(numberOfPersons);
				
				//Set GUI frame settings
				frame.pack();
				frame.setSize(1150, 475);
				frame.setTitle("PA07");
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			
			}// end main
			
	
	}
