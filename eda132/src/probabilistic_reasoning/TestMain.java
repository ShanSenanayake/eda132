package probabilistic_reasoning;


public class TestMain {

	public static void main(String[] args) {
		MovingBot mb = new MovingBot(8);
		MovingBotGraphics graphics = new MovingBotGraphics(8);
		ForwardAlgorithm fa = new ForwardAlgorithm(8);
		while(true){
			Point sensorPos = mb.sensorOutput();
			Point botPos = mb.pos();
			graphics.updateView(botPos, sensorPos);
			Point estimatePos = fa.getEstimatedLocation(sensorPos);
			System.out.println("Bot: " + botPos);
			System.out.println("Sensor: " + botPos);
			System.out.println("Estimate: " + botPos);
			mb.move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		


	}

}
