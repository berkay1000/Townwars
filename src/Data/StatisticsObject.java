package Data;

import java.util.ArrayList;

import townwars.Faction;

public class StatisticsObject {
	
ArrayList<Faction> statFaction;

	public StatisticsObject(ArrayList<Faction> factionList) {
		statFaction=factionList;
}

	public ArrayList<Faction> getStatFaction() {
		return statFaction;
	}

	public void setStatFaction(ArrayList<Faction> statFaction) {
		this.statFaction = statFaction;
	}

}
