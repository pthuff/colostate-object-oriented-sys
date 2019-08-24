/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								PayRoll class for inputting/outputting pay records and creating associated objects
 * 
 * 								Date Created: 4/03/2019
 * 
 * 								Saved in: PayRoll.java
 */
package PP03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class PayRoll {

	private String fileName;
	private PayRecord[] payRecords = new PayRecord[100];
	private double totalNetPay;
	private double avgNetPay;
	private Employee empReturn;
	
	public PayRoll(String fileName){
		this.fileName = fileName;
       }

	/**read PayRoll.txt file and add to created PayRecord.txt file*/
   public void readFromFile(){
		
	   // read the initial data from PayRoll file to create the full 
		BufferedReader br = null;
		BufferedReader newbr = null;
		Address newAddress;
		Status newStatus;
		PayPeriod newPayPeriod;
		Date start = new Date();
		Date end = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		try {

			String line;

			br = new BufferedReader(new FileReader(this.fileName));
			
			while ((line = br.readLine()) != null) {
				
				String[] stringArr = line.split(",");
					
					//create employee record
					if (stringArr[0].toString().trim().compareTo("employee") == 0 && Employee.getNoOfPersons() < UserGUI.getEmployeesInInt()) {
						
						if (stringArr[4].toString().trim().compareTo("FULLTIME") == 0)
							newStatus = Status.FullTime;
						else newStatus = Status.Hourly;
						
						newAddress = new Address(stringArr[5].trim(),stringArr[6].trim()
								,stringArr[7].trim(),stringArr[8].trim(),Integer.parseInt(stringArr[9].trim()));
						
						empReturn = createEmployee(stringArr[2].trim(),stringArr[3].trim(),newAddress
								,Integer.parseInt(stringArr[1].trim()),newStatus);
					
						//start new while loop to read and add payRecords for most recent employee added
						String newline;

						newbr = new BufferedReader(new FileReader(this.fileName));
	
						while ((newline = newbr.readLine()) != null) {
							
							String[] newStringArr = newline.split(",");
					
							if ((newStringArr[0].toString().trim().compareTo("payRecord") == 0) && (Integer.parseInt(newStringArr[2].toString().trim()) == empReturn.geteID())) {
								
								try {
									start = dateFormat.parse(newStringArr[6].toString().trim());
									end = dateFormat.parse(newStringArr[7].toString().trim());
								} catch (ParseException ex) {
									JOptionPane.showMessageDialog(null,"Unable to convert to date");
								}
								
								newPayPeriod = new PayPeriod(Integer.parseInt(newStringArr[5].toString().trim()),start,end);

								if(empReturn.getEmpStatus().compareTo(Status.FullTime) == 0) {
									createPayRecord(Integer.parseInt(newStringArr[1].toString().trim()),empReturn,newPayPeriod
													,0.0,0.0,Double.parseDouble(newStringArr[3].toString().trim()),Integer.parseInt(newStringArr[4].toString().trim()));
								}//end of add full pay record
								
								else if(empReturn.getEmpStatus().compareTo(Status.Hourly) == 0) { 
									createPayRecord(Integer.parseInt(newStringArr[1].trim()),empReturn,newPayPeriod
											,Double.parseDouble(newStringArr[3].trim()),Double.parseDouble(newStringArr[4].trim()),0.0,0);
								}//end of add hourly pay record

							}//end of check for pay record and matching employee IF		
						
						}//end of newPayRecord while loop
							
					}//end of new employee creation
					
			}//end of while loop

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
   }//end of readFromFile() method 
   
   /**add imported and user added employee pay records to PayRecord.txt file*/
   // write employees' pay records to the PayRecord.txt file, it should add employee pay record to the current file data
   public void writeToFile(){
	   
	 //Declare local variables
   	String display = "";
   	
		try 
		{
			// Instantiate new file object, wrap in PrintWriter
			java.io.File file = new java.io.File("PayRecord.txt");
			java.io.PrintWriter output = new java.io.PrintWriter(file);

			// Print each array index to the output file
			for (int i = 0; i < PayRecord.getNoOfRecords(); i++) 
			{		
				// Write to output using payRecords toString
				display = payRecords[i].toString();
				output.println(display);
			}
			// Close the writer
			output.close();

		} 
		catch (FileNotFoundException e) 
		{
			// Display exception
			JOptionPane.showMessageDialog(null, "Error writing file!");
		}//end of catch   
	   
	}//end add record method 
   
    /**create employee and address objects*/
	public Employee createEmployee(String fname, String lname, Address address, int eID, Status status){
		// creates a new Employee object and add it to the employees array, you need to pass parameters to this method
		
		Employee newEmployee = new Employee(fname,lname,address,eID,status);
		
		return newEmployee;
	
	}
	
	/**creates payroll record*/
	public void createPayRecord(int rID, Employee e, PayPeriod pp, double hours, double rate
			, double moIncome, int months){
		
		if (e.getEmpStatus() == Status.Hourly) {
		payRecords[PayRecord.getNoOfRecords()] = new PayRecord(rID, e, pp, hours, rate);
		}
		else if (e.getEmpStatus() == Status.FullTime) {
		payRecords[PayRecord.getNoOfRecords()] = new PayRecord(rID, e, pp, moIncome, months);
		}
		
	}//end of createpayRecord method
	
	/**called by UserGUIto append new payroll record to text area*/
    public void displayPayRecord(){
		
		// it shows all payroll records for all currently added employee and the total net pay and average net pay in the GUI text area
    	// at should append data to text area, it must not overwrite data in the GUI text area
    	
    	String display = "";
    	String empDisplay = "";
    	
		for (int i = PayRecord.getNoOfRecords()-1; i < PayRecord.getNoOfRecords(); i++) {
			
			empDisplay += payRecords[i].toString() + "\n";
		
		}	
			display = empDisplay
					+ "Total net: " + String.format("%.2f",avgNetPay()*PayRecord.getNoOfRecords()) 
					+ "  Average net: " + String.format("%.2f",avgNetPay())
					+ "  Net per employee: " + String.format("%.2f",(avgNetPay()*PayRecord.getNoOfRecords())/Employee.getNoOfPersons()) + "\n";
			
			UserGUI.setTextAreaString(display);
    	    	
	}//end of displayPayRecord() method

   /**calculate average net pay*/ 
   public double avgNetPay(){
		
	   totalNetPay = 0;
	   
		for (int i = 0; i < PayRecord.getNoOfRecords(); i++) {
		totalNetPay = totalNetPay + payRecords[i].netPay();
		}
		
		avgNetPay = totalNetPay/PayRecord.getNoOfRecords();
		
		return avgNetPay;
	}//end of avgNetPay() method

}//end of class
