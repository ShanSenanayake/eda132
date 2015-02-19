package probabilistic_reasoning;

import java.util.HashSet;

public class TestMain {

	public static void main(String[] args) {
		MovingBot mb = new MovingBot(8);
		MovingBotGraphics graphics = new MovingBotGraphics(8);
		while(true){
			Point sensorPos = mb.sensorOutput();
			Point botPos = mb.pos();
			graphics.updateView(botPos, sensorPos);
			mb.move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		


	}

}
