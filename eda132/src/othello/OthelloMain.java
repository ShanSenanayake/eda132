package othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OthelloMain {
	private static int EMPTY = 0;
	private static int LIGHT = 1;
	private static int DARK = 2;

	public static void main(String args[]) {
		int[][] board = new int[8][8];

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
		System.out.print("  +");
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

	public static void AI() {

	}

	public static int[][] makeMove(int[][] board, String pos, int y) {
		return board;

	}

	public static String[] findValidMoves(int[][] board) {
		return null;
	}

}
