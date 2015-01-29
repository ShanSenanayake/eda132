package othello;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestMain {

	public static void main(String[] args) {
		OthelloGame ob = new OthelloGame(8);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Welcome to the classical game of Othello");
		while(ob.nextTurn()){
			ob.print();
			char player = ob.getPlayer();
			System.out.print("\nPlayer " + player + " make your move:");
			try {
				String s = br.readLine();
				while(!ob.makeMove(s)){
					System.out.print("\nInvalid move try again:");
					s = br.readLine();
				};
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Leading: " + ob.score());
		}
		System.out.println("Game over! Winner: " + ob.score());

	}

}
