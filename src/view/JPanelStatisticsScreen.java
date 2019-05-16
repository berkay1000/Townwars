package view;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import Data.StatisticsObject;
import townwars.Faction;

public class JPanelStatisticsScreen extends JPanel {

	StatisticsObject statObject;
	private ArrayList<Faction> guiFactionList;

	public JPanelStatisticsScreen(GUI gui, StatisticsObject inputstatobject) {
		statObject = inputstatobject;
		guiFactionList = new ArrayList<Faction>();

		guiFactionList = statObject.getStatFaction();
	}

	public void paint(Graphics g) {
		super.paint(g);
		float timepointReducingfactor;
		float timepointskipcounter = 0;
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));

		System.out.println("originalsize:" + guiFactionList.get(3).getTownAmountInTimeProgress().size());

		for (int i = 0; i < guiFactionList.size(); i++) {
			while (guiFactionList.get(i).getTownAmountInTimeProgress().size() > 1100) {

				int randomNumber = (int) (Math.random() * guiFactionList.get(i).getTownAmountInTimeProgress().size());

				guiFactionList.get(i).getTownAmountInTimeProgress().remove(randomNumber);
			}
		}
		System.out.println("danach:" + guiFactionList.get(3).getTownAmountInTimeProgress().size());
		
		g.drawLine(100, 800, 1000, 800);	

		for (int i = 0; i < guiFactionList.size(); i++) {
			g.setColor(guiFactionList.get(i).getCol());

			for (int timep = 0; timep < guiFactionList.get(i).getTownAmountInTimeProgress().size() - 1; timep++) {

				g2.drawLine(100 + timep, 800 - guiFactionList.get(i).getTownAmountInTimeProgress().get(timep) * 3,
						100 + (timep + 1),
						800 - guiFactionList.get(i).getTownAmountInTimeProgress().get(timep + 1) * 3);
			}
			System.out.println(guiFactionList.get(i).getTownAmountInTimeProgress().size());

		}

	}

}
