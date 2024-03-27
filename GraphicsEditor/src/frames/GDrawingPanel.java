package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import shapetools.GShape;

public class GDrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private boolean bDrawing;
	private Vector<GShape> shapes;
	private GShape shapeTool;
	private GShape currentShape;

	public GDrawingPanel() {
		this.setBackground(Color.gray);
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
		
		this.bDrawing = false;

		this.shapes = new Vector<GShape>();
	}

	public void setShapeTool(GShape shapeTool) {
		// TODO Auto-generated method stub
		this.shapeTool = shapeTool;

	}

	public void paint(Graphics g) {
		System.out.println("paint");
		for (GShape shape : shapes) {
			shape.draw(g);
		}
	}

	private class MouseEventHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("mouseClicked");
			
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
		
		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("mousePressed");
			if(!bDrawing) {
				currentShape = shapeTool.clone();
				currentShape.setP1(e.getX(), e.getY());
				bDrawing = true;
			}
					}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("mouseReleased");
			
			if(bDrawing) {
				shapes.add(currentShape); 
				bDrawing = false;
			}

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			System.out.println("mouseDragged");
			if(bDrawing) {
				currentShape.setP2(e.getX(), e.getY());
				currentShape.drag(getGraphics());
				}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			System.out.println("mouseEntered");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			System.out.println("mouseExited");
		}

	}

}
