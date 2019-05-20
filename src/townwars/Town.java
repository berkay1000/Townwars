package townwars;

import java.awt.Point;
import java.util.ArrayList;
import Buildings.Buildings;
import Data.Data;

public class Town {

	String name;
	ArrayList<Soldat> soldaten = new ArrayList<Soldat>();
	ArrayList<Soldat> feindlicheSoldaten = new ArrayList<Soldat>();

	ArrayList<Town> otherTowns;
	Town nahsteStadt;
	Town nahstefeindlicheStadt;
	Town anvisierteStadt;
	Town bedrohteVerbuendeteStadt;

	public Town getAnvisierteStadt() {
		return anvisierteStadt;
	}

	Buildings[] buildings;
	Governor governor;
	Point stadtposition;
	Towneconomy towneco;
	Faction townfaction;
	Faction factionlastattacked;
	int defensiveAdvantage = 0;
	public int targetedby = 0;
	int fortificationBonus = 50;

	boolean stadtImKampf;
	private boolean hasWall;
	boolean isPlayer;
	boolean autoFocus;

	int zyklus = 0; // zyklus damit manche Aktionen nicht zu häufig ausgeführt werden 0 - 60
	private Data data;
	boolean boolAutoDefend = false;
	boolean boolAutoAttack = true;
	boolean boolStandStill = false;
	public boolean boolAutoECO = true;

	public Town(ArrayList<Town> inputTown, Faction inputfaction, Data inputdata) {

		data = inputdata;
		governor = new Governor();
		towneco = new Towneconomy(this);
		townfaction = inputfaction;
		if (inputfaction.FactionID == 0) {
			isPlayer = true;
		}

		otherTowns = inputTown;
		stadtposition = new Point();
		// ArrayList<String> cars = new ArrayList<String>();
		stadtposition.x = (int) (Math.random() * 1000);
		stadtposition.y = (int) ((Math.random() * 670) + 100);
		// System.out.println(" "+stadtposition.x+" "+ stadtposition.y);

	}

	public Faction getFactionlastattacked() {
		return factionlastattacked;
	}

	public Town getNahstefeindlicheStadt() {
		return nahstefeindlicheStadt;
	}

	public Faction getTownfaction() {
		return townfaction;
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

	public ArrayList<Soldat> getFeindlicheSoldaten() {
		return feindlicheSoldaten;
	}

	public ArrayList<Soldat> getSoldaten() {
		return soldaten;
	}

	public void setFactionlastattacked(Faction factionlastattacked) {
		this.factionlastattacked = factionlastattacked;
	}

	public boolean setNearestTown() { // gives every town the nearest neighbor
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

	public boolean setNearestTownforTheFirstTime() { // gives every town the nearest neighbor but additionally checks if
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

					// System.out.println("stadt ist zu nahe, ändere stadtposition");
					return false;

				}
			}

		}
		// System.out.println("minIndex: " + minIndex + " ist mit mindistance " +
		// mindistance + " am nahsten");
		nahsteStadt = otherTowns.get(minIndex);
		return true;

	}

	public void setSoldaten(ArrayList<Soldat> soldaten) {
		this.soldaten = soldaten;
	}

	public void setTownfaction(Faction townfaction) {
		this.townfaction = townfaction;
	}

	public void setTargetTownNearestToMouse(Point mousereleasedPosition) {
		int eigenePositionX = mousereleasedPosition.x;
		int eigenePositionY = mousereleasedPosition.y;
		int anderePositionX;
		int anderePositionY;

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
				// System.out.println(distance + "etwas ist näher dran!");
				minIndex = i;
				mindistance = distance;
				anvisierteStadt = otherTowns.get(minIndex);
			}
			if (mindistance > 45) {
				anvisierteStadt = null;
			}

		}
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
				// System.out.println(distance + "etwas ist näher dran!");
				minIndex = i;
				mindistance = distance;
			}

		}

		nahstefeindlicheStadt = otherTowns.get(minIndex);
		return true;

	}

	public void setFeindlicheSoldaten(ArrayList<Soldat> feindlicheSoldaten) {
		this.feindlicheSoldaten = feindlicheSoldaten;
	}

	public void createSoldiers() {

		soldaten.add(new Soldat(stadtposition));

	}

	public void update() {
//System.out.println("stadtupdate!");

		if (zyklus % 4 == 1) { // alle 4 zyklen soll gekämpft werden
			this.stadtImKampf = false;
			this.stadtKampf();
		}

		// falls verbündete Truppen 0 werden, aber feindliche anwesend sind, dann soll
		// stadt erobert werden
		if (soldaten.isEmpty() && this.feindlicheSoldaten.size() > 0) {
			stadtWurdeErobert();
		} else {

			// eco update,
			this.towneco.aiUpdate();
			this.towneco.playerUpdate();
			this.setnahstefeindlicheStadt();
			this.nahstefeindlicheStadt.incrementTargetedBy();
			this.townfaction.countTowns();

			if (targetedby > 2) {
				sendDistressCall();
			}

			try {
				if (this.bedrohteVerbuendeteStadt.targetedby < 2) {
					bedrohteVerbuendeteStadt = null;
				}
			} catch (Exception E) {
			}
			try {
				this.createAngriffsArmeeComputer();
			} catch (Exception e) {
				
			}

			if (soldaten.size() > 200 && this.feindlicheSoldaten.isEmpty() & this.targetedby == 0) {

				sendVerteidigungsTruppen();

			}

		}

		zyklus++;
		if (zyklus >= 60)
			zyklus = 0;

	}

	private void sendVerteidigungsTruppen() {
		if (bedrohteVerbuendeteStadt != null) {
			Angriffsarmee aa = new Angriffsarmee(stadtposition, townfaction, bedrohteVerbuendeteStadt);

			System.out.println("governor baseattacksize: " + governor.getBaseattacksize());
			for (int i = 0; i < governor.getBaseattacksize(); i++) {
				if (soldaten.isEmpty())
					break;
				soldaten.remove(0);
				aa.addToArmy();
			}

			data.getAngriffsarmeelist().add(aa);
		}

	}

	private void stadtKampf() {

		int kampfgroesse = feindlicheSoldaten.size() / 30;
		if (kampfgroesse == 0 || kampfgroesse == 1)
			kampfgroesse = 2;

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
						this.stadtImKampf = true;
						feindlicheSoldaten.remove(0);
						soldaten.remove(0);

						defensiveAdvantage += fortificationBonus;

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

	public boolean createAngriffsArmeeComputer() throws Exception {

		// nur bots und autoattackierende Player-städte gehen diesen weg
		if ((soldaten.size() > governor.getBaseattacksize() && this.isPlayer == false)
				|| (soldaten.size() > governor.getBaseattacksize() && boolAutoAttack == true)) {
			Angriffsarmee aa;

			if (anvisierteStadt == null) {

				aa = new Angriffsarmee(stadtposition, townfaction, nahstefeindlicheStadt);
			} else {

				aa = new Angriffsarmee(stadtposition, townfaction, anvisierteStadt);
			}
			int createArmyOffset = governor.defensivness * 10;
			for (int i = createArmyOffset; i < governor.getBaseattacksize(); i++) {
				
				if(soldaten.isEmpty()==true) {
					break;
				}
				soldaten.remove(0);
				aa.addToArmy();
			

			}
			governor.setBaseattacksize(10 + governor.getBaseattacksize());

			data.getAngriffsarmeelist().add(aa);
			return true;
		} else {
			throw new NullPointerException("demo");
		}

		// Mit einschränkungen später

	}

	public Angriffsarmee createAngriffsArmee() throws Exception {

		if (this.townfaction.getFactionID() == 0 && anvisierteStadt != null) {

			System.out.println(stadtposition);
			System.out.println(townfaction);
			System.out.println(anvisierteStadt);
			Angriffsarmee aa = new Angriffsarmee(stadtposition, townfaction, anvisierteStadt);

			System.out.println("governor baseattacksize: " + governor.getBaseattacksize());
			for (int i = 0; i < governor.getBaseattacksize(); i++) {

				if (soldaten.isEmpty())
					break;

				soldaten.remove(0);
				aa.addToArmy();
				System.out.print("i");

			}

			System.out.println("füge armeeliste hinzu");
			System.out.print("armeeliste größe vorher:");
			System.out.println(data.getAngriffsarmeelist().size());

			data.getAngriffsarmeelist().add(aa);
			System.out.print("armeeliste größe nacher:");
			System.out.println(data.getAngriffsarmeelist().size());

		} else {
			throw new NullPointerException("no target or not your faction");
		}
		return null;

		// Mit einschränkungen später

	}

	public boolean isHasWall() {
		return hasWall;
	}

	public void setHasWall(boolean hasWall) {
		this.hasWall = hasWall;
	}

	public void stadtWurdeErobert() {
		townfaction = factionlastattacked;
		while (this.feindlicheSoldaten.isEmpty() == false) {
			this.feindlicheSoldaten.remove(0);
			this.soldaten.add(new Soldat(stadtposition));
		}
		this.setnahstefeindlicheStadt();
		this.setNearestTown();
		this.governor = new Governor();

		if (townfaction.FactionID == 0) {
			isPlayer = true;
		}

		boolAutoAttack = true;
		boolAutoDefend = false;
		boolStandStill = false;
		this.towneco.effizienz=0;

	}

	public void boolEnableAutoDefend() {
		boolAutoDefend = true;
		boolAutoAttack = false;
		boolStandStill = false;

	}

	public void boolEnableAutoAttack() {
		boolAutoDefend = false;
		boolAutoAttack = true;
		boolStandStill = false;

	}

	public void boolEnablestandStill() {
		boolAutoDefend = false;
		boolAutoAttack = false;
		boolStandStill = true;

	}

	public boolean isBoolAutoDefend() {
		return boolAutoDefend;
	}

	public boolean isBoolAutoAttack() {
		return boolAutoAttack;
	}

	public boolean isBoolStandStill() {
		return boolStandStill;
	}

	public Town getTargetTownNearestToMouse(Point mousereleasedPosition) {
		int eigenePositionX = mousereleasedPosition.x;
		int eigenePositionY = mousereleasedPosition.y;
		int anderePositionX;
		int anderePositionY;

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
				// System.out.println(distance + "etwas ist näher dran!");
				minIndex = i;
				mindistance = distance;
				anvisierteStadt = otherTowns.get(minIndex);
			}
			if (mindistance > 45) {
				anvisierteStadt = null;
			}

		}
		return anvisierteStadt;
	}

	public Town getBedrohteVerbuendeteStadt() {
		return bedrohteVerbuendeteStadt;
	}

	public void setBedrohteVerbuendeteStadt(Town inputTown) {
		// eine Hilfeanfrage kam, soll Hilfe geschickt werden?
		// ist Stadt nah genug dran und verbündet?
		int eigenePositionX = this.stadtposition.x;
		int eigenePositionY = this.stadtposition.y;

		int anderePositionX = inputTown.stadtposition.x;
		int anderePositionY = inputTown.stadtposition.y;
		int deltaX = eigenePositionX - anderePositionX;
		int deltaY = eigenePositionY - anderePositionY;
		int distancesum = deltaX * deltaX + deltaY * deltaY;
		int distance = (int) Math.sqrt(distancesum);

		if (distance < 300 && this.townfaction.FactionID == inputTown.townfaction.FactionID && distance > 0) {
			if (this.isPlayer == false) {
				this.bedrohteVerbuendeteStadt = inputTown;
				
			}
			if (this.isPlayer == true && this.boolAutoDefend == true) {
				this.bedrohteVerbuendeteStadt = inputTown;
				
			}
		}

	}

	public void incrementTargetedBy() {
		this.targetedby++;
	}

	public void resetIncrementTargetedBy() {
		targetedby = 0;
	}

	private void sendDistressCall() {
		for (int i = 0; i < otherTowns.size(); i++) {

			if (otherTowns.get(i).townfaction.FactionID == townfaction.FactionID && otherTowns.get(i) != this)
				this.otherTowns.get(i).setBedrohteVerbuendeteStadt(this);

		}

	}
}
