/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA04
 * 
 * 								Sort Class for sorting arrays
 * 
 * 								Date Created: 2/22/2019
 * 
 * 								Saved in: Sort.java
 */

public class Sort {
	
	//Method to sort arrays by price (ascending)
	public static void selectionSort(String[] arrayName, double[] arrayPrice)
	{
		//Declare local variables to hold swap values
		double tempPrice = 0.00;
		String tempName;
		
		//Loop to sort price array ascending and sort name array simultaneously
        for (int j = 0; j < arrayPrice.length - 1; j++)
        {
        	for (int i = 0; i < arrayPrice.length - 1; i++) 
        	{
        		if (arrayPrice[i] > arrayPrice[i + 1]) 
        		{
        			tempPrice = arrayPrice[i];
        			tempName = arrayName[i];
        			
        			arrayPrice[i] = arrayPrice[i + 1];
        			arrayName[i] = arrayName[i + 1];
        			
        			arrayPrice[i + 1] = tempPrice;
        			arrayName[i + 1] = tempName;
        			
        			Product.pPrice = arrayPrice;
        			Product.pName = arrayName;
        		}
        	}
        }
	}
}
