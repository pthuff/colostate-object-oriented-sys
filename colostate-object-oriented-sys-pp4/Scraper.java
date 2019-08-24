/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								Scraper class for scraping NFL data and outputting formated data
 * 
 * 								Date Created: 4/24/2019
 * 
 * 								Saved in: Scraper.java
 */
package PP4;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Scraper {
	
	private Matcher matcher;
	private Regex regex;
	private String url;
	private String display = String.format("%-12s%-12s%-30s%-10s%-8s%-8s%-12s%-12s", " Pos", "Num", "Player Name", "\tStatus", " TCKL", "\tSCK", " INT", "\tTeam");
	
	// constructor
	public Scraper (String url) {		
		this.url = url;
	} 
	
	// reads the data from a web page and searches for the string matches
	public void parseData() 
	{		
		//Declare local variables
		String line = "";
		String[] urlArray = {"http://www.nfl.com/players/search?category=position&playerType=current&conference=ALL&d-447263-p=1&filter=defensiveback&conferenceAbbr=null", 
							"http://www.nfl.com/players/search?category=position&playerType=current&conference=ALL&d-447263-p=2&filter=defensiveback&conferenceAbbr=null", 
							"http://www.nfl.com/players/search?category=position&playerType=current&conference=ALL&d-447263-p=3&filter=defensiveback&conferenceAbbr=null", 
							"http://www.nfl.com/players/search?category=position&playerType=current&conference=ALL&d-447263-p=4&filter=defensiveback&conferenceAbbr=null", 
							"http://www.nfl.com/players/search?category=position&playerType=current&conference=ALL&d-447263-p=5&filter=defensiveback&conferenceAbbr=null", 
							"http://www.nfl.com/players/search?category=position&playerType=current&conference=ALL&d-447263-p=6&filter=defensiveback&conferenceAbbr=null",
							"http://www.nfl.com/players/search?category=position&playerType=current&conference=ALL&d-447263-p=7&filter=defensiveback&conferenceAbbr=null"};
		
		//Declare patterns
		Pattern posPattern = Pattern.compile("<td class=\"tbdy\">(CB|DB|FS|SS|SAF)</td>");
		Pattern numPattern = Pattern.compile("<td class=\"tbdy\">([0-9]{0,3}|--|[0-9]*\\.[0-9])</td>");
		Pattern namePattern = Pattern.compile("<a href=\"/player/[^>]*>(.*?)</a>");
		Pattern statusPattern = Pattern.compile("<td class=\"tbdy\">(ACT|UFA|RFA|UDF)</td>");
		Pattern tcklPattern = Pattern.compile("(TCKL)");
		Pattern sckPattern = Pattern.compile("(SCK)");
		Pattern inttPattern = Pattern.compile("(INT)");
		Pattern teamPattern = Pattern.compile("<a href=\"/teams/[^>]*>(.*?)</a>");
		
		//Declare matchers, url, scanner
		Matcher posMatcher, numMatcher, nameMatcher, statusMatcher, tcklMatcher, sckMatcher, inttMatcher, teamMatcher;
		URL url;
		Scanner input = null;
				
		//Instantiate new Regex
		regex = new Regex(posPattern, numPattern, namePattern, statusPattern, tcklPattern, sckPattern, inttPattern, teamPattern);
		
		//Loop through every page and find matches			
		try 
		{			
			for (int i = 0; i < urlArray.length; i++)
			{
				url = new URL(urlArray[i]);
				input = new Scanner(url.openStream());
				
				while (input.hasNext()) 
				{
					line = input.nextLine();
					String player = "";
					
					//Position and player number match
					posMatcher = regex.getPos().matcher(line);					
					if (posMatcher.find())
					{
						player += "\r\n" + String.format("%-12s", posMatcher.group(1));
												
						while (input.hasNext())
						{
							line = input.nextLine();
							
							numMatcher = regex.getNum().matcher(line);
							if (numMatcher.find())
							{
								player += String.format("%-12s", numMatcher.group(1));
								break;
							}
						}
					}
					
					//Player name match
					nameMatcher = regex.getPlayerName().matcher(line);
					if (nameMatcher.find())
					{
						if (nameMatcher.group(1).length() < 28)
						{
							player += String.format("%-30s", nameMatcher.group(1)) + "\t";
						}
						else
						{
							player += String.format("%-30s", nameMatcher.group(1));
						}																	
					}
					
					//Player status match
					statusMatcher = regex.getStatus().matcher(line);
					if (statusMatcher.find())
					{
						player += String.format("%-12s", statusMatcher.group(1));
					}
					
					//Tackles match
					tcklMatcher = regex.getTckl().matcher(line);
					if (tcklMatcher.find())
					{
						while (input.hasNext())
						{
							line = input.nextLine();
							
							numMatcher = regex.getNum().matcher(line);
							if (numMatcher.find())
							{
								player += String.format("%-6s", numMatcher.group(1)) + "\t";
								break;
							}
						}
					}
					
					//Sacks match
					sckMatcher = regex.getSck().matcher(line);
					if (sckMatcher.find())
					{
						while (input.hasNext())
						{
							line = input.nextLine();
							
							numMatcher = regex.getNum().matcher(line);
							if (numMatcher.find())
							{
								player += String.format("%-12s", numMatcher.group(1));
								break;
							}
						}
					}
					
					//Interceptions match
					inttMatcher = regex.getIntt().matcher(line);
					if (inttMatcher.find())
					{
						while (input.hasNext())
						{
							line = input.nextLine();
							
							numMatcher = regex.getNum().matcher(line);
							if (numMatcher.find())
							{
								player += String.format("%-12s", numMatcher.group(1)) + "\t";
								break;
							}
						}
					}
					
					//Team name match
					teamMatcher = regex.getTeam().matcher(line);
					if (teamMatcher.find())
					{
						player += String.format("%-12s", teamMatcher.group(1));
					}
					
					//Send player string to display method
					display(player);
				}
			}
			
			//Close the input
			input.close();			
		} 
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Invalid URL.");
			return;
		}
		
		//Write results to file
		writeToFile(this.display);
	}	
	
	// shows the output (scraped data) in a text-area 
	public String display(String display)
	{
		this.display += display;
		return this.display.trim();
	}
	
	//Write the result to a file 
	public void writeToFile(String input) 
	{
		//Write results to file
		try 
		{		
			//Instantiate new file object, wrap in PrintWriter
			java.io.File outFile = new java.io.File("NFLStat.txt");
			java.io.PrintWriter output = new java.io.PrintWriter(outFile);
			
			//Write to file
			output.print(input);
			output.close();
		} 
		catch (FileNotFoundException e) 
		{
			// Display exception
			JOptionPane.showMessageDialog(null, "Error writing file.");
		}
	}
} //end class
		
		
			

	

	
	

