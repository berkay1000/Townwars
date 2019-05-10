package Controls;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.*;

import javax.swing.JButton;

import com.sun.prism.paint.Color;

import Data.Data;
import townwars.Angriffsarmee;
import townwars.Faction;
import townwars.Town;
import view.GUI;

public class Control implements ActionListener, MouseListener, MouseMotionListener, KeyListener {

	GUI gui;
	Data data;

	public Control() {

		
	}

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

	public void setGUIDATA(GUI g, Data d) {
		gui = g;
		data = d;
	}

	public void setActionListener(JButton button) {

		button.addActionListener(this);

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("setze mouseReleasePosition");
		Point point = MouseInfo.getPointerInfo().getLocation();

		data.setMouseReleasedposition(point);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
//		System.out.println("setze mouseposition");
		Point point = MouseInfo.getPointerInfo().getLocation();

		data.setMouseposition(point);

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
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

	@Override
	public void keyReleased(KeyEvent e) {
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

}
