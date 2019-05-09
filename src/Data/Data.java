package Data;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

import Controls.Control;
import townwars.Angriffsarmee;
import townwars.Faction;
import townwars.Town;
import view.GUI;

public class Data {

	public ReadPropertyFile rpf = new ReadPropertyFile();
	Control ctrl;
	GUI gui;
	ArrayList<Town> townlist;
	ArrayList<Faction> FactionList;
	ArrayList<Angriffsarmee> angriffsarmeelist;
	public boolean hasStarted = false;
	int zyklus = 0;
	StatisticsObject statobject;
	private int activeTownIndex=-1;
	private boolean gameEnded = false;
	Point Mouseposition;
	Point MousereleasedPosition;

	public Point getMouseposition() {
		return Mouseposition;
	}

	public void setMouseposition(Point mouseposition) {
		Mouseposition = mouseposition;
		System.out.println("mouseposition geseetzt" + Mouseposition);
	}

	public StatisticsObject getStatobject() {
		return statobject;
	}

	public ArrayList<Angriffsarmee> getAngriffsarmeelist() {
		return angriffsarmeelist;
	}

	public ArrayList<Town> getTownlist() {
		return townlist;
	}

	public void setTownlist(ArrayList<Town> inputtownlist) {
		townlist = inputtownlist;
	}

	public Data() {

		createWorld();

	}

	public ArrayList<Faction> getFactionList() {
		return FactionList;
	}

	public void setCTRLGUI(Control c, GUI g) {
		ctrl = c;
		gui = g;

	}

	public void updateSoldierPosition() {

	}

	public void createWorld() {

		Mouseposition = new Point();
		if (angriffsarmeelist == null) {
			angriffsarmeelist = new ArrayList<Angriffsarmee>();
			townlist = new ArrayList<Town>();
			FactionList = new ArrayList<Faction>();
			
		}
		
	
		FactionList.add(new Faction(Color.YELLOW, "You"));
		FactionList.add(new Faction(Color.gray, "Gray walker"));
		FactionList.add(new Faction(Color.RED, "red walker"));
		FactionList.add(new Faction(Color.black, "black walker"));
		FactionList.add(new Faction(Color.BLUE, "Blue walker"));
		FactionList.add(new Faction(Color.magenta, "magenta walker"));
		FactionList.add(new Faction(Color.CYAN, "Cyan walker"));
		FactionList.add(new Faction(Color.DARK_GRAY, "DG walker"));
		FactionList.add(new Faction(Color.ORANGE, "Orange walker"));
		FactionList.add(new Faction(Color.green, "Cyan walker"));

		int anztown = Integer.parseInt(rpf.prop.getProperty("townamounts"));
		int facam = Integer.parseInt(rpf.prop.getProperty("FactionAmounts"));
		statobject = new StatisticsObject(FactionList);

		while (FactionList.size() < facam) {
			Color hexcolor = randomnumberColor();
			FactionList.add(new Faction(hexcolor, "undefined"));
		}

		for (int i = 0; i < anztown; i++) {

			int randnumb = (int) (Math.random() * (facam));

			townlist.add(new Town(townlist, FactionList.get(randnumb),this));

		}

		boolean keineStadtzunahe = true;
		for (int i = 0; i < anztown; i++) {
			keineStadtzunahe = townlist.get(i).getNearestTownforTheFirstTime();

			if (keineStadtzunahe == false) {
				i = -1;
				// System.out.println("keineStadtzunahe" + keineStadtzunahe);
			}
		}
		// System.out.println("stadtnähe wurde erzeugt");

		for (int i = 0; i < anztown; i++) {
			keineStadtzunahe = townlist.get(i).setnahstefeindlicheStadt();
		}

	}

	private void resetguiButton() {
	 
		gui.getGroundView().resetButton();
		
	}

	private Color randomnumberColor() {
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		Color randomColor = new Color(r, g, b);
		System.out.println("Randcomcolor:" + r + g + b);

		return randomColor;
	}

	public void update() {

		if (hasStarted == false) {

		} else {

			zyklus++;

			// Count for every Faction the Townamount
			for (int i = 0; i < FactionList.size(); i++) {
				FactionList.get(i).resetTownCount();
			}

			for (int i = 0; i < townlist.size(); i++) {
				townlist.get(i).update();

				// AI tries to create Army
				try {
					angriffsarmeelist.add(townlist.get(i).createAngriffsArmeeComputer());

				} catch (Exception e) {

				}
			}

			for (int i = 0; i < angriffsarmeelist.size(); i++) {
				angriffsarmeelist.get(i).update();
			}
			for (int i = 0; i < angriffsarmeelist.size();) {

				if (angriffsarmeelist.get(i).getSoldaten().size() == 0) {
					angriffsarmeelist.remove(i);
				} else {
					i++;
				}

			}
		}
		int facAnzTown;
		for (int i = 0; i < FactionList.size(); i++) {

			facAnzTown = FactionList.get(i).getAmountTown();

			if (zyklus > 10) {
				FactionList.get(i).factionAmmendToTownAmountInTimeProgess(facAnzTown);
			}

			int anzTown = Integer.parseInt(rpf.prop.getProperty("townamounts"));

			if (facAnzTown == anzTown & gameEnded == false) {

				hasStarted = false;
				gameEnded = true;
				gui.changeView(gui.getEndscreen());
			}
		}
		if (zyklus > 10) {

			zyklus = 0;
		}
	}

	public void clearMap() {
		while (angriffsarmeelist.isEmpty() == false) {
			angriffsarmeelist.remove(0);
		}
		while (townlist.isEmpty() == false) {
			townlist.remove(0);
			
		}
		while (FactionList.isEmpty() == false) {
			FactionList.remove(0);
			Faction.setFactioncounter(0);
		}

		createWorld();
	}

	public void setactiveTownIndex(int townindexnumber) {
		activeTownIndex = townindexnumber;

	}

	public int getactiveTownIndex() {
		return this.activeTownIndex;
	}

	public void setMouseReleasedposition(Point point) {
		MousereleasedPosition = point;
		;
		MousereleasedPosition.y-=30;
		
		try {
		townlist.get(activeTownIndex).setTargetTownNearestToMouse(MousereleasedPosition);
		}
		catch(Exception e){
			
		}
		
	}

}
