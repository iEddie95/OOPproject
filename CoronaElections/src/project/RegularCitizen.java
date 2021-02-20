package project;

public class RegularCitizen extends Citizen {

	public RegularCitizen(String name, int year, int month, int day, int id, String city) throws Exception {
		super(name, year, month, day, id, city);
		this.isQuarantine = false;
	}

}
