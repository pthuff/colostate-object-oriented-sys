/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								UserGUI class for display scraped NFL data
 * 
 * 								Date Created: 4/24/2019
 * 
 * 								Saved in: UserGUI.java
 */
package PP4;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

public class UserGUI extends JPanel {
	
	
	  private JButton scrapeButton;
	  private JButton closeButton;
	  // add more UI components as needed
	  private Scraper scraper;;	  
	  private JTextArea jtDisplay;
	  private JScrollPane sp;
		
	  private String url;
	  
	  public UserGUI() {
		 
		  // uses the url provided in the document
		 Scraper scraper = new Scraper(url);
		 this.scraper = scraper;

	    initGUI();
	    doTheLayout();

	    scrapeButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            scrape();
	            }
	    });
	    
	    closeButton.addActionListener( new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e){
	            close();
	            }
	    });
	    
	   
	  } // end of constructor

	  
	  // Creates and initialize the GUI objects
	  private void initGUI(){
		  
		// Initialize the GUI components
		jtDisplay = new JTextArea();
		jtDisplay.setEditable(false);
		jtDisplay.setWrapStyleWord(true);
		sp = new JScrollPane(jtDisplay);
		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		//Buttons
		scrapeButton = new JButton("Scrape NFL Page");
		closeButton = new JButton("Close");
		
	  
	  }// end of creating objects method

	
	  // Layouts the UI components as shown in the project document
	  private void doTheLayout(){
		  
		  // Arrange the UI components into GUI window
		  //Orientation panels
		  JPanel top = new JPanel();
		  JPanel center = new JPanel();
		  JPanel bottom = new JPanel();
		  
		  //Add to panels
		  top.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		  top.add(scrapeButton);
		  
		  center.setPreferredSize(new Dimension(525, 300));
		  center.setLayout(new BorderLayout());
		  center.add(sp, BorderLayout.CENTER);
		  center.setBorder(new TitledBorder("Output:"));
		  
		  bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		  bottom.add(closeButton);
		    
		  //Add panels to frame
		  setLayout( new BorderLayout());
		  this.add(top, "North");
		  this.add(center, "Center");
		  this.add(bottom, "South");

	  }// end of Layout method

	 
	// Uses the Scraper object reference to return and display the data as shown in the project document 
	 void scrape(){
		 scraper.parseData();
		 jtDisplay.setText(scraper.display(""));
	       
	  }// end of scrape action event method
	  
	 
	  void close(){
	      System.exit(0);
	  }// end of close action event method


	public static void main(String[] args) {
	   JFrame f = new JFrame("NFL Stats");	   
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       Container contentPane = f.getContentPane();
       contentPane.add( new UserGUI());
       f.pack();
       f.setLocationRelativeTo(null);
       f.setVisible(true);

	}// end of main method

}// end of class
