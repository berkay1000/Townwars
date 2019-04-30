package townwars;

import java.awt.Point;
import java.util.ArrayList;
import Buildings.Buildings;

public class Town {

	String name;
	ArrayList<Soldat> soldaten = new ArrayList<Soldat>();
	ArrayList<Soldat> feindlicheSoldaten = new ArrayList<Soldat>();

	public ArrayList<Soldat> getFeindlicheSoldaten() {
		return feindlicheSoldaten;
	}

	public void setFeindlicheSoldaten(ArrayList<Soldat> feindlicheSoldaten) {
		this.feindlicheSoldaten = feindlicheSoldaten;
	}

	public ArrayList<Soldat> getSoldaten() {
		return soldaten;
	}

	public void setSoldaten(ArrayList<Soldat> soldaten) {
		this.soldaten = soldaten;
	}

	int ressourcen;
	Buildings[] buildings;
	Governor governor;
	Point stadtposition;
	ArrayList<Town> otherTowns;
	Town nahsteStadt;
	Town nahstefeindlicheStadt;
	Faction townfaction;
	Faction factionlastattacked;
	int defensiveAdvantage = 0;
	int targetedby=0;

	public Faction getFactionlastattacked() {
		return factionlastattacked;
	}

	public void setFactionlastattacked(Faction factionlastattacked) {
		this.factionlastattacked = factionlastattacked;
	}

	public Town getNahstefeindlicheStadt() {
		return nahstefeindlicheStadt;
	}

	public Faction getTownfaction() {
		return townfaction;
	}

	public void setTownfaction(Faction townfaction) {
		this.townfaction = townfaction;
	}

	public Town getNahsteStadt() {
		return nahsteStadt;
	}

	public Point getStadtposition() {
		return stadtposition;
	}

	public void setStadtposition(Point stadtposition) {
		this.stadtposition = stadtposition;
	}

	public Town(ArrayList<Town> inputTown, Faction inputfaction) {

		governor = new Governor();
		townfaction = inputfaction;
		otherTowns = inputTown;
		stadtposition = new Point();
		// ArrayList<String> cars = new ArrayList<String>();
		stadtposition.x = (int) (Math.random() * 1000);
		stadtposition.y = (int) ((Math.random() * 670) + 100);
		// System.out.println(" "+stadtposition.x+" "+ stadtposition.y);

	}

	public void createSoldiers() {

		soldaten.add(new Soldat(stadtposition));

	}

	public void update() {

		// Mit einschr�nkungen sp�ter

		if (soldaten.isEmpty() && this.feindlicheSoldaten.size() > 0) {
			stadtWurdeErobert();
		} else {
			createSoldiers();

			System.out.println(soldaten.size());
			System.out.println(feindlicheSoldaten.size());

			int kampfgroesse = feindlicheSoldaten.size() / 10;
			if (kampfgroesse==0)kampfgroesse=1;

			try {
				if (this.townfaction.FactionID == this.factionlastattacked.FactionID) {

					for (int i = 0; i < kampfgroesse; i++) {
						if (feindlicheSoldaten.size() > 0 && soldaten.size() > 0) {
							feindlicheSoldaten.remove(0);
							soldaten.add(new Soldat(stadtposition));

						}
					}
				}

				else {
					for (int i = 0; i < kampfgroesse; i++) {
						if (feindlicheSoldaten.size() > 0 && soldaten.size() > 0) {
							feindlicheSoldaten.remove(0);
							soldaten.remove(0);

							defensiveAdvantage += 100;

							if (defensiveAdvantage >= 100) {
								feindlicheSoldaten.remove(0);
								defensiveAdvantage -= 100;
							}

						}
					}
				}
			} catch (Exception e) {

			}
		}

		this.setnahstefeindlicheStadt();
		this.townfaction.countTowns();
	}

	public Angriffsarmee createAngriffsArmee() throws Exception {

		if (soldaten.size() > governor.getBaseattacksize()) {
			Angriffsarmee aa = new Angriffsarmee(stadtposition, townfaction, nahstefeindlicheStadt);

			int createArmyOffset = governor.defensivness;
			for (int i = createArmyOffset; i < governor.getBaseattacksize(); i++) {
				soldaten.remove(0);
				aa.addToArmy();

			}
			governor.setBaseattacksize(10 + governor.getBaseattacksize());

			return aa;
		} else {
			throw new NullPointerException("demo");
		}

		// Mit einschr�nkungen sp�ter

	}

	public boolean getNearestTownforTheFirstTime() { // gives every town the nearest neighbor but additionally checks if
														// they are too close and then gives the town a new position
		int eigenePositionX = stadtposition.x;
		int eigenePositionY = stadtposition.y;
		int anderePositionX, anderePositionY;
		int deltaX, deltaY;
		int distance;
		int distancesum;
		int mindistance = 1200;
		int minIndex = 0;

		for (int i = 0; i < otherTowns.size(); i++) {
			anderePositionX = otherTowns.get(i).stadtposition.x;
			anderePositionY = otherTowns.get(i).stadtposition.y;
			deltaX = eigenePositionX - anderePositionX;
			deltaY = eigenePositionY - anderePositionY;
			distancesum = deltaX * deltaX + deltaY * deltaY;
			distance = (int) Math.sqrt(distancesum);

			if (distance == 0) {
				distance = 1500;
			}

			if (distance < mindistance) {
				// System.out.println(distance+" von minindex i "+i+"ist kleiner");
				minIndex = i;
				mindistance = distance;
				if (mindistance < 33) {
					mindistance = 1200;
					i = 0;
					stadtposition.x = (int) (Math.random() * 1000);
					stadtposition.y = (int) ((Math.random() * 680) + 100);

					System.out.println("stadt ist zu nahe, �ndere stadtposition");
					return false;

				}
			}

		}
		System.out.println("minIndex: " + minIndex + " ist mit mindistance " + mindistance + " am nahsten");
		nahsteStadt = otherTowns.get(minIndex);
		return true;

	}

	public boolean getNearestTown() { // gives every town the nearest neighbor
		int eigenePositionX = stadtposition.x;
		int eigenePositionY = stadtposition.y;
		int anderePositionX, anderePositionY;
		int deltaX, deltaY;
		int distance;
		int distancesum;
		int mindistance = 1200;
		int minIndex = 0;

		for (int i = 0; i < otherTowns.size(); i++) {
			anderePositionX = otherTowns.get(i).stadtposition.x;
			anderePositionY = otherTowns.get(i).stadtposition.y;
			deltaX = eigenePositionX - anderePositionX;
			deltaY = eigenePositionY - anderePositionY;
			distancesum = deltaX * deltaX + deltaY * deltaY;
			distance = (int) Math.sqrt(distancesum);

			if (distance == 0) {
				distance = 1500;
			}

			if (distance < mindistance) {
				// System.out.println(distance+" von minindex i "+i+"ist kleiner");
				minIndex = i;
				mindistance = distance;
			}

		}
		// System.out.println("minIndex: "+minIndex+" ist mit mindistance
		// "+mindistance+" am nahsten");
		nahsteStadt = otherTowns.get(minIndex);
		return true;

	}

	public boolean setnahstefeindlicheStadt() { // gives every town the nearest neighbor
		int eigenePositionX = stadtposition.x;
		int eigenePositionY = stadtposition.y;
		int anderePositionX, anderePositionY;
		int deltaX, deltaY;
		int distance;
		int distancesum;
		int mindistance = 1200;
		int minIndex = 0;

		for (int i = 0; i < otherTowns.size(); i++) {
			anderePositionX = otherTowns.get(i).stadtposition.x;
			anderePositionY = otherTowns.get(i).stadtposition.y;
			deltaX = eigenePositionX - anderePositionX;
			deltaY = eigenePositionY - anderePositionY;
			distancesum = deltaX * deltaX + deltaY * deltaY;
			distance = (int) Math.sqrt(distancesum);

			if (distance == 0) {
				distance = 1500;
			}

			if (distance < mindistance && otherTowns.get(i).getTownfaction().FactionID != this.townfaction.FactionID) {
				System.out.println(distance + "etwas ist n�her dran!");
				minIndex = i;
				mindistance = distance;
			}

		}

		nahstefeindlicheStadt = otherTowns.get(minIndex);
		return true;

	}

	public void stadtWurdeErobert() {
		townfaction = factionlastattacked;
		while (this.feindlicheSoldaten.isEmpty() == false) {
			this.feindlicheSoldaten.remove(0);
			this.soldaten.add(new Soldat(stadtposition));
		}
		this.setnahstefeindlicheStadt();
		this.getNearestTown();

	}

}