package shapetools;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;

public abstract class GShape implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum EDrawingStyle {
		e2PStyle, eNPStyle
	}

	private EDrawingStyle eDrawingStyle;

	public EDrawingStyle getEDrawingStyle() {
		return this.eDrawingStyle;
	}
	
	// int x[],int y[]
	protected Shape shape; 
	protected int x1, y1, x2, y2, ox2, oy2;
	
	//드로잉스타일 세팅

	public GShape(EDrawingStyle eDrawingStyle, Shape shape) {
		this.eDrawingStyle = eDrawingStyle;
		this.shape = shape;
		
		this.x1 = 0;
		this.y1 = 0;
		this.x2 = 0;
		this.y2 = 0;
		this.ox2 = 0;
		this.oy2 = 0;

	}



	public void draw(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.draw(shape);
	};

	public void setOrigin(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;
		
		this.x2 = x1;
		this.y2 = y1;
		this.ox2 = x1;
		this.oy2 = y1;
	}

	public void movePoint(int x2, int y2) {

		this.ox2 = this.x2;
		this.oy2 = this.y2;
		this.x2 = x2;
		this.y2 = y2;
	}

	public void addPoint(int x2, int y2) {
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public boolean onShape(int x, int y) {
		return false;
	}

	public abstract GShape clone();
	public abstract void drag(Graphics graphics);
	
	public  void startMove(Graphics graphics, int x, int y) {
		//좌표 저장
		this.setOrigin(x, y);
	};

	public  void keepMove(Graphics graphics, int x, int y) {
		this.movePoint(x, y);
	};

	public  void stopMove(Graphics graphics, int x, int y) {
		
	};

}
