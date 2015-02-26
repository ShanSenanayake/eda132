package probabilistic_reasoning;


public class TestMain {

	public static void main(String[] args) {
		MovingBot mb = new MovingBot(8);
		MovingBotGraphics graphics = new MovingBotGraphics(8);
		ForwardAlgorithm fa = new ForwardAlgorithm(8);
		while(true){
			Point sensorPos = mb.sensorOutput();
			Point botPos = mb.pos();
			Point estimatePos = fa.getEstimatedLocation(sensorPos);
			graphics.updateView(botPos, sensorPos,estimatePos);
			System.out.println("Bot: " + botPos);
			System.out.println("Sensor: " + sensorPos);
			System.out.println("Estimate: " + estimatePos);
			mb.move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		


	}

}
