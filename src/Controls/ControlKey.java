package Controls;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Data.Data;
import townwars.Angriffsarmee;
import townwars.Town;

public class ControlKey implements KeyListener {
	
	Control ctrl;
	Data data;

	public ControlKey(Control control) {
		ctrl= control;
		data= ctrl.data;
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
	
	

}
