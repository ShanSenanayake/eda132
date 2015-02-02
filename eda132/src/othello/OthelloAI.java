package othello;

import java.util.HashMap;
import java.util.HashSet;

public class OthelloAI {
	private OthelloGame othelloGame;
	
	public OthelloAI(OthelloGame game){
		othelloGame = game;
	}

	public String deduceMove(int iterations) {
		System.out.println("Thinking...");
		HashMap<String, HashSet<Integer[]>> validMoves = othelloGame.getValidMoves();
		String move = "";
		int score = Integer.MIN_VALUE;
		if(!validMoves.isEmpty()){
			for(String s: validMoves.keySet()){
				OthelloGame copy = othelloGame.getCopyBoard();
				copy.makeMove(s);
				copy.nextTurn();
				int temp = recursiveMin(copy,iterations,Integer.MIN_VALUE);
				if(temp>score){
					score = temp;
					move = s;
				}
			}
		}
		return move;
	}

	private int recursiveMax(OthelloGame game,int iterations,int prevMin) {
		HashMap<String, HashSet<Integer[]>> validMoves = game.getValidMoves();
		int max = Integer.MIN_VALUE;
		if (!validMoves.isEmpty() && iterations>0) {
			for (String s : validMoves.keySet()) {
				OthelloGame copy = game.getCopyBoard();
				copy.makeMove(s);
				copy.nextTurn();
				int temp = recursiveMin(copy,iterations-1,max);
				if (temp > prevMin) {
					return temp;
				}else if(temp > max)
					max = temp;
			}
			return max;
		}else{
			return game.sumScore();
		}
	}

	private int recursiveMin(OthelloGame game,int iterations,int prevMax) {
		HashMap<String, HashSet<Integer[]>> validMoves = game.getValidMoves();
		int min = Integer.MAX_VALUE;
		if (!validMoves.isEmpty() && iterations > 0) {
			for (String s : validMoves.keySet()) {
				OthelloGame copy = game.getCopyBoard();
				copy.makeMove(s);
				copy.nextTurn();
				int temp = recursiveMax(copy,iterations-1,min);
				if (temp <prevMax) {
					return temp;
				}else if(temp<min){
					min = temp;
				}
			}
			return min;
		}else{
			return game.sumScore();
		}
	}
}
