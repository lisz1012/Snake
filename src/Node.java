import java.awt.Color;
import java.awt.Graphics;

public class Node extends GameObject {
	public static final int SIDE_LENGTH = 10;
	//private Direction dir = Direction.RIGHT;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLACK);
		g.fillRect(x, y, SIDE_LENGTH, SIDE_LENGTH);
		g.setColor(c);
	}
	@Override
	void update(Graphics g) {
		
	}	
}
