import javax.swing.JMenuBar;

public class GMenuBar extends JMenuBar {

	private static final long serialVersionUID = 9000032843023099413L;

	public GFileMenu fileMenu;

	public GMenuBar() {

		this.fileMenu = new GFileMenu("file");

		this.add(this.fileMenu);
	}
}