
import graphics.Picture;



public class lifeGame {

	public static void main(String[] args) {
		int x = 200;
		int y = 300;
		Picture picture = new Picture( 2 * x, 2 * y );
		Board board = new Board ( x, y );
		board.generateRandom();
		board.drawCells(picture);
		board.run(picture);
	}

}
