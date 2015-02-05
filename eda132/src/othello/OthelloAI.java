package othello;

import java.util.HashMap;
import java.util.HashSet;

public class OthelloAI {
	private OthelloGame othelloGame;
	private long timeLimit;
	private long currentTime;

	public OthelloAI(OthelloGame game, int timeLimitInSeconds) {
		othelloGame = game;
		timeLimit = timeLimitInSeconds * 1000;
		currentTime = 0;
	}

	public String deduceMove() {
		currentTime = System.currentTimeMillis();
		System.out.println("Thinking...");
		HashMap<String, HashSet<Integer[]>> validMoves = othelloGame
				.getValidMoves();
		String move = "";
		int score = Integer.MIN_VALUE;
		int iterations = 1;
		while (System.currentTimeMillis() - currentTime < timeLimit) {
			int localMax = Integer.MIN_VALUE;
			String localMove = "";
			if (!validMoves.isEmpty()) {
				for (String s : validMoves.keySet()) {
					OthelloGame copy = othelloGame.getCopyBoard();
					copy.makeMove(s);
					copy.nextTurn();
					int temp = recursiveMin(copy, iterations, Integer.MIN_VALUE);
					if (temp > localMax) {
						localMax = temp;
						localMove = s;
					}
				}
				if(System.currentTimeMillis() - currentTime < timeLimit){
					score = localMax;
					move = localMove;
				}
			}
			iterations++;
		}
		System.out.println("Time it took: "
				+ (System.currentTimeMillis() - currentTime) / 1000.0);
		return move;
	}

	private int recursiveMax(OthelloGame game, int iterations, int prevMin) {
		HashMap<String, HashSet<Integer[]>> validMoves = game.getValidMoves();
		int max = Integer.MIN_VALUE;
		if (!validMoves.isEmpty() && iterations > 0) {
			for (String s : validMoves.keySet()) {
				OthelloGame copy = game.getCopyBoard();
				copy.makeMove(s);
				copy.nextTurn();
				int temp = max;
				if (System.currentTimeMillis() - currentTime < timeLimit) {
					temp = recursiveMin(copy, iterations - 1, max);
				}else{
					return Integer.MAX_VALUE;
				}
				if (temp > prevMin) {
					return temp;
				} else if (temp > max)
					max = temp;
			}
			return max;
		} else {
			return game.sumScore();
		}
	}

	private int recursiveMin(OthelloGame game, int iterations, int prevMax) {
		HashMap<String, HashSet<Integer[]>> validMoves = game.getValidMoves();
		int min = Integer.MAX_VALUE;
		if (!validMoves.isEmpty() && iterations > 0) {
			for (String s : validMoves.keySet()) {
				OthelloGame copy = game.getCopyBoard();
				copy.makeMove(s);
				copy.nextTurn();
				int temp = min;
				if (System.currentTimeMillis() - currentTime < timeLimit) {
					temp = recursiveMax(copy, iterations - 1, min);
				}else{
					return Integer.MIN_VALUE;
				}
				if (temp < prevMax) {
					return temp;
				} else if (temp < min) {
					min = temp;
				}
			}
			return min;
		} else {
			return game.sumScore();
		}
	}
}
