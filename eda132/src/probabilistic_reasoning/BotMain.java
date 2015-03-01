package probabilistic_reasoning;

public class TestMain {

	public static void main(String[] args) {
		MovingBot mb = new MovingBot(8);
//		MovingBotGraphics graphics = new MovingBotGraphics(8);
		double[] error = new double[1000];
		double[] probability = new double[1000];
		for (int i = 0; i < 100; i++) {
			ForwardAlgorithm fa = new ForwardAlgorithm(8);
			int estimates = 0;
			int iterations = 0;
			while (iterations < 1000) {
				iterations++;
				Point sensorPos = mb.sensorOutput();
				Point botPos = mb.pos();
				Point estimatePos = fa.getEstimatedLocation(sensorPos);
				if (botPos.equals(estimatePos)) {
					estimates++;
				}
				double prob = ((double) (estimates)) / iterations;
				// System.out.println("Probability: " + prob*100 + "% " +
				// estimates + " " + iterations + " dist: " +
				// botPos.distance(estimatePos));
				// graphics.updateView(botPos, sensorPos,estimatePos);
				// System.out.println("Bot: " + botPos);
				// System.out.println("Sensor: " + sensorPos);
				// System.out.println("Estimate: " + estimatePos);
				error[iterations - 1] += botPos.distance(estimatePos);
				probability[iterations - 1] += prob;
				mb.move();
			}
		}
		for (int i = 0; i < 1000; i++) {
			System.out.println(error[i] / 100);
		}
		System.out.println("--------------");
		for (int i = 1; i <= 1000; i++) {
			System.out.println(i);
		}
	}

}
