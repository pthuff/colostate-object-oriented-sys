/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA03
 * 
 * 								SkiRentalCalculation Class for calculating a ski rental charge
 * 
 * 								Date Created: 2/6/2019
 * 
 * 								Saved in: SkiRentalCalculation.java
 */

import javax.swing.*;

public class SkiRentalCalculation {

	public static void main(String[] args) 
	{
		//Declare variables
		String numberOfSkiCustomers = null;
		int numberOfCustomers = 0;
		
		
		//Try/Catch for skier input
		try
		{
			//Input number of skiers
			numberOfSkiCustomers =JOptionPane.showInputDialog("Enter the number of ski customers in whole number, e.g., 3");
			
			//Convert to integer
			numberOfCustomers = Integer.parseInt(numberOfSkiCustomers);
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Value must be a whole number");
			
			//Close application
		    System.exit(0);
		}
			
			//Declare arrays
			String[] namesArray = new String[numberOfCustomers];
			double[] totalsArray = new double[numberOfCustomers];

			//Loop for values and populate arrays
			for (int i=0; i < numberOfCustomers; i++)
			{
				//Declare local variables
				int numOfDays = 0;
				int numOfRentals = 0;
				String firstTime = null;
				String coupon = null;
				String membership = null;
				
				try
				{
					//Declare variables
					boolean nameValid = false;
					boolean rentalsValid = false;
					boolean firstTimeValid = false;
					boolean couponValid = false;
					boolean membershipValid = false;
					String nameOfSkier = null;
					String rentals = null;
							
					//Input name of skier with validation
					while (nameValid != true)
					{
						nameOfSkier = JOptionPane.showInputDialog("Enter your name, e.g., John Doe ");
						nameValid = (nameOfSkier != null) && nameOfSkier.matches("^[A-Z][a-z]*(\\s)+[A-Z][a-z]*$");
					}
					
					//Populate names array
					namesArray[i] = nameOfSkier;
					
					//Input number of days with validation
					String days = JOptionPane.showInputDialog("Enter the number of days in whole number, e.g., 3");
					
					//Convert days
					numOfDays = Integer.parseInt(days);
					
					//Input number of rentals with validation
					while (rentalsValid != true)
					{
						rentals = JOptionPane.showInputDialog("Enter the number of ski rentals in whole number, between 0-3 ");
						rentalsValid = (rentals != null) && rentals.matches("[0-3]");
					}
					
					//Convert rentals
					numOfRentals = Integer.parseInt(rentals);
					
					//Input first time user with validation
					while (firstTimeValid != true)
					{
						firstTime = JOptionPane.showInputDialog("First time user? Yes or No ");
						firstTimeValid = (firstTime != null) && firstTime.matches("^(?:Yes|No)$");
					}
					
					//Input discount coupon with validation
					while (couponValid != true)
					{
						coupon = JOptionPane.showInputDialog("Do you have a coupon? Yes or No ");
						couponValid = (coupon != null) && coupon.matches("^(?:Yes|No)$");
					}
					
					//Input membership with validation
					while (membershipValid != true)
					{
						membership = JOptionPane.showInputDialog("Do you have a membership? Yes or No ");
						membershipValid = (membership != null) && membership.matches("^(?:Yes|No)$");
					}
					
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, "Incorrect value was entered!");
					
					//Close application
				    System.exit(0);
				}	
				
				//Declare local calculation variables
				double baseRate = 0.00;
				double memberDiscount = 0.00;
				double firstTimeDiscount = 0.00;
				double couponDiscount = 0.00;
				final double SALES_TAX = .075; 
				
				//Set base rate
				if (numOfRentals == 0)
				{
					baseRate = 60.00;
					
					//Switch statement for membership discount
					switch(membership)
					{	   
			        case "Yes":
			        	memberDiscount = .05;
			            break;
			        case "No":
			        	memberDiscount = 0.00;
			            break;
			        default:
			        	memberDiscount = 0.00;
			            break;
					}
			            
					//Switch statement for first time discount
					switch(firstTime)
					{	   
			        case "Yes":
			        	firstTimeDiscount = .10;
			            break;
			        case "No":
			        	firstTimeDiscount = 0.00;
			            break;
			        default:
			        	firstTimeDiscount = 0.00;
			            break;
					}
					
					//Switch statement for coupon discount
					switch(coupon)
					{	   
			        case "Yes":
			        	couponDiscount = 5.00;
			            break;
			        case "No":
			        	couponDiscount = 0.00;
			            break;
			        default:
			        	couponDiscount = 0.00;
			            break;
					}
				}				
				else if (numOfRentals == 1)
				{
					baseRate = 110.00;
					
					//Switch statement for membership discount
					switch(membership)
					{	   
			        case "Yes":
			        	memberDiscount = .08;
			            break;
			        case "No":
			        	memberDiscount = 0.00;
			            break;
			        default:
			        	memberDiscount = 0.00;
			            break;
					}
					
					//Switch statement for first time discount
					switch(firstTime)
					{	   
			        case "Yes":
			        	firstTimeDiscount = .12;
			            break;
			        case "No":
			        	firstTimeDiscount = 0.00;
			            break;
			        default:
			        	firstTimeDiscount = 0.00;
			            break;
					}
					
					//Switch statement for coupon discount
					switch(coupon)
					{	   
			        case "Yes":
			        	couponDiscount = 10.00;
			            break;
			        case "No":
			        	couponDiscount = 0.00;
			            break;
			        default:
			        	couponDiscount = 0.00;
			            break;
					}
				}
				else if (numOfRentals == 2)
				{
					baseRate = 155.00;
					
					//Switch statement for membership discount
					switch(membership)
					{	   
			        case "Yes":
			        	memberDiscount = .08;
			            break;
			        case "No":
			        	memberDiscount = 0.00;
			            break;
			        default:
			        	memberDiscount = 0.00;
			            break;
					}
					
					//Switch statement for first time discount
					switch(firstTime)
					{	   
			        case "Yes":
			        	firstTimeDiscount = .12;
			            break;
			        case "No":
			        	firstTimeDiscount = 0.00;
			            break;
			        default:
			        	firstTimeDiscount = 0.00;
			            break;
					}
					
					//Switch statement for coupon discount
					switch(coupon)
					{	   
			        case "Yes":
			        	couponDiscount = 10.00;
			            break;
			        case "No":
			        	couponDiscount = 0.00;
			            break;
			        default:
			        	couponDiscount = 0.00;
			            break;
					}
				}
				else if (numOfRentals == 3)
				{
					baseRate = 190.00;
					
					//Switch statement for membership discount
					switch(membership)
					{	   
			        case "Yes":
			        	memberDiscount = .08;
			            break;
			        case "No":
			        	memberDiscount = 0.00;
			            break;
			        default:
			        	memberDiscount = 0.00;
			            break;
					}
					
					//Switch statement for first time discount
					switch(firstTime)
					{	   
			        case "Yes":
			        	firstTimeDiscount = .12;
			            break;
			        case "No":
			        	firstTimeDiscount = 0.00;
			            break;
			        default:
			        	firstTimeDiscount = 0.00;
			            break;
					}
					
					//Switch statement for coupon discount
					switch(coupon)
					{	   
			        case "Yes":
			        	couponDiscount = 10.00;
			            break;
			        case "No":
			        	couponDiscount = 0.00;
			            break;
			        default:
			        	couponDiscount = 0.00;
			            break;
					}
				}
				
				//Calculate total charge
				double totalCharge = (baseRate * numOfDays) - ((baseRate * numOfDays) * (memberDiscount + firstTimeDiscount));
				
				//Populate totals array
				totalsArray[i] = (totalCharge + (totalCharge * SALES_TAX)) - couponDiscount;
			}
			
			//Parse results to formatted string
			String display = "";
			
			for (int i=0; i < numberOfCustomers; i++)
		    {
				display += "Customer: " + namesArray[i] + ", " + "Total: $" + String.format("%.2f", totalsArray[i]) + "\n";
		    }
			
			//Display results
			JOptionPane.showMessageDialog(null, display);
			
		    //Close application
		    System.exit(0);
	}
}
