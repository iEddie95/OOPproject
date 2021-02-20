package project;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Citizen {
	protected String name;
	protected int id;
	protected BallotBox<? extends Citizen> myBallotBox;
	protected boolean isQuarantine;
	protected boolean hasVoted;
	protected String city;
	protected LocalDate birthDate;

//6 types: regular citizen, candidate, sick candidate, Corona sick, soldier, sick soldier
	public Citizen(String name, int year, int month, int day, int id, String city) throws Exception {
		this.name = name;
		this.city = city;
		this.hasVoted = false;
		setBirthDate(LocalDate.of(year, month, day));
		setId(id);
	}

	public BallotBox<?> getMyBallotBox() {
		return myBallotBox;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public boolean setId(int id) throws Exception {
		if (String.valueOf(id).length() != 9) {
			throw new Exception("ID must be 9 digits");
		} else {
			this.id = id;
			return true;
		}
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public int getId() {
		return id;

	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public boolean setMyBallotBox(BallotBox<? extends Citizen> myBallotBox) {
		if (this.myBallotBox == null) {
			this.myBallotBox = myBallotBox;
			return true;
		}
		return false;
	}

	public boolean isInQuarantine() {
		return isQuarantine;

	}

	public void setVote() {
		this.hasVoted = true;
	}

	public boolean voted() {
		return hasVoted;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Citizen)) {
			return false;
		}
		Citizen c = (Citizen) other;
		return c.getId() == id;
	}

	@Override
	public String toString() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return "Name: " + name + "\nID: " + id + "\nBorn at: " + birthDate.format(format).toString() + "\nFrom: "
				+ getCity() + "\nVoting at BallotBox number: " + myBallotBox.getId() + "\nQuarantine status: "
				+ isQuarantine;
	}
}
