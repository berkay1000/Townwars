package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

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
	JButton attack;
	ArrayList<JButton> townbutton;
	ArrayList<Angriffsarmee> guiangriffsarmee;

	public JPanelGroundView(GUI inputgui) {
		townbutton = new ArrayList<JButton>();
		guiangriffsarmee = new ArrayList<Angriffsarmee>();
		guiSoldatfeindlich = new ArrayList<Soldat>();

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

		attack = new JButton("attack");
		attack.setBounds(1080, 200, 100, 50);
		attack.setVisible(true);
		attack.setActionCommand("attack");

		attack.addActionListener(gui.ctrl);
		exit.addActionListener(gui.ctrl);
		pause.addActionListener(gui.ctrl);
		this.add(exit);
		this.add(pause);
		this.add(attack);

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
			townbutton.get(i).addActionListener(gui.ctrl);
			townbutton.get(i).setActionCommand("" + i);
			this.add(townbutton.get(i));
		}
		// System.out.println("fertig mit stadtknopf");

	}

	public void paint(Graphics g) {

		// ArrayList<Soldat> guiSoldat ;
		int activeTownIndex = gui.data.getactiveTownIndex();
		Town activeTown = this.getGuiTown().get(activeTownIndex);
		super.paint(g);
		int x=0;
		for (int i = 0; i < guiFaction.size(); i++) {

			if (guiFaction.get(i).getAmountTown() != 0) {
				g.drawString("" + this.guiFaction.get(i).getAmountTown(), 1200, 400 + i * 15-x*15);
			}
			else x++;
		}
		// show active Town stats
		g.drawString("Fraktion: " + activeTown.getTownfaction().getFactionname(), 1080, 150);
		g.drawString("active Soldaten" + activeTown.getSoldaten().size(), 1080, 170);
		g.drawString("feindliche Soldaten" + activeTown.getFeindlicheSoldaten().size(), 1080, 190);

		// show frames
		g.fillRect(0, 80, 1500, 10);
		g.fillRect(0, 800, 1500, 10);
		g.fillRect(1050, 0, 10, 1500);

		// draw unteren Balken

		int anzTown = 0;
		int offset = 0;
		for (int i = 0; i < guiFaction.size(); i++) {

			anzTown = guiFaction.get(i).getAmountTown();
			Color faccol = guiFaction.get(i).getCol();

			g.setColor(faccol);
			g.fillRect(10 + offset, 820, anzTown * 4, 30);
			offset += anzTown * 4;
		}
		// System.out.println("paint von groundview wurde ausgelöst");

		// Draw städte
		for (int i = 0; i < guiTown.size(); i++) {

			g.setColor(guiTown.get(i).getTownfaction().getCol());
			int tx = guiTown.get(i).getStadtposition().x;
			int ty = guiTown.get(i).getStadtposition().y;
			g.fillOval(tx, ty, 30, 30);
			if (i == activeTownIndex) {
				g.setColor(Color.green);
				g.fillOval(tx, ty, 30, 30);
			}

			if (guiTown.get(i).hasWall == true) {
				g.setColor(Color.black);
				g.drawOval(tx - 1, ty - 1, 32, 32);

			}

		}

		// draw nearest line
		g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
		for (int i = 0; i < guiTown.size(); i++) {

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

}
