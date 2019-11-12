import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

public class Snake extends GameObject {
	private static final int INIT_X = 10;
	private static final int INIT_Y = 10;
	private static final int INIT_LENGTH = 3;
	private static final Direction INIT_DIRECTION = Direction.RIGHT;
	
	private List<Node> nodes = new LinkedList<>();
	public Direction dir = INIT_DIRECTION;
	private SnakeFrame snakeFrame;
	
	public Snake(SnakeFrame snakeFrame) {
		this.snakeFrame = snakeFrame;
		for (int i = 0; i < INIT_LENGTH; i++) {
			nodes.add(new Node((INIT_X - i) * Node.SIDE_LENGTH, INIT_Y * Node.SIDE_LENGTH));
		}
	}
	
	@Override
	void paint(Graphics g) {
		//nodes.forEach(n->paint(g));
		for (Node node : nodes) {
			node.paint(g);
		}
		update(g);
	}
	
	@Override
	void update(Graphics g) {
		if (eat(snakeFrame.food)) {
			nodes.add(0, new Node(snakeFrame.food.x, snakeFrame.food.y));
			//snakeFrame.gameObjects.remove(snakeFrame.food);
			snakeFrame.generateFood();
		} else {
			moveLastNodeToHead();
		}
	}

	private boolean eat(Node food) {
		if (food == null) {
			return false;
		}
		Node head = nodes.get(0);
		if (dir == Direction.LEFT && (food.x == head.x - Node.SIDE_LENGTH || food.x == (SnakeFrame.WIDTH - 1) * Node.SIDE_LENGTH && head.x == 0) && food.y == head.y) {
			return true;
		} else if (dir == Direction.UP && (food.y == head.y - Node.SIDE_LENGTH || food.y == (SnakeFrame.HEIGHT - 1) * Node.SIDE_LENGTH && head.y == 0) && food.x == head.x) {
			return true;
		} else if (dir == Direction.RIGHT && (food.x == head.x + Node.SIDE_LENGTH || food.x == 0 && head.x == (SnakeFrame.WIDTH - 1) * Node.SIDE_LENGTH) && food.y == head.y) {
			return true;
		} else if (dir == Direction.DOWN && (food.y == head.y + Node.SIDE_LENGTH || food.y == 0 && head.y == (SnakeFrame.HEIGHT - 1) * Node.SIDE_LENGTH) && food.x == head.x) {
			return true;
		}
		return false;
	}

	private void moveLastNodeToHead() {
		Node head = nodes.get(0);
		Node last = nodes.remove(nodes.size() - 1);
		nodes.add(0, last);
		switch (dir) {
		case LEFT:  last.setX((head.getX() - Node.SIDE_LENGTH) < 0 ? (SnakeFrame.WIDTH - 1) * Node.SIDE_LENGTH : head.getX() - Node.SIDE_LENGTH); last.setY(head.getY()); break;
		case UP: last.setY((head.getY() - Node.SIDE_LENGTH) < 0 ? (SnakeFrame.HEIGHT - 1) * Node.SIDE_LENGTH : head.getY() - Node.SIDE_LENGTH); last.setX(head.getX()); break;
		case RIGHT: last.setX((head.getX() + Node.SIDE_LENGTH) % (SnakeFrame.WIDTH * Node.SIDE_LENGTH)); last.setY(head.getY()); break;
		case DOWN: last.setY((head.getY() + Node.SIDE_LENGTH) % (SnakeFrame.HEIGHT * Node.SIDE_LENGTH)); last.setX(head.getX()); break;
		default: break;
		}
	}
		
	@Override
	protected boolean collideWith(GameObject o) {
		for (Node node : nodes) {
			if (node.collideWith(o)) {
				return true;
			}
		}
		return false;
	}
}
