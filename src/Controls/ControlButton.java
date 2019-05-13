package Controls;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Data.Data;
import view.GUI;

public class ControlButton implements ActionListener{
	
	Control ctrl;
	GUI gui;
	Data data;

	public ControlButton(Control control) {
		ctrl= control;
		data= ctrl.getData();
		gui= ctrl.getGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("it hit");
		System.out.println("" + e.getActionCommand());

		if (e.getActionCommand().equals("start")) {
			System.out.println("wechsle sicht von main zu ground");
			gui.changeView(gui.getGroundView());
			data.hasStarted = true;

		} else if (e.getActionCommand().equals("exit")) {
			System.out.println("wechsle sicht von ground zu main");
			data.hasStarted = false;
			gui.changeView(gui.getMainmenu());
			data.clearMap();
			gui.getGroundView().resetButton();
		} else if (e.getActionCommand().equals("save")) {
			System.out.println("wechsle sicht von optionen zu mainmenu");
			gui.changeView(gui.getMainmenu());
			gui.getOptionMenu().save();
			data.clearMap();
		} else if (e.getActionCommand().equals("optionen")) {
			gui.changeView(gui.getOptionMenu());
			data.clearMap();
		} else if (e.getActionCommand().equals("pause")) {
			if (data.hasStarted == false)
				data.hasStarted = true;
			else if (data.hasStarted == true)
				data.hasStarted = false;
		} else if (e.getActionCommand().equals("attack")) {
			try {
				data.getTownlist().get(data.getactiveTownIndex()).createAngriffsArmee();
			} catch (Exception e1) {

			}
		}

		else if (e.getActionCommand().equals("autoAttack")) {
			try {
				data.getTownlist().get(data.getactiveTownIndex()).boolEnableAutoAttack();
			} catch (Exception e1) {

			}
		} else if (e.getActionCommand().equals("autoDefend")) {
			try {
				data.getTownlist().get(data.getactiveTownIndex()).boolEnableAutoDefend();
			} catch (Exception e1) {

			}
		} else if (e.getActionCommand().equals("standStill")) {
			try {
				data.getTownlist().get(data.getactiveTownIndex()).boolEnablestandStill();
			} catch (Exception e1) {

			}
		}
		
		
		else {
			String Townindex = e.getActionCommand();
			int townindexnumber = Integer.parseInt(Townindex);

			data.setactiveTownIndex(townindexnumber);

		}

	}

	public void setActionListener(JButton button) {

		button.addActionListener(this);

	}

}
