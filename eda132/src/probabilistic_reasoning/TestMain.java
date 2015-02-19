package probabilistic_reasoning;

public class TestMain {

	public static void main(String[] args) {
		MovingBot mb = new MovingBot(8);
		while(true){
			Point p = mb.sensorOutput();
			System.out.println("Sensor: x=" + p.x + " y=" + p.y);
			p = mb.pos();
			System.out.println("Robot: x=" + p.x + " y=" + p.y);
			mb.move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
