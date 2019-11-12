import java.awt.Graphics;

public abstract class GameObject {
	protected int x;
	protected int y;
	abstract void paint(Graphics g);
	abstract void update(Graphics g);
	protected boolean collideWith(GameObject o) {
		return x == o.x && y == o.y;
	}
}
