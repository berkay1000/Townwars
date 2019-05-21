package Controls;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Data.Data;
import view.GUI;

public class ControlChangeListener implements ChangeListener {

	
	Control ctrl;
	GUI gui;
	Data data;
	
	
	public ControlChangeListener(Control control) {
		ctrl = control;
		data = ctrl.getData();
		gui = ctrl.getGui();
	}

	public void stateChanged(ChangeEvent e) {
		data.setAttackAmountFromSlider(gui.getGroundView().getSliderValue());
		
	}

}
