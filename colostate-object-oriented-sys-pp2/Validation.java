/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 2
 * 
 * 								Validation class for validating a customer credit card number.
 * 
 * 								Date Created: 3/07/2019
 * 
 * 								Saved in: Validation.java
 */
package PP2;

public class Validation {

  // Return true if the card number is valid, otherwise returns false, this method is already implemented
  public boolean aValidNumber(String n) 
  {

	long number = Long.parseLong(n);
	return  (numLength(number) >= 13) && (numLength(number) <= 16) &&
        (prefixCheck(number, 4) || prefixCheck(number, 5) ||
        prefixCheck(number, 6) || prefixCheck(number, 37)) &&
        (totalEvenNumbers(number) + totalOddNumbers(number)) % 10 == 0;
  }// end of aValidNumber method

  //get the sum of even places numbers, Starting from the second digit from right
  private int totalEvenNumbers(long number) 
  {
	  int index = 0;
	  int even = 0;
	  String value = "";
	  int valueInInt = 0;
	  String strValue = Long.toString(number);
	 
	  if (strValue.length() % 2 == 0)
		  index = 0;
	  else index = 1;
	  
	  while (index < strValue.length()-1) {
		  
		  value = strValue.substring(index,index + 1);		  

		  valueInInt = Integer.parseInt(value);
		  
		  valueInInt = singleDigit(valueInInt);
		  
		  even += valueInInt; 
		  index = index + 2; 
	  }

	  return even;
	  
  }// end of totalEevenNumbers method

  //Return the same number if it is a single digit, otherwise, return the sum of
  // the two digits in this number
  private int singleDigit(int number) 
  {
	  int valueInInt = number;

	  if ((valueInInt * 2) > 9)
		valueInInt = 1 + ((valueInInt*2) % 10);
	  else valueInInt = valueInInt*2;
	  
	  return valueInInt;
	  
  } // end of singleDigit method

  //Return the sum of odd place digits in number
  private int totalOddNumbers(long number) 
  {

	  int index = 0;
	  int odd = 0;
	  String value = "";
	  int valueInInt = 0;
	  String strValue = Long.toString(number);
	 
	  if (strValue.length() % 2 == 0)
		  index = 1;
	  else index = 0;
	  
	  while (index <= strValue.length()-1) {
		  
		  value = strValue.substring(index,index + 1);		  

		  valueInInt = Integer.parseInt(value);
		  
		  odd += valueInInt; 
		  index = index + 2; 
	  }
	  
    return odd;
    
  }// end of totalOddNumbers method

  // Return true if the digit d is a prefix for number
  private boolean prefixCheck(long number, int d) 
  {
	  //Declare local variables
	  boolean check = false;
	  
	  //Get numPrefix
	  number = numPrefix(number, d);
	  
	  //Check if d
	  if (number == d)
	  {
		  check = true;
	  }
	  else
	  {
		  //Divide down to single digit
		  number = number / 10;
		  if (number == d)
		  {
			  check = true;
		  }
	  }

	 //Return the status 
	 return check;
	 
  }// end of prefixCheck method

  // Return the number of digits in this number parameter
  private int numLength(long number) 
  {
	  //Declare local variables
	  int length = 0;
	  
	  //Found this on Baeldung.com
	  length =  (int) (Math.log10(number) + 1);
	  
	  //Return the value
	  return length;
	  
  }// end of numLength method

  // Return the first k number of digits from number, which is either a first digit or first two digits
  // Depending on the card type
  private long numPrefix(long number, int k) 
  {
	  //Divide down to two digits (Amex cards)
	  while (number > 99)
	  {
		  number = number / 10;
	  }
	  
	  //Return the value
      return number;
      
  }// end of numPrefix method

}// end of the class
