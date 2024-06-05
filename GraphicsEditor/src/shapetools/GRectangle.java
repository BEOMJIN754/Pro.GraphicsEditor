package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

import shapetools.GShape.EDrawingStyle;

public class GRectangle extends GShape {

	public GRectangle() {
		super(EDrawingStyle.e2PStyle, new Rectangle());
	}

	public GRectangle clone() {
		return new GRectangle();
	}

	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;

		RectangularShape shape = (RectangularShape) this.shape;
		shape.setFrame(x1, y1, x2 - x1, y2 - y1);
		graphics2D.draw(shape);

	}

	
}
