package townwars;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Angriffsarmee {

	Point angriffsarmeeposition;
	ArrayList<Soldat> soldaten;
	Faction armyfaction;
	private Town targetTown;

	public ArrayList<Soldat> getSoldaten() {
		return soldaten;
	}

	public void setSoldaten(ArrayList<Soldat> soldaten) {
		this.soldaten = soldaten;
	}

	public Point getPosition() {
		return angriffsarmeeposition;
	}

	public void setPosition(Point position) {
		this.angriffsarmeeposition = position;
	}

	public Angriffsarmee(Point stadtposition, Faction townfaction, Town targetTown) {
		angriffsarmeeposition = new Point();

		this.targetTown = targetTown;
		int posx = stadtposition.x;
		int posy = stadtposition.y;
		angriffsarmeeposition.x = posx;
		angriffsarmeeposition.y = posy;
		armyfaction = townfaction;
		soldaten = new ArrayList<Soldat>();

	}

	public void addToArmy() {
		soldaten.add(new Soldat(angriffsarmeeposition));

	}

	public Color getCol() {
		// TODO Auto-generated method stub
		return armyfaction.getCol();
	}

	public Town getTargetTown() {
		return targetTown;
	}

	public void setTargetTown(Town targetTown) {
		this.targetTown = targetTown;
	}

	public void movetowardstarget() {

		try {
			int ownpositionx = angriffsarmeeposition.x;
			int ownpositiony = angriffsarmeeposition.y;
			int theirpositionx = targetTown.getStadtposition().x;
			int theirpositiony = targetTown.getStadtposition().y;
			int deltax = ownpositionx - theirpositionx;
			int deltay = ownpositiony - theirpositiony;

			if (deltax > 0) {
				angriffsarmeeposition.x -= 2;
			} else if (deltax < 0) {
				angriffsarmeeposition.x += 2;
			}
			if (deltay > 0) {
				angriffsarmeeposition.y -= 2;
			} else if (deltay < 0) {
				angriffsarmeeposition.y += 2;
			}

			// checkt ob armee bereits in der nähe vom zielstadt ist
			if (deltax < 10 && deltay < 10 && deltax > -10 && deltay > -10) { // noch checken ob zielstadt verbündet
				for (int x = 0; x < 3; x++) {
					if (soldaten.isEmpty() == false && this.armyfaction != targetTown.townfaction) {
						this.soldaten.remove(0);
						targetTown.getFeindlicheSoldaten().add(new Soldat(targetTown.stadtposition));
						targetTown.setFactionlastattacked(armyfaction);
					} else if (soldaten.isEmpty() == false) {
						this.soldaten.remove(0);
						targetTown.soldaten.add(new Soldat(targetTown.stadtposition));
					}
				}

//                                         
//			}

			}

		} catch (Exception e) {
			
		}
	}

	public void update() {

		movetowardstarget();
		// siegetheTown();
	}

}
