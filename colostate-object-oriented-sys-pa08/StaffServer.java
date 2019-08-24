/*
 * CIS611						Spring Session 2019						Paul Huff
 *
 * 								Program Assignment PA08
 * 
 * 								StaffServer class for sending/receiving messages to the SQl db
 * 
 * 								Date Created: 4/11/2019
 * 
 * 								Saved in: StaffServer.java
 */
package PA08;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.*;

import java.sql.*;

public class StaffServer extends JFrame {
  
  private int port;
  private Connection connect;
  private Statement statement;
  private PreparedStatement preparedStatement;
  private JTextArea status;
  
  private Message message;
  ObjectInputStream input;
  ObjectOutputStream output;
  
  public StaffServer (int port) throws ClassNotFoundException, IOException, SQLException
  {
	  
	  this.port = port;
	  
	  //Setup a server window
	  JFrame frame = new JFrame();
	  status = new JTextArea();
	  JScrollPane jp = new JScrollPane(status);
	  frame.add(jp);
	  frame.setSize(200,290);
	  frame.setTitle("Staff Server PA08");
	  frame.setLocationRelativeTo(null);
	  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  frame.setVisible(true);
	  
	  //Setup the DB connection
	  initializeDB();	  
  }
  
  private void initializeDB() throws IOException, ClassNotFoundException, SQLException
  {  
	  try 
	  {
		  // create the server
		  ServerSocket serverSocket = new ServerSocket(port);
		  System.out.println("Server started at port :" + port);
		  status.append("Server started at port: "+port+"\n");
		  
		  // Connect to your database using your credentials
		  Class.forName("com.mysql.jdbc.Driver");
		  System.out.println("Driver loaded");
		  status.append("Driver loaded." +"\n");
		  	  
		  connect = DriverManager.getConnection("jdbc:mysql://BusCISMySQL01:3306/pthuffdb", "pthuff", "c611c!09883");
		  System.out.println("Database connected");
		  status.append("Database loaded." +"\n");
	      
	      // Create a statement object
		  statement = connect.createStatement();
		  
		  // loops for ever waiting for the client connection requests
		  while (true) 
		  {
			  	// Listen for a new connection request
		        Socket socket = serverSocket.accept();

		        // Create a new thread for the connection
		        HandleAClient task = new HandleAClient(socket);

		        // Start the new thread
		        new Thread(task).start();	             
		  } 
	  }
	  catch(Exception ex) 
	  {
		  System.out.println("Port in use.");
		  status.append("Port in use." +"\n");
	  }    
  }
  
  /**View record by ID*/
  private void view() throws IOException 
  {
	  	// Build a SQL SELECT statement
	  	int id = message.getId();
	  	String sql = "SELECT s.id, s.lastName, s.firstName, s.mi, s.address, s.city, s.state, s.mobilephone, m.phonecarrier AS mcarrier, s.homephone, h.phonecarrier AS hcarrier "
	  			+ "FROM staff s "
	  			+ "INNER JOIN telephone m ON m.phone = s.mobilephone "
	  			+ "INNER JOIN telephone h ON h.phone = s.homephone "
	  			+ "WHERE s.id ="+id;
		
	  	//Create the view message
	  	try 
	  	{		    
	  		preparedStatement = connect.prepareStatement(sql);
	  		ResultSet rs = preparedStatement.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columns = rsmd.getColumnCount();
			
			//Check if exists
			if (rs.next() & message.getId() > 0) 
			{
				for (int i = 1; i <= columns; i++) 
				{
					int rId = rs.getInt("id");
					String lastName = rs.getString("lastName");
					String firstName = rs.getString("firstName");
					String middleI = rs.getString("mi");
					char mi = middleI.charAt(0);
					String address = rs.getString("address");
					String city = rs.getString("city");
					String state = rs.getString("state");
					String mobilePhone = rs.getString("mobilephone");
					String mobileCarrier = rs.getString("mcarrier");
					String homePhone = rs.getString("homephone");
					String homeCarrier = rs.getString("hcarrier");
					message = new Message (rId, lastName, firstName, mi, address, city, state, mobilePhone, homePhone, mobileCarrier, homeCarrier, 1);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(status, "ID does not exist.");
				return;
			}
		}
	  	catch (SQLException e) 
	  	{
			// TODO Auto-generated catch block
	  		JOptionPane.showMessageDialog(status, "SQL error.");	  		
			e.printStackTrace();
		}	  	
		status.append("Record retrieved" +"\n");
  }

  /**Insert a new record*/
  private void insert() throws InterruptedException, SQLException 
  {
	  // Build a SQL INSERT statement
	  int id = message.getId();
	  String lastName = message.getLastName();
	  String firstName = message.getFirstFName();
	  char mi = message.getMi();
	  String address = message.getAddress();
	  String city = message.getCity();
	  String state = message.getState();
	  String mobilePhone = message.getmPhoneNo();
	  String homePhone = message.gethPhoneNo();
	  String mobileCarrier = message.getmPhoneCarrier();
	  String homeCarrier = message.gethPhoneCarrier();
	  
	  //Check if records exist        
      boolean pass = false;
      while (!pass)
      {   	    	  
    	  ResultSet rsm = statement.executeQuery("SELECT * FROM telephone WHERE phone = " + mobilePhone + ";");
    	  if (rsm.next())
    	  {
    		  JOptionPane.showMessageDialog(status, "Mobile number already exists.");
        	  break;
    	  }
    	  
    	  ResultSet rsh = statement.executeQuery("SELECT * FROM telephone WHERE phone = " + homePhone + ";");
    	  if (rsh.next())
          {
    		  JOptionPane.showMessageDialog(status, "Home number already exists.");
        	  break;
          }
    	  
    	  ResultSet rss = statement.executeQuery("SELECT * FROM staff WHERE id = " + id + ";");
    	  if (rss.next())
    	  {
    		  JOptionPane.showMessageDialog(status, "ID already exists.");
        	  break;
    	  }
    	  else
    	  {
    		  pass = true;
    	  }	  
      }
      
      //Create records
      if (pass)
      {
    	  //Create the mobile phone record 
          String mobileSql = "INSERT INTO pthuffdb.telephone VALUES(?,?)";
    	  preparedStatement = connect.prepareStatement(mobileSql);
    	  preparedStatement.setString(1, mobilePhone);
    	  preparedStatement.setString(2, mobileCarrier);
    	  preparedStatement.execute();
          	  
    	  //Wait
    	  Thread.sleep(100);
    	  
    	  //Create the home phone record
    	  String homeSql = "INSERT INTO pthuffdb.telephone VALUES(?,?)";
    	  preparedStatement = connect.prepareStatement(homeSql);
    	  preparedStatement.setString(1, homePhone);
    	  preparedStatement.setString(2, homeCarrier);
    	  preparedStatement.execute();
    	  
    	  //Wait
    	  Thread.sleep(100);
    	  
    	  //Create the staff record
    	  String staffSql = "INSERT INTO pthuffdb.staff VALUES(?,?,?,?,?,?,?,?,?)";
    	  preparedStatement = connect.prepareStatement(staffSql);
    	  preparedStatement.setInt(1, id);
    	  preparedStatement.setString(2, lastName);
    	  preparedStatement.setString(3, firstName);
    	  preparedStatement.setString(4, String.valueOf(mi));
    	  preparedStatement.setString(5, address);
    	  preparedStatement.setString(6, city);
    	  preparedStatement.setString(7, state);
    	  preparedStatement.setString(8, mobilePhone);
    	  preparedStatement.setString(9, homePhone);
    	  preparedStatement.execute();
      }
      else
      {
    	  return;
      }
      
      //Confirmation
      JOptionPane.showMessageDialog(status, "Record created.");	  
	  status.append("Record inserted" +"\n");
  }

  /**Update a record*/
  private void update() throws InterruptedException, SQLException 
  {
	  // Build a SQL UPDATE statement
	  int id = message.getId();
	  String lastName = message.getLastName();
	  String firstName = message.getFirstFName();
	  char mi = message.getMi();
	  String address = message.getAddress();
	  String city = message.getCity();
	  String state = message.getState();
	  String mobilePhone = message.getmPhoneNo();
	  String homePhone = message.gethPhoneNo();
	  String mobileCarrier = message.getmPhoneCarrier();
	  String homeCarrier = message.gethPhoneCarrier();
	  
	  //Check if records exist        
      boolean pass = false;
      while (!pass)
      {   	    	  
		  ResultSet rss = statement.executeQuery("SELECT * FROM staff WHERE id = " + id + ";");
    	  if (!rss.next())
    	  {
    		  JOptionPane.showMessageDialog(status, "ID does not exist.");
	    	  break;
    	  }
    	  
    	  ResultSet rsm = statement.executeQuery("SELECT * FROM telephone WHERE phone = " + mobilePhone + ";");
    	  if (!rsm.next())
          {
    		  JOptionPane.showMessageDialog(status, "Mobile number does not exist.");
        	  break;
          }
    	  
    	  ResultSet rsh = statement.executeQuery("SELECT * FROM telephone WHERE phone = " + homePhone + ";");
    	  if (!rsh.next())
    	  {
    		  JOptionPane.showMessageDialog(status, "Home number does not exist.");
        	  break;
    	  }
    	  else
    	  {
    		  pass = true;
    	  }	  
      }
      
      //Update records
      if (pass)
      {
    	  //Update staff record
    	  String staffSql = "UPDATE pthuffdb.staff SET lastName=?,firstName=?,mi=?,address=?,city=?,state=? "
    		  		+ "WHERE id ="+id;
    	  preparedStatement = connect.prepareStatement(staffSql);
		  preparedStatement.setString(1, lastName);
		  preparedStatement.setString(2, firstName);
		  preparedStatement.setString(3, String.valueOf(mi));
		  preparedStatement.setString(4, address);
		  preparedStatement.setString(5, city);
		  preparedStatement.setString(6, state);	  
		  preparedStatement.execute();
		  
		  //Wait
		  Thread.sleep(100);
		  
		  //Update mobile phone record
		  String mobileSql = "UPDATE pthuffdb.telephone SET phone=?,phonecarrier=? "
			  		+ "WHERE phone ="+mobilePhone;
		  
		  preparedStatement = connect.prepareStatement(mobileSql);
		  preparedStatement.setString(1, mobilePhone);
		  preparedStatement.setString(2, mobileCarrier);
		  preparedStatement.execute();
		  
		  //Wait
		  Thread.sleep(100);
		  
		  //Update home phone record
		  String homeSql = "UPDATE pthuffdb.telephone SET phone=?,phonecarrier=? "
			  		+ "WHERE phone ="+homePhone;
		  preparedStatement = connect.prepareStatement(homeSql);
		  preparedStatement.setString(1, homePhone);
		  preparedStatement.setString(2, homeCarrier);
		  preparedStatement.execute();
      }
      else
      {
    	  return;
      }	  	  
	  	
	  //Confirmation
	  JOptionPane.showMessageDialog(status, "Record updated.");
	  status.append("Record updated" +"\n");	  
  }

  /**Clear text fields*/
  private void delete() throws InterruptedException, SQLException 
  {
	  // Build a SQL DELETE statement
	  int id = message.getId();
	  String mobilePhone = message.getmPhoneNo();
	  String homePhone = message.gethPhoneNo();
	  
	  //Check if records exist        
      boolean pass = false;
      while (!pass)
      {   	    	  
    	  ResultSet rss = statement.executeQuery("SELECT * FROM staff WHERE id = " + id + ";");
    	  if (!rss.next())
    	  {
    		  JOptionPane.showMessageDialog(status, "ID does not exist.");
	    	  break;
    	  }
    	  
    	  ResultSet rsm = statement.executeQuery("SELECT * FROM telephone WHERE phone = " + mobilePhone + ";");
    	  if (!rsm.next())
          {
    		  JOptionPane.showMessageDialog(status, "Mobile number does not exist.");
        	  break;
          }
    	  
    	  ResultSet rsh = statement.executeQuery("SELECT * FROM telephone WHERE phone = " + homePhone + ";");
    	  if (!rsh.next())
    	  {
    		  JOptionPane.showMessageDialog(status, "Home number does not exist.");
        	  break;
    	  }
    	  else
    	  {
    		  pass = true;
    	  }	  
      }
      
      //Delete records
      if (pass)
      {
    	  //Delete staff record
          String staffSql = "DELETE FROM pthuffdb.staff "
      	  		+ "WHERE id ="+id;    
    	  preparedStatement = connect.prepareStatement(staffSql);
    	  preparedStatement.execute();
    	  
    	  //Wait
    	  Thread.sleep(100);
    	  
    	  //Delete mobile phone record
    	  String mobileSql = "DELETE FROM pthuffdb.telephone "
    	  		+ "WHERE phone ="+mobilePhone;
    	  preparedStatement = connect.prepareStatement(mobileSql);
    	  preparedStatement.execute();
    	    
    	  //Wait
    	  Thread.sleep(100);
    	  
    	  //Delete home phone record
    	  String homeSql = "DELETE FROM pthuffdb.telephone "
    	  		+ "WHERE phone ="+homePhone;
    	  preparedStatement = connect.prepareStatement(homeSql);
    	  preparedStatement.execute(); 
      }
      else
      {
    	  return;
      } 
	  
	  //Confirmation
	  JOptionPane.showMessageDialog(status, "Record deleted.");
	  status.append("Record deleted" +"\n");
  }

    // inner Runnable class handle a client connection
	class HandleAClient implements Runnable {
	    private Socket socket; // A connected socket

	    /** Construct a thread */
	    public HandleAClient(Socket socket) {
	      this.socket = socket;
	    }

	    /** Run a thread */
	    public void run() 
	    {
	    	// write the code to call a proper method to process the client request	    	
	  	    try 
	  	    {
	  	    	input = new ObjectInputStream(socket.getInputStream());
	  	    	output = new ObjectOutputStream(socket.getOutputStream());
				
				// Continuously serve the client
		        while (true) 
		        {
		        	message = null;
		    		message = (Message)input.readObject();		    		
		    		
		            switch (message.getOpType()) 
		            {		    		
		    		case 1: view();
		    		break; 
		    				        
		    		case 2: insert();
		            break; 

		    		case 3:  update();		    		
		            break;
		                                 
		    		case 4:  delete();
	                break; 
		    		}		            
		            output.writeObject(message);
		    	  }
			} 
	  	    catch (IOException e) 
	  	    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  	    catch (ClassNotFoundException e) 
	  	    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  	    catch (SQLException e) 
	  	    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	  	    catch (InterruptedException e) 
	  	    {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	   }// end of class Runnable 
  
  public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException 
  {
	  new StaffServer(8000); 
  }


}
