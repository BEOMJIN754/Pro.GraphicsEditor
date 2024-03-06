import javax.swing.JFrame;

public class GMainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private GMenuBar menuBar;
	public GMainFrame() {
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.setLocation(200, 100);
		this.setSize(400, 600);
	}
}
