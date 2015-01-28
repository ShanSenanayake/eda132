package othello;


public class OthelloMain {
	private static int EMPTY = 0;
	private static int LIGHT = 1;
	private static int DARK = 2;
	
	public static void main(String args[]){
		int[][] test = new int[8][8];
		for (int i = 0; i < test.length;i++){
			for (int j = 0; j < test.length; j++){
				test[i][j]=(i+j) % 3;
			}
		}
		printBoard(test);
	}
	
	public static void printBoard(int[][] board){
		System.out.print("  +");
		for (int i = 0; i < board.length; i++){
			System.out.print("---+");
		}
		for (int i = 0; i < board.length;i++){
			char c = (char) ('a'+ i);
			System.out.print("\n" + c +" |");
			for (int j = 0; j < board.length; j++){
				char ch;
				if (board[i][j] == LIGHT){
					ch = 'O';
				}else if (board[i][j] == DARK){
					ch = 'X';
				}else{
					ch = ' ';
				}
				System.out.print(" " + ch + " |");
				}
			System.out.print("\n  +");
			for (int i1 = 0; i1 < board.length; i1++){
				System.out.print("---+");
			}	
		}
		System.out.print("\n ");
		for (int i1 = 0; i1 < board.length; i1++){
			System.out.print("   " + (i1+1));
		}
		
	}
	
	public static void AI(){
		
	}
	
	public static int[][] makeMove(int[][] board, int x, int y){
		return board;
		
	}

	
}
