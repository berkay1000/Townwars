package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

import townwars.Angriffsarmee;
import townwars.Faction;
import townwars.Soldat;
import townwars.Town;

public class JPanelGroundView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Town> guiTown;
	ArrayList<Soldat> guiSoldat;
	ArrayList<Soldat> guiSoldatfeindlich;
	ArrayList<Faction> guiFaction;
	GUI gui;
	JButton exit;
	JButton pause;
	JButton attack, autoAttack, autoDefend, standStill, build;
	ArrayList<JButton> townbutton;
	ArrayList<Angriffsarmee> guiangriffsarmee;
	JSlider attackAmountSlider;
	

	JPanelGroundView(GUI inputgui) {

		

		System.out.println("erstelle JPanel");
		townbutton = new ArrayList<JButton>();
		guiangriffsarmee = new ArrayList<Angriffsarmee>();
		guiSoldatfeindlich = new ArrayList<Soldat>();
		
		attackAmountSlider= new JSlider(JSlider.HORIZONTAL,10,120,25);
		attackAmountSlider.setBounds(1080, 500, 100, 30);
		attackAmountSlider.setMajorTickSpacing(90);
		
		attackAmountSlider.setPaintTicks(true);
		attackAmountSlider.setPaintLabels(true);
		
		

		gui = inputgui;
		this.setLayout(null);
		exit = new JButton("exit");
		exit.setBounds(10, 10, 100, 50);
		exit.setVisible(true);
		exit.setActionCommand("exit");

		pause = new JButton("pause");
		pause.setBounds(120, 10, 100, 50);
		pause.setVisible(true);
		pause.setActionCommand("pause");

		build = new JButton("build");
		build.setBounds(1060, 370, 90, 50);
		build.setVisible(true);
		build.setActionCommand("build");

		attack = new JButton("attack");
		attack.setBounds(1080, 200, 100, 50);
		attack.setVisible(true);
		attack.setActionCommand("attack");

		autoAttack = new JButton("");
		autoAttack.setBounds(1060, 270, 50, 50);
		autoAttack.setVisible(true);
		autoAttack.setActionCommand("autoAttack");

		autoDefend = new JButton("");
		autoDefend.setBounds(1120, 270, 50, 50);
		autoDefend.setVisible(true);
		autoDefend.setActionCommand("autoDefend");

		standStill = new JButton("");
		standStill.setBounds(1180, 270, 50, 50);
		standStill.setVisible(true);
		standStill.setActionCommand("standStill");

		attack.addActionListener(gui.ctrl.cb);
		exit.addActionListener(gui.ctrl.cb);
		pause.addActionListener(gui.ctrl.cb);
		autoAttack.addActionListener(gui.ctrl.cb);
		autoDefend.addActionListener(gui.ctrl.cb);
		standStill.addActionListener(gui.ctrl.cb);
		build.addActionListener(gui.ctrl.cb);
		attackAmountSlider.addChangeListener(gui.ctrl.cc);

		// add buttons to gui
		this.add(exit);
		this.add(pause);
		this.add(attack);
		this.add(autoAttack);
		this.add(autoDefend);
		this.add(standStill);
		this.add(build);
		this.addKeyListener(gui.ctrl.ck);
		this.add(attackAmountSlider);
		

		guiTown = gui.data.getTownlist();
		guiFaction = gui.data.getFactionList();
		guiangriffsarmee = gui.data.getAngriffsarmeelist();
		for (int i = 0; i < guiTown.size(); i++) {
			// System.out.println("stadtknopf wird erstellt");

			int posx = guiTown.get(i).getStadtposition().x;
			int posy = guiTown.get(i).getStadtposition().y;
			townbutton.add(new JButton());

			townbutton.get(i).setBounds(posx, posy, 32, 32);
			townbutton.get(i).setOpaque(false);
			townbutton.get(i).setContentAreaFilled(false);
			townbutton.get(i).setBorderPainted(false);
			townbutton.get(i).addActionListener(gui.ctrl.cb);
			townbutton.get(i).setActionCommand("" + i);
			this.add(townbutton.get(i));
		}
		// System.out.println("fertig mit stadtknopf");

		autoDefend.setOpaque(false);
		autoDefend.setContentAreaFilled(false);
		autoDefend.setBorderPainted(false);

		autoAttack.setOpaque(false);
		autoAttack.setContentAreaFilled(false);
		autoAttack.setBorderPainted(false);

		standStill.setOpaque(false);
		standStill.setContentAreaFilled(false);
		standStill.setBorderPainted(false);

	}

	public void paint(Graphics g) {

		BufferedImage townImage = null;
		BufferedImage shieldDefenseImage = null;
		BufferedImage activeShieldDefenseImage = null;
		BufferedImage activeAutoAttackImage = null;
		BufferedImage autoAttackImage = null;
		BufferedImage standStillImage = null;
		BufferedImage activeStandStillImage = null;

		try {
			townImage = ImageIO.read(new File("src/view/Sprites/town.png"));
			shieldDefenseImage = ImageIO.read(new File("src/view/Sprites/shieldDefIcon.png"));
			activeShieldDefenseImage = ImageIO.read(new File("src/view/Sprites/activeShieldDefenseIcon.png"));
			activeAutoAttackImage = ImageIO.read(new File("src/view/Sprites/activeAutoAttackImage.png"));
			autoAttackImage = ImageIO.read(new File("src/view/Sprites/autoAttackImage.png"));
			standStillImage = ImageIO.read(new File("src/view/Sprites/standStillImage.png"));
			activeStandStillImage = ImageIO.read(new File("src/view/Sprites/activeStandStillImage.png"));
			;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Beschriftung rechts

		// ArrayList<Soldat> guiSoldat ;
		int activeTownIndex = gui.data.getactiveTownIndex();
		Town activeTown;
		if (activeTownIndex != -1) {
			activeTown = this.getGuiTown().get(activeTownIndex);

		} else {
			activeTown = null;
		}
		super.paint(g);
		

		int x = 0;
		for (int i = 0; i < guiFaction.size(); i++) {

			if (guiFaction.get(i).getAmountTown() != 0) {
				g.drawString("" + this.guiFaction.get(i).getAmountTown(), 1200, 400 + i * 15 - x * 15);
			} else
				x++;
		}
		// show active Town stats
		if (activeTownIndex != -1) {
			g.drawString("Fraktion: " + activeTown.getTownfaction().getFactionname(), 1080, 150);
			g.drawString("active Soldaten" + activeTown.getSoldaten().size(), 1080, 170);
			g.drawString("feindliche Soldaten" + activeTown.getFeindlicheSoldaten().size(), 1080, 190);
		}
		// Draw Line between active Town and mousePosition
		try {

			if (gui.data.getMouseposition().x != 0 && activeTown.getTownfaction().getFactionID() == 0) {
				g.drawLine(activeTown.getStadtposition().x + 15, activeTown.getStadtposition().y + 15,
						gui.data.getMouseposition().x, gui.data.getMouseposition().y - 30);
			}
		} catch (Exception e) {

		}

		// show frames
		g.setColor(Color.black);
		g.fillRect(0, 80, 1500, 10);
		g.fillRect(0, 800, 1500, 10);
		g.fillRect(1050, 0, 10, 1500);

		
		// draw unteren Balken(Anteil welche Fraktion wie viele Städte hat)
		int anzTown = 0;
		int offset = 0;
		for (int i = 0; i < guiFaction.size(); i++) {

			anzTown = guiFaction.get(i).getAmountTown();
			Color faccol = guiFaction.get(i).getCol();

			g.setColor(faccol);
			g.fillRect(10 + offset, 820, anzTown * 4, 30);
			offset += anzTown * 4;
		}

		// Draw städte
		for (int i = 0; i < guiTown.size(); i++) {

			g.setColor(guiTown.get(i).getTownfaction().getCol());
			int tx = guiTown.get(i).getStadtposition().x;
			int ty = guiTown.get(i).getStadtposition().y;
			g.fillOval(tx, ty, 32, 32);
			if (i == activeTownIndex) {
				g.setColor(Color.green);
				g.fillOval(tx, ty, 32, 32);

			}

			if (guiTown.get(i).isHasWall() == true) {
				g.setColor(Color.black);
				g.drawOval(tx - 1, ty - 1, 32, 32);

			}
			// wenn bool auto stances aktiv sind soll auf karte markiert werden

			g.drawImage(townImage, tx, ty + 2, null);

		}
		

		

		if (activeTown != null && activeTown.getTownfaction().getFactionID() == 0) {
			g.setColor(Color.black);
			g.drawString("autoatt", 1060, 340);
			g.drawString("autodef", 1120, 340);
			g.drawString("stand", 1190, 340);
			if (activeTown.isBoolAutoDefend() == false) {
				g.drawImage(shieldDefenseImage, 1120, 270, null);
			} else {
				g.drawImage(activeShieldDefenseImage, 1120, 270, null);
			}

			if (activeTown.isBoolAutoAttack() == false) {
				g.drawImage(autoAttackImage, 1060, 270, null);
			} else {
				g.drawImage(activeAutoAttackImage, 1060, 270, null);
			}

			if (activeTown.isBoolStandStill() == false) {
				g.drawImage(standStillImage, 1180, 270, null);
			} else {
				g.drawImage(activeStandStillImage, 1180, 270, null);
			}
		}

		// draw nearest line
		g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		for (

				int i = 0; i < guiTown.size(); i++) {

			g.drawLine(guiTown.get(i).getStadtposition().x + 15, guiTown.get(i).getStadtposition().y + 15,
					guiTown.get(i).getNahsteStadt().getStadtposition().x + 15,
					guiTown.get(i).getNahsteStadt().getStadtposition().y + 15);

		}
		// Draw nearest enemy line

		for (int i = 0; i < guiTown.size(); i++) {
			try {
				g.setColor(Color.red);
				g.drawLine(guiTown.get(i).getStadtposition().x + 15, guiTown.get(i).getStadtposition().y + 15,
						guiTown.get(i).getNahstefeindlicheStadt().getStadtposition().x + 15,
						guiTown.get(i).getNahstefeindlicheStadt().getStadtposition().y + 15);

			}

			catch (Exception e) {
				guiTown.get(i).setnahstefeindlicheStadt();
			}

		}
		// draw soldier count
		g.setColor(Color.black);
		for (int i = 0; i < guiTown.size(); i++) {
			int guiAnzsoldaten = guiTown.get(i).getSoldaten().size();
			int stadtposX = guiTown.get(i).getStadtposition().x;
			int stadtposY = guiTown.get(i).getStadtposition().y;
			g.drawString("" + guiAnzsoldaten, stadtposX, stadtposY);
		}

		// soldier count but enemy
		g.setColor(Color.red);
		for (int i = 0; i < guiTown.size(); i++) {
			int guiAnzsoldatenfeindlich = guiTown.get(i).getFeindlicheSoldaten().size();
			int stadtposX = guiTown.get(i).getStadtposition().x + 30;
			int stadtposY = guiTown.get(i).getStadtposition().y;
			g.drawString("" + guiAnzsoldatenfeindlich, stadtposX, stadtposY);

		}
		// draw Angriffsarmeen
		
		for (int i = 0; i < guiangriffsarmee.size(); i++) {
//			System.out.println("position der angriffsarmee" + guiangriffsarmee.get(i).getPosition().x);
			g.setColor(guiangriffsarmee.get(i).getCol());
			int tx = guiangriffsarmee.get(i).getPosition().x;
			int ty = guiangriffsarmee.get(i).getPosition().y;
			g.fillRect(tx, ty, 5, 5);
		}
		
		// draw TargetLines
		for (int i = 0; i < guiTown.size(); i++) {
			try {
				g.setColor(Color.green);
				if (guiTown.get(i).getTownfaction().getFactionID() == 0) {
					g.drawLine(guiTown.get(i).getStadtposition().x + 15, guiTown.get(i).getStadtposition().y + 15,
							guiTown.get(i).getAnvisierteStadt().getStadtposition().x + 15,
							guiTown.get(i).getAnvisierteStadt().getStadtposition().y + 15);
				}

			}

			catch (Exception e) {

			}

		}
		
	}

	public ArrayList<Town> getGuiTown() {
		return guiTown;
	}

	public void setGuiTown(ArrayList<Town> guiTown) {
		this.guiTown = guiTown;
		guiFaction = gui.data.getFactionList();
	}

	public ArrayList<Soldat> getGuiSoldat() {
		return guiSoldat;
	}

	public void setGuiSoldat(ArrayList<Soldat> guiSoldat) {
		this.guiSoldat = guiSoldat;
	}

	public void setAngriffsarmeen(ArrayList<Angriffsarmee> guiArmeen) {
		this.guiangriffsarmee = guiArmeen;
	}

	public void resetButton() {

		for (int i = 0; i < guiTown.size(); i++) {
			// System.out.println("stadtknopf wird erstellt(neuerstellt)");

			int posx = guiTown.get(i).getStadtposition().x;
			int posy = guiTown.get(i).getStadtposition().y;
			townbutton.add(new JButton());

			townbutton.get(i).setBounds(posx, posy, 32, 32);
			townbutton.get(i).setOpaque(false);
			townbutton.get(i).setContentAreaFilled(false);
			townbutton.get(i).setBorderPainted(false);
			townbutton.get(i).addActionListener(gui.ctrl.cb);
			townbutton.get(i).setActionCommand("" + i);
			this.add(townbutton.get(i));
		}

	}

	public int getSliderValue() {
		return this.attackAmountSlider.getValue();
		
	}

}
