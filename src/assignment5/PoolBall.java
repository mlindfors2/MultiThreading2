package assignment5;

import java.awt.Color;
import java.util.Random;

public class PoolBall {
	private int x;
	private int y;
	private Color color;
	private Random rand = new Random();

	public PoolBall() {
		this.x = rand.nextInt(1300);
		this.y = rand.nextInt(600);
	}

	public PoolBall(int x, int y) {
		this.x = x;
		this.y = y;
		color = Color.WHITE;
	}

	public PoolBall(int x, int y, Color color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
}
