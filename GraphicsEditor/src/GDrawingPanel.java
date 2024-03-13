import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GDrawingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public GDrawingPanel() {
		this.setBackground(Color.gray);
		MouseEventHandler mouseEventHandler = new MouseEventHandler();
		this.addMouseListener(mouseEventHandler);
		this.addMouseMotionListener(mouseEventHandler);
	}
	
	public void paint(Graphics g) {
	}
	
	public void draw(int x ,int y) {
		Graphics graphics = this.getGraphics();
		graphics.drawRect(x, y, 20, 30);
	}
	private class MouseEventHandler implements MouseListener, MouseMotionListener{


		@Override
		public void mouseClicked(MouseEvent e) {
		//	System.out.println(this.getClass().getEnclosingMethod());
		}

		@Override
		public void mousePressed(MouseEvent e) {
		//	System.out.println(this.getClass().getEnclosingMethod());
			draw(e.getX(), e.getY());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		//	System.out.println(this.getClass().getEnclosingMethod());
			draw(e.getX(), e.getY());
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		//	System.out.println(this.getClass().getEnclosingMethod());
			draw(e.getX(), e.getY());
			draw(e.getX(), e.getY());
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		//	System.out.println(this.getClass().getEnclosingMethod());
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
		//	System.out.println(this.getClass().getEnclosingMethod());
		}

		@Override
		public void mouseExited(MouseEvent e) {
		//	System.out.println(this.getClass().getEnclosingMethod());
		}
		
	}
	
}
