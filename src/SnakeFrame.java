import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeFrame extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	private static final Random RANDOM = new Random();
	
	public List<GameObject> gameObjects = new ArrayList<>();
	public Snake snake = new Snake(this);
	public Node food = null;
	private boolean running = true;
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public SnakeFrame() throws HeadlessException {
		setSize(WIDTH * Node.SIDE_LENGTH, HEIGHT * Node.SIDE_LENGTH);
		setResizable(false);
		setTitle("Snake");
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		addKeyListener(new MyKeyListener(this));
		initGameObjects();
	}

	public void generateFood() {
		int x = RANDOM.nextInt(WIDTH) * Node.SIDE_LENGTH;
		int y = RANDOM.nextInt(HEIGHT) * Node.SIDE_LENGTH;
		Node food = new Node(x, y);
		while (snake.collideWith(food)) {
			x = RANDOM.nextInt(WIDTH) * Node.SIDE_LENGTH;
			y = RANDOM.nextInt(HEIGHT) * Node.SIDE_LENGTH;
			food = new Node(x, y);
		}
		this.food = food;
	}

	@Override
	public void paint(Graphics g) {
		//super.paint(g);
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH * Node.SIDE_LENGTH, HEIGHT * Node.SIDE_LENGTH);
		g.setColor(c);
		
		drawAllGameObjects(g);
		if (!running) {
			drawGameOver();
		}
	}
	
	private void drawAllGameObjects(Graphics g) {
		for (GameObject gameObject : gameObjects) {
			gameObject.paint(g);
		}
		if (food != null) {
			food.paint(g);
		}
	}
	
	private void initGameObjects() {
		gameObjects.add(snake);
		generateFood();
		//gameObjects.add(food);
	}
	
	public void drawGameOver() {
		Graphics g = getGraphics();
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.drawString("Game Over!", 50, 50);
		g.setColor(c);
	}
}
