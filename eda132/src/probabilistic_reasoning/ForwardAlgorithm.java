package probabilistic_reasoning;

import java.util.HashMap;
import java.util.HashSet;

public class ForwardAlgorithm {

	private double[] stateProbability;
	private double[][] transitionMatrix;
	private HashMap<State, Integer> mapping;
	private State[] mapping2;
	private int nbrStates;

	public ForwardAlgorithm(int size) {
		nbrStates = 4 * size * size;
		stateProbability = new double[nbrStates];
		transitionMatrix = new double[nbrStates][nbrStates];

		mapping = new HashMap<State, Integer>();
		mapping2 = new State[nbrStates];

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
				stateProbability[i] = 1.0 / nbrStates;
			}
		}

		for (int i = 0; i < nbrStates; i++) {
			State from = mapping2[i];
			for (int j = 0; j < nbrStates; j++) {
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

	private double[] getObservationMatrix(Point p) {
		double[] o = new double[nbrStates];
		if (p.x == -1 && p.y == -1) {
			for (int i = 0; i < nbrStates; i++) {
				o[i] = 0.1;
			}
		} else {
			for (int i = 0; i < nbrStates; i++) {
				State s = mapping2[i];
				if (p.equals(s.p)) {
					o[i] = 0.1;
				} else if (checkStep(p, s, 1)) {
					o[i] = 0.05;
				} else if (checkStep(p, s, 2)) {
					o[i] = 0.025;
				} else {
					o[i] = 0;
				}

			}
		}
		return o;
	}

	private boolean checkStep(Point p, State s, int step) {
		for (int x = -step; x <= step; x++) {
			for (int y = -step; y <= step; y++) {
				Point tmp = new Point(p.x + x, p.y + y);
				if (tmp.equals(s.p)) {
					return true;
				}
			}
		}
		return false;
	}

	private int multiplication(double[] o) {
		double[] temp = new double[nbrStates];
		double alpha = 0;
		double max = 0;
		int mostLikelyState = -1;
		for (int row = 0; row < nbrStates; row++) {
			temp[row] = 0;
			for (int i = 0; i < nbrStates; i++) {
				// transponat
				temp[row] += transitionMatrix[i][row] * stateProbability[i]
						* o[i];
			}
			alpha += temp[row];
		}
		alpha = 1/alpha;
		stateProbability = temp;
		for(int i = 0; i<nbrStates;i++){
			stateProbability[i] = stateProbability[i]*alpha;
			if(stateProbability[i]>max){
				mostLikelyState = i;
				max = stateProbability[i];
			}
		}
		return mostLikelyState;
	}
}
