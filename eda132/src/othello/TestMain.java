package othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestMain {

	public static void main(String[] args) {
		OthelloGame game = new OthelloGame(8);
		OthelloGame copy = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the classical game of Othello");
		while (game.nextTurn()) {
			game.print();
			char player = game.getPlayer();
			System.out.print("\nPlayer " + player + " make your move:");
			try {
				String s = br.readLine();
				if (s.equals("copy")) {
					copy = game.getCopyBoard();
				} else if (copy != null && s.equals("insert")) {
					OthelloGame tmp = game;
					game = copy;
					copy = tmp;
					game.print();
				}
					while (!game.makeMove(s)) {
						System.out.print("\nInvalid move try again:");
						s = br.readLine();
					};
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Game over! Winner: " + game.score());

	}

}
