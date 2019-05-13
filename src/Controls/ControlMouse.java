package Controls;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ControlMouse implements  MouseListener, MouseMotionListener{
	Control ctrl;

	public ControlMouse(Control control) {
		ctrl= control;
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
	
			ctrl.getData().setMouseReleasedposition(point);
	
		}

	@Override
		public void mouseDragged(MouseEvent e) {
	//		System.out.println("setze mouseposition");
			Point point = MouseInfo.getPointerInfo().getLocation();
	
			ctrl.data.setMouseposition(point);
	
		}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}

}
