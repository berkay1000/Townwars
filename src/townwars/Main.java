package townwars;

import javax.swing.JFrame;

import Controls.Control;
import Data.Data;
import view.GUI;

public class Main {

	public static void main(String[] args) {

		// erstelle Objekte die für MVC-Pattern benötigt werden
		Control ctrl = new Control();
		Data data = new Data();
		GUI gui = new GUI();

		// verbinde mvc
		ctrl.setGUIDATA(gui, data);
		data.setCTRLGUI(ctrl, gui);
		gui.setDATACTRL(data, ctrl);

		// erstelle unterliegenden Jpanes wie Menü und groundview/schlachtfeld
		gui.createViews();
		gui.setpanetomain();// zuerst kriegt mainmenü den fokus

		gui.setContentPane(gui.getCurrentpane());
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(Integer.parseInt(data.rpf.prop.getProperty("framewidth")),
				Integer.parseInt(data.rpf.prop.getProperty("frameheight")));
		gui.setVisible(true);

		gui.addMouseListener(ctrl);
		gui.addMouseMotionListener(ctrl);

		int zaehler = 0;

		while (true) { // true soll später zu pause werden asd

			try {
				Thread.sleep(17); // 17
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

//Die datenquelle wird immer wieder updated
			data.update();

			gui.getCurrentpane().repaint();
		}

	}

}
