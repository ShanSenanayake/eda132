package probabilistic_reasoning;

public class BotMain {
	public static void main(String[] args) {
		MovingBot mb = new MovingBot(8);
		MovingBotGraphics graphics = new MovingBotGraphics(8);
		ForwardAlgorithm fa = new ForwardAlgorithm(8);
		int correctEstimates = 0;
		int iterations = 0;
		while (graphics.run()) {
			iterations++;
			Point sensorPos = mb.sensorOutput();
			Point botPos = mb.pos();
			Point estimatePos = fa.getEstimatedLocation(sensorPos);
			if (botPos.equals(estimatePos)) {
				correctEstimates++;
			}
			graphics.updateView(botPos, sensorPos, estimatePos);
			mb.move();
			try {
				Thread.sleep(graphics.time());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		double prob = ((double) (correctEstimates)) / iterations;
		System.out.println("Ratio of correct estimates this run: " + prob + " Iterations: " + iterations);
		graphics.close();
	}

}
