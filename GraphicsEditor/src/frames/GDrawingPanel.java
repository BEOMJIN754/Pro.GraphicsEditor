package frames;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import shapetools.GShape;
import shapetools.GShape.EAnchors;
import shapetools.GShape.EDrawingStyle;

public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;

	private enum EDrawingState {
		eIdle, e2PState, eNPState, eTransformation
	}

	private enum EAnchor {
		eMove, eResize, eRotate
	}

	private EDrawingState eDrawingState;
//	private enum ETransformation{
//		eDraw,
//		eMove,
//		eResize,
//		eRotate
//	}
//	private ETransformation eTransformation;

	// component 부품
	private Vector<GShape> shapes;
	private GShape shapeTool;
	private GShape currentShape;

	// constructors
	public GDrawingPanel() {
		this.setBackground(Color.gray);
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);

		this.eDrawingState = EDrawingState.eIdle;

		this.shapes = new Vector<GShape>();
	}

	public void initialize() {
		// TODO Auto-generated method stub

	}

	// setters and getters
	public void setShapeTool(GShape shapeTool) {
		// TODO Auto-generated method stub
		this.shapeTool = shapeTool;

	}

	public Vector<GShape> getShapes() {
		return this.shapes;
	}

	public void setShapes(Object object) {
		this.shapes = (Vector<GShape>) object;
	}

	// methods

	public void paint(Graphics graphics) {
		for (GShape shape : shapes) {
			shape.draw(graphics);
		}
	}

	private void startDrawing(int x, int y) {
		currentShape = shapeTool.clone();
		currentShape.setOrigin(x, y);
	}

	private void keepDrawing(int x, int y) {
		currentShape.movePoint(x, y);
		currentShape.drag(getGraphics());
	}

	private void continueDrawing(int x, int y) {
		currentShape.addPoint(x, y);
	}

	private void stopDrawing(int x, int y) {
		// currentShape.addPoint(x, y);
		shapes.add(currentShape);
		currentShape.setSelected(getGraphics());
	}
//
//	private EAnchor onShape(int x,int y ) {
//		EAnchor eAnchor = null;
//		for(GShape shape:this.shapes) {
//			boolean isShape  = shape.onShape(x,y);
//			if(isShape) {
//				return shape;
//			}
//		}
//		return null;
//	}

	private GShape onShape(int x, int y) {
		for (GShape shape : this.shapes) {
			boolean isShape = shape.onShape(x, y);
			if (isShape) {
				System.out.println("sdf");
				return shape;
			}
		}
		return null;
	}

	private void changeCursor(int x, int y) {
		GShape shape = this.onShape(x, y);
		if (shape == null) {
			this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		} else {
			this.setCursor(shape.getCursor());
			System.out.println("123");
		}
	}

	private class MouseEventHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {
				if (eDrawingState == EDrawingState.eIdle) {
					if (shapeTool.getEDrawingStyle() == EDrawingStyle.eNPStyle) {
						startDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.eNPState;
					}
				} else if (eDrawingState == EDrawingState.eNPState) {
					continueDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNPState;
				}
			} else if (e.getClickCount() == 2) {
				if (eDrawingState == EDrawingState.eNPState) {
					stopDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.eIdle;
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				changeCursor(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.eNPState) {
				keepDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eNPState;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				currentShape = onShape(e.getX(), e.getY());
				// 선택된 도형이 없을 때
				if (currentShape == null) {
					// 2개 점으로 그리는 것들
					if (shapeTool.getEDrawingStyle() == EDrawingStyle.e2PStyle) {
						startDrawing(e.getX(), e.getY());
						eDrawingState = EDrawingState.e2PState;
					} else {
						// ploygon drawing (click에서 진행중임)

					}
					// 도형 내 클릭했을 때 MOVE
				} else {
					if (currentShape.getSelectedAnchor() == EAnchors.eMM) {
						currentShape.startMove(getGraphics(), e.getX(), e.getY());
					} else if (currentShape.getSelectedAnchor() == EAnchors.eRR) {

					} else {
						currentShape.startResize(getGraphics(), e.getX(), e.getY());
					}
					eDrawingState = EDrawingState.eTransformation;
				}

			}
		}
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2PState) {
				keepDrawing(e.getX(), e.getY());
			} else if (eDrawingState == EDrawingState.eTransformation) {
				if (currentShape.getSelectedAnchor() == EAnchors.eMM) {
					currentShape.keepMove(getGraphics(), e.getX(), e.getY());
				} else if (currentShape.getSelectedAnchor() == EAnchors.eRR) {

				} else {
					currentShape.keepResize(getGraphics(), e.getX(), e.getY());
				}
				eDrawingState = EDrawingState.eIdle;
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.e2PState) {
				stopDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			} else if (eDrawingState == EDrawingState.eTransformation) {
				if (currentShape.getSelectedAnchor() == EAnchors.eMM) {
					currentShape.stopMove(getGraphics(), e.getX(), e.getY());
				} else if (currentShape.getSelectedAnchor() == EAnchors.eRR) {

				} else {
					currentShape.stopResize(getGraphics(), e.getX(), e.getY());
				}
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
