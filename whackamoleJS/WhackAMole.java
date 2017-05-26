

import java.util.Scanner;

/**
 * Homework 1 for edX Java Software Dev class
 * WhackAMole is an abstraction of the classic WhackAMole game
 * @author piercegresham
 */
public class WhackAMole {
    // Instance vars
    int score = 0;
    int molesLeft = 0;
    int attempts;
    char[][] moleGrid;
    
    /**
     * WhackAMole Constructor
     * @param numAttempts - Max attempts allowed in game
     * @param gridDimensions - Size of game board height and width
     */
    public WhackAMole(int numAttempts, int gridDimensions) {
	moleGrid = new char[gridDimensions][gridDimensions];
	// Initialize moleGrid array
	for(int i = 0 ; i < gridDimensions; i++ ) {
	    for(int j = 0; j < gridDimensions; j++ ) {
		moleGrid[i][j] = '*';
	    }
	}
	
	this.attempts = numAttempts;
    }
    /**
     * Dumps entire moleGrid to console
     * Shows Moles, Whacks, and empty spaces
     */
    void printGrid() {
	for(int i = 0; i < moleGrid.length; i++) {
	    for (int j = 0; j < moleGrid[i].length; j++) {
		System.out.print(moleGrid[i][j]);
	    }
	    System.out.println();
	}
	System.out.println();
    }
    
    /**
     * Place a hidden mole on the grid
     * @return boolean if placement is successful
     */
    boolean place(int x, int y) {
	if(x > moleGrid.length - 1 || y > moleGrid.length -1) {
	    return false;
	}
	if(x < 0 || y < 0) {
	    return false;
	}
	if(moleGrid[x][y] == '*') {
	    moleGrid[x][y] = 'M';
	    molesLeft++;
	    return true;
	}
	else { 
	    return false;
	}
    }
    
    /**
     * User takes a shot at a mole
     * @param x - row value in grid
     * @param y - column value in grid
     */
    void whack(int x, int y) {
	
	if(moleGrid[x][y] == 'M') {
	  score++;
	  molesLeft--;
	  moleGrid[x][y] = 'W';
	}
	attempts--;
    }
    
    
    
    /**
     * Prints grid to user
     * Shows either '*' or 'W' only if they found a mole
     */
    void printGridToUser() {
	for(int i = 0; i < moleGrid.length; i++) {
	    for (int j = 0; j < moleGrid[i].length; j++) {
		if(moleGrid[i][j] == 'W') {
		    System.out.print('W');
		}
		else {
		    System.out.print('*');
		}
	    }
	    System.out.println();
	}
	System.out.println();
    }
    
    /**
     * Displays current score
     */
    void showScore() {
	System.out.println("Score: "+score);
	System.out.println("Whacks remaining: "+attempts);
	System.out.println("Moles remaining: "+molesLeft);
    }
    
    
    
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	WhackAMole game = new WhackAMole(50, 10);
	Scanner in = new Scanner(System.in);
	for(int i = 0;i<10;i++){
	    game.place(i, i);
	    // game.place(i, (int)(Math.random()*10));
	}
	game.printGridToUser();
	System.out.println("Enter x and y coordinates to try to 'Whack A Mole'"
		+ " at the given location.");
	System.out.println("You have "+game.attempts+" tries to get all of the moles.");
	System.out.println("Use only positive integers 0 to "+( game.moleGrid.length -1 )+".");
	System.out.println("You may enter -1, -1 to exit the game before finishing.");
	System.out.println("Good Luck!");
	System.out.println();
	
	while(game.attempts > 0) {
	    game.showScore();
	    game.printGridToUser();
	    int x;
	    int y;
	    System.out.print("Enter a value for x: ");
	    if(in.hasNextInt()){
		x = in.nextInt();
		System.out.print("Enter a value for y: ");
		if(in.hasNextInt()) {
		    y = in.nextInt();
		  
		    if(x == -1 && y == -1) {
			System.out.println("Thanks for playing! See you next time.\n");
			game.printGrid();
			break;
		    }
		    else if(x > game.moleGrid.length -1 || y > game.moleGrid.length -1) {
			System.out.println("Values are out of range.");
			System.out.println();
		    }
		    else if( x < 0 || y < 0 ) {
			System.out.println("Values are out of range.");
			System.out.println();
		    }
		    else {
			// Here the game goes on
			game.whack(x, y);
			game.showScore();
			game.printGridToUser();
			if(game.molesLeft == 0) {
			    System.out.println("Congratulations! You found all of the moles");
			    break;
			}
			else if (game.attempts == 0) {
			    System.out.println("Sorry, you are out of tries. Try again!");
			    game.printGrid();
			}
		    }
		}
		else {
		    System.out.println("Only positive ints 0 to "+(game.moleGrid.length -1));
		    in.next();
		    System.out.println();
		}
	    }
	    else {
		System.out.println("Only positive ints 0 to "+(game.moleGrid.length -1));
		in.next();
	    }
	    
	   
	    
	    
	    
	}
	
	in.close();
    }

}
