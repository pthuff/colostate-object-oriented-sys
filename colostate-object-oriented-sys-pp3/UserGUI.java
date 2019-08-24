/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								UserGUI Class for gathering Employee/PayRecord info
 * 
 * 								Date Created: 4/03/2019
 * 
 * 								Saved in: UserGUI.java
 */
package PP03;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class UserGUI extends JFrame implements ActionListener {

	private PayRoll payroll;
	private static int employeesInInt = 0;
	private static int employeesFromFile = 0;
	private String filePath = ""; //create string for filepath info
	private Employee empReturn;
	private String textAreaString = "";

	/**add employee fields, labels, combo box, radio buttons, etc. */
	  //create employee text fields and labels to be entered
	  private JTextField jtfEmpID;
		private JLabel lblEmpID;
	  private JTextField jtfLastName;
	  	private JLabel lblLastName;
	  private JTextField jtfFirstName;
	  	private JLabel lblFirstName;
	  private JTextField jtfStreet;
	  	private JLabel lblStreet;
	  private JTextField jtfAptNo;
	  	private JLabel lblAptNo;
	  private JTextField jtfCity;
	  	private JLabel lblCity;
	  	private JLabel lblState;
	  private JTextField jtfZip;
	  	private JLabel lblZip;
	  private JTextField jtfEmpty1;
	  private JTextField jtfEmpty2;
	  
	  //Create Student/Faculty Radio Buttons
	  private JRadioButton fulltime;
      private JRadioButton hourly;
      ButtonGroup group;
	  
	  //Create a Compute Payment button
	  private JButton jbtCreateEmployee;

	  //Create combo boxes for faculty rank and student status
	  private JComboBox stateList;	  
	  
	  /**add payr record fields, labels, and buttons, etc. */
	  //create pay record text fields and labels to be entered
	  private JTextField jtfppID;
		private JLabel lblppID;
	  private JTextField jtfppStart;
	  	private JLabel lblppStart;
	  private JTextField jtfppEnd;
	  	private JLabel lblppEnd;
	  private JTextField jtfprID;
	  	private JLabel lblprID;
	  private JTextField jtfmonthlyIncome;
	  	private JLabel lblmonthlyIncome;
	  private JTextField jtfnoOfMonths;
	  	private JLabel lblnoOfMonths;
	  private JTextField jtfpayHours;
	  	private JLabel lblpayHours;
	  private JTextField jtfpayRate;
	  	private JLabel lblpayRate;
	  
	  private JButton jbtAddPayRecord;
	  
	  /**add text field to display employee information and stats and close button */
	  //Create text area to store person strings
	  private static JTextArea textArea;
	  private JScrollPane jp;

	  private JButton jbtClose;	  
	  
	  /** create UserGUI constructor */
	  public UserGUI() {
		
			boolean valid = false;//to check if # of persons is valid
			
			while (valid == false) {
				try {
					
					//parse user input in int
					employeesInInt = Integer.parseInt(JOptionPane.showInputDialog("Please enter # of employees to add..."));
					
					//check that input is positive
					if (employeesInInt > 0) 
						valid = true;
					
					else if (employeesInInt < 1)
						JOptionPane.showMessageDialog(null,"Number of persons must be a positive integer");
					
				}//end of try 
				
				catch (Exception ex) {
			    	JOptionPane.showMessageDialog(null,"Number of persons must be a positive integer");
			    	valid = false;
				}//end of catch
				
			}//end of while loop			

		  //begin try to determine if file was chosen
			try {
			//create dialog to accept file selection ***add catch when nothing is selected***
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Choose a File or select Cancel if no file to choose");
			    chooser.showOpenDialog(null);
				
			filePath = chooser.getSelectedFile().getPath();
			}//end of try
			
			catch (Exception ex) 
			{
				JOptionPane.showMessageDialog(null,"File not selected. Will continue program.");
				
			}//end of catch
			

			if (!filePath.isEmpty())
			{
				payroll = new PayRoll(filePath);
				payroll.readFromFile();
				employeesFromFile = Employee.getNoOfPersons();				
			}
			else
			{
				payroll = new PayRoll(filePath);
			}
		
		initGUI();  
		doTheLayout();
		
		if (!filePath.isEmpty())
		{
			payroll.displayPayRecord();
		}

		payroll.writeToFile();

		jbtCreateEmployee.addActionListener(this);
		jbtClose.addActionListener(this);
		jbtAddPayRecord.addActionListener(this);
		fulltime.addActionListener(this);
		hourly.addActionListener(this);
	  }
		  
	  	/**method to start the creation of GUI objects*/
	    private void initGUI() {

	    /**initiate employee gui labels, radios, buttons for top pane*/
	    lblEmpID = new JLabel("EmpID");
	  	  jtfEmpID = new JTextField(15);
	  	lblLastName = new JLabel("Last Name");
		  jtfLastName = new JTextField(15);
	  	lblFirstName = new JLabel("First Name");
	  	  jtfFirstName = new JTextField(15);
		lblStreet = new JLabel("Street Name");
		  jtfStreet = new JTextField(15);
		lblAptNo = new JLabel("Apt Number");
		  jtfAptNo = new JTextField(15);
		lblCity = new JLabel("City");
		  jtfCity = new JTextField(15);
		lblState =  new JLabel("State");
		lblZip = new JLabel("Zip");
		  jtfZip = new JTextField(15);
		  
		  //Create Student/Faculty Radio Buttons
		  fulltime = new JRadioButton("Full Time", true);
	      hourly = new JRadioButton("Hourly");
		  
	      group = new ButtonGroup();
		  group.add(fulltime);
		  group.add(hourly);
		  
	      //Create a Compute Payment button
		  jbtCreateEmployee = new JButton("Create Employee");
		  //jbtCreateEmployee.setSize(20,15);

		  //Create combo box for 50 US states
		  String[] stateStrings = {"Alabama", "Alaska", "Arizona", "Arkansas", "California",
			        "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
			        "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
			        "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
			        "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana",
			        "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
			        "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma",
			        "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota",
			        "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia",
			        "Wisconsin", "Wyoming"};
		  stateList = new JComboBox(stateStrings);	  
		  stateList.setSelectedIndex(-1);
		  
		  /**text fields and add button for pay periods in middle pane*/
		    lblppID = new JLabel("Pay Period ID");
		  	  jtfppID = new JTextField(15);
		  	lblppStart = new JLabel("Pay Period Start");
			  jtfppStart = new JTextField(15);
		  	lblppEnd = new JLabel("Pay Period End");
		  	  jtfppEnd = new JTextField(15);
		  	 lblprID = new JLabel("Pay Record ID");
			  jtfprID = new JTextField(15);
			lblmonthlyIncome = new JLabel("Monhtly Income");
			  jtfmonthlyIncome = new JTextField(15);
			  jtfmonthlyIncome.setText("0");
			lblnoOfMonths = new JLabel("No of Months");
			  jtfnoOfMonths = new JTextField(15);
			  jtfnoOfMonths.setText("0");
			lblpayHours = new JLabel("No of Hours");
			  jtfpayHours = new JTextField(16);
			  jtfpayHours.setText("0");
			  jtfpayHours.setEditable(false);
			lblpayRate = new JLabel("Rate of Pay");
			  jtfpayRate = new JTextField(15);
			  jtfpayRate.setText("0");
			  jtfpayRate.setEditable(false);
			  
		  	  jtfEmpty1 = new JTextField(15);
		  	  	jtfEmpty1.setVisible(false);
		  	  jtfEmpty2 = new JTextField(15);
		  	  	jtfEmpty2.setVisible(false);
		  	  	
			  jbtAddPayRecord = new JButton("Create Pay Record");
			  //jbtAddPayRecord.setSize(20,15);
		  
		  /**text area and close button for bottom pane*/
		  //Create text area to store person strings
	      textArea = new JTextArea(5, 60);
	      textArea.setEditable(false);
	      textArea.setLineWrap(true);
	      textArea.setWrapStyleWord(true);
	      textArea.setText("");
	      textArea.setLineWrap(false);
	      jp = new JScrollPane(textArea);

		  jbtClose = new JButton("Close");
	      
	    }//end of initiate component
	
	    /**method to put together the layout of the GUI */
		private void doTheLayout (){
			  	
				JPanel top = new JPanel();
				JPanel center = new JPanel();
				JPanel bottom = new JPanel();
				
			  	/**build panels for top main top Panel with Employee info*/
				JPanel employeeInfo = new JPanel();
			  	JPanel addressInfo = new JPanel();
			  	
			    employeeInfo.setLayout(new GridLayout(5,2));
			    employeeInfo.add(lblEmpID);
			    employeeInfo.add(jtfEmpID);
			    employeeInfo.add(lblLastName);
			    employeeInfo.add(jtfLastName);
			    employeeInfo.add(lblFirstName);
			    employeeInfo.add(jtfFirstName);
			    employeeInfo.add(fulltime);
			    employeeInfo.add(hourly);
			    employeeInfo.add(jbtCreateEmployee);
			    employeeInfo.setBorder(new TitledBorder("Enter Data for Each Employee"));
			    
			    addressInfo.setLayout(new GridLayout(5,2));
			    addressInfo.add(lblStreet);
			    addressInfo.add(jtfStreet);
			    addressInfo.add(lblAptNo);
			    addressInfo.add(jtfAptNo);
			    addressInfo.add(lblCity);
			    addressInfo.add(jtfCity);
			    addressInfo.add(lblState);
			    addressInfo.add(stateList);
			    addressInfo.add(lblZip);
			    addressInfo.add(jtfZip);
			    addressInfo.setBorder(new TitledBorder("Enter Address for Each Employee"));
			  	
			    //Add employee and address info to top panel
			    top.add(employeeInfo);
			    top.add(addressInfo);
			    top.setBorder(new TitledBorder("Employee"));

			  	/**build panels for center main top Panel with pay period info*/
				JPanel payPeriodInfo = new JPanel();
			  	JPanel payRecordInfo = new JPanel();
			  	
			    payPeriodInfo.setLayout(new GridLayout(5,2));
			    payPeriodInfo.add(lblppID);
			    payPeriodInfo.add(jtfppID);
			    payPeriodInfo.add(lblppStart);
			    payPeriodInfo.add(jtfppStart);
			    payPeriodInfo.add(lblppEnd);
			    payPeriodInfo.add(jtfppEnd);
			    //payPeriodInfo.add(jtfEmpty1);
			    //payPeriodInfo.add(jtfEmpty2);
			    payPeriodInfo.add(jbtAddPayRecord);
			    payPeriodInfo.setBorder(new TitledBorder("Enter Data for Pay Period"));
			    
			    payRecordInfo.setLayout(new GridLayout(5,2));
			    payRecordInfo.add(lblprID);
			    payRecordInfo.add(jtfprID);
			    payRecordInfo.add(lblmonthlyIncome);
			    payRecordInfo.add(jtfmonthlyIncome);
			    payRecordInfo.add(lblnoOfMonths);
			    payRecordInfo.add(jtfnoOfMonths);
			    payRecordInfo.add(lblpayHours);
			    payRecordInfo.add(jtfpayHours);
			    payRecordInfo.add(lblpayRate);
			    payRecordInfo.add(jtfpayRate);
			    payRecordInfo.setBorder(new TitledBorder("Enter Pay Record Information"));
			    
			    center.add(payPeriodInfo);
			    center.add(payRecordInfo);
			    center.setBorder(new TitledBorder("Pay Period & Pay Record"));
			    
			    /**create panel to hold display text and scroll pane*/
			  	JPanel textInfo = new JPanel();

			    textInfo.setLayout(new FlowLayout(FlowLayout.LEFT));
			    textInfo.add(jp);
			    
			    //add text info and close button to bottom panel
			    bottom.add(textInfo);
			    bottom.add(jbtClose);
			    bottom.setBorder(new TitledBorder("Current Employee Record & Stats (Total & Average Payment)"));
			    
			    /**Add the panels to the frames */
			    setLayout(new BorderLayout());
			    add(top, BorderLayout.NORTH);
			    add(center, BorderLayout.CENTER);
			    add(bottom, BorderLayout.SOUTH);
			    
		  }//end of doTheLayout method

		  /** Handle the Buttons*/
		  @Override
		  public void actionPerformed(ActionEvent e) {
			
			  if (e.getSource() == jbtCreateEmployee) {
				  createEmployee();
			  JOptionPane.showMessageDialog(null, "Employee Created.");
			  }
			  else if (e.getSource() == jbtAddPayRecord) {
				  addPayRecord();
			  	}
			  else if (e.getSource() == jbtClose)
				  close();
			  
			  if (e.getActionCommand() == "Full Time") {
				  jtfpayHours.setEditable(false);
			  	  jtfpayRate.setEditable(false);
				  jtfmonthlyIncome.setEditable(true);
				  jtfnoOfMonths.setEditable(true);
			  }

			  else if (e.getActionCommand() == "Hourly") {
				  jtfmonthlyIncome.setEditable(false);
				  jtfnoOfMonths.setEditable(false);
				  jtfpayHours.setEditable(true);
			  	  jtfpayRate.setEditable(true);
			  }
		  
		  }//end of actionPerformed method
		
	
		  /**method to add persons to student/faculty objects */
		  private void createEmployee() {

			  //check for non integer employee ID
				int empID = 0;
				try {
					empID = Integer.parseInt(jtfEmpID.getText().trim());
			    } catch (Exception ex) {
			    	JOptionPane.showMessageDialog(jtfEmpID,"Invalid Employee ID Input");
			    	jtfEmpID.setText("");
			    	return;  
			    }

				//check for blank last name
				if(jtfLastName.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null,"Last name cannot be blank.");
					jtfLastName.setText("");
					return;
				}
				
				//check for blank first name
				if(jtfFirstName.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null,"First name cannot be blank.");
					jtfFirstName.setText("");
					return;
				}

				//check for blank street name
				if(jtfStreet.getText().trim().length()==0) {
					JOptionPane.showMessageDialog(null,"Street name cannot be blank.");
					jtfStreet.setText("");
					return;
				}

				//check for blank apartment number
			    if(jtfAptNo.getText().trim().length()==0) {
			    	JOptionPane.showMessageDialog(jtfAptNo,"City cannot be blank.");
			    	jtfAptNo.setText("");
			    	return;
				}
				
				//check for blank city name
			    if(jtfCity.getText().trim().length()==0) {
			    	JOptionPane.showMessageDialog(jtfCity,"City cannot be blank.");
			    	jtfCity.setText("");
			    	return;
				}
			     
				//check for non integer house number
			    int zip = 0;
			    try {
			    	zip = Integer.parseInt(jtfZip.getText().trim());
			    } catch (Exception ex) {
			    	JOptionPane.showMessageDialog(jtfZip,"Invalid zip code Input");
			    	jtfZip.setText("");
			    	return;  
			    }

				if (Employee.getNoOfPersons() < employeesInInt) {
				//create new address objects
			    Address newAddress 
			    	= new Address(jtfStreet.getText().trim(),jtfAptNo.getText().trim()
			    			,jtfCity.getText().trim(),stateList.getSelectedItem().toString(),zip);	
				
			    Status newStatus;
			    
			    if (fulltime.isSelected())
			    	newStatus = Status.FullTime;
			    else 
			    	newStatus = Status.Hourly;
			    
				empReturn = payroll.createEmployee(jtfFirstName.getText().trim(),jtfLastName.getText().trim(),newAddress,empID,newStatus);
				}
				
				else JOptionPane.showMessageDialog(null,"The array of employees is full");
				
				  jtfEmpID.setText("");
				  jtfLastName.setText("");
				  jtfFirstName.setText("");
				  jtfStreet.setText("");
				  jtfAptNo.setText("");
				  jtfCity.setText("");
				  stateList.setSelectedIndex(-1);
				  jtfZip.setText("");

			    
			}//end of addButtonClicked method
			    
		/** method to display sorted or unsorted person objects */
		private void addPayRecord() {
		
			  /**start pay record creation */  
			if (employeesFromFile != Employee.getNoOfPersons())
			{
				//check for non integer payroll ID
				int ppID = 0;
				try {
					ppID = Integer.parseInt(jtfppID.getText().trim());
			    } catch (Exception ex) {
			    	JOptionPane.showMessageDialog(jtfppID,"Invalid payroll ID Input");
			    	jtfppID.setText("");
			    	return;  
			    }
				
				//check for bad pay period start date
				Date start = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				try {
					start = dateFormat.parse(jtfppStart.getText().trim());
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(jtfppStart,"Start Date must be in format mm/dd/yyyy");
					jtfppStart.setText("");
					return;
				}
				
				//check for bad pay period end date
				boolean valid = false;
				Date end = new Date();
				
				while (valid == false) {		
					try {
						end = dateFormat.parse(jtfppEnd.getText().trim());
						if (end.compareTo(start) > 0) {
							valid = true;
						}	
						else if (end.compareTo(start) < 0 || end.compareTo(start) == 0) {	
							JOptionPane.showMessageDialog(jtfppEnd,"Pay period end date can't be before or equal to "
								+ "pay period start date");
							jtfppEnd.setText("");
							return;
						}	
					} catch (Exception ex) {
							JOptionPane.showMessageDialog(jtfppEnd,"End Date must be in format mm/dd/yyyy");
							jtfppEnd.setText("");
							return;
					}
				}//end of end date validation
				

				//check for non int pay record ID
				int prID = 0;
				try {
					prID = Integer.parseInt(jtfprID.getText().trim());
			    } catch (Exception ex) {
			    	JOptionPane.showMessageDialog(jtfprID,"Invalid pay record ID Input");
			    	jtfprID.setText("");
			    	return;  
			    }
				
				//check for non double monthly income
				double monthlyIncome = 0;
				try {
					monthlyIncome = Double.parseDouble(jtfmonthlyIncome.getText().trim());
			    } catch (Exception ex) {
			    	JOptionPane.showMessageDialog(jtfmonthlyIncome,"Invalid monthly income");
			    	jtfmonthlyIncome.setText("");
			    	return;  
			    }

				//check for non int number of months
				int months = 0;
				long diff = end.getTime() - start.getTime();
				try 
				{		
				months = Integer.parseInt(jtfnoOfMonths.getText().trim());				
					if ((TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)) < ((long)months * 28))
					{
						throw new ArithmeticException();
					}
			    } catch (Exception ex) {
			    	JOptionPane.showMessageDialog(jtfnoOfMonths,"Invalid number of months");
			    	jtfnoOfMonths.setText("");
			    	return;  
			    }
				
				//check for non double pay hours
				double hours = 0;
				try {
					hours = Double.parseDouble(jtfpayHours.getText().trim());
			    } catch (Exception ex) {
			    	JOptionPane.showMessageDialog(jtfpayHours,"Invalid number of hours");
			    	jtfpayHours.setText("");
			    	return;  
			    }

				//check for non double pay hours
				double rate = 0;
				try {
					rate = Double.parseDouble(jtfpayRate.getText().trim());
			    } catch (Exception ex) {
			    	JOptionPane.showMessageDialog(jtfpayRate,"Invalid pay rate");
			    	jtfpayRate.setText("");
			    	return;  
			    }
			     
				//if student add student status
				PayPeriod newPP = new PayPeriod(ppID,start,end);	
					
				payroll.createPayRecord(prID,empReturn,newPP,hours,rate,monthlyIncome,months);
				
				payroll.writeToFile();
				
				payroll.displayPayRecord();
				
				  jtfppID.setText("");
				  jtfppStart.setText("");
				  jtfppEnd.setText("");
				  jtfprID.setText("");
			  	  jtfpayHours.setText("0.0");
			  	  jtfpayRate.setText("0.0");
				  jtfmonthlyIncome.setText("0.0");
				  jtfnoOfMonths.setText("0");

			}
			else
			{
				JOptionPane.showMessageDialog(null,"Employee must be created before adding a new pay record.");
			}	
			
			
		}//end of addPayRecord method
		
		public static void setTextAreaString(String payRollDisplay) {
			textArea.append(payRollDisplay);
		}

		private void close() {
			JOptionPane.showMessageDialog(null, "System Will Close");
			System.exit(0);
		}
		
		/** main method */	    
		public static void main(String[] args) {
			
		UserGUI frame = new UserGUI();
	    frame.pack();
	    frame.setTitle("Add New Person");
	    frame.setLocationRelativeTo(null); // Center the frame
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.addWindowListener(new WindowAdapter() {
	    	public void windowClosing(WindowEvent windowEvent){
	               System.exit(0);
	            }
	        });
	    
	    }//end of main method

		public static int getEmployeesInInt() {
			return employeesInInt;
		}

}//end of class	    