// This class must have the code in the run() method, sub-program/routine to solve the maze;
//   searches for the Java logo based on the provided path/direction algorithm

public class Direction extends Thread{

	Maze maze;
	Position location;
	
	Direction(Maze maze, Position location) {
		
		this.maze = maze;
		this.location = location;
	
	}
	
	public boolean downEven() {
		
		//Begin loop on even columns to move down
		while (!maze.isDone() && maze.moveDown() && ((maze.getCurrCol() % 2) == 0)) {

			//maze.moveDown();
			location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
			
			}//end unobstructed downwards movement
		
		return false;
	}
	
	public boolean avoidEven() {
		
		
		//instructions to move around obstacles when moving down except on final row
		if (!maze.isDone() && !maze.moveDown() && ((maze.getCurrCol() % 2) == 0) && (maze.getCurrRow() != (maze.getHeight()-1))) {
				
			if (!maze.isDone() && maze.moveRight())
				location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");

			if (!maze.isDone() && maze.moveDown())
				location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");

			if(!maze.isDone() && maze.moveDown())
				location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");

			if(!maze.isDone() && maze.moveLeft())
				location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
				
			}//end if movement around obstacles
		
	return false;
		
	}
	
	public boolean rightEven() {
		
		//move right when reach bottom of even column and have not found the java logo
		if (!maze.isDone() && ((maze.getCurrCol() % 2) == 0) && (maze.getCurrRow() == (maze.getHeight()-1))) 
			maze.moveRight();
		
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		return false;
		
	}
	
	public boolean upOdd() {
		
		//Begin loop on odd columns to move up
		while (!maze.isDone() && maze.moveUp() && ((maze.getCurrCol() % 2) != 0)) {
				
			location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
			
		}//end unobstructed upwards movement
		
		return false;
		
	}
	
	public boolean avoidOdd() {
		
		//instructions to move around obstacles when moving up except on first row
		if (!maze.isDone() && !maze.moveUp() && ((maze.getCurrCol() % 2) != 0) && (maze.getCurrRow() != 0)) {
				
			if (!maze.isDone() && maze.moveLeft())
				location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
				
			if (!maze.isDone() && maze.moveUp())
				location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
				
			if (!maze.isDone() && maze.moveUp())
				location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
				
			if (!maze.isDone() && maze.moveRight())
				location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
				
		}//end if movement around obstacles
		
		return false;
		
	}
	
	public boolean rightOdd() {
		
		//move right when reach bottom of even column and have not found the java logo
		if (!maze.isDone() && ((maze.getCurrCol() % 2) != 0) && (maze.getCurrRow() == 0))
			maze.moveRight();
		
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		return false;
		
	}
	
	
	// this is the code part that needs to be programmed by students to solve the maze 
	// using the provided path/direction algorithm
	public void run(){
		
		/*
		//test manual movement to java logo
		maze.moveDown(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveDown() + "\n");
		
		maze.moveDown(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveDown() + "\n");
		
		maze.moveDown(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveDown() + "\n");		
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");

		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveUp(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveUp() + "\n");
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		maze.moveLeft(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");

		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");

		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveRight(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveRight() + "\n");
		
		maze.moveDown(); 
		location.textArea.append("Moved to row " + maze.getCurrRow() + ", column " + maze.getCurrCol() + "\n");
		
		if (!maze.isDone()) location.textArea.append("Failure...\n");
		else location.textArea.append("Success...\n");
		
		location.textArea.append(maze.moveDown() + "\n");
		
		*/
		
		///*
		//Begin all encompassing loop to move through maze
		while (!maze.isDone()) {
			
			downEven();
			avoidEven();
			rightEven();				
			upOdd();
			avoidOdd();
			rightOdd();	
						
		} //end all encompassing loop
		//*/
				
		location.textArea.append("Logo Found \n");
		
	}//End of Run 
   	
}//End of class
