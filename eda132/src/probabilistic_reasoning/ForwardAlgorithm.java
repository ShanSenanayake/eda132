package probabilistic_reasoning;

import java.util.HashMap;
import java.util.HashSet;

public class ForwardAlgorithm {
	private double[][] stateProbMatrix;
	private double[][] transitionMatrix;
	private HashMap<Point, Integer> mapping;
	private HashMap<Integer, Point> mapping2;

	public ForwardAlgorithm(int size) {
		int matrixSize = size * size;
		stateProbMatrix = new double[matrixSize][matrixSize];
		transitionMatrix = new double[matrixSize][matrixSize];
		
		mapping = new HashMap<Point, Integer>();
		mapping2 = new HashMap<Integer, Point>();
		
		for (int i = 0; i < matrixSize; i++) {
			mapping.put(new Point(i % size, i / size), i);
			mapping2.put(i, new Point(i % size, i / size));
		}
		
		for (int i = 0; i < matrixSize; i++) {
			Point from = mapping2.get(i);
			HashSet<Point> neighbours = new HashSet<Point>();
			neighbours = calcNeighbours(from, size);
			for (int j = 0; j < matrixSize; j++) {
				if (i == j) {
					stateProbMatrix[i][j] = 1.0 / size;
					transitionMatrix[i][j] = 0;
				} else if (neighbours.contains(mapping2.get(j))) {
					transitionMatrix[i][j] = 1.0 / neighbours.size();
				} else {
					transitionMatrix[i][j] = 0;
				}

			}
		}
		
	}

	private HashSet<Point> calcNeighbours(Point from, int size) {
		HashSet<Point> neighbours = new HashSet<Point>();
		if (from.x <= 0) {
			neighbours.add(new Point(from.x + 1, from.y));
		} else if (from.x >= size - 1) {
			neighbours.add(new Point(from.x - 1, from.y));
		} else {
			neighbours.add(new Point(from.x - 1, from.y));
			neighbours.add(new Point(from.x + 1, from.y));
		}
		if (from.y <= 0) {
			neighbours.add(new Point(from.x, from.y + 1));
		} else if (from.y >= size - 1) {
			neighbours.add(new Point(from.x, from.y - 1));
		} else {
			neighbours.add(new Point(from.x, from.y - 1));
			neighbours.add(new Point(from.x, from.y + 1));
		}

		return neighbours;
	}
}
