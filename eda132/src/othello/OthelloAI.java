package othello;

import java.util.HashMap;
import java.util.HashSet;

public class OthelloAI {
	private OthelloGame othelloGame;
	private long timeLimit;
	private long startTime;
	private boolean isDone;
	private char AIChar;

	public OthelloAI(OthelloGame game, long timeLimit, char AIChar) {
		othelloGame = game;
		this.timeLimit = timeLimit;
		startTime = 0;
		isDone = false;
		this.AIChar = AIChar;
	}

	public String deduceMove() {
		isDone = false;
		startTime = System.currentTimeMillis();
		System.out.println("Thinking...");
		HashMap<String, HashSet<Integer[]>> validMoves = othelloGame
				.getValidMoves();
		String move = "";
		int score = Integer.MIN_VALUE;
		int layer = 1;
		while (System.currentTimeMillis() - startTime < timeLimit && !isDone) {
			if (!validMoves.isEmpty()) {
				for (String s : validMoves.keySet()) {
					OthelloGame copy = othelloGame.getCopyBoard();
					copy.makeMove(s);
					copy.nextTurn();
					int temp;
					if (copy.getPlayer() == AIChar) {
						temp = recursiveMax(copy, layer - 1, Integer.MAX_VALUE);
					} else {
						temp = recursiveMin(copy, layer - 1, Integer.MIN_VALUE);
					}	
					if (temp > score) {
						score = temp;
						move = s;
					}
				}
			}
			layer++;
		}
		System.out.println("Time it took: "
				+ (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
		return move;
	}

	private int recursiveMax(OthelloGame game, int layer, int prevMin) {
		HashMap<String, HashSet<Integer[]>> validMoves = game.getValidMoves();
		int max = Integer.MIN_VALUE;
		if (!validMoves.isEmpty() && layer > 0) {
			for (String s : validMoves.keySet()) {
				OthelloGame copy = game.getCopyBoard();
				copy.makeMove(s);
				copy.nextTurn();
				int temp = max;
				if (System.currentTimeMillis() - startTime < timeLimit) {
					if (copy.getPlayer() == AIChar) {
						temp = recursiveMax(copy, layer - 1, prevMin);
					} else {
						temp = recursiveMin(copy, layer - 1, max);
					}
				} else {
					return Integer.MAX_VALUE;
				}
				if (temp > prevMin) {
					return temp;
				} else if (temp > max)
					max = temp;
			}
			return max;
		} else if (layer > 0) {
			isDone = true;
		}
		return game.sumScore();
	}

	private int recursiveMin(OthelloGame game, int layer, int prevMax) {
		HashMap<String, HashSet<Integer[]>> validMoves = game.getValidMoves();
		int min = Integer.MAX_VALUE;
		if (!validMoves.isEmpty() && layer > 0) {
			for (String s : validMoves.keySet()) {
				OthelloGame copy = game.getCopyBoard();
				copy.makeMove(s);
				copy.nextTurn();
				int temp = min;
				if (System.currentTimeMillis() - startTime < timeLimit) {
					if (copy.getPlayer() == AIChar) {
						temp = recursiveMax(copy, layer - 1, min);
					} else {
						temp = recursiveMin(copy, layer - 1, prevMax);
					}
				} else {
					return Integer.MIN_VALUE;
				}
				if (temp < prevMax) {
					return temp;
				} else if (temp < min) {
					min = temp;
				}
			}
			return min;
		} else if (layer > 0) {
			isDone = true;
		}
		return game.sumScore();
	}
}
