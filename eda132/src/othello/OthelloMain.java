package othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class OthelloMain {
	private static int EMPTY = 0;
	private static int LIGHT = 1;
	private static int DARK = 2;

	public static void main(String args[]) {
		int[][] board = new int[8][8];
		board[4][3] = 1;
		board[4][4] = 2;
		board[3][3] = 2;
		board[3][4] = 1;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the classic game of Reversi");
		printBoard(board);
		String s = "";
		int player = 1;
		HashMap<String, HashSet<Integer[]>> moves = findValidMoves(board,
				player);
		try {
			while (!moves.isEmpty()) {
				String theplayer;
				if (player == LIGHT){
					theplayer = "O";
				}else{
					theplayer = "X";
				}
				System.out.print("\nPlayer " + theplayer + " make your move:");
				boolean isValidMove = false;
				while (!isValidMove) {
					s = br.readLine();
					if (moves.containsKey(s)) {
						isValidMove = true;
					} else {
						System.out.print("\nInvalid move, choose again: ");
					}
				}
				board = makeMove(board, s, player, moves);
				player = player % 2 + 1;
				printBoard(board);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Game Over");
	}

	public static void printBoard(int[][] board) {
		System.out.print(" ");
		for (int i1 = 0; i1 < board.length; i1++) {
			System.out.print("   " + (i1 + 1));
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

	public static String posToString(int x, int y) {
		char[] c = { (char) (x + 'a'), (char) (y + '1') };
		return new String(c);
	}

	public static void AI() {

	}

	public static int[][] makeMove(int[][] board, String pos, int player,
			HashMap<String, HashSet<Integer[]>> moves) {

		flipTiles(board, moves.get(pos), player);
		char[] c = pos.toCharArray();
		board[(int) (c[0] - 'a')][(int) (c[1] - '1')] = player;

		return board;

	}

	public static int[][] flipTiles(int[][] board,
			HashSet<Integer[]> toBeFlipped, int player) {
		for (Integer[] i : toBeFlipped) {
			board[i[0]][i[1]] = player;
		}
		return board;
	}

	public static HashMap<String, HashSet<Integer[]>> findValidMoves(
			int[][] board, int player) {
		HashMap<String, HashSet<Integer[]>> moves = new HashMap<String, HashSet<Integer[]>>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == EMPTY) {
					// Check South Direction
					HashSet<Integer[]> score = checkDirection(board, player, i,
							j, 1, 0);
					// Check North Direction
					score.addAll(checkDirection(board, player, i, j, -1, 0));
					// Check East Direction
					score.addAll(checkDirection(board, player, i, j, -0, 1));
					// Check West Direction
					score.addAll(checkDirection(board, player, i, j, 0, -1));
					// Check South-East Direction
					score.addAll(checkDirection(board, player, i, j, 1, 1));
					// Check South-West Direction
					score.addAll(checkDirection(board, player, i, j, 1, -1));
					// Check North-East Direction
					score.addAll(checkDirection(board, player, i, j, -1, 1));
					// Check North-West Direction
					score.addAll(checkDirection(board, player, i, j, -1, -1));
					if (!score.isEmpty()) {
						moves.put(posToString(i, j), score);
					}
				}
			}
		}
		return moves;
	}

	public static HashSet<Integer[]> checkDirection(int[][] board, int player,
			int x, int y, int incX, int incY) {
		HashSet<Integer[]> score = new HashSet<Integer[]>();
		boolean undone = true;
		while (undone) {
			x = x + incX;
			y = y + incY;
			if (x < 0 || y < 0 || x >= board.length || y >= board.length) {
				undone = false;
			} else {
				if (board[x][y] == (player % 2 + 1)) {
					Integer[] temp = new Integer[2];
					temp[0] = x;
					temp[1] = y;
					score.add(temp);
				} else if (board[x][y] == 0) {
					return new HashSet<Integer[]>();
				} else {
					return score;
				}
			}
		}
		return new HashSet<Integer[]>();
	}
}
