package probabilistic_reasoning;


public class TestMain {

	public static void main(String[] args) {
		MovingBot mb = new MovingBot(8);
		MovingBotGraphics graphics = new MovingBotGraphics(8);
		ForwardAlgorithm fa = new ForwardAlgorithm(8);
		int iterations = 0;
		int estimates = 0;
		while(true){
			iterations++;
			Point sensorPos = mb.sensorOutput();
			Point botPos = mb.pos();
			Point estimatePos = fa.getEstimatedLocation(sensorPos);
			if(botPos.equals(estimatePos)){
				estimates++;
			}
			double prob = ((double)(estimates))/iterations;
			System.out.println("Probability: " + prob*100 + "% " + estimates + " " + iterations + " dist: " + botPos.distance(estimatePos));
			graphics.updateView(botPos, sensorPos,estimatePos);
//			System.out.println("Bot: " + botPos);
//			System.out.println("Sensor: " + sensorPos);
//			System.out.println("Estimate: " + estimatePos);
			mb.move();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		


	}

}
