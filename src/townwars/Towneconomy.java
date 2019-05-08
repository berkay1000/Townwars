package townwars;

public class Towneconomy {

	Town belongsToTown;
	int ressource;
	int ressourceIncrease;
	int zaehler;
	int ressourceDerivedToBuildWall;
	 Governor townGov;
	

	Towneconomy(Town inputtown) {
		belongsToTown = inputtown;
		ressource = 0;
		zaehler = 0;
		townGov=belongsToTown.governor;

	}

	public void update() {
		while (ressource > 10) {
			createSoldiers();
		}
		ressource = ressource + 5;
		
		int randomnumb= (int) (Math.random() * (101));
		
		if(randomnumb < (townGov.defensivness-5)) {
			ressource-=5;
			ressourceDerivedToBuildWall+=5;
		}
		if(ressourceDerivedToBuildWall>500) {
			
			belongsToTown.setHasWall(true);
			ressourceDerivedToBuildWall=0;
			belongsToTown.fortificationBonus+=15;
		}
		

	}

	public void createSoldiers() {

		ressource -= 10;
		belongsToTown.createSoldiers();

	}
}
