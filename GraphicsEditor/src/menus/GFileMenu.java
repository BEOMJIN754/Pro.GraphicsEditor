package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import frames.GDrawingPanel;

public class GFileMenu extends JMenu {

	private static final long serialVersionUID = 1L;

	private GDrawingPanel drawingpanel;
	public void associate(GDrawingPanel drawingPanel) {
		this.drawingpanel = drawingPanel;
	}
	public GFileMenu(String s) {
		super(s);
		ActionHandler actionHandler = new ActionHandler();
		
		JMenuItem menuItemOpen = new JMenuItem("open");
		menuItemOpen.setActionCommand("open");
		menuItemOpen.addActionListener(actionHandler);
		this.add(menuItemOpen);
		

		JMenuItem menuItemSave = new JMenuItem("save");
		menuItemSave.setActionCommand("save");
		menuItemSave.addActionListener(actionHandler);
		this.add(menuItemSave);
	}

	private class ActionHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("open")){
				drawingpanel.open();
			}else if(e.getActionCommand().equals("save")) {
				drawingpanel.save();
			}
				
		}
		
	}
}

