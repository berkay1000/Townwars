package view;

import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import Data.ReadPropertyFile;

public class JPanelOptionMenu extends JPanel {
	GUI gui;

	JSlider FactionSlider,TownAmountSlider;
	int FactionAmounts, townamounts;
	public ReadPropertyFile rpf = new ReadPropertyFile();
	
	public JPanelOptionMenu(GUI inputgui) {
		gui=inputgui;

		this.setLayout(null);
		
		 FactionSlider = new JSlider();
		FactionSlider.setBounds(30, 150, 300, 30);
		FactionSlider.setMinimum(2);
		FactionSlider.setMaximum(9);
		FactionSlider.setValue(9);
		
		 TownAmountSlider = new JSlider();
			TownAmountSlider.setBounds(30, 250, 300, 30);
			TownAmountSlider.setMinimum(15);
			TownAmountSlider.setMaximum(250);
			TownAmountSlider.setValue(100);
		
		
		
		JButton save = new JButton("save");
		save.setBounds(10, 10, 100, 50);
		save.setVisible(true);
		save.setActionCommand("save");
		save.addActionListener(gui.ctrl.cb);
		
		
		this.add(save);
		this.add(FactionSlider);
		this.add(TownAmountSlider);
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		
		g.drawString("FactionAmount", 130, 130);
		g.drawString(""+FactionSlider.getValue() , 220, 130);
		
		g.drawString("TownAmount", 130, 230);
		g.drawString(""+TownAmountSlider.getValue() , 220, 230);
		FactionAmounts=FactionSlider.getValue();
		townamounts=TownAmountSlider.getValue();

	
		
		
		
		
	}
	public void save() {
		
		System.out.println("speichere einstellungen");
//		rpf.prop.setProperty("FactionAmounts", ""+FactionSlider.getValue());
//		rpf.prop.setProperty("townamounts",""+TownAmountSlider.getValue());
		
		rpf.storePropertyFile();
		System.out.println(rpf.prop.getProperty("FactionAmounts"));
		
		
	}

}
