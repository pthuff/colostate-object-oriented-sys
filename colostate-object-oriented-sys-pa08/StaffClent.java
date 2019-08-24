/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA08
 * 
 * 								StaffClent class for user input GUI and sending messages 
 * 
 * 								Date Created: 4/11/2019
 * 
 * 								Saved in: StaffClent.java
 */
package PA08;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

class StaffClent extends JFrame implements ActionListener{

	private String hostname;
	private int port;
		
	private Socket socket;
	private Message message;
	ObjectOutputStream output;
	ObjectInputStream input;

    // declare UI component objects
	private JTextField jtfID;
	private JLabel lblID;
	private JTextField jtfLastName;
  	private JLabel lblLastName;
  	private JTextField jtfFirstName;
  	private JLabel lblFirstName;
  	private JTextField jtfMI;
  	private JLabel lblMI;
  	private JTextField jtfAddress;
  	private JLabel lblAddress;
  	private JTextField jtfCity;
  	private JLabel lblCity;
  	private JComboBox stateList;
  	private JLabel lblState;
  	private JTextField jtfmobilePhone;
  	private JLabel lblmobilePhone;
  	private JTextField jtfhomePhone;
  	private JLabel lblhomePhone;
  	private JTextField jtfmobileCarrier;
  	private JLabel lblmobileCarrier;
  	private JTextField jtfhomeCarrier;
  	private JLabel lblhomeCarrier;
  	
  	//Buttons
  	private JButton jbtView;
  	private JButton jbtInsert;
  	private JButton jbtUpdate;
  	private JButton jbtDelete;
  	private JButton jbtClear;
  	private JButton jbtClose;
	
public StaffClent(String hostname, int port) 
{
	
	this.port = port;
	this.hostname = hostname;
	
	// Create a connection with the StaffServer server on port number 8000
	try 
	{
		//Instantiate socket to connect with server
		socket = new Socket(hostname,port);
		
		//Instantiate in/out streams
		output = new ObjectOutputStream(socket.getOutputStream());
		input = new ObjectInputStream(socket.getInputStream());
			      
	} 
	catch (UnknownHostException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	catch (IOException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	// call these two methods to create user GUI
	initComponenet();
	doTheLayout();
	
	//Button action listeners
	jbtView.addActionListener(this);
	jbtInsert.addActionListener(this);
	jbtUpdate.addActionListener(this);
	jbtDelete.addActionListener(this);
	jbtClear.addActionListener(this);
	jbtClose.addActionListener(this);	
}

private void initComponenet()
{
	// Initialize the GUI components
	lblID = new JLabel("ID:");
	jtfID = new JTextField();
	lblLastName = new JLabel("Last Name:");
	jtfLastName = new JTextField(10);
	lblFirstName = new JLabel("First Name:");
	jtfFirstName = new JTextField(10);
	lblMI = new JLabel("MI:");
	jtfMI = new JTextField(1);
	lblAddress = new JLabel("Address:");
	jtfAddress = new JTextField(20);
	lblState =  new JLabel("State:");
	jtfCity = new JTextField(20);
	lblCity = new JLabel("City:");
	jtfmobilePhone = new JTextField(10);
	lblmobilePhone =  new JLabel("Mobile Phone Number:");
	jtfhomePhone = new JTextField(10);
	lblhomePhone =  new JLabel("Home Phone Number:");
	jtfmobileCarrier = new JTextField();
	lblmobileCarrier =  new JLabel("Mobile Phone Carrier:");
	jtfhomeCarrier = new JTextField();
	lblhomeCarrier =  new JLabel("Home Phone Carrier:");
	  
	// Create combo box for 50 US states
	String[] stateStrings = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut",
				"Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
				"Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi",
				"Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York",
				"North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island",
				"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington",
				"West Virginia", "Wisconsin", "Wyoming" };
	stateList = new JComboBox(stateStrings);
	stateList.setSelectedIndex(-1);
	
	//Buttons
	jbtView = new JButton("View");
	jbtInsert = new JButton("Insert");
	jbtUpdate = new JButton("Update");
	jbtDelete = new JButton("Delete");
	jbtClear = new JButton("Clear");
	jbtClose = new JButton("Close");
}

private void doTheLayout()
{
	// Arrange the UI components into GUI window
	JPanel top = new JPanel();
	JPanel center = new JPanel();
	JPanel bottom = new JPanel();
	
	JPanel staffInfo = new JPanel();
	JPanel addressInfo = new JPanel();
  	JPanel phoneInfo = new JPanel();
  	JPanel buttons = new JPanel();
  	
  	//Identification panel
  	staffInfo.setLayout(new GridLayout(4,2,5,5));
  	staffInfo.add(lblID);
  	lblID.setHorizontalAlignment(JLabel.RIGHT);
    staffInfo.add(jtfID);
    staffInfo.add(lblLastName);
    lblLastName.setHorizontalAlignment(JLabel.RIGHT);
    staffInfo.add(jtfLastName);
    staffInfo.add(lblFirstName);
    lblFirstName.setHorizontalAlignment(JLabel.RIGHT);
    staffInfo.add(jtfFirstName);
    staffInfo.add(lblMI);
    lblMI.setHorizontalAlignment(JLabel.RIGHT);
    staffInfo.add(jtfMI);
    
    //Address panel
    addressInfo.setLayout(new GridLayout(3,2,5,5));
    addressInfo.add(lblAddress);
    lblAddress.setHorizontalAlignment(JLabel.RIGHT);
    addressInfo.add(jtfAddress);
    addressInfo.add(lblCity);
    lblCity.setHorizontalAlignment(JLabel.RIGHT);
    addressInfo.add(jtfCity);
    addressInfo.add(lblState);
    lblState.setHorizontalAlignment(JLabel.RIGHT);
    addressInfo.add(stateList);
    
    //Phone panel
    phoneInfo.setLayout(new GridLayout(2,2,5,5));
    phoneInfo.add(lblmobilePhone);
    lblmobilePhone.setHorizontalAlignment(JLabel.RIGHT);
    phoneInfo.add(jtfmobilePhone);
    phoneInfo.add(lblmobileCarrier);
    lblmobileCarrier.setHorizontalAlignment(JLabel.RIGHT);
    phoneInfo.add(jtfmobileCarrier);
    phoneInfo.add(lblhomePhone);
    lblhomePhone.setHorizontalAlignment(JLabel.RIGHT);
    phoneInfo.add(jtfhomePhone);  
    phoneInfo.add(lblhomeCarrier);
    lblhomeCarrier.setHorizontalAlignment(JLabel.RIGHT);
    phoneInfo.add(jtfhomeCarrier);
    
    //Button panel
    buttons.setLayout(new GridLayout(1,6,40,5));
    buttons.add(jbtView);
    buttons.add(jbtInsert);
    buttons.add(jbtUpdate);
    buttons.add(jbtDelete);
    buttons.add(jbtClear);
    buttons.add(jbtClose);
       
    //Add panels to orientation panels
    top.add(staffInfo);
    top.add(addressInfo);
    top.setBorder(new TitledBorder("Staff Personal Information")); 
    
    center.add(phoneInfo);
    center.setBorder(new TitledBorder("Staff Phone Information")); 
    
    bottom.add(buttons);
    
    //Add panels to the frame
    add(top, BorderLayout.NORTH);
    add(center, BorderLayout.CENTER);
    add(bottom, BorderLayout.SOUTH);
}	
	
 
@Override
public void actionPerformed(ActionEvent e) 
{
	//View button
	if (e.getSource() == jbtView)
	{
		try 
		{			
			viewButtonClicked();
		} 
		catch (IOException | ClassNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
	//Insert button
	else if (e.getSource() == jbtInsert)
	{
		try 
		{
			insertButtonClicked();
		} 
		catch (IOException | ClassNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//Update button
	else if (e.getSource() == jbtUpdate)
	{
		try 
		{
			updateButtonClicked();
		} 
		catch (IOException | ClassNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//Delete button
	else if (e.getSource() == jbtDelete)
	{
		try 
		{
			deleteButtonClicked();
		} 
		catch (IOException | ClassNotFoundException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	//Clear button
	else if (e.getSource() == jbtClear)
	{
		clearButtonClicked();
	}
	//Close button
	else if (e.getSource() == jbtClose)
	{
		try 
		{
			closeButtonClicked();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	
}

private void viewButtonClicked() throws IOException, ClassNotFoundException
{	  
	// handle view button clicked event 
	//Get ID from input
	int id;
	
	try 
	{
		id=Integer.parseInt(jtfID.getText().trim());
	}
	catch(Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfID, "Please enter a valid ID#.");
		return;
	}
	
	//Retrieve the object associated with specified id
	message = new Message (id, "", "", '\u0000', "", "", "", "", "", "", "", 1);
	output.writeObject(message);
	message = (Message)input.readObject();
	
	//Set values from returned object
	jtfLastName.setText(message.getLastName());
	jtfFirstName.setText(message.getFirstFName());
	jtfMI.setText(String.valueOf(message.getMi()));
	jtfAddress.setText(message.getAddress());
	jtfCity.setText(message.getCity());
	stateList.setSelectedItem(message.getState());
	jtfmobilePhone.setText(message.getmPhoneNo());
	jtfhomePhone.setText(message.gethPhoneNo());
	jtfmobileCarrier.setText(message.getmPhoneCarrier());
	jtfhomeCarrier.setText(message.gethPhoneCarrier());
}

private void insertButtonClicked() throws IOException, ClassNotFoundException
{	  
	// handle insert button clicked event	
	//Check for non integer ID
	int id;
	try 
	{
		id = Integer.parseInt(jtfID.getText().trim());
    } 
	catch (Exception ex) 
	{
    	JOptionPane.showMessageDialog(jtfID, "Please enter a valid ID#.");
    	jtfID.setText("");
    	return;  
    }
	
	//Check for blank last name
	String lastName = jtfLastName.getText();
	if(jtfLastName.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfLastName,"Last name cannot be blank.");
		jtfLastName.setText("");
		return;
	}
	
	//Check for blank first name
	String firstName = jtfFirstName.getText();
	if(jtfFirstName.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfFirstName,"First name cannot be blank.");
		jtfFirstName.setText("");
		return;
	}
	
	//Check for middle initial
	char mi;
	try 
	{
		String middleI = jtfMI.getText();
	    mi = middleI.charAt(0);
	}
	catch (Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfMI, "Enter a valid middle initial (e.g., T)");
		jtfMI.setText("");
		return;
	}

	//Check for blank address
	String address = jtfAddress.getText();
	if(jtfAddress.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfAddress, "Address cannot be blank.");
		jtfAddress.setText("");
		return;
	}

	//Check for blank city name
	String city = jtfCity.getText();
    if(jtfCity.getText().trim().length()==0) 
    {
    	JOptionPane.showMessageDialog(jtfCity,"City cannot be blank.");
    	jtfCity.setText("");
    	return;
	}
    
    //Check for blank state
    String state = stateList.getSelectedItem().toString();
    if(stateList.getSelectedIndex() == -1)
    {
    	JOptionPane.showMessageDialog(stateList, "Select a state.");
    	return;
    }
    
    //Check for valid mobile number
    String mobilePhone;
	try 
	{
		mobilePhone = jtfmobilePhone.getText().trim();

		if (!mobilePhone.matches("^[0-9]{10}$")) 
		{
			JOptionPane.showMessageDialog(jtfmobilePhone, "Enter a valid mobile phone number (e.g., 3031231234)");
			return;
		}
	}
	catch (Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfmobilePhone, "Enter a valid mobile phone number (e.g., 3031231234)");
		return;
	}
	
	//Check for valid home number
    String homePhone;
	try 
	{
		homePhone = jtfhomePhone.getText().trim();

		if (!homePhone.matches("^[0-9]{10}$")) 
		{
			JOptionPane.showMessageDialog(jtfhomePhone, "Enter a valid home phone number (e.g., 3031231234)");
			return;
		}
	}
	catch (Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfhomePhone, "Enter a valid home phone number (e.g., 3031231234)");
		return;
	}
	
	//Check for mobile carrier
	String mobileCarrier = jtfmobileCarrier.getText();
	if(jtfmobileCarrier.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfmobileCarrier, "Mobile carrier cannot be blank.");
		jtfmobileCarrier.setText("");
		return;
	}
	
	//Check for home carrier
	String homeCarrier = jtfhomeCarrier.getText();
	if(jtfhomeCarrier.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfhomeCarrier, "Home carrier cannot be blank.");
		jtfhomeCarrier.setText("");
		return;
	}
	
	//Send new object
	message = new Message (id, lastName, firstName, mi, address, city, state, mobilePhone, homePhone, mobileCarrier, homeCarrier, 2);
	output.writeObject(message);
	message = (Message)input.readObject();
}
	
private void updateButtonClicked() throws IOException, ClassNotFoundException
{	
	// handle update button clicked event
	//Check for non integer ID
	int id;
	try 
	{
		id = Integer.parseInt(jtfID.getText().trim());
	} 
	catch (Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfID, "Please enter a valid ID#.");
	    jtfID.setText("");
	    return;  
	}
		
	//Check for blank last name
	String lastName = jtfLastName.getText();
	if(jtfLastName.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfLastName,"Last name cannot be blank.");
		jtfLastName.setText("");
		return;
	}
		
	//Check for blank first name
	String firstName = jtfFirstName.getText();
	if(jtfFirstName.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfFirstName,"First name cannot be blank.");
		jtfFirstName.setText("");
		return;
	}
		
	//Check for middle initial
	char mi;
	try 
	{
		String middleI = jtfMI.getText();
		mi = middleI.charAt(0);
	}
	catch (Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfMI, "Enter a valid middle initial (e.g., T)");
		jtfMI.setText("");
		return;
	}

	//Check for blank address
	String address = jtfAddress.getText();
	if(jtfAddress.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfAddress, "Address cannot be blank.");
		jtfAddress.setText("");
		return;
	}

	//Check for blank city name
	String city = jtfCity.getText();
	if(jtfCity.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfCity,"City cannot be blank.");
	    jtfCity.setText("");
	    return;
	}
	    
	//Check for blank state
	String state = stateList.getSelectedItem().toString();
	if(stateList.getSelectedIndex() == -1)
	{
		JOptionPane.showMessageDialog(stateList, "Select a state.");
	    return;
	}
	    
	//Check for valid mobile number
	String mobilePhone;
	try 
	{
		mobilePhone = jtfmobilePhone.getText().trim();

		if (!mobilePhone.matches("^[0-9]{10}$")) 
		{
			JOptionPane.showMessageDialog(jtfmobilePhone, "Enter a valid mobile phone number (e.g., 3031231234)");
			return;
		}
	}		
	catch (Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfmobilePhone, "Enter a valid mobile phone number (e.g., 3031231234)");
		return;
	}
		
	//Check for valid home number
	String homePhone;
	try 
	{
		homePhone = jtfhomePhone.getText().trim();

		if (!homePhone.matches("^[0-9]{10}$")) 
		{
			JOptionPane.showMessageDialog(jtfhomePhone, "Enter a valid home phone number (e.g., 3031231234)");
			return;
		}
	}
	catch (Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfhomePhone, "Enter a valid home phone number (e.g., 3031231234)");
		return;
	}
		
	//Check for mobile carrier
	String mobileCarrier = jtfmobileCarrier.getText();
	if(jtfmobileCarrier.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfmobileCarrier, "Mobile carrier cannot be blank.");
		jtfmobileCarrier.setText("");
		return;
	}
	
	//Check for home carrier
	String homeCarrier = jtfhomeCarrier.getText();
	if(jtfhomeCarrier.getText().trim().length()==0) 
	{
		JOptionPane.showMessageDialog(jtfhomeCarrier, "Home carrier cannot be blank.");
		jtfhomeCarrier.setText("");
		return;
	}
		
	//Send new object
	message = new Message (id, lastName, firstName, mi, address, city, state, mobilePhone, homePhone, mobileCarrier, homeCarrier, 3);
	output.writeObject(message);
	message = (Message)input.readObject();
}

private void deleteButtonClicked() throws IOException, ClassNotFoundException
{	  
	// handle delete button clicked event
	//Get ID from input
	int id;
		
	try 
	{
		id=Integer.parseInt(jtfID.getText().trim());
	}
	catch(Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfID, "Please enter a valid ID#.");
		return;
	}
	
	//Check for valid mobile number
	String mobilePhone;
	try 
	{
		mobilePhone = jtfmobilePhone.getText().trim();

		if (!mobilePhone.matches("^[0-9]{10}$")) 
		{
			JOptionPane.showMessageDialog(jtfmobilePhone, "Enter a valid mobile phone number (e.g., 3031231234)");
			return;
		}
	}		
	catch (Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfmobilePhone, "Enter a valid mobile phone number (e.g., 3031231234)");
		return;
	}
			
	//Check for valid home number
	String homePhone;
	try 
	{
		homePhone = jtfhomePhone.getText().trim();

		if (!homePhone.matches("^[0-9]{10}$")) 
		{
			JOptionPane.showMessageDialog(jtfhomePhone, "Enter a valid home phone number (e.g., 3031231234)");
			return;
		}
	}
	catch (Exception ex) 
	{
		JOptionPane.showMessageDialog(jtfhomePhone, "Enter a valid home phone number (e.g., 3031231234)");
		return;
	}
	
	//Send new object
	message = new Message (id, null, null, '\u0000', null, null, null, mobilePhone, homePhone, null, null, 4);
	output.writeObject(message);
	message = (Message)input.readObject();
	
	//Reset fields
	jtfID.setText("");
	jtfLastName.setText("");
	jtfFirstName.setText("");
	jtfMI.setText("");
	jtfAddress.setText("");
	jtfCity.setText("");
	stateList.setSelectedIndex(-1);
	jtfmobilePhone.setText("");
	jtfhomePhone.setText("");;
	jtfmobileCarrier.setText("");
	jtfhomeCarrier.setText("");
}
  
  
void clearButtonClicked()
{	  
	// handle clear button clicked event
	jtfID.setText("");
	jtfLastName.setText("");
	jtfFirstName.setText("");
	jtfMI.setText("");
	jtfAddress.setText("");
	jtfCity.setText("");
	stateList.setSelectedIndex(-1);
	jtfmobilePhone.setText("");
	jtfhomePhone.setText("");;
	jtfmobileCarrier.setText("");
	jtfhomeCarrier.setText("");
}
  

void closeButtonClicked() throws IOException
{	  
	// handle close button clicked event
	input.close();
	output.close();
	socket.close();

	System.exit(0);    
}
  
  /**Main method*/
  public static void main(String[] args) 
  {  
	  // create the user GUI
	  StaffClent frame = new StaffClent("localhost",8000);
	  
	  frame.pack();
	  frame.setSize(725,290);
	  frame.setTitle("Staff Client PA08");
	  frame.setLocationRelativeTo(null);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setVisible(true);	  
  }
}