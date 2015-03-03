package probabilistic_reasoning;

public class CopyOfBotMain {

	public static void main(String[] args) {
		MovingBot mb = new MovingBot(8);
		MovingBotGraphics graphics = null; 
		double[] error = new double[1000];
		double[] probability = new double[100];
		for (int i = 0; i < 100; i++) {
			graphics = new MovingBotGraphics(8);
			ForwardAlgorithm fa = new ForwardAlgorithm(8);
			int estimates = 0;
			int iterations = 0;
			double prob = 0;
			while (iterations < 1000) {
				iterations++;
				Point sensorPos = mb.sensorOutput();
				Point botPos = mb.pos();
				Point estimatePos = fa.getEstimatedLocation(sensorPos);
				if (botPos.equals(estimatePos)) {
					estimates++;
				}
				prob = ((double) (estimates)) / iterations;
//				 System.out.println("Probability: " + prob*100 + "%");
				// botPos.distance(estimatePos));
				graphics.updateView(botPos, sensorPos,estimatePos);
				// System.out.println("Bot: " + botPos);
				// System.out.println("Sensor: " + sensorPos);
				// System.out.println("Estimate: " + estimatePos);
				error[iterations - 1] += botPos.distance(estimatePos);
				mb.move();
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			probability[i] = prob;
			graphics.close();
		}
		double sum = 0;
		for (int i = 0; i < 100; i++) {
			sum += probability[i];
		}
		System.out.println(sum);
//		System.out.println("--------------");
//		for (int i = 1; i <= 1000; i++) {
//			System.out.println(i);
//		}
	}

}
