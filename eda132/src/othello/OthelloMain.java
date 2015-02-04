package othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OthelloMain {

	public static void main(String[] args) {
		OthelloGame game = new OthelloGame(8);
		OthelloAI ai = new OthelloAI(game,10);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the classical game of Othello");
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				String move = ai.deduceMove();
				game.makeMove(move);
			}
		}
		System.out.println("Game over! Winner: " + game.score());

	}

}
