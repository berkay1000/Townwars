package townwars;

public class Governor {

	int baseattacksize;
	public int defensivness; //

	public Governor() {
		defensivness = (int) (Math.random() * 11)+5; // wert von 5-15 für wie defensiv sich die Ki Verhält
	
		baseattacksize=20;

	}

	public int getBaseattacksize() {
		return baseattacksize;
	}

	public void setBaseattacksize(int baseattacksize) {
		this.baseattacksize = baseattacksize;
	}

}
