package assignment5;

import java.awt.Color;
import java.util.Random;

public class PoolBall {
	private int x;
	private int y;
	private Color color;
	private Random rand = new Random();
	private final int diameter = 50;
	private int x_direction;
	private int y_direction;
	
	
	public PoolBall() {
		this.x = rand.nextInt(1300)+50;
		this.y = rand.nextInt(600)+50;
		this.x_direction = rand.nextInt(2)-1;
		this.y_direction = rand.nextInt(2)-1;
		
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

	public int getx_direction() {
		return x_direction;
	}
	public int gety_direction() {
		return y_direction;
	}
	public void setx_direction(int xdir) {
		this.x_direction = xdir;
		
	}
	public void sety_direction(int ydir) {
		this.y_direction = ydir;
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
	public int getDiameter() {
		return diameter;
	}
}
