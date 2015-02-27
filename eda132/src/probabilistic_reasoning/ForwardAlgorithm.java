package probabilistic_reasoning;

import java.util.HashMap;

public class ForwardAlgorithm {

	private double[] stateProbability;
	private double[][] transitionMatrix;
	private HashMap<State, Integer> mapping;
	private State[] mapping2;
	private int nbrStates;
	private int size;

	public ForwardAlgorithm(int size) {
		nbrStates = 4 * size * size;
		stateProbability = new double[nbrStates];
		transitionMatrix = new double[nbrStates][nbrStates];
		this.size = size;

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
				int index = (i * 4) + j;
				mapping.put(state, index);
				mapping2[index] = state;
				stateProbability[index] = 1.0 / nbrStates;
				// System.out.println("index: " + index + " " + state.p + " h: "
				// + state.heading);
			}
		}

		for (int i = 0; i < nbrStates; i++) {
			State from = mapping2[i];
			for (int j = 0; j < nbrStates; j++) {
				State to = mapping2[j];
				if (from.isNeighbour(to)) {
					// System.out.println("from: " + from.p + " h: " +
					// from.heading+ " to: " + to.p + " h: " +to.heading);
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
//					 System.out.println("Actual " + mapping2[i].p);
				} else if (checkStep(p, s, 1)) {
					// check amount of legal inner hood
//					 System.out.println("close hood " + mapping2[i].p);
					int n = legitHood(p, 1);
					o[i] = 0.4 / n;
				} else if (checkStep(p, s, 2)) {
					// check amount of legal outer hood
//					 System.out.println("far hood " + mapping2[i].p);
					int n = legitHood(p, 2);
					o[i] = 0.4 / n;
				} else {
					o[i] = 0;
				}

			}
		}
		return o;
	}

	public int legitHood(Point p, int step) {
		int legits = 0;
		for (int x = -step; x <= step; x++) {
			for (int y = -step; y <= step; y++) {
				if (Math.abs(x) == step || Math.abs(y) == step) {
					Point tmp = new Point(p.x + x, p.y + y);
					if (tmp.x >= 0 && tmp.y >= 0 && tmp.x < size
							&& tmp.y < size) {
						legits++;
					}
				}
			}
		}
		return legits;
	}

	private boolean checkStep(Point p, State s, int step) {
		for (int x = -step; x <= step; x++) {
			for (int y = -step; y <= step; y++) {
				if (Math.abs(x) == step || Math.abs(y) == step) {
					Point tmp = new Point(p.x + x, p.y + y);
					if (tmp.equals(s.p)) {
						return true;
					}
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
				double troll = transitionMatrix[i][row] * stateProbability[i]*o[row];
				// double troll = transitionMatrix[row][i] *
				// stateProbability[i]* o[i];
				if (troll > 0) {
//					System.out.println("row: " + mapping2[row].p + " h: "
//							+ mapping2[row].heading + " col: " + mapping2[i].p
//							+ " h: " + mapping2[i].heading + " trans: " + transitionMatrix[i][row]);
				}
				temp[row] += troll;
			}
			alpha += temp[row];
		}
		alpha = 1 / alpha;
		int count = 0;
		for (int i = 0; i < nbrStates; i++) {
			stateProbability[i] = temp[i] * alpha;
			if (temp[i] > 0) {
				count++;
				// System.out.println("point: " + mapping2[i].p + " h: " +
				// mapping2[i].heading + " value: " + stateProbability[i]);
			}
			if (temp[i] > max) {
				mostLikelyState = i;
				max = temp[i];
			}
		}
		// System.out.println("Number of possible states: " + count);
		return mostLikelyState;
	}

	public Point getEstimatedLocation(Point p) {
		int index = multiplication(getObservationMatrix(p));
		return mapping2[index].p;
	}

}
