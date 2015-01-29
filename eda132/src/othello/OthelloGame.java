package othello;

import java.util.HashMap;
import java.util.HashSet;

public class OthelloGame {
	private static final int EMPTY = 0;
	private static final int LIGHT = 1;
	private static final int DARK = -1;
	private static final char L = 'O';
	private static final char D = 'X';
	private static final char E = ' ';
	private static final char V = '#';
	private int[][] board;
	private int player;
	private HashMap<String, HashSet<Integer[]>> validMoves;

	public OthelloGame(int size) {
		board = new int[size][size];
		board[size / 2][size / 2 - 1] = LIGHT;
		board[size / 2][size / 2] = DARK;
		board[size / 2 - 1][size / 2 - 1] = DARK;
		board[size / 2 - 1][size / 2] = LIGHT;
	}

	public void print() {
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
					ch = L;
				} else if (board[i][j] == DARK) {
					ch = D;
				} else {
					ch = E;
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

	private void findValidMoves() {
		validMoves = new HashMap<String, HashSet<Integer[]>>();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == EMPTY) {
					// Check South Direction
					HashSet<Integer[]> score = checkDirection(i, j, 1, 0);
					// Check North Direction
					score.addAll(checkDirection(i, j, -1, 0));
					// Check East Direction
					score.addAll(checkDirection(i, j, -0, 1));
					// Check West Direction
					score.addAll(checkDirection(i, j, 0, -1));
					// Check South-East Direction
					score.addAll(checkDirection(i, j, 1, 1));
					// Check South-West Direction
					score.addAll(checkDirection(i, j, 1, -1));
					// Check North-East Direction
					score.addAll(checkDirection(i, j, -1, 1));
					// Check North-West Direction
					score.addAll(checkDirection(i, j, -1, -1));
					if (!score.isEmpty()) {
						validMoves.put(posToString(i, j), score);
					}
				}
			}
		}
	}

	private HashSet<Integer[]> checkDirection(int x, int y, int incX, int incY) {
		HashSet<Integer[]> score = new HashSet<Integer[]>();
		boolean undone = true;
		while (undone) {
			x = x + incX;
			y = y + incY;
			if (x < 0 || y < 0 || x >= board.length || y >= board.length) {
				undone = false;
			} else {
				if (board[x][y] == opponent()) {
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

	private int opponent() {
		if (player == LIGHT) {
			return DARK;
		} else {
			return LIGHT;
		}
	}

	public boolean makeMove(String pos) {
		if (validMoves.containsKey(pos)) {
			flipTiles(validMoves.get(pos));
			int[] p = stringToPos(pos);
			board[p[0]][p[1]] = player;
			return true;
		} else {
			return false;
		}
	}

	private void flipTiles(HashSet<Integer[]> toBeFlipped) {
		for (Integer[] i : toBeFlipped) {
			board[i[0]][i[1]] = player;
		}
	}

	public boolean nextTurn() {
		switchPlayer();
		findValidMoves();
		return !validMoves.isEmpty();
	}

	private void switchPlayer() {
		if (player == DARK) {
			player = LIGHT;
		} else {
			player = DARK;
		}
	}

	public char getPlayer() {
		if (player == LIGHT) {
			return L;
		} else {
			return D;
		}
	}

	public HashMap<String, HashSet<Integer[]>> getValidMoves() {
		return validMoves;
	}

	private String posToString(int x, int y) {
		char[] c = { (char) (x + 'a'), (char) (y + '1') };
		return new String(c);
	}

	private int[] stringToPos(String pos) {
		char[] c = pos.toCharArray();
		int[] result = { (int) (c[0] - 'a'), (int) (c[1] - '1') };
		return result;
	}

	public String score() {
		int score = sum();
		if (score < 0) {
			score = -score;
			return "Player: " + D + " Score: " + score;
		} else if (score > 0) {
			return "Player: " + L + " Score: " + score;
		} else {
			return "Draw";
		}
	}

	private int sum() {
		int sum = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				sum += board[i][j];
			}
		}
		return sum;
	}
}
