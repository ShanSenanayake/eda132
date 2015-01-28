package othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class OthelloMain {
	private static int EMPTY = 0;
	private static int LIGHT = 1;
	private static int DARK = 2;
	private static final int N = 1;
	private static final int S = 2;
	private static final int W = 3;
	private static final int E = 4;
	private static final int NW = 5;
	private static final int NE = 6;
	private static final int SW = 7;
	private static final int SE = 8;
	
	public static void main(String args[]) {
		int[][] board = new int[8][8];
		board[4][3] = 1;
		board[4][4] = 2;
		board[3][3] = 2;
		board[3][4] = 1;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the classic game of Reversi");
		printBoard(board);
		String s;
		int player = 1;
		try {
			for (int i = 0; i < 60; i++) {
				System.out.print("\nPlayer " + player + " make your move:");
				s = br.readLine();
				board = makeMove(board,s,player);
				player = player % 2 + 1;
				printBoard(board);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void printBoard(int[][] board) {
		for (int i1 = 0; i1 < board.length; i1++) {
			System.out.print("    " + (i1 + 1));
		}
		System.out.print("\n  +");
		for (int i = 0; i < board.length; i++) {
			System.out.print("---+");
		}
		for (int i = 0; i < board.length; i++) {
			char c = (char) ('a' + i);
			System.out.print("\n" + c + " |");
			for (int j = 0; j < board.length; j++) {
				char ch;
				if (board[i][j] == LIGHT) {
					ch = 'O';
				} else if (board[i][j] == DARK) {
					ch = 'X';
				} else {
					ch = ' ';
				}
				System.out.print(" " + ch + " |");
			}
			System.out.print(" " + c);
			System.out.print("\n  +");
			for (int i1 = 0; i1 < board.length; i1++) {
				System.out.print("---+");
			}
		}
		System.out.print("\n ");
		for (int i1 = 0; i1 < board.length; i1++) {
			System.out.print("   " + (i1 + 1));
		}

	}
	
	public static String posToString(int x,int y){
		char[] c = {(char)(x+'a'), (char)(y+'1')};
		return new String(c);
	}

	public static void AI() {

	}

	public static int[][] makeMove(int[][] board, String pos, int y) {
		char[] c = pos.toCharArray();
		board[(int)(c[0]-'a')][(int)(c[1]-'1')] = y;
		return board;

	}

	public static String[] findValidMoves(int[][] board, int player) {
		for(int i = 0; i<board.length;i++){
			for(int j = 0; j<board.length;j++){
				if(board[i][j] == EMPTY){
					
				}
			}
		}
		return null;
	}
	public static HashSet<Integer[]> checkDirection(int[][] board,int player, int x, int y, int dir){
		HashSet<Integer[]> score = new HashSet<Integer[]>();
		switch(dir){
		case N: 
			for(int i = (x-1); i>=0; i--){
				if(board[i][y] == (player % 2 +1)){ 
					Integer[] temp = new Integer[2];
					temp[0] = i;
					temp[1] = y;
					score.add(temp);
				}else if(board[i][y] == 0){
					return new HashSet<Integer[]>();
				}else{
					return score;
				}
			}
			break;
		case S:
			for(int i = (x+1); i<board.length; i++){
				if(board[i][y] == (player % 2 +1)){ 
					Integer[] temp = new Integer[2];
					temp[0] = i;
					temp[1] = y;
					score.add(temp);
				}else if(board[i][y] == 0){
					return new HashSet<Integer[]>();
				}else{
					return score;
				}
			}
			break;
		case E:
			for(int i = (y+1); i<board.length; i++){
				if(board[x][i] == (player % 2 +1)){ 
					Integer[] temp = new Integer[2];
					temp[0] = x;
					temp[1] = i;
					score.add(temp);
				}else if(board[x][i] == 0){
					return new HashSet<Integer[]>();
				}else{
					return score;
				}
			}
			break;
		case W:
			for(int i = (y-1); i>=0; i--){
				if(board[x][i] == (player % 2 +1)){ 
					Integer[] temp = new Integer[2];
					temp[0] = x;
					temp[1] = i;
					score.add(temp);
				}else if(board[x][i] == 0){
					return new HashSet<Integer[]>();
				}else{
					return score;
				}
			}
			break;
		case NW:
			break;
		case NE:
			break;
		case SW:
			break;
		case SE:
			break;
			
		}
		return score;
	}
}
