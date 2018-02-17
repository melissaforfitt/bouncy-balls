import java.util.Random;

import javafx.scene.paint.Paint;

public class Ball {

	private int x;
	private int y;
	private int width;
	private int height;
	private Paint colour;
	Random r = new Random();
	private double speedX = r.nextInt(4) + 1;
	private double speedY = r.nextInt(4) + 1;

	public Ball(int x, int y, int width, int height, Paint colour) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.colour = colour;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	public Paint colour() {
		return colour;
	}

	public void update() {

		if (x > height || x < 0) {
			// Change direction every time ball hits right/left wall
			speedX = -speedX;

		}

		if (y > height || y < 0) {
			// Change direction every time ball hits top/bottom wall
			speedY = -speedY;
		}

		x += speedX;
		y += speedY;

	}

	public void collide(Ball a) {

		// If balls collide, make them go opposite ways
		if (this.x == a.x && this.y == a.y) {
			speedX = -speedX;
			speedY = -speedY;
		}
	}

}
