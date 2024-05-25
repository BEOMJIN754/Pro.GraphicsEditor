package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.io.Serializable;

import global.Constants;

public class GPolygon extends GShape {

	private int xPoints[];
	private int yPoints[];
	private int nPoints;
	
	public GPolygon() {
		super(EDrawingStyle.eNPStyle, new Polygon());
	}
	public GPolygon clone() {
		return new GPolygon();
	}
	
	
	@Override
	public void drag(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setXORMode(graphics2D.getBackground());
		Polygon polygon = (Polygon)this.shape;
		
		//erase old shape 
		graphics.drawLine(
				polygon.xpoints[polygon.npoints-2], polygon.ypoints[polygon.npoints-2],
				polygon.ypoints[polygon.npoints-1], polygon.ypoints[polygon.npoints-1]
				);
		
		polygon.xpoints[polygon.npoints-1] = x2;
		polygon.ypoints[polygon.npoints-1] = y2;
		
		graphics.drawLine(
				polygon.xpoints[polygon.npoints-2], polygon.ypoints[polygon.npoints-2],
				polygon.xpoints[polygon.npoints-1], polygon.ypoints[polygon.npoints-1]
				);

	}
	@Override
	public void draw(Graphics graphics) {
		//draw new shape 
		Polygon polygon = (Polygon)this.shape;
		
		graphics.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
		
	}
	@Override
	public void setOrigin(int x,int y) {
		
		Polygon polygon = (Polygon)this.shape;
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}

	@Override
	public void addPoint(int x,int y) {
		Polygon polygon = (Polygon)this.shape;
		polygon.addPoint(x, y);
}
}