import java.util.Scanner;

public class connect6game {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		char[][] grid = new char[8][9];
		
		
		for (int row = 0; row < grid.length; row++){
			for (int column = 0; column < grid[0].length; column++){
				grid[row][column] = ' ';
			}
		}
		
		int turn = 1;
		char player = 'A';
		boolean winner = false;		
		
		//play a turn
		while (winner == false && turn <= 72){
			boolean validPlay;
			int play;
			do {
				display(grid);
				
				System.out.print("Player " + player + ", Play your Turn: ");
				play = input.nextInt();
				
				//validate play
				validPlay = validate(play,grid);
				
			}while (validPlay == false);
			
			//drop the checker
			for (int row = grid.length-1; row >= 0; row--){
				if(grid[row][play] == ' '){
					grid[row][play] = player;
					break;
				}
			}
			
			//determine if there is a winner
			winner = isWinner(player,grid);
			
			//switch players
			if (player == 'A'){
				player = 'B';
			}else{
				player = 'A';
			}
			
			turn++;			
		}
		display(grid);
		
		if (winner){
			if (player=='A'){
				System.out.println(" Player B won");
			}else{
				System.out.println(" Player A won");
			}
		}else{
			System.out.println("Match Drawn");
		}
		
	}
	
	public static void display(char[][] grid){
		System.out.println("  1   2   3   4   5   6   7   8   9");
		System.out.println("____________________________________");
		for (int row = 0; row < grid.length; row++){
			System.out.print("| ");
			for (int column = 0; column < grid[0].length; column++){
				System.out.print(grid[row][column]);
				System.out.print(" | ");
			}
			//System.out.println();
			System.out.println("\n"+"------------------------------------");
		}
		System.out.println("  1   2   3   4   5   6   7   8   9");
		System.out.println();
	}
	
	public static boolean validate(int column, char[][] grid){
		//valid column?
		if (column < 0 || column > grid[0].length){
			return false;
		}
		
		//full column?
		if (grid[0][column] != ' '){
			return false;
		}
		
		return true;
	}
	
	public static boolean isWinner(char player, char[][] grid){
		//check for 6 across
		for(int row = 0; row<grid.length; row++){
			for (int column = 0;column < grid[0].length - 5;column++){
				if (grid[row][column] == player   && 
					grid[row][column+1] == player &&
					grid[row][column+2] == player &&
					grid[row][column+3] == player &&
					grid[row][column+4] == player &&
                    grid[row][column+5] == player)
                    {
				
					return true;
				}
			}			
		}
		//check for 6 up and down
		for(int row = 0; row < grid.length - 5; row++){
			for(int column = 0; column < grid[0].length; column++){
				if (grid[row][column] == player   && 
					grid[row+1][column] == player &&
					grid[row+2][column] == player &&
					grid[row+3][column] == player&&
					grid[row+4][column] == player &&
					grid[row+5][column] == player){
					return true;
				}
			}
		}
		//check upward diagonal
		for(int row = 3; row < grid.length; row++){
			for(int column = 0; column < grid[0].length - 5; column++){
				if (grid[row][column] == player   && 
					grid[row-1][column+1] == player &&
					grid[row-2][column+2] == player &&
					grid[row-3][column+3] == player &&
					grid[row-4][column+4] == player &&
					grid[row-5][column+5] == player)  {
					return true;
				}
			}
		}
		//check downward diagonal
		for(int row = 0; row < grid.length - 5; row++){
			for(int column = 0; column < grid[0].length - 5; column++){
				if (grid[row][column] == player   && 
					grid[row+1][column+1] == player &&
					grid[row+2][column+2] == player &&
					grid[row+3][column+3] == player &&
					grid[row+4][column+4] == player &&
					grid[row+5][column+5] == player) {
					return true;
				}
			}
		}
		return false;
	}
}