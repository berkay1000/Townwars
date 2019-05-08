package townwars;

import java.awt.Color;
import java.util.ArrayList;

public class Faction {

	static int factioncounter = 0;
	int FactionID = 0;
	public int getFactionID() {
		return FactionID;
	}


	Color col;
	int fac;
	String factionname;
	ArrayList<Integer> TownAmountInTimeProgress;

	public ArrayList<Integer> getTownAmountInTimeProgress() {
		return TownAmountInTimeProgress;
	}

	public String getFactionname() {
		return factionname;
	}

	public Color getCol() {
		return col;
	}

	public void setCol(Color col) {
		this.col = col;
	}

	
	int amountTown = 0;

	public int getAmountTown() {
		return amountTown;
	}

	public Faction(Color inputcol, String inputfactionname) {

		col = inputcol;
		factionname = inputfactionname;
		FactionID = factioncounter;
		factioncounter++;
		TownAmountInTimeProgress= new ArrayList<Integer>();

	}

	public void countTowns() {
		amountTown++;
	}

	public void resetTownCount() {
		amountTown = 0;
	}
	
	public void factionAmmendToTownAmountInTimeProgess(int Timeprogress) {
		TownAmountInTimeProgress.add(Timeprogress);
	}

}
