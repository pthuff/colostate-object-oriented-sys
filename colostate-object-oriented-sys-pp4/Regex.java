/*
 * CIS611						Spring Session 2019						Paul Huff, Chris Herbold, Juste McClurg
 *
 * 								Programming Project 3
 * 
 * 								Regex class for holding regex patterns
 * 
 * 								Date Created: 4/24/2019
 * 
 * 								Saved in: Regex.java
 */
package PP4;
 
import java.util.regex.Pattern;

public class Regex {
	
	
	
	//pattern attributes
	private Pattern pos;
	private Pattern num;
	private Pattern playerName;
	private Pattern status;
	private Pattern tckl;
	private Pattern sck;
	private Pattern intt;
	private Pattern team;
	
	//constructor
	public Regex(Pattern pos, Pattern num, Pattern playerName, Pattern status, Pattern tckl, Pattern sck, Pattern intt,
			Pattern team) {
		this.pos = pos;
		this.num = num;
		this.playerName = playerName;
		this.status = status;
		this.tckl = tckl;
		this.sck = sck;
		this.intt = intt;
		this.team = team;
	}
	
	// 	add getter and setter methods
	public Pattern getPos() {
		return pos;
	}

	public Pattern getNum() {
		return num;
	}

	public Pattern getPlayerName() {
		return playerName;
	}

	public Pattern getStatus() {
		return status;
	}

	public Pattern getTckl() {
		return tckl;
	}

	public Pattern getSck() {
		return sck;
	}

	public Pattern getIntt() {
		return intt;
	}

	public Pattern getTeam() {
		return team;
	}
		
}
