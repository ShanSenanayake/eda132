package probabilistic_reasoning;

public class test2 {

	public static void main(String[] args) {
		MovingBot mb = new MovingBot(8);
		ForwardAlgorithm fa = new ForwardAlgorithm(8);
		
		System.out.println(fa.getEstimatedLocation(new Point(0,7)));
		System.out.println(fa.getEstimatedLocation(new Point(4,3)));
		System.out.println(fa.getEstimatedLocation(new Point(5,7)));

	}

}
