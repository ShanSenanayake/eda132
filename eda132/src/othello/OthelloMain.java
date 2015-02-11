package othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OthelloMain {

	public static void main(String[] args) {
		OthelloGame game = new OthelloGame(8);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the classical game of Othello");
		System.out.println("Enter maximum thinking time for AI (seconds) default is 10 seconds: ");
		long time = 10000;

			try {
				time = Math.round(Double.parseDouble(br.readLine())*1000);
			} catch (NumberFormatException e1) {
				System.out.println("Using default thinking time!");
			} catch (IOException e1) {
				System.out.println("Bad input, using default thinking time!");
			}
		
		OthelloAI ai = new OthelloAI(game,time,OthelloGame.L);
		while (game.nextTurn()) {
			game.print();
			char player = game.getPlayer();
			if (player == OthelloGame.D) {
				System.out.print("\nPlayer " + player + " make your move:");
				try {
					String s = br.readLine();
					while (!game.makeMove(s)) {
						System.out.print("\nInvalid move try again:");
						s = br.readLine();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				String move = ai.deduceMove();
				game.makeMove(move);
				System.out.println("Move made: " + move);
			}
		}
		game.print();
		System.out.println("Game over! Winner: " + game.score());

	}

}
