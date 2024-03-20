package shapetools;
import java.awt.Graphics;

public class GRectangleTool extends GShapeTool {

	private Graphics graphics;
	
	public void draw(int x, int y, int w , int h , Graphics graphics) {
		this.graphics = graphics;
		
		graphics.drawRect(x, y, w, h);
	}
}
