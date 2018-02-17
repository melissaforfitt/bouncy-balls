import javafx.application.*;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.*;
import javafx.animation.*;
import java.util.*;

public class Bounce extends Application {
	/** Boilerplate method to get JavaFX going */
	public static void main(String[] args) {
		launch(args);
	}

	/** Width of our screen in pixels */
	private final int width = 1024;
	/** Height of our screen in pixels */
	private final int height = 768;
	/** Canvas that we will draw things on */
	private Canvas canvas = new Canvas(width, height);
	/** Time when the last frame was drawn, in nanoseconds */
	private long last = 0;

	private Ball greenBall;
	private Ball pinkBall;
	private Ball blueBall;

	/** Set up anything that is needed later */
	private void setup() {

		pinkBall = new Ball(512, 394, width, height, Color.PINK);
		greenBall = new Ball(512, 394, width, height, Color.GREEN);
		blueBall = new Ball(512, 394, width, height, Color.BLUE);
		
	}

	/**
	 * Draw a frame.
	 * 
	 * @param elapsed
	 *            Time in seconds since the last frame was drawn.
	 */
	private void draw(GraphicsContext gc, double elapsed) {

		pinkBall.update();
		greenBall.update();
		blueBall.update();

		gc.setFill(pinkBall.colour());
		gc.fillOval(pinkBall.getX(), pinkBall.getY(), 30, 30);

		gc.setFill(greenBall.colour());
		gc.fillOval(greenBall.getX(), greenBall.getY(), 30, 30);

		gc.setFill(blueBall.colour());
		gc.fillOval(blueBall.getX(), blueBall.getY(), 30, 30);
		
		pinkBall.collide(greenBall);
		pinkBall.collide(blueBall);
		
		greenBall.collide(pinkBall);
		greenBall.collide(blueBall);
		
		blueBall.collide(pinkBall);
		blueBall.collide(greenBall);
		
		pinkBall.update();
		greenBall.update();
		blueBall.update();
		
	}

	/** Set up the window and the timer */
	public void start(Stage stage) {
		/* Make the JavaFX scene */
		Group root = new Group();
		Scene scene = new Scene(root, width, height, Color.WHITE);
		root.getChildren().add(canvas);
		stage.setScene(scene);
		stage.show();

		setup();

		/* Set up a timer to update the screen every frame */
		new AnimationTimer() {
			public void handle(long now) {
				if (last == 0) {
					last = now;
				}

				/* Get a context and clear the window */
				GraphicsContext gc = canvas.getGraphicsContext2D();
				gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

				/* Update things */
				draw(gc, (now - last) * 1e-9);
				last = now;

			}
		}.start();
	}
}
