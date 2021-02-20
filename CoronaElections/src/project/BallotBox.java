package project;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

public class BallotBox<T extends Citizen> {

	private static int idCount = 100;
	private final static int MIN_CITIZENS = 2;
	private int id;
	private String address;
	private Vector<T> citizensList;
	private double presentageOfVotes = 0;
	private int votersCounter = 0;
	private CitizenType boxType;
	private LocalDate electionDay;
	private ArrayList<Integer> result;

	public BallotBox(String address, CitizenType boxType) {
		this.id = idCount++;
		this.address = address;
		this.boxType = boxType;
		this.citizensList = new Vector<T>(MIN_CITIZENS);
	}

	public Vector<T> getCitizensList() {
		return citizensList;
	}

	public CitizenType getBoxType() {
		return boxType;
	}

	public void add(T citizen) {
		if (!citizensList.contains(citizen)) {
			this.citizensList.add(citizen);
			citizen.setMyBallotBox(this);
		}
	}

	public String getAddress() {
		return address;
	}

	public int getNumOfCitizens() {
		return citizensList.size();
	}

	public int getId() {
		return id;
	}

	public void setEllectionDay(LocalDate electionDay) {
		this.electionDay = electionDay;
	}

	public LocalDate getEllectionDay() {
		return electionDay;
	}

	public void startElection(int numOfPartys) {
		result = new ArrayList<Integer>(numOfPartys);
		for (int i = 0; i < numOfPartys; i++) {
			result.add(0);
		}
	}

	public boolean setResult(int choise) {
		if (!(choise == -1)) {
			int vote = result.get(choise) + 1; // get value
			result.set(choise, vote); // replace value
			votersCounter++;
			this.presentageOfVotes = ((((double) this.votersCounter) / ((double) citizensList.size())) * 100);
			return true;
		}
		return false;
	}

	public int getResult(int i) {
		return result.get(i);
	}

	public ArrayList<Integer> getResult() {
		return result;
	}

	public double getPresentageOfVotes() {
		return presentageOfVotes;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof BallotBox)) {
			return false;
		}
		BallotBox<?> b = (BallotBox<?>) other;
		return (b.id == id && b.getAddress().equals(address));
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < citizensList.size(); i++) {
			buff.append((i + 1) + ") " + citizensList.get(i).toString() + "\n");
		}
		return getBoxType().name() + " Ballot Box " + " Number: " + id + ", City: " + address + " has "
				+ citizensList.size() + " voters " + ", Presentage of votes: " + getPresentageOfVotes() + "%"
				+ "\nVoters List:\n" + buff.toString();
	}

	public String toStringWithoutCitizens() {
		return "BallotBox Number: " + id + ", From " + address + " with " + citizensList.size() + " voters "
				+ ", Presentage of vote is: " + getPresentageOfVotes() + "%";
	}

}
