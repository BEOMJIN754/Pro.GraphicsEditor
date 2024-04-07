package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import shapetools.GShape;
import shapetools.GShape.EDrawingStyle;

public class GDrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private enum EDrawingState {
		eIdle, e2PState, eNPState
	}

	private EDrawingState eDrawingState;
	private Vector<GShape> shapes;
	private GShape shapeTool;
	private GShape currentShape;

	public GDrawingPanel() {
		this.setBackground(Color.gray);
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);

		this.eDrawingState = EDrawingState.eIdle;

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

	private void startDrawing(int x, int y) {
		currentShape = shapeTool.clone();
		currentShape.setP1(x, y);
	}

	private void keepDrawing(int x, int y) {
		currentShape.setP2(x, y);
		currentShape.drag(getGraphics());
	}

	private void stopDrawing(int x, int y) {

	}

	private class MouseEventHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseMoved(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("mousePressed");
			if (eDrawingState == EDrawingState.e2PState) {
				if (shapeTool.getEDrawingStyle() == EDrawingStyle.e2PStyle) {
					startDrawing(e.getX(), ABORT);
				}
				eDrawingState = EDrawingState.e2PState;
			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2PState) {
				keepDrawing(e.getX(), e.getY());
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("mouseReleased");

			if (eDrawingState == EDrawingState.e2PState) {
				stopDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}

		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

}
