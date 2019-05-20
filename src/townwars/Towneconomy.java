package townwars;

public class Towneconomy {

	Town belongsToTown;
	int ressource;
	int ressourceIncome=10;
	int zaehler;
	int ressourceDerivedToBuildWall;
	Governor townGov;
	public int effizienz=100;

	Towneconomy(Town inputtown) {
		belongsToTown = inputtown;
		ressource = 0;
		zaehler = 0;
		townGov = belongsToTown.governor;

	}

	public void playerUpdate() {
		if (belongsToTown.isPlayer == true && belongsToTown.boolAutoECO == false) {

			ressource = (int)(((ressource + ressourceIncome)*effizienz)/100);
		}
		if (effizienz<=100) {effizienz++;}

	}

	public void aiUpdate() {

		if (belongsToTown.isPlayer == false || (belongsToTown.isPlayer == true && belongsToTown.boolAutoECO == true))
			while (ressource > 10) {
				createSoldiers();
			}
		ressource = ((ressource + ressourceIncome)*effizienz/100);

		int randomnumb = (int) (Math.random() * (101));

		if (randomnumb < (townGov.defensivness - 5)) {
			ressource -= 5;
			ressourceDerivedToBuildWall += 5;
		}
		if (ressourceDerivedToBuildWall > 500) {

			belongsToTown.setHasWall(true);
			ressourceDerivedToBuildWall = 0;
			belongsToTown.fortificationBonus += 15;
		}
		if (effizienz<=100) {effizienz++;}
	}

	public void createSoldiers() {

		ressource -= 10;
		belongsToTown.createSoldiers();

	}
}
