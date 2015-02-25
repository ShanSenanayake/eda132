package probabilistic_reasoning;

import java.util.HashMap;
import java.util.HashSet;

public class ForwardAlgorithm {

	private double[] stateProbability;
	private double[][] transitionMatrix;
	private HashMap<State, Integer> mapping;
	private State[] mapping2;

	public ForwardAlgorithm(int size) {
		int matrixSize = 4 * size * size;
		stateProbability = new double[matrixSize];
		transitionMatrix = new double[matrixSize][matrixSize];

		mapping = new HashMap<State, Integer>();
		mapping2 = new State[size];

		for (int i = 0; i < size * size; i++) {
			for (int j = 0; j < 4; j++) {
				State state = null;
				switch (j) {
				case State.N:
					state = new State(new Point(i / size, i % size), State.N);
					break;
				case State.S:
					state = new State(new Point(i / size, i % size), State.S);
					break;
				case State.E:
					state = new State(new Point(i / size, i % size), State.E);
					break;
				case State.W:
					state = new State(new Point(i / size, i % size), State.W);
					break;
				default:
					System.out.println("something wrong");
				}
				mapping.put(state, i);
				mapping2[i] = state;
				stateProbability[i] = 1.0 / matrixSize;
			}
		}

		for (int i = 0; i < matrixSize; i++) {
			State from = mapping2[i];
			for (int j = 0; j < matrixSize; j++) {
				State to = mapping2[j];
				if (from.isNeighbour(to)) {
					if (from.encounterWall(size)) {
						transitionMatrix[i][j] = 1.0 / from
								.amountOfNeighbours(size);
					} else if (from.heading == to.heading) {
						transitionMatrix[i][j] = 0.7;
					} else {
						transitionMatrix[i][j] = 0.3 / (from
								.amountOfNeighbours(size) - 1);
					}
				} else {
					transitionMatrix[i][j] = 0;
				}
			}
		}
	}


}
