
public class Main {

	public static void main(String[] args) {
		SnakeFrame snakeFrame = new SnakeFrame();
		while (true) {
			snakeFrame.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
