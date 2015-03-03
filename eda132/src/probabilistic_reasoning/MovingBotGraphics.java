package probabilistic_reasoning;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MovingBotGraphics implements KeyListener, MouseWheelListener {
	private JFrame frame;
	private JLabel[][] board;
	private HashSet<JLabel> previousBot;
	private JLabel message;
	private Point botPosition;
	private Point sensorPosition;
	private Point estimatePosition;
	private Color labelColor = new JLabel().getBackground();
	private JLabel estimateLabel;
	private int iter;
	private int estimate;
	private boolean run;
	private long waitTime;

	public MovingBotGraphics(int size) {
		iter = 0;
		estimate = 0;
		run = true;
		waitTime = 100;
		
		frame = new JFrame("Moving Bot simulation");
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel msgPanel = new JPanel(new GridLayout(1,2));
		JPanel matrixPanel = new JPanel(new GridLayout(size, size));

		previousBot = new HashSet<JLabel>();

		message = new JLabel("Info goes here");
		estimateLabel = new JLabel("estimates goes here");
		board = new JLabel[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				board[j][i] = new JLabel();
				board[j][i].setSize(50, 50);
				board[j][i].setBorder(BorderFactory
						.createLineBorder(Color.BLACK));
				matrixPanel.add(board[j][i]);
			}
		}

		msgPanel.add(message);
		msgPanel.add(estimateLabel);
		msgPanel.setSize(100, 20);

		mainPanel.add(msgPanel, BorderLayout.NORTH);
		mainPanel.add(matrixPanel, BorderLayout.CENTER);

		frame.add(mainPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.addKeyListener(this);
		frame.addMouseWheelListener(this);


		botPosition = new Point(0, 0);
		sensorPosition = new Point(0, 0);
		estimatePosition = new Point(0, 0);
	}

	public void updateView(Point botPos, Point sensorPos, Point estimatePos) {
		iter++;
		message.setOpaque(false);
		setBotPosition(botPos);
		setSensorPosition(sensorPos);
		setEstimatePos(estimatePos);
		
		if(botPos.equals(estimatePos)){
			estimate++;
		}
		
		if (sensorPos.equals(State.NOTHING)) {
			message.setText("<html>Robot: " + botPosition
					+ "<br>Sensor: Nothing<br>Estimate: " + estimatePos
					+ "</html>");

		} else {
			message.setText("<html>Robot: " + botPosition + "<br>Sensor: "
					+ sensorPosition + "<br>Estimate: " + estimatePos
					+ "</html>");
		}
		
		double p = 100*((double)estimate)/iter;
		estimateLabel.setText("<html>Correct estimates: " + p + "%<br>Wait Time: " + waitTime);
	}

	private void setEstimatePos(Point pos) {
		if (!estimatePosition.equals(botPosition)
				&& !estimatePosition.equals(sensorPosition)) {
			board[estimatePosition.x][estimatePosition.y].setText("");
			board[estimatePosition.x][estimatePosition.y]
					.setBackground(labelColor);
			board[estimatePosition.x][estimatePosition.y].setOpaque(false);
		}
		if (pos.equals(botPosition) && pos.equals(sensorPosition)) {
			board[pos.x][pos.y].setText("<html>Bot, Est <br>and Sensor</html>");
			board[pos.x][pos.y].setBackground(Color.white);
		} else if (pos.equals(botPosition)) {
			board[pos.x][pos.y].setText("<html> Bot and <br> Est </html>");
			board[pos.x][pos.y].setOpaque(true);
			board[pos.x][pos.y].setBackground(Color.cyan);
		} else if (pos.equals(sensorPosition)) {
			board[pos.x][pos.y].setText("<html> Est and <br> Sensor </html>");
			board[pos.x][pos.y].setOpaque(true);
			board[pos.x][pos.y].setBackground(Color.magenta);
		} else {
			board[pos.x][pos.y].setText("Est");
			board[pos.x][pos.y].setOpaque(true);
			board[pos.x][pos.y].setBackground(Color.blue);
		}
		estimatePosition = pos;

	}

	private void setBotPosition(Point pos) {
		HashSet<JLabel> toBeRemoved = new HashSet<JLabel>();
		for (JLabel label : previousBot) {
			int color = label.getBackground().getGreen();
			if (color <= 50) {
				toBeRemoved.add(label);
			} else {
				label.setBackground(new Color(0, color - 50, 0));
			}
		}
		if (!toBeRemoved.isEmpty()) {
			for (JLabel l : toBeRemoved) {
				l.setBackground(labelColor);
				l.setOpaque(false);
				previousBot.remove(l);
			}
		}
		board[botPosition.x][botPosition.y].setText("");
		board[pos.x][pos.y].setText("Bot");
		board[pos.x][pos.y].setOpaque(true);
		board[pos.x][pos.y].setBackground(new Color(0, 250, 0));
		previousBot.add(board[pos.x][pos.y]);
		botPosition = pos;
	}

	private void setSensorPosition(Point pos) {
		if (!sensorPosition.equals(botPosition)) {
			board[sensorPosition.x][sensorPosition.y].setText("");
			board[sensorPosition.x][sensorPosition.y].setBackground(labelColor);
			board[sensorPosition.x][sensorPosition.y].setOpaque(false);
		}
		if (pos.equals(botPosition) && pos.x >= 0 && pos.y >= 0) {
			board[pos.x][pos.y].setText("<html>Bot and<br>Sensor</html>");
			board[pos.x][pos.y].setBackground(Color.orange);
			sensorPosition = pos;
		} else if (pos.x >= 0 && pos.y >= 0) {
			board[pos.x][pos.y].setText("Sensor");
			board[pos.x][pos.y].setOpaque(true);
			board[pos.x][pos.y].setBackground(Color.red);
			sensorPosition = pos;
		} else {
			message.setOpaque(true);
			message.setBackground(Color.red);
		}
	}
	
	public void close(){
		frame.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		run = false;
		
	}
	
	public boolean run(){
		return run;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		return;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		return;
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		int time = arg0.getWheelRotation()*5;
		long tmp = waitTime + time;	
		if (tmp >= 0){
			waitTime = tmp;
		}
	}
	
	public long time(){
		return waitTime;
	}

}
