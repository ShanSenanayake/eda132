package probabilistic_reasoning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovingBotGraphics {
	private JLabel[][] board;
	private JLabel message;
	private Point botPosition;
	private Point sensorPosition;

	public MovingBotGraphics(int size) {
		JFrame frame = new JFrame("Moving Bot simulation");
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel msgPanel = new JPanel();
		JPanel matrixPanel = new JPanel(new GridLayout(size, size));

		message = new JLabel("Info goes here");
		board = new JLabel[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[i][j] = new JLabel();
				board[i][j].setSize(50, 50);
				// board[i][j].setOpaque(true);
				// board[i][j].setBackground(Color.magenta);
				board[i][j].setBorder(BorderFactory
						.createLineBorder(Color.BLACK));
				matrixPanel.add(board[i][j]);
			}
		}

		msgPanel.add(message);
		msgPanel.setSize(100, 20);

		mainPanel.add(msgPanel, BorderLayout.NORTH);
		mainPanel.add(matrixPanel, BorderLayout.CENTER);

		frame.add(mainPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);

		botPosition = new Point(0, 0);
		sensorPosition = new Point(0, 0);
	}

	public void updateView(Point botPos, Point sensorPos) {
		message.setOpaque(false);
		setBotPosition(botPos);
		setSensorPosition(sensorPos);
		message.setText("Robot: x=" + botPosition.x + " y=" + botPosition.y
				+ "\nSensor: x=" + sensorPosition.x + " y=" + sensorPosition.y);
	}

	private void setBotPosition(Point pos) {
		board[botPosition.x][botPosition.y].setText("");
		board[botPosition.x][botPosition.y].setOpaque(false);
		board[pos.x][pos.y].setText("Bot");
		board[pos.x][pos.y].setOpaque(true);
		board[pos.x][pos.y].setBackground(Color.green);
		botPosition = pos;
	}

	private void setSensorPosition(Point pos) {
		if (sensorPosition != botPosition) {
			board[sensorPosition.x][sensorPosition.y].setText("");
			board[sensorPosition.x][sensorPosition.y].setOpaque(false);
		}
		if (!pos.equals(botPosition) && pos.x > 0 && pos.y > 0) {
			board[pos.x][pos.y].setText("Sensor");
			board[pos.x][pos.y].setOpaque(true);
			board[pos.x][pos.y].setBackground(Color.red);
			sensorPosition = pos;
		} else if (pos.x > 0 && pos.y > 0) {
			board[pos.x][pos.y].setText("Bot and\nSensor");
			board[pos.x][pos.y].setBackground(Color.orange);
			sensorPosition = pos;
		} else {
			message.setOpaque(true);
			message.setBackground(Color.red);
		}
	}


}
