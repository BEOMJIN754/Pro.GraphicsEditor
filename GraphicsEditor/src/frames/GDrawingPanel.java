package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import javax.swing.JPanel;

import shapetools.GShape;
import shapetools.GShape.EDrawingStyle;

public class GDrawingPanel extends JPanel {
	//attributes
	private static final long serialVersionUID = 1L;

	private enum EDrawingState {
		eIdle, e2PState, eNPState
	}

	private EDrawingState eDrawingState;
	
	//component 부품
	private Vector<GShape> shapes;
	private GShape shapeTool;
	private GShape currentShape;
	
	//constructors
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

	//setters and getters 
	public void setShapeTool(GShape shapeTool) {
		// TODO Auto-generated method stub
		this.shapeTool = shapeTool;

	}

	
	//methods
	public void open() {
	    try {
	    	File file = new File("output");
	        ObjectInputStream objectInputStream = new ObjectInputStream(
	                new BufferedInputStream(new FileInputStream(file))); // 이미 생성한 File 객체 사용
	        this.shapes = (Vector<GShape>) objectInputStream.readObject();
	        this.repaint(); // 화면 갱신
	        objectInputStream.close(); 
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace(); 
	    }
	}
	public void save() {
	    try {
	    	File file = new File("output");
	        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
	                new BufferedOutputStream(
	                		new FileOutputStream(file)));
	        objectOutputStream.writeObject(this.shapes);
	        objectOutputStream.close(); 
	    } catch (IOException e) {
	        e.printStackTrace(); 
	    }
	}
		
	
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
		currentShape.addPoint(x, y);
		shapes.add(currentShape);
	}

	private class MouseEventHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount()==1) {
			if (eDrawingState == EDrawingState.eIdle) {
				if (shapeTool.getEDrawingStyle() == EDrawingStyle.eNPStyle) {
					startDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.eNPState;
				}
			}else if (eDrawingState == EDrawingState.eNPState) {
				continueDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eNPState;
			}
		}else if(e.getClickCount()==2) {
			if (eDrawingState == EDrawingState.eNPState) {
				stopDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eIdle;
			}
		}
		}

	

		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.eNPState) {
				keepDrawing(e.getX(), e.getY());
				eDrawingState = EDrawingState.eNPState;
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (eDrawingState == EDrawingState.eIdle) {
				if (shapeTool.getEDrawingStyle() == EDrawingStyle.e2PStyle) {
					startDrawing(e.getX(), e.getY());
					eDrawingState = EDrawingState.e2PState;
				}

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
