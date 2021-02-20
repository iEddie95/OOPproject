package project;

import java.util.Random;

public class SoldierCoronaSick extends Soldier implements CoronaSickable {
	private int sickDays;
	private boolean isWearingProtection;

	public SoldierCoronaSick(String name, int year, int month, int day, int id, String city, int sickDays)
			throws Exception {
		super(name, year, month, day, id, city);
		this.isQuarantine = true;
		this.sickDays = sickDays;
		Random rand = new Random();
		setWearingProtection(rand.nextBoolean());

	}

	@Override
	public boolean isWearingProtection() {
		return isWearingProtection;
	}

	@Override
	public void setWearingProtection(Boolean wearingProtection) {
		this.isWearingProtection = wearingProtection;
	}

	@Override
	public int numOfDaysSick() {
		return sickDays;
	}

	@Override
	public String toString() {
		return super.toString() + " \nIs sick: " + sickDays + " days";
	}
}
