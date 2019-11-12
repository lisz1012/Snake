import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MyKeyListener implements KeyListener {
	private SnakeFrame snakeFrame;
	
	public MyKeyListener(SnakeFrame snakeFrame) {
		this.snakeFrame = snakeFrame;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			if (snakeFrame.snake.dir == Direction.UP || snakeFrame.snake.dir == Direction.DOWN) {
				snakeFrame.snake.dir = Direction.LEFT;
			}
			break;
		case KeyEvent.VK_RIGHT:
			if (snakeFrame.snake.dir == Direction.UP || snakeFrame.snake.dir == Direction.DOWN) {
				snakeFrame.snake.dir = Direction.RIGHT;
			}
			break;
		case KeyEvent.VK_UP:
			if (snakeFrame.snake.dir == Direction.LEFT || snakeFrame.snake.dir == Direction.RIGHT) {
				snakeFrame.snake.dir = Direction.UP;
			}
			break;
		case KeyEvent.VK_DOWN:
			if (snakeFrame.snake.dir == Direction.LEFT || snakeFrame.snake.dir == Direction.RIGHT) {
				snakeFrame.snake.dir = Direction.DOWN;
			}
			break;
		default:
			break;
		}
	}

}
