/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA04
 * 
 * 								PasswordCheck Class for verifying password characters/length
 * 
 * 								Date Created: 2/22/2019
 * 
 * 								Saved in: PasswordCheck.java
 */

import javax.swing.JOptionPane;

public class PasswordCheck {

	public static void main(String[] args) 
	{
		//Declare local variables
		String password = null;
		int morePasswords = 0;
		boolean userExit = false;
		
		//Prompt to enter password
		while (userExit != true)
		{
			password =JOptionPane.showInputDialog("Please enter a password.\nPassword must be at least 8 characters, contain two or more numbers, and contain one of these special characters: %, &, #.");
			if (isValidPassword(password) == true)
			{
				JOptionPane.showMessageDialog(null, "Valid password");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid password");
			}
			
			//Prompt to enter more passwords
			morePasswords = JOptionPane.showConfirmDialog (null, "Enter more passwords?","Confirmation",JOptionPane.YES_NO_OPTION);
			if (morePasswords == 1)
			{
				userExit = true;
			}
			else
			{
				userExit = false;
			}
		}
		//Close application
	    System.exit(0);
	}
	//Password check method
	public static boolean isValidPassword(String check)
	{
		if (check != null && check.matches("((?=.*[a-zA-Z])(?=.*?\\d.*\\d)(?=.*[#&%]).{8,40})"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
