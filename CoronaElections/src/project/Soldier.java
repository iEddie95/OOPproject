package project;

import java.util.Random;

public class Soldier extends Citizen implements Soldierable {
	private boolean isCarryWeapon;

	public Soldier(String name, int year, int month, int day, int id, String city) throws Exception {
		super(name, year, month, day, id, city);
		setCarryWeapon();
	}

	@Override
	public boolean setCarryWeapon() {
		Random rand = new Random();
		return isCarryWeapon = rand.nextBoolean();
	}

	@Override
	public String toString() {
		return super.toString() + ("\nhas weapon: " + isCarryWeapon);
	}

}
