package view;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;

public class JPanelMainMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton buttonstart, buttonbeenden, buttonOptionen;
	GUI gui;

	public JButton getButtonstart() {
		return buttonstart;
	}

	public void setButtonstart(JButton buttonstart) {
		this.buttonstart = buttonstart;
	}

	public JButton getButtonbeenden() {
		return buttonbeenden;
	}

	public void setButtonbeenden(JButton buttonbeenden) {
		this.buttonbeenden = buttonbeenden;
	}

	public JButton getButtonOptionen() {
		return buttonOptionen;
	}

	public void setButtonOptionen(JButton buttonOptionen) {
		this.buttonOptionen = buttonOptionen;
	}

	public JPanelMainMenu(GUI g) {
		gui = g;
		buttonstart = new JButton("start");
		buttonbeenden = new JButton("beenden");
		buttonOptionen = new JButton("Optionen");
		this.setLayout(null);
		buttonstart.setBounds(200, 100, 100, 50);
		buttonbeenden.setBounds(200, 300, 100, 50);
		buttonOptionen.setBounds(200, 500, 100, 50);

		this.add(buttonstart);
		this.add(buttonbeenden);
		this.add(buttonOptionen);
		buttonstart.setActionCommand("start");
		buttonbeenden.setActionCommand("beenden");
		buttonOptionen.setActionCommand("optionen");

		gui.ctrl.cb.setActionListener(buttonstart);
		gui.ctrl.cb.setActionListener(buttonbeenden);
		gui.ctrl.cb.setActionListener(buttonOptionen);
		buttonstart.setVisible(true);
		buttonbeenden.setVisible(true);
		buttonOptionen.setVisible(true);

	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(50, 50, 100, 100);
		//System.out.println("paint von mainview wurde ausgelöst");

	}

}
