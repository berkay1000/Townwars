package Controls;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;

import Data.Data;
import townwars.Angriffsarmee;
import townwars.Town;
import view.GUI;

public class Control  {

	GUI gui;
	Data data;
public ControlButton cb;
public ControlKey ck;
public ControlMouse cm;
public ControlChangeListener cc;
	public Control() {
		
	}
	
	public void createControls() {
		 cb= new ControlButton(this);
		 ck= new ControlKey(this);
		 cm= new ControlMouse(this);
		cc= new ControlChangeListener(this);
	}

	

	

	public void setGUIDATA(GUI g, Data d) {
		gui = g;
		data = d;
	}

	

	
	public void keyPressed(KeyEvent e) {
		System.out.println("es wurde gedrückt");
		Town targetTown;
		targetTown=data.getTownlist().get(0).getTargetTownNearestToMouse(MouseInfo.getPointerInfo().getLocation());
		
		Angriffsarmee aa=new Angriffsarmee(	new Point(300,300),data.getFactionList().get(2) , targetTown);
		
		for(int i=0;i <10000;i++) {
		aa.addToArmy();
		}
		
		data.getAngriffsarmeelist().add(aa);
		
			

		
	}

	
	public void keyReleased(KeyEvent e) {
		
		
		
	}

	
	public void keyTyped(KeyEvent e) {
		
		
	}

	public Data getData() {
		return data;
	}

	public GUI getGui() {
		return gui;
	}

}
